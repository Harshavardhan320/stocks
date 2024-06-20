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
@Table(name="Alert")
public class Alert {
	
	
	/*
	 * 1
	 * Amount Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/*
	 * 2
	 * alert name
	 */
	@Column(name="alertname", nullable=false)
	private String alertname;
	/*
	 * 3
	 * alert message
	 * 
	 * 
	 */
	@Column(name="message", nullable=false, columnDefinition = "text")
	private String message;
	
	/*
	 * 4
	 * alert data and time 
	 */
	@Column(name="time")
	private Date date = new Date();
	
	/*
	 * 5
	 * ALERT SEEN status
	 */
	@Column(name="seen")
	@Enumerated(EnumType.STRING)
	private Seen seen  = Seen.UNSEEN;
	
	
	
	public enum Seen{
		SEEN, UNSEEN
	}
	
	public Alert(String alertName,String message,Alert.Seen seen) {
		this.alertname = alertName;
		this.message = message;
		this.seen = seen;
	}
}
