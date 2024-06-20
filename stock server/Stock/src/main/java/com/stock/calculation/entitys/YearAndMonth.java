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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name="yearandmonth")
public class YearAndMonth {

	/*
	 * 1
	 * Amount Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/*
	 * 2
	 * year and month name
	 */
	@Column(name="yearmonthName", nullable=false)
	private String yearMonthName;
	
	/*
	 * 3
	 * month data update status
	 */
	@Column(name="dataStatus", nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	public enum Status{
		UPDATED
		,PENDING
	}
	
	/*
	 * 4
	 * date of update
	 * 
	 */
	@Column(name="date")
	private Date date = new Date();
	
}









