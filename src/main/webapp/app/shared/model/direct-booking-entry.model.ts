import { Moment } from 'moment';
import { IDirectBookingDetails } from './direct-booking-details.model';
import { IDirectBookingTdsDetails } from './direct-booking-tds-details.model';
import { IOrderpartnertds } from 'app/shared/db2/model/orderpartnertds.model';
export interface IDirectBookingEntry {
  id?: number;
  company?: string;
  division?: string;
  businessunitcompanycode?: string;
  businessunitcode?: string;
  factorycode?: string;
  factorystate?: string;
  bookingdate?: Moment;
  bookingtype?: string;
  bookingfor?: string;
  suppliercustomertype?: string;
  suppliercustomercode?: string;
  suppliercustomerdesc?: string;
  suppliercustomerstate?: string;
  supplierlegalname?: string;
  gstin?: string;
  address?: string;
  billno?: string;
  billdate?: Moment;
  billamount?: number;
  paymenttermcode?: string;
  paymenttermdesc?: string;
  costcentercode?: string;
  costcenterdesc?: string;
  remarks?: string;
  vehicleNo?: string;
  projectcode?: string;
  gatenumber?: string;
  gateentrynumber?: string;
  gateNoRequired?: boolean;
  rcmBill?: boolean;
  shippingBill?: boolean;
  freightApplicable?: boolean;
  freightType?: string;
  freightValue?: number;
  discountApplicable?: boolean;
  discountType?: string;
  discountValue?: number;
  otherChargesApplicable?: boolean;
  otherChargesType?: string;
  otherChargesValue?: number;
  tcsApplicable?: boolean;
  tcsValue?: number;
  value?: number;
  itaxvalue?: number;
  ctaxvalue?: number;
  staxvalue?: number;
  taxvalue?: number;
  totalvalue?: number;
  roundOffValue?: number;
  roundOffType?: string;
  netAmount?: number;
  tdsValue?: number;
  mtds?: boolean;
  flag?: string;
  copyFlag?: string;
  orderpartnertdss?: IOrderpartnertds[];
  directBookingDetails?: IDirectBookingDetails[];
  directBookingTdsDetails?: IDirectBookingTdsDetails[];
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  findocumentcode?: string;
  styleNo?: string;
  customerCode?: string;
  customerName?: string;
  customerGstName?: string;
}

export class DirectBookingEntry implements IDirectBookingEntry {
  constructor(
    public id?: number,
    public company?: string,
    public division?: string,
    public businessunitcompanycode?: string,
    public businessunitcode?: string,
    public factorycode?: string,
    public factorystate?: string,
    public bookingdate?: Moment,
    public bookingtype?: string,
    public bookingfor?: string,
    public suppliercustomertype?: string,
    public suppliercustomercode?: string,
    public suppliercustomerdesc?: string,
    public suppliercustomerstate?: string,
    public supplierlegalname?: string,
    public gstin?: string,
    public address?: string,
    public billno?: string,
    public billdate?: Moment,
    public billamount?: number,
    public paymenttermcode?: string,
    public paymenttermdesc?: string,
    public costcentercode?: string,
    public costcenterdesc?: string,
    public remarks?: string,
    public vehicleNo?: string,
    public projectcode?: string,
    public gatenumber?: string,
    public gateentrynumber?: string,
    public gateNoRequired?: boolean,
    public rcmBill?: boolean,
    public shippingBill?: boolean,
    public freightApplicable?: boolean,
    public freightType?: string,
    public freightValue?: number,
    public discountApplicable?: boolean,
    public discountType?: string,
    public discountValue?: number,
    public otherChargesApplicable?: boolean,
    public otherChargesType?: string,
    public otherChargesValue?: number,
    public tcsApplicable?: boolean,
    public tcsValue?: number,
    public value?: number,
    public itaxvalue?: number,
    public ctaxvalue?: number,
    public staxvalue?: number,
    public taxvalue?: number,
    public totalvalue?: number,
    public roundOffValue?: number,
    public roundOffType?: string,
    public netAmount?: number,
    public tdsValue?: number,
    public mtds?: boolean,
    public flag?: string,
    public copyFlag?: string,
    public orderpartnertdss?: IOrderpartnertds[],
    public directBookingDetails?: IDirectBookingDetails[],
    public directBookingTdsDetails?: IDirectBookingTdsDetails[],
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public findocumentcode?: string,
    public styleNo?: string,
    public customerCode?: string,
    public customerName?: string,
    public customerGstName?: string
  ) {
    this.rcmBill = this.rcmBill || false;
    this.shippingBill = this.shippingBill || false;
    this.freightApplicable = this.freightApplicable || false;
    this.discountApplicable = this.discountApplicable || false;
    this.otherChargesApplicable = this.otherChargesApplicable || false;
    this.tcsApplicable = this.tcsApplicable || false;
  }
}
