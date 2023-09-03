import { Moment } from 'moment';

export interface IVendorBankDetails {
  id?: number;
  vendorId?: number;
  bankName?: string;
  accountType?: string;
  ifsc?: string;
  swiftCode?: string;
  addressLine1?: string;
  addressLine2?: string;
  addressLine3?: string;
  addressLine4?: string;
  countryId?: number;
  stateId?: number;
  pinCode?: string;
  bankMailId?: string;
  accountNumber?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VendorBankDetails implements IVendorBankDetails {
  constructor(
    public id?: number,
    public vendorId?: number,
    public bankName?: string,
    public accountType?: string,
    public ifsc?: string,
    public swiftCode?: string,
    public addressLine1?: string,
    public addressLine2?: string,
    public addressLine3?: string,
    public addressLine4?: string,
    public countryId?: number,
    public stateId?: number,
    public pinCode?: string,
    public bankMailId?: string,
    public accountNumber?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
