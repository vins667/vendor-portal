import { Moment } from 'moment';

export interface IVendorTerms {
  id?: number;
  vendorId?: number;
  terms?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VendorTerms implements IVendorTerms {
  constructor(
    public id?: number,
    public vendorId?: number,
    public terms?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
