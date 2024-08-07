package com.stock.calculation.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stock.calculation.entitys.Alert;
import com.stock.calculation.entitys.BankAccount;
import com.stock.calculation.exception.AmountException;
import com.stock.calculation.repository.BalanceRepo;

@Service
public class BalanceService {
	
	/*
	 * 1
	 * Auto injecting balance repository 
	 * 
	 */
	@Autowired
	BalanceRepo balanceRepo;
	/*
	 * 2
	 * Auto injecting alert service
	 * 
	 */
	@Autowired
	AlertService alertService;
	/*
	 * 1
	 * Getting balance
	 */
	public double getBalance(){
		
		try {
			BankAccount balance = balanceRepo.Totalbalance();
			if(balance == null) {
				throw new NullPointerException("0 balacne. Add your balance in bank section.");
			}else {
				return balance.getBalance();
			}
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("No response from the server.");
		}
	}

	
	/*
	 * 2
	 * Service method for adding balance
	 * 
	 */
	public BankAccount addbalance(BankAccount balance1) throws AmountException{
		
		BankAccount balance = balance1;
		if(balance == null) {
			throw new NullPointerException("Can't process amount");
		}else if(balance.getDeposit() <1) {
			throw new AmountException(balance.getDeposit()+" Can't be deposit.");
		}
		
		
		/*
		 * 
		 * checking availableAmount
		 */
		BankAccount currentBalance = this.balanceRepo.Totalbalance();
		BigDecimal depositAmount = new BigDecimal(String.valueOf(balance.getDeposit()));
		
		if(currentBalance != null) {
			
			BigDecimal availableAmount = new BigDecimal(String.valueOf(currentBalance.getBalance()));
			BigDecimal totalBalance = depositAmount.add(availableAmount);
			
			balance.setBalance(totalBalance.doubleValue());
			balance.setDeposit(depositAmount.doubleValue());
			
		}else{
			balance.setBalance(depositAmount.doubleValue());
			balance.setDeposit(depositAmount.doubleValue());
		}
		
		
		
		try {
			
			
			BankAccount bal = this.balanceRepo.save(balance);
			
			if(bal == null) {
				
				throw new NullPointerException("Balance Not Added");
			}else {
				String alertName = bal.getPaymentType().toString();
				String message = balance.getMessage()+" Amount "+balance.getDeposit()+" credited totalbalance "+bal.getBalance();
				Alert.Seen seen = Alert.Seen.UNSEEN;
				
				Alert alert = new Alert(alertName, message, seen);
				
				this.alertService.addAlert(alert);
				
				return bal;
			}
		}catch(DataIntegrityViolationException e) {
			
			
			throw new DataIntegrityViolationException("No response from the server. No balance added");
		}
	}
	
	
	/*
	 * 
	 * 3
	 * Withdraw method
	 */
	public BankAccount withDraw(double amount1, String message, BankAccount.PaymentMethod paymentType) throws AmountException{
		
		BankAccount balance = this.balanceRepo.Totalbalance();
		
		if(balance == null) {
			throw new NullPointerException("0 balance to withdraw");
			
		}else if(amount1 ==0 || amount1 <=0.9) {
			throw new AmountException("Can't withdraw the "+amount1+" amount");
		}
		
		BigDecimal amount = new  BigDecimal(String.valueOf(amount1));
		
		BigDecimal accountbalance = new BigDecimal(String.valueOf(balance.getBalance()));
		
		BigDecimal withdrawtaxes = new BigDecimal(String.valueOf(balance.getWithDrawTaxes()));
		
		if(accountbalance.doubleValue() < amount.doubleValue() || accountbalance.doubleValue() <= withdrawtaxes.doubleValue() || accountbalance.doubleValue() ==0 ) {
			throw new AmountException("insufficient funds, your balance "+accountbalance);
		}
		
		BigDecimal withDrawAmount = amount.subtract(withdrawtaxes);
		
		BigDecimal balanceAmount = accountbalance.subtract(withDrawAmount);
		
		BankAccount withdaraw = new BankAccount();
		
		
		withdaraw.setBalance(balanceAmount.doubleValue());
		withdaraw.setWithDraw(withDrawAmount.doubleValue());
		withdaraw.setMessage(message);
		withdaraw.setPaymentType(paymentType);
		
		
		try {
			
			BankAccount acc= this.balanceRepo.save(withdaraw);
			
			if(acc != null) {
				String alertName = acc.getPaymentType().toString();
				String alertmessage = message+" Rs "+withDrawAmount+"/- debited totalbalance "+acc.getBalance();
				Alert.Seen seen = Alert.Seen.UNSEEN;
				
				Alert alert = new Alert(alertName, alertmessage, seen);
				
				this.alertService.addAlert(alert);
			}
			return acc;
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Can't process for with draw");
		}	
	}

	/*
	 * 
	 * Service method for account passbook
	 * 
	 */
	public List<BankAccount> passBook() {
		try {
			List<BankAccount> passbook =  this.balanceRepo.findAll();
			
			passbook.sort(Comparator.comparingLong(BankAccount::getId).reversed());
				
			return passbook;
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("No response from the server.");
		}
	}
	
}


















