import { Component, OnDestroy, OnInit } from '@angular/core';
import { Data, Router, RouterLink } from '@angular/router';
import { DateserviceService } from '../../service/dateservice.service';
import { CommonModule } from '@angular/common';
import { HoldingsService } from '../../service/holdings.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {


  months:string[] = ["January", "February", "March", "April", "May", "June", "July", "August", "September", 
    "October", "November", "December"]
  ;


  public message: string="Holdings";
  public class!: string;
  public yearmonthdata: any[]=[];
  public holdings:any[]=[];
  error:number=0;
  updatestatus:String="INCOMPLET";
  constructor(private holdingsService:HoldingsService){
    
  
    this.holdingsService.profit().subscribe({
       
      next:(value)=>{
        this.yearmonthdata=value;
      }, error:(err)=>{
        this.error +=1;
        this.errors(err);
      }
    });
    const date = new Date();

    let month:string = date.getFullYear().toString()+" "+this.months[date.getMonth()];
    
    this.holdingsService.presentmonth(month).subscribe({

      next:(value)=>{
        this.holdings=value;
        this.updatestatus="UPDATED"

      },error:(err)=>{
        this.error +=1;
        this.errors(err);
      }
    });
  
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
