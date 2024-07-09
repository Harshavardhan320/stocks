import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../service/notification.service';
import id from '@angular/common/locales/id';
import { window } from 'rxjs';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent implements OnInit{


  notificationCount:any =0;
  error:any=0
  message:String="Notification";
  class:string="bg-light";
  notificationData:any;

  constructor(private service:NotificationService){
    
  }
  ngOnInit(): void {
   
    this.notification();
    
  }
  notification():any{
    this.service.getAllalerts().subscribe({
      next:(value)=>{
        this.notificationData=value;
        for(let i=0; i<value.length; i++){
          if(value[i].seen == "UNSEEN")
            this.notificationCount +=1;
        }
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
  updateSeenStatus(id:any){
    this.service.updateSeenStatus(id).subscribe({
      error:(err:any)=>{
        this.error +=1;
        this.errors(err);
      }
    });
    
    location.reload();
  }

  update() {
    this.service.update().subscribe({
      next: (value) => {
        alert("update all records to seen");
      },error:(err)=>{
        this.errors(err);
      }
    });
    location.reload();
  }
}
