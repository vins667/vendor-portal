import { Moment } from 'moment';

export interface IBankMaster {
  id?: number;
  bankCode?: string;
  bankName?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class BankMaster implements IBankMaster {
  constructor(
    public id?: number,
    public bankCode?: string,
    public bankName?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
