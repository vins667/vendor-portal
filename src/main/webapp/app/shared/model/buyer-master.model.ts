import { Moment } from 'moment';

export interface IBuyerMaster {
  id?: number;
  buyerCode?: string;
  buyerName?: string;
  contactNo?: string;
  emailId?: string;
  address?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  mapping?: boolean;
  auditName?: string;
}

export class BuyerMaster implements IBuyerMaster {
  constructor(
    public id?: number,
    public buyerCode?: string,
    public buyerName?: string,
    public contactNo?: string,
    public emailId?: string,
    public address?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public mapping?: boolean,
    public auditName?: string
  ) {}
}
