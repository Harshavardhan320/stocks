package com.stock.calculation.controller;

import java.util.List;
import java.util.Optional;

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

import com.stock.calculation.entitys.Holdings;
import com.stock.calculation.entitys.QuantityBuyorSell;
import com.stock.calculation.pojo.MonthProfitPojo;
import com.stock.calculation.pojo.ProfitDetails;
import com.stock.calculation.pojo.UpDateCrrPrice;
import com.stock.calculation.service.HoldingsService;

@RestController
@RequestMapping(value="/api/Holdings")
@CrossOrigin
public class HoldingesController {

	
	/*
	 * 1
	 * Auto injecting HoldingesService object
	 */
	@Autowired
	HoldingsService holdingsService;
	
	/*
	 * 1
	 * method for getting all stocks date
	 */
	@GetMapping(value="/allstocksdetails")
	public ResponseEntity<?> getAllStocksDatails(){
		try {
			List<Holdings> hold = this.holdingsService.getAllStocks();
			
			return new ResponseEntity<>(hold, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/*
	 * 2
	 * controller method for adding stock
	 * 
	 */
	@PostMapping(value="/addstock")
	public ResponseEntity<?> BuyStock(@RequestBody Holdings holdings){
		try {
			Holdings hold = this.holdingsService.buyStock(holdings);
			
			return new ResponseEntity<>(hold, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	/*
	 * 3
	 * Updating the status and selling stock
	 * 
	 */
	@PostMapping(value="/sellstock")
	public ResponseEntity<?> sellstock(@RequestBody QuantityBuyorSell quantityBuyorSell){
		try {
			Holdings hold = this.holdingsService.SellStocks(quantityBuyorSell);
			
			return new ResponseEntity<>(hold, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/*
	 * 4
	 * Adding Extra Quantity
	 * 
	 */
	@PostMapping(value="/addquantity")
	public ResponseEntity<?> addExtraQuantity(@RequestBody QuantityBuyorSell quantityBuyorSell){
		try {
			Holdings hold = this.holdingsService.extraQuantity(quantityBuyorSell);
			
			return new ResponseEntity<>(hold, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	/*
	 * 5
	 * find by id
	 */
	@GetMapping(value="/stockid/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		try {
			Optional<Holdings> hold = this.holdingsService.getStockById(id);
			
			return new ResponseEntity<>(hold, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/*
	 * 4
	 * controller method for find profit per month
	 *  
	 */
	@GetMapping(value="/monthprofit")
	public ResponseEntity<?> monthprofi(){
		try {
			List<MonthProfitPojo> profit = this.holdingsService.monthlyProfit();
			
			return new ResponseEntity<>(profit, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	/*
	 * 
	 * select by month
	 */
	@GetMapping(value="/selectbymonth/{month}")
	public ResponseEntity<?> selectbymonth(@PathVariable String month){
		try {
			List<Holdings> holdings = this.holdingsService.selectbymonth(month);
			
			return new ResponseEntity<>(holdings, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/*
	 * Controller method for updating current value
	 */
	@PostMapping(value="/update/holdings")
	public ResponseEntity<?> updateHoldings(@RequestBody UpDateCrrPrice upDateCrrPrice){
		try {
			
			Holdings holdings = this.holdingsService.updateCrrPrice(upDateCrrPrice);

			return new ResponseEntity<>(holdings, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	/*
	 * Controller method for getting profits
	 * 
	 */
	@GetMapping(value="/monthly/profits/{monthname}")
	public ResponseEntity<?> getYearMonth(@PathVariable String monthname){
		
	try {
			
		ProfitDetails profitdetails = this.holdingsService.getProfitDetails(monthname);

			return new ResponseEntity<>(profitdetails, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}
	
	/*
	 * 
	 * controller method for changing duration
	 */
	@PostMapping(value="/update/holdings/{duration}")
	public ResponseEntity<?> updateHoldings(@RequestBody UpDateCrrPrice upDateCrrPrice,@PathVariable String duration){
		try {	
			
			int holdings = this.holdingsService.editStockPriceduration(upDateCrrPrice, duration);

			return new ResponseEntity<>(holdings, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}










