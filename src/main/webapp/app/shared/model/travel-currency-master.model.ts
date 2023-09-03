import { Moment } from 'moment';

export interface ITravelCurrencyMaster {
  id?: number;
  currencyType?: string;
  currencyName?: string;
  status?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  conversionFactor?: number;
}

export class TravelCurrencyMaster implements ITravelCurrencyMaster {
  constructor(
    public id?: number,
    public currencyType?: string,
    public currencyName?: string,
    public status?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public conversionFactor?: number
  ) {
    this.status = this.status || false;
  }
}
