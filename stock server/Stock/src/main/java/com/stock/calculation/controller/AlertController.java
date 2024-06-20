package com.stock.calculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.calculation.entitys.Alert;
import com.stock.calculation.service.AlertService;

@RestController
@RequestMapping(value="/api/alert")
@CrossOrigin
public class AlertController {
	
	
	@Autowired
	AlertService alertService;
	
	@GetMapping(value="/getallalerts")
	public ResponseEntity<?> getAllAlerts(){
		try {
			List<Alert> alerts = this.alertService.getAllAlerts();
			
			return new ResponseEntity<>(alerts, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(value="/getallalerts/{alertname}")
	public ResponseEntity<?> getAlertByName(String alertName){
		try {
			List<Alert> alerts = this.alertService.getName(alertName);
			
			return new ResponseEntity<>(alerts, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@GetMapping(value="/seen/{id}")
	public ResponseEntity<?> seen(@PathVariable long id){
		try {
			Alert alerts = this.alertService.updateSeen(id);
			
			return new ResponseEntity<>(alerts, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
