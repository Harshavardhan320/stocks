package com.stock.calculation.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.stock.calculation.entitys.Alert;
import com.stock.calculation.entitys.Alert.Seen;
import com.stock.calculation.repository.AlertRepo;

@Service
public class AlertService {
	/*
	 * 1
	 * auto injecting repository
	 */
	@Autowired
	AlertRepo alertRepo;
	
	
	/*
	 * 1
	 * service method for adding alert
	 */
	public Alert addAlert(Alert alert) {
		try {
			return this.alertRepo.save(alert);
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("Server not responding for alert");
		}
	}
	
	/*
	 * 2
	 * service method for getting all alerts
	 */
	public List<Alert> getAllAlerts(){
		try {
			List<Alert> list= this.alertRepo.findAll();
			
			list.sort(Comparator.comparing(Alert::getId).reversed());
			
			return list;
			
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("Server not responding for alert");
		}
	}
	/*
	 * 3
	 * service method for getting all alerts using name
	 */
	
	public List<Alert> getName(String alertName){
		try {
			
			List<Alert> data =  this.alertRepo.findByAlertname(alertName);
			
			if(data.isEmpty()) {
				
				throw new NullPointerException("Alert Found with name "+alertName);
			}else {
				
				return data;
			}
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("Server not responding for alert");
		}
	}
	/*
	 * 3
	 * updating the status
	 */
	public Alert updateSeen(long id) {
		try {
			
			Seen seen  = Alert.Seen.SEEN;
			
			Optional<Alert> alertmessage = this.alertRepo.findById(id);
			
			if(alertmessage.isPresent()) {
				
				Alert alert = alertmessage.get();
				alert.setSeen(seen);
				
				return this.alertRepo.save(alert);
			}else {
				throw new NullPointerException("Notification not found");
			}
			
			
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Server not responding.");
		}
	}
	
	public int updateStatus() {
		
		try {
			this.alertRepo.updatestatus();
			return 1;
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Can't process data, server error");
		}
	}
}














