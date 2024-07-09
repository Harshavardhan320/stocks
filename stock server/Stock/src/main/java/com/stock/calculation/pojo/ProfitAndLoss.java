package com.stock.calculation.pojo;

import java.math.BigDecimal;

import com.stock.calculation.entitys.Holdings;
import com.stock.calculation.entitys.Holdings.Duration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfitAndLoss {
	
	private BigDecimal percentage;
	private BigDecimal profitamount;
	private Status status;
	private Holdings.Duration duration;
	

	



	public ProfitAndLoss(BigDecimal percentage, BigDecimal profitamount, Status status, Duration duration) {
		super();
		this.percentage = percentage;
		this.profitamount = profitamount;
		this.status = status;
		this.duration = duration;
	}






	public enum Status{
		ABOVETOSELL,
		ABOVETOBUY,
		LOSSTOSELL
	}
	
}
