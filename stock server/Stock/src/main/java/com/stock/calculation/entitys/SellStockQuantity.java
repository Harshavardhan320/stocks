package com.stock.calculation.entitys;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class SellStockQuantity implements Serializable  {

	/*
	 * 1
	 * Quantity
	 */
	@Column(name="quantity")
	private int Quantity;
	/*
	 * 2
	 * Date of purchase
	 */
	@Column(name="date")
	private Date date = new Date();
	/*
	 * 3
	 * taxes assign
	 */
	@Column(name="taxes")
	private double taxes;
	/*
	 * 4
	 * Price of buying
	 */
	@Column(name="sellingPrise")
	private double sellingPrise;
	/*
	 * 5
	 * in selling stocks released amount
	 */
	@Column(name="relesedamount")
	private double relesedamount;
	
	/*
	 * 6
	 * Stocks selling taxes
	 */
	@Column(name="Brokerage", nullable=false)
	private double brokerage;
}
