import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { DateserviceService } from '../../service/dateservice.service';
import { CommonModule } from '@angular/common';
import { HoldingsService } from '../../service/holdings.service';
import { Stockclass } from '../../classes/stockclass';

@Component({
  selector: 'app-monthdata',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './monthdata.component.html',
  styleUrl: './monthdata.component.css'
})
export class MonthdataComponent implements OnInit{


  public yearmonthname:any[]=[];
  public profitDetails:any;
  public message:any;
  public class:any;
  public holdings:any;
  public openbuyorsall:boolean= false;
  
  constructor(private rout:ActivatedRoute, private yearMonthService:DateserviceService,
    private holdingsService:HoldingsService, private router:Router
  ){}

  ngOnInit(): void {
    this.rout.params.subscribe((yearmonthname)=>{
      let monthname = yearmonthname["monthName"];

      if(monthname == null){
        this.message = "Please select a month";
        this.class = "alert alert-danger";
      }
      
      this.yearMonthService.getYearMonth(monthname).subscribe({
        next:(value)=>{
          this.yearmonthname = value;
          this.message = this.yearmonthname[0].yearMonthName +" Stocks Data.";
        },
        error:(err)=>{
          let classs = "text-center";
          this.errors(err, classs);
        }
      });

      this.holdingsService.getProfitDetails(monthname).subscribe({

        next:(value)=>{
          this.profitDetails = value;
        },error:(err)=>{
          let classs ="";
          this.errors(err, classs);
        }
      })
      this.holdingsService.presentmonth(monthname).subscribe({
        next:(value)=>{
          this.holdings = value;

          let stockclass = new Stockclass();
          let month:string = stockclass.getYearmonth();


          this.yearMonthService.getYearMonth(month).subscribe({
            next:(value)=>{

              if(value[0].id == this.holdings[0].yearMonth.id){
                this.openbuyorsall = true;
              }
            },error:(err)=>{
              this.errors(err, "");
            }
          });
        },error:(err)=>{
          let classs ="";
          this.errors(err, classs);
        }
      })
    });
  }

  ChangeStatus(status: string) {
    
    this.yearMonthService.changeStatus(status).subscribe({
      next: (value) => {
        this.openbuyorsall = false;
        this.ngOnInit();
      }
    })
  }

  errors(err:any, classs:any){
    if(err.statusText != "OK" ){
      this.message="Waiting for Server Response";
      this.class = "alert alert-danger";
    }else{
      console.log(err);
      this.message = err.error;
      this.class= "alert alert-danger "+classs;
    }
  }
}

