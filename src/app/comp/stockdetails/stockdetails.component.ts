import { Component, OnInit } from '@angular/core';
import { HoldingsService } from '../../service/holdings.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Stockclass } from '../../classes/stockclass';
import { DateserviceService } from '../../service/dateservice.service';

@Component({
  selector: 'app-stockdetails',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './stockdetails.component.html',
  styleUrl: './stockdetails.component.css'
})
export class StockdetailsComponent implements OnInit {

  public message:any;
  public class:any;

  public holdings:any;
  public holdingsBuyQuantity:any[]=[];
  public holdingsSellingQuantity:any[]=[];
  public openbuyorsall:boolean= false;

  public annalysis:any=true;

  constructor(private holdingservice:HoldingsService, private rout:ActivatedRoute
    ,private yearmonth:DateserviceService
  ){}


  ngOnInit(): void {


    this.rout.params.subscribe((paramater)=>{
      let id = paramater["id"];
      
      this.holdingservice.findById(id).subscribe({

        next:(value:any)=>{

          this.holdings = value;
          this.holdingsBuyQuantity = value.buyingquantity;
          this.holdingsSellingQuantity = value.sellingquantity;
          this.message = value.stockName+" Details";
          this.class ="h4";
          let stockclass = new Stockclass();

          let month:string = stockclass.getYearmonth();


          this.yearmonth.getYearMonth(month).subscribe({
            next:(value)=>{

              if(value[0].id == this.holdings.yearMonth.id){
                this.openbuyorsall = true;
              }
            },error:(err)=>{
              this.errors(err);
            }
          });
        },
        error:(err:any)=>{
          this.errors(err);
        }

      });
    })
  }

  errors(err:any){
    if(err.statusText != "OK" ){
      this.message="Waiting for Server Response";
      this.class = "alert alert-danger";
    }else{
      console.log(err);
      this.message = err.error;
      this.class= "alert alert-danger ";
    }
  }
}
