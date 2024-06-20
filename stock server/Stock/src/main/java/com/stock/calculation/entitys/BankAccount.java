package com.stock.calculation.entitys;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="Bankaccount")

public class BankAccount {

	/*
	 * 1
	 * Amount Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	/*
	 * 2
	 * deposit
	 */
	@Column(name="deposit", columnDefinition = "double default 0.0")
	private double deposit=0.0;
	
	/*
	 * 3
	 * balance
	 */
	@Column(name="balance", columnDefinition = "double default 0.0")
	private double balance=0.0;
	
	/*
	 * 6
	 * Withdraw
	 */
	@Column(name="withdraw", columnDefinition = "double default 0.0")
	public double withDraw =0.0;
	
	/*
	 * 6
	 * Withdraw taxes
	 */
	@Column(name="withdraw_taxes", columnDefinition = "double default 0.0")
	public double withDrawTaxes =0.0;
	
	/*
	 * 5
	 * Amount from
	 */
	@Column(name="message", nullable=false)
	private String message;
	
	
	/*
	 * 6
	 * data for action
	 */
	@Column(name="date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	public Date date= new Date();
	
	/*
	 * 7
	 * payment type
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="PaymentType",  nullable=false)
	
	private PaymentMethod paymentType;
	public enum PaymentMethod{
		DEPOSIT,
		WITHDRAW,
		STOCKBUY,
		STOCKSELL,
	}
	
	
}
