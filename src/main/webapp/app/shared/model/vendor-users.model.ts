import { Moment } from 'moment';

export interface IVendorUsers {
  id?: number;
  userType?: string;
  userName?: string;
  designation?: string;
  emailId?: string;
  mobileNumber?: string;
  vendorId?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  index?: number;
  dataFlag?: string;
}

export class VendorUsers implements IVendorUsers {
  constructor(
    public id?: number,
    public userType?: string,
    public userName?: string,
    public designation?: string,
    public emailId?: string,
    public mobileNumber?: string,
    public vendorId?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public index?: number,
    public dataFlag?: string
  ) {}
}
