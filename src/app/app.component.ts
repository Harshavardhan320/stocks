import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { DateserviceService } from './service/dateservice.service';
import { CommonModule } from '@angular/common';
import { Stockclass } from './classes/stockclass';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'stockwebsit';

  public day!:string;
  public month!:string;
  public year!:string;

  error:number =0;
  message:string | undefined;
  class:String="";
  data:any="null";
  pendingDataCount=0;
  notificationCount =0;
  yearandmonth="";

  public dateAndTime!:string;
  months:string[] = ["January", "February", "March", "April", "May", "June", "July", "August", "September", 
    "October", "November", "December"];

  constructor(private service:DateserviceService){
    let presentmonth = new Stockclass();

    this.service.getYearMonth(presentmonth.getYearmonth()).subscribe({
      next:(value:any)=> {
       this.yearandmonth = value[0].yearMonthName;
      }
    });

    setInterval(()=>{
      const date = new Date();
      this.clock(date);
    }, 1000);
    setTimeout(() => {
     
    }, 100000);

    this.findallmonths();
    /*
      Calling function notifications
    */
   this.notification();
  }

  ngOnInit(){
   this.updatemonth();
  }
  /*

  updating this month in data base

  */
  updatemonth(){
    let presentmonth = this.year+" "+this.month;
    this.service.getYearMonth(presentmonth).subscribe({
      next:(value:any)=> {
        if(value.length==0){
          let yearmonth={
            yearMonthName: presentmonth,
            status:"PENDING"
          }
          this.service.addingMonth(yearmonth).subscribe({
            next:(value)=>{
              this.message ="This month lable updated you can add Your stocks."
              this.class="alert alert-success";
              setInterval(()=>{
                window.location.reload();
              },30000)
            }, error:(err:any)=>{
              this.error +=0;
              this.errors(err);
            }
          })
        }
      },error:(err)=>{
        this.errors(err);
      }
    })
    
  }

  findallmonths(){

    const date = new Date();
    this.month = this.months[date.getMonth()];
    this.year = date.getFullYear().toString();


    let returnmonth =null;

    this.service.getYearmonth().subscribe({
      next:(value: any)=>{
        returnmonth =value;
        this.data=value;
        this.error +=0;
        let pendingDataCount =0;

        for (let i = 0; i < value.length; i++) {
          if(value[i].status == "PENDING")
            pendingDataCount +=1;
        }
        this.pendingDataCount = pendingDataCount;
      },
      error:(err:any)=>{
        this.error +=1;
        this.errors(err);
      }
    });
  }

  clock(date:Date){
    let day = date.getDate();
    let month = date.getMonth();
    let year = date.getFullYear();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec= date.getSeconds();

    this.dateAndTime = day+"-"+this.months[month]+"-"+year+" "+hour+":"+min;

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
  /*
  notification
  */
  notification():any{
    this.service.getAllalerts().subscribe({
      next:(value)=>{
        for(let i=0; i<value.length; i++){
          if(value[i].seen == "UNSEEN")
            this.notificationCount +=1;
        }
      },error:(err)=>{
        this.error +=1;
        this.errors(err);
      }
    })
  }
}
