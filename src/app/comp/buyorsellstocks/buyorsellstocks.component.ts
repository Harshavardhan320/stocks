import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HoldingsService } from '../../service/holdings.service';

@Component({
  selector: 'app-buyorsellstocks',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './buyorsellstocks.component.html',
  styleUrl: './buyorsellstocks.component.css'
})
export class BuyorsellstocksComponent implements OnInit {

  public message: any;
  public class: any;

  buyorsell: any;
  stockName: any;
  stockid:any;
  URLstatus:boolean=false;

  holdings:any;

  constructor(private rout: ActivatedRoute, private nav:Router, private holdingservice:HoldingsService) { }



  ngOnInit(): void {

    this.rout.params.subscribe((paramater) => {
      this.stockid = paramater["id"];
      this.stockName = paramater["stockname"];
      this.buyorsell = paramater["buyorsell"];

      this.message = this.buyorsell+"ING STOCKS OF "+this.stockName;
      this.class="h3";
      
      this.holdingservice.findById(this.stockid).subscribe({
        next:(value)=>{
          this.holdings = value;
        }, error:(err)=>{
          this.errors(err);
        }
      })
    });
  }

  formbuystocks(buyingdata: any) {
   
    if(buyingdata.value["stockname"] != this.stockName){
      this.message = "User error try again after some time";
      this.class="h3 text-center alert alert-warning";
      return;
    }
    let addQuantity ={
      stockId:this.stockid,
      stockName:buyingdata.value["stockname"] ,
      quantity:buyingdata.value["quantity"] ,
      taxes:buyingdata.value["buyingtaxes"] ,
      "buyOrSellPrise":buyingdata.value["stockprice"]
    }
    this.holdingservice.buyQuantity(addQuantity).subscribe({
      next:(value)=>{
        this.message = "Stocks bought successfully";
        this.class="h3 text-center alert alert-success";
      },
      error:(err)=>{
        this.errors(err);
      }
    });
    setTimeout(() => {
      this.ngOnInit();
      buyingdata.reset();
    }, 4000);
  }

  formsellstocks(sellingdata: any) {
    if(sellingdata.value["stockname"] != this.stockName){
      this.message = "User error try again after some time";
      this.class="h3 text-center alert alert-warning";
      return;
    }

    let sellQuantity ={
      "stockId":this.stockid,
      "stockName":this.stockName,
      "quantity":sellingdata.value["quantity"],
      "taxes":sellingdata.value["sellingtaxes"],
      "buyOrSellPrise":sellingdata.value["sellingprice"],
      "brokerage":sellingdata.value["brockerage"]
    }
    this.holdingservice.sellQuantity(sellQuantity).subscribe({
      next:(value)=>{
        this.message = "Stocks bought successfully";
        this.class="h3 text-center alert alert-success";
      },
      error:(err)=>{
        this.errors(err);
      }
    });
    setTimeout(() => {
      this.ngOnInit();
      sellingdata.reset();
    }, 4000);
  }


  errors(err:any){
    if(err.statusText != "OK" ){
      this.message="Waiting for Server Response";
      this.class = "alert alert-danger";
    }else{
      console.log(err);
      this.message = err.error;
      this.class= "alert alert-danger"
    }
  }
}
