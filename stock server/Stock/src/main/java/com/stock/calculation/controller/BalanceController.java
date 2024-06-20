package com.stock.calculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.calculation.entitys.BankAccount;
import com.stock.calculation.service.BalanceService;

@RestController
@RequestMapping(value="/api/bankaccount")
@CrossOrigin
public class BalanceController {
	
	/*
	 *Auto injecting balance service object 
	 */
	@Autowired
	BalanceService balanceService;
	
	/*
	 * Controller class getBalance URL method
	 * URL=/getBalance
	 */
	
	@GetMapping(value="/getbalance")
	public ResponseEntity<?> getBalance(){
		
		try {
		 double balance = this.balanceService.getBalance();
		 
		 return new ResponseEntity<>(balance, HttpStatus.OK);
		
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/*
	 * 2
	 * Adding balance
	 */
	@PostMapping(value="/addbalance")
	public ResponseEntity<?> addBalance(@RequestBody BankAccount balance){
		try {
			 BankAccount bal = this.balanceService.addbalance(balance);
			 
			 return new ResponseEntity<>(bal, HttpStatus.OK);
			
			}catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
	}
	
	
	/*
	 * 3
	 * WithDraw method
	 */
	@GetMapping(value="/withdraw/{amount}/{message}/{paymentType}")
	public ResponseEntity<?> withDraw(@PathVariable double amount, @PathVariable String message, @PathVariable BankAccount.PaymentMethod paymentType){
		try {
			 BankAccount bal = this.balanceService.withDraw(amount, message, paymentType);
			 
			 return new ResponseEntity<>(bal, HttpStatus.OK);
			
			}catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
	}
	/*
	 * 4
	 * controller passBook
	 */
	@GetMapping(value="/passbook")
	public ResponseEntity<?> getPassBook(){
		try {
			List<BankAccount> passBook = this.balanceService.passBook();
			 
			 return new ResponseEntity<>(passBook, HttpStatus.OK);
			
			}catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
	}
}















