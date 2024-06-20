import { Component } from '@angular/core';
import { BankserviceService } from '../../service/bankservice.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-bankpassbook',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './bankpassbook.component.html',
  styleUrl: './bankpassbook.component.css'
})
export class BankpassbookComponent {


  public message:any = "PassBook";
  public class:any = "text-primary p-1 h3";
  public passbookData:any;
  public balance:any=0;

  constructor(private bankservice:BankserviceService){

    //getting passbook details and service
    this.bankservice.getPassBook().subscribe({
      next:(value)=>{
        this.passbookData = value;
      }, error:(err)=>{
        let message:string =".Can't fetch the passbook";
        let clas:string="";
        this.errors(err, message, clas);
      }
    });

    this.bankservice.getbalance().subscribe({
      next:(value)=>{
        this.balance = value;
      }, error:(err)=>{
        let message:string =".Can't fetch the balance";
        let clas:string="";
        this.errors(err, message, clas);
      }
       
    });
  }
  errors(err:any, message:string, clas:string){
    if(err.statusText != "OK" ){
      this.message="Waiting for Server Response";
      this.class = "alert alert-danger";
    }else{
      console.log(err);
      this.message = err.error+" "+message;
      this.class= "alert alert-danger "+clas;
    }
  }
}
