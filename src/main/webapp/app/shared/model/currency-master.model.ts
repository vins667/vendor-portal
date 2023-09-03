import { Moment } from 'moment';

export interface ICurrencyMaster {
  id?: number;
  currencyCode?: string;
  symbol?: string;
  exchangeRate?: number;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class CurrencyMaster implements ICurrencyMaster {
  constructor(
    public id?: number,
    public currencyCode?: string,
    public symbol?: string,
    public exchangeRate?: number,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
