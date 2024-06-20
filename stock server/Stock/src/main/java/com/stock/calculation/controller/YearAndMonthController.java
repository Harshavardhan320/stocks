package com.stock.calculation.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.calculation.entitys.YearAndMonth;
import com.stock.calculation.service.YearAndMonthService;

@RestController
@RequestMapping(value="/api/calandar")
@CrossOrigin
public class YearAndMonthController {

	/*
	 * 1
	 * injecting YearAndMonthService
	 */
	@Autowired
	YearAndMonthService yearAndMonthService;
	
	/*
	 * 1
	 * Controller for saving year month 
	 */
	@PostMapping(value="/addyearmonth")
	public ResponseEntity<?> addYearMonth(@RequestBody YearAndMonth yearAndMonth){
		
		
		try {
			YearAndMonth yearandMonth = this.yearAndMonthService.updateMonth(yearAndMonth);
			
			return new ResponseEntity<>(yearandMonth, HttpStatus.CREATED);
			
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	/*
	 * 2
	 * controller for getting month and year
	 */
	@GetMapping(value="/getYearmonth/{yearmonth}")
	public ResponseEntity<?> getYearMonth(@PathVariable String yearmonth){
		try {
			List<YearAndMonth> yearandMonth = this.yearAndMonthService.findyearmonth(yearmonth);
			
			return new ResponseEntity<>(yearandMonth, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/getYearmonth")
	public ResponseEntity<?> getYearMonth(){
		try {
			List<YearAndMonth> yearandMonth = this.yearAndMonthService.getYearMonth();
			
			yearandMonth.sort(Comparator.comparingLong(YearAndMonth::getId).reversed());
			
			return new ResponseEntity<>(yearandMonth, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/change/status/{status}")
	public ResponseEntity<?> changeStatus(@PathVariable YearAndMonth.Status status){
		try {
			YearAndMonth yearandMonth = this.yearAndMonthService.changeStatus(status);
			
		
			return new ResponseEntity<>(yearandMonth, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	
}
