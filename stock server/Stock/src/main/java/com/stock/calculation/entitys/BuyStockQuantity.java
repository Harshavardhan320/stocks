package com.stock.calculation.entitys;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@SuppressWarnings("serial")
@Setter
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class BuyStockQuantity implements Serializable{

	/*
	 * 1
	 * Quantity
	 * 
	 */
	@Column(name="quantity", nullable=false)
	private int quantity;
	/*
	 * 2
	 * Date of purchase
	 */
	@Column(name="date", nullable=false)
	private Date date = new Date();
	/*
	 * 3
	 * taxes assign
	 */
	@Column(name="taxes", nullable=false)
	private double taxes;
	/*
	 * 4
	 * Price of buying
	 */
	@Column(name="buyingprise", nullable=false)
	private double buyingPrise;
	/*
	 * 5
	 * Investment amount
	 */
	@Column(name="investedamount", nullable=false)
	private double investedAmount;
	/*
	 * 6
	 * Stocks selling taxes
	 */
	@Column(name="Brokerage", nullable=false)
	private double brokerage;
}
