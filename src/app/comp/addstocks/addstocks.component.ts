import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DateserviceService } from '../../service/dateservice.service';
import { HoldingsService } from '../../service/holdings.service';
import { Stockclass } from '../../classes/stockclass';

@Component({
  selector: 'app-addstocks',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './addstocks.component.html',
  styleUrl: './addstocks.component.css'
})
export class AddstocksComponent  implements OnInit{

  public message:any;
  public class:any;

  public dateId:any;

  constructor(private DateService:DateserviceService, private holdings:HoldingsService){

   
  }
  ngOnInit(): void {
    this.message="Add Stock";
    this.class="h3";

    let stockclass = new Stockclass();

    let monthyearname= stockclass.getYearmonth();

    this.DateService.getYearMonth(monthyearname).subscribe({

      next:(data) => {

        this.dateId = data[0].id;

        },
        error:(error) => {

          this.errors(error);

        }
      });
  }


  addstock(data:any){
    
    let quantity =  data.value["quantity"];
    let price = data.value["stockprise"];
    let taxes =  data.value["buyingtaxes"];

    if(quantity ==0 || price<= 0 || taxes < 0){
      alert("Please enter valid values");
      return;
    }else{
      let stockdata  ={
        stockName:data.value["stockname"],
        stockBuyingkPrise:price,
        currentPrise:price,
        yearMonth:{
          id:this.dateId
        },
        status:"UNSOLD",
        buyingquantity:[{
          quantity:quantity,
          taxes:taxes,
          buyingPrise:price
        }]
      }

      this.holdings.addStock(stockdata).subscribe({
        next:(data) => {
          
          this.message= stockdata.buyingquantity[0].quantity+" Quanitity Of "+stockdata.stockName+" added to you holdings..";
          this.class="alert alert-success";
        
        },
        error:(err)=>{
          this.errors(err);
        }
      })
    }
    setTimeout(()=>{
      this.ngOnInit();
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
