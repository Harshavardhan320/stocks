package com.stock.calculation.entitys;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="holdings")
public class Holdings {
	/*
	 * 1
	 * Amount Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/*
	 * 2
	 * Stock name
	 */
	@Column(name="stockName", nullable=false)
	private String stockName;
	/*
	 * 3
	 * Total Stocks Quantity
	 */
	@Column(name="totalStocks", nullable=false)
	private int totalStocks;
	/*
	 * 4
	 * Stock Quantity
	 */
	@ElementCollection
	private  List<BuyStockQuantity> buyingquantity = new ArrayList<>();
	
	/*
	 * 5
	 * Stock buying price
	 */
	@Column(name="stockBuyingPrise", nullable=false)
	private double stockBuyingkPrise;
	/*
	 * 6
	 * Stock invested amount
	 */
	@Column(name="InvestedAmount")
	private double investedAmount;
	/*
	 * 7
	 * Current price
	 */
	@Column(name="currentPrise", nullable=false)
	private double currentPrise;
	/*
	 * 8
	 * selling stock
	 */
	@ElementCollection
	
	private  List<SellStockQuantity> sellingquantity = new ArrayList<>();
	
	/*
	 * 9
	 * yearMonth
	 */
	@JoinColumn(name="yearmonth_id", nullable=false)
	@ManyToOne
	private YearAndMonth yearMonth;
	/*
	 * 10
	 * status
	 * SOLD or UNSOLD
	 */
	@Column(name="status", nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.UNSOLD;
	
	public enum Status{
		SOLD,UNSOLD
	}
	/*
	 * 11
	 * Total Selling stock
	 */
	@Column(name="TotalSoldstocks", nullable=false)
	private int totalSoldStocks;
	/*
	 * 12
	 * Total released amount
	 */
	@Column(name="TotalReleasedamount", nullable=false)
	private double totalReleasedAmount;
	
	/*
	 * 13
	 * Validity for a stock
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="duration", columnDefinition = "enum('LONGTERM','SHORTTERM') default 'LONGTERM'")
	private Duration duration = Duration.LONGTERM;
	public enum Duration{
		SHORTTERM,
		LONGTERM
	}
}















































































