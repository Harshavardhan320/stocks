import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const url:string = "http://localhost:8080/api/";
@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http:HttpClient) { }

  
  getAllalerts():Observable<any>{
    return this.http.get(url+`alert/getallalerts`);
  }

  updateSeenStatus(id:any):Observable<any>{
    return this.http.get(url+"alert/seen/"+id);
  }
}
