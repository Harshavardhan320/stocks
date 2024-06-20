import { Component, OnInit } from '@angular/core';
import { HoldingsService } from '../../service/holdings.service';
import { Stockclass } from '../../classes/stockclass';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-editstocks',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editstocks.component.html',
  styleUrl: './editstocks.component.css'
})
export class EditstocksComponent implements OnInit {



  public class:any;
  public message:any;
  public submessage:any
  public subclass:any;

  public holdingsDate:any[]=[];


  public stockClass = new Stockclass();
  constructor(private holdingsService:HoldingsService){}


  ngOnInit(): void {
    
    
   
    let monthname = this.stockClass.getYearmonth();

    this.message = "You can update this "+ monthname+" stock data. ";
    this.class="fs-6 mt-1";
    this.submessage = monthname+" Holdings";
    this.subclass = "fs-4 p-2"
    this.holdingsService.presentmonth(monthname).subscribe({
      next:(data)=>{
        this.holdingsDate = data;
      }, error:(err)=>{
        this.errors(err);
      }
    })

  }

  updatestocks(formdata:any) {
    
    let id:any = formdata.value["id"];
    let stockname:any = formdata.value["stockName"];
    let currentvalue:any = formdata.value["currentPrice"];
    let monthid:any = formdata.value["monthYearId"]
    let duration:any = formdata.value["duration"];

   
    let data ={
      "id":id,
      "stockName":stockname,
      "currentPrice": currentvalue,
      "monthYearId":monthid
    }
    this.holdingsService.updateCurrPriceDuration(data, duration).subscribe({
      next:(data)=>{
        this.message = data+" Stocks data updated successfully";
        this.class="fs-5 mt-3 alert alert-success";
      }, error:(err)=>{
        this.errors(err);
      }
    })
   
    setTimeout(() => {
      this.ngOnInit();
    }, 6000);
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
