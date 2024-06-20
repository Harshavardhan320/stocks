package com.stock.calculation.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfitDetails {
	
	private double profit;
	private double investementAmount; 
	
	public ProfitDetails(double profit, double investementAmount) {
		// TODO Auto-generated constructor stub
		
		this.profit=profit;
		this.investementAmount =investementAmount;
	}
	public ProfitDetails() {}

}
