import { DateserviceService } from "../service/dateservice.service";

export class Stockclass {
      constructor(){}
      private months:String[] = ["January", "February", "March", "April", "May", "June", "July", "August", "September", 
            "October", "November", "December"]
      ;
      
      public getYearmonth():any{
            let date  = new Date();
            let month = date.getMonth();
            let year = date.getFullYear();
            return year +" "+this.months[month];
      }
      public getLastYearmonth():any{
            let date  = new Date();
            let lastmonth = date.getFullYear()+1+" "+this.months[0];
            if(this.getYearmonth() == lastmonth ){

                  return date.getFullYear()-1+" "+this.months[11];
            }

            
            let month = date.getMonth();
            let year = date.getFullYear();
            return year +" "+this.months[month-1];
      }
}
