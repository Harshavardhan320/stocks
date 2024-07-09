package com.stock.calculation.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stock.calculation.entitys.Holdings;
import com.stock.calculation.pojo.ProfitAndLoss;
import com.stock.calculation.repository.HoldingesRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ProfitAndLossAnalysis {

	@Autowired
	HoldingesRepository holdingsRepo;
	
	private Map<String, ProfitAndLoss> analysisdata = new TreeMap<>();
	
	public void monthlyAnalysis(List<Holdings> holding) {
		
		List<Holdings> holdings = holding;
		
		BigDecimal shorttermPercentage= null;
		BigDecimal longtermPercentage = null;
				
		for(Holdings i : holdings) {
			if(i.getStatus().toString() == "UNSOLD") {

				BigDecimal quantity = new BigDecimal(i.getTotalStocks());
				BigDecimal currentValue = new BigDecimal(i.getCurrentPrise());
				BigDecimal investedAmount = new BigDecimal(i.getInvestedAmount());
				BigDecimal percentageValue = new BigDecimal(100);
				
				
				
				BigDecimal currentAmount = quantity.multiply(currentValue);
				
				
				BigDecimal percentage = ((quantity.multiply(currentValue).divide(investedAmount, 100, RoundingMode.HALF_UP)).multiply(percentageValue)).subtract(percentageValue);
				
				
				if(quantity.intValue() <=50) {
					
					shorttermPercentage = new BigDecimal(34.8);
					longtermPercentage = new BigDecimal(65.8);
					
					if(i.getDuration().toString() == "SHORTTURM") {
					
						if(percentage.doubleValue() > shorttermPercentage.doubleValue()) {
							
							this.analysisdata.put(i.getStockName(), new ProfitAndLoss(percentage ,currentAmount, ProfitAndLoss.Status.ABOVETOSELL, i.getDuration() ));
						}
						
					}else {
						
						if(percentage.doubleValue() > longtermPercentage.doubleValue()) {
							
							this.analysisdata.put(i.getStockName(), new ProfitAndLoss(percentage ,currentAmount, ProfitAndLoss.Status.ABOVETOSELL, i.getDuration()));
						}
					}
					
				}else {
					
					shorttermPercentage = new BigDecimal(55.8);
					longtermPercentage = new BigDecimal(78.8);
					
					if(i.getDuration().toString() == "SHORTTURM") {
					
						if(percentage.doubleValue() > shorttermPercentage.doubleValue()) {
							
							this.analysisdata.put(i.getStockName(), new ProfitAndLoss(percentage ,currentAmount, ProfitAndLoss.Status.ABOVETOSELL, i.getDuration()));
						}
						
						
					}else {
						
						if(percentage.doubleValue() > longtermPercentage.doubleValue()) {
							
							this.analysisdata.put(i.getStockName(), new ProfitAndLoss(percentage ,currentAmount, ProfitAndLoss.Status.ABOVETOSELL, i.getDuration()));
						}					
					}	
				}
			}
		}
				
	}
}
