import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const url:string = "http://localhost:8080/api/";
@Injectable({
  providedIn: 'root'
})

export class DateserviceService {

  /*
    changing status
  */
  changeStatus(status: string):Observable<any> {
    return this.http.get(url+`calandar/change/status/${status}`);
  }

  constructor(private http:HttpClient) { }
  /*
    Service method for getting year and month
  */
  getYearMonth(yearmonth:string):Observable<any>{
    return this.http.get(url+`calandar/getYearmonth/${yearmonth}`);
  }
  getYearmonth():Observable<any>{
    return this.http.get(url+`calandar/getYearmonth`);
  }

  /*
    service method for month 
  */
  addingMonth(yearmonth:any):Observable<any>{
    return this.http.post(url+"calandar/addyearmonth", yearmonth);
  }

  getAllalerts():Observable<any>{
    return this.http.get(url+`alert/getallalerts`);
  }
}
