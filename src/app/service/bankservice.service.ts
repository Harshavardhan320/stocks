import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const url:string = "http://localhost:8080/api/bankaccount";
@Injectable({
  providedIn: 'root'
})
export class BankserviceService {

  constructor(private Http:HttpClient) { }
  
  //passbook
  public getPassBook():Observable<any>{
    return this.Http.get(url+'/passbook');
  }

  //getting balance
  public getbalance():Observable<any>{
    return this.Http.get(url+"/getbalance");
  }

  //adding balance
  public addbalance(balance:any):Observable<any>{
    return this.Http.post(url+"/addbalance", balance);
  }

  //withdraw amount
  public withdraw(amount:any, message:any, paymentType:any):Observable<any>{
    return this.Http.get(url+`/withdraw/${amount}/${message}/${paymentType}`);
  }
}
