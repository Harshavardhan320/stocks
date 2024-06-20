import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HoldingsService } from '../../service/holdings.service';
import { Stockclass } from '../../classes/stockclass';
import { DateserviceService } from '../../service/dateservice.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-holdings',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './holdings.component.html',
  styleUrl: './holdings.component.css'
})
export class HoldingsComponent {
  public sold:any[] =[];
  public unsold:any[] =[];
  public message:any = "All Holdings";
  public class:any = "text-primary p-1 h4";
  public monthId:any;
  public profit:number=0;
  public investmentAmount=0;

  public stockclass = new Stockclass();


  constructor(private HoldingsService:HoldingsService, private dateserviceService:DateserviceService){

    let yearmonth:string = this.stockclass.getYearmonth();
  
    this.dateserviceService.getYearMonth(yearmonth).subscribe({
      next:(value)=>{
        this.monthId=value[0].id;    
      },error:(err)=>{
        console.log(err.error);
      }
    });
    
    this.HoldingsService.getAllstocks().subscribe({
      next:(value)=>{
        for(let i=0; i<value.length; i++){
          if(value[i].status=='UNSOLD' && value[i].yearMonth.id==this.monthId){ //&& value[i].yearMonth.id==monthId 
            this.unsold.push(value[i]);

            this.investmentAmount += value[i].investedAmount;
            this.profit += ((value[i].currentPrise - value[i].stockBuyingkPrise)*(value[i].totalStocks));

          }else if(value[i].status=='SOLD'){
            this.sold.push(value[i]);
          }
        }
      },error:(err)=>{
        this.errors(err);
      }
    })
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
