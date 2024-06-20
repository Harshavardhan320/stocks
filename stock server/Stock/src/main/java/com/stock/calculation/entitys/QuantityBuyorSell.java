package com.stock.calculation.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class QuantityBuyorSell {
	
	private long stockId;
	private String stockName;
	private int quantity;
	private double taxes;
	private double buyOrSellPrise;
	private double brokerage;

}
