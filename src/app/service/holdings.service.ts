import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const url:string = "http://localhost:8080/api/Holdings/";
@Injectable({
  providedIn: 'root'
})
export class HoldingsService {
  

  constructor(private Http:HttpClient) { }


  /*
    get all stocks
  */
  public getAllstocks():Observable<any>{
    return this.Http.get(url+`allstocksdetails`);
  }
  /*
    service method getting profits from the server 
  */
 public profit():Observable<any>{
  return this.Http.get(url+`monthprofit`);
 }

 /*
  getting all stocks month
 */

  public presentmonth(month:string):Observable<any>{

    return this.Http.get(url+`selectbymonth/${month}`);
  }

  /*
    getting profit details
  */
    getProfitDetails(monthname: any):Observable<any> {
      return this.Http.get(url+`monthly/profits/${monthname}`);
    }

    /*
      service ethod add stocks
    */
   public addStock(data:any):Observable<any>{
    return this.Http.post(url+"addstock", data);
   }
   /*
    find by id
   */
  public findById(id:any):Observable<any>{
    return this.Http.get(url+`stockid/${id}`);
  }

  /*
    service method for buying
  */
 public buyQuantity(data:any):Observable<any>{
  return this.Http.post(url+"addquantity", data);
 }

   /*
    service method for selling
  */
  public sellQuantity(data:any):Observable<any>{
    return this.Http.post(url+"sellstock", data);
  }

  /*
    service method for updating current price

  */
  public updateCurrPrice(data:any):Observable<any>{
    return this.Http.post(url+"update/holdings", data);
  }

   /*
    service method for updating current price and duration of the stock

  */
    public updateCurrPriceDuration(data:any, duration:string):Observable<any>{
      return this.Http.post(url+`update/holdings/${duration}`, data);
    }
}
