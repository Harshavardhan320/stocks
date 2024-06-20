import { Routes } from '@angular/router';
import { HomeComponent } from './comp/home/home.component';
import { NotificationComponent } from './comp/notification/notification.component';
import { MonthdataComponent } from './comp/monthdata/monthdata.component';
import { HoldingsComponent } from './comp/holdings/holdings.component';
import { BankpassbookComponent } from './comp/bankpassbook/bankpassbook.component';
import { BankaccountComponent } from './comp/bankaccount/bankaccount.component';
import { AddstocksComponent } from './comp/addstocks/addstocks.component';
import { StockdetailsComponent } from './comp/stockdetails/stockdetails.component';
import { UpdatestocksComponent } from './comp/updatestocks/updatestocks.component';
import { BuyorsellstocksComponent } from './comp/buyorsellstocks/buyorsellstocks.component';
import { EditstocksComponent } from './comp/editstocks/editstocks.component';

export const routes: Routes = [

      {path:"", component:HomeComponent},
      {path:"notification", component:NotificationComponent},
      {path:"home", component:HomeComponent},
      {path:"monthdata/:monthName", component:MonthdataComponent},
      {path:"holdings", component:HoldingsComponent},
      {path:"passbook", component:BankpassbookComponent},
      {path:"bankaccount", component:BankaccountComponent},
      {path:"addstock", component:AddstocksComponent},
      {path:"stockdetails/:id", component:StockdetailsComponent},
      {path:"updatestocks", component:UpdatestocksComponent},
      {path:"buyorsellstocks/:id/:buyorsell/:stockname", component:BuyorsellstocksComponent},
      {path:"editstocks", component:EditstocksComponent},
      {path:"**", redirectTo:"home"}

];
