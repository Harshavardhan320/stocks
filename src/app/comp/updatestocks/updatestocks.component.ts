import { Component, OnInit } from '@angular/core';
import * as XLSX from 'xlsx'
import { HoldingsService } from '../../service/holdings.service';
import { Stockclass } from '../../classes/stockclass';
import { Updatestockclass } from '../../classes/updatestockclass';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-updatestocks',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './updatestocks.component.html',
  styleUrl: './updatestocks.component.css'
})
export class UpdatestocksComponent implements OnInit{


  public class:any;
  public message:any;
  public sheetvalues:any;
  public submessage:any="Update stocks";
  public subclass:any;

  public monthId:any;

  public stockData = new Map<string, Updatestockclass>();
  public displayStocks: Array<{key:string, value:Updatestockclass}> =[];

  constructor(private holdingsService:HoldingsService){}


  ngOnInit(): void {
    this.class="h3 m-4";
    this.message="UPLOAD STOCKS";

    let presentmonth = new Stockclass();

    this.holdingsService.presentmonth(presentmonth.getLastYearmonth()).subscribe({

      next:(value)=>{
        this.monthId = value[0].yearMonth.id;
        for(let i=0; i<value.length; i++){
          if(value[i].status == "UNSOLD"){
            this.stockData.set(value[i].stockName,new Updatestockclass(value[i].id, value[i].currentPrise, 0));
          }
        }
      }, error:(err)=>{
        this.errors(err);
      }
    });
  }


  upload(event:any){

    let file = event.target.files[0];

    let filereader = new FileReader();
    filereader.readAsArrayBuffer(file);

    filereader.onload = (e)=>{
      var zerodha = XLSX.read(filereader.result, {type:'array'});
      var zerodhaSheetName = zerodha.SheetNames;
      this.sheetvalues = XLSX.utils.sheet_to_json(zerodha.Sheets[zerodhaSheetName[0]]);
    }
  }

  process() {
    if(this.sheetvalues == null){
      this.message="invalid or some stock where added to you holdings.";
      this.class="h3 alert alert-warning";
      return;
    }

    if(this.sheetvalues.length != this.stockData.size){
      this.message="invalid or some stock where added to you holdings..";
      this.class="h3 alert alert-warning";
      return;
    }
    for(let i=0; i<this.sheetvalues.length; i++){
      let stockname = this.sheetvalues[i].Instrument;
      let stockDetails  = this.stockData.get(stockname);

      if(stockDetails != null){
        stockDetails?.setprisentPrice(this.sheetvalues[i].LTP)
      }else{
        this.message="New stock where added to you holdings. Can't move forther process. Add you stocks in your holdings";
        this.class="fs-6 alert alert-warning";
        return;
      }
     
    }
    this.displayStocks = Array.from(this.stockData.entries()).map(([key, value])=>({key, value}));
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


  updatestocks(data: any) {
    let sendDate={
      "id":data.value["id"],
      "stockName":data.value["stockname"],
      "currentPrice":data.value["prisentPrice"],
      "monthYearId":this.monthId
    }
    this.holdingsService.updateCurrPrice(sendDate).subscribe({
      next:(value)=>{
        this.submessage="Stock Price Updated to "+value.currentPrise;
        this.subclass="text-success";
      },error:(err)=>{
        this.errors(err);
      }
    })
  }
}
