export class Updatestockclass {

      public id:any;
      public currentPrice:any;
      public prisentPrice:any;

      constructor(id:any, currentPrice:any, prisentPrice:any){
            this.id = id;
            this.currentPrice  = currentPrice;
            this.prisentPrice = prisentPrice;
      }

      public getId():any{
            return this.id;
      }
      public getCurrentPrice():any{
            return this.currentPrice;
      }
      public getPrisentPrice():any{
            return this.prisentPrice;
      }


      public settId(id:any){
            this.id = id;
      }
      public setCurrentPrice(currentPrice:any){
            this.currentPrice  = currentPrice;
      }
      public setprisentPrice(prisentPrice:any){
            this.prisentPrice = prisentPrice;
      }
}
