import { Moment } from 'moment';

export interface IVendors {
  id?: number;
  vendorCode?: string;
  vendorShortName?: string;
  vendorName?: string;
  approvalStatus?: string;
  requestedBy?: string;
  requestedDate?: Moment;
  approvedBy?: string;
  approvedDate?: Moment;
  deliveryTermMasterId?: number;
  payTermMasterId?: number;
  shipmentTermMasterId?: number;
  currencyMasterId?: number;
  orderAllowed?: boolean;
  size?: number;
  pageNo?: number;
}

export class Vendors implements IVendors {
  constructor(
    public id?: number,
    public vendorCode?: string,
    public vendorShortName?: string,
    public vendorName?: string,
    public approvalStatus?: string,
    public requestedBy?: string,
    public requestedDate?: Moment,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public deliveryTermMasterId?: number,
    public payTermMasterId?: number,
    public shipmentTermMasterId?: number,
    public currencyMasterId?: number,
    public orderAllowed?: boolean
  ) {
    this.orderAllowed = this.orderAllowed || false;
  }
}
