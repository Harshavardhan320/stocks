import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BankserviceService } from '../../service/bankservice.service';
const Deposit: any="DEPOSIT";
@Component({
  selector: 'app-bankaccount',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './bankaccount.component.html',
  styleUrl: './bankaccount.component.css'
})
export class BankaccountComponent implements OnInit {
public deposit:any = Deposit;
 
public message ="Bank Account";
public class="";

public accountData:any;
public accountbalance:any=0;
public paymentType:any;

  constructor(private bankService:BankserviceService){}

  ngOnInit(): void {
    
    this.bankService.getbalance().subscribe({
      next:(value)=> {
          this.accountbalance=value;
      },error:(err)=>{
        this.errors(err, "", "");
      }
    })
  }

  addBalance(balance:any){
    if(balance.value["deposit"]>0){
      let obj ={
        deposit:balance.value["deposit"],
        message:"deposit",
        paymentType:"DEPOSIT"
      }
      this.bankService.addbalance(obj).subscribe({
        next:(data)=>{
          this.accountData=data;
          console.log(data);
          this.paymentType=data.paymentType;

          this.message= "Amount "+data.deposit+"/- deposited successfully";
          this.class="alert alert-success";
        },error:(err)=>{
          this.errors(err, "", "");
        }
      }); 
    }else{
     this.message= "Invalid Amount";
     this.class= "alert alert-danger";
    }
    setTimeout(()=>{
      balance.reset();
      this.ngOnInit();
      this.message="Bank Account";
      this.class="";
    },3000);
  }


  //method for withdraw
  withdrawAmount(data:any){
    if(data.value["Withdraw"]>0){
      
      this.bankService.withdraw(data.value["Withdraw"],"user withdraw", "WITHDRAW").subscribe({
        next:(data)=>{
          this.accountData=data;
          this.paymentType=data.paymentType;
          this.message= "Amount "+data.withDraw+"/- credited successfully";
          this.class="alert alert-success";
        }, error:(err)=> {
          this.errors(err,"","");
        }
      });
    }else{
      this.message= "Invalid Amount";
      this.class= "alert alert-danger";
    }
    setTimeout(()=>{
      data.reset();
      this.ngOnInit();
      this.message="Bank Account";
      this.class="";
    },3000);
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
