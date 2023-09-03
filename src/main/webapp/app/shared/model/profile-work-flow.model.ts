import { Moment } from 'moment';

export interface IProfileWorkFlow {
  id?: number;
  userCode?: string;
  userName?: string;
  forwardCode?: string;
  forwardName?: string;
  forwardFlag?: string;
  userType?: string;
  remarks?: string;
  createdDate?: Moment;
  createdBy?: string;
  vendorId?: number;
  vendorCode?: string;
  vendorShortName?: string;
  deliveryTermMasterId?: number;
  payTermMasterId?: number;
  shipmentTermMasterId?: number;
  currencyMasterId?: number;
  orderAllowed?: boolean;
}

export class ProfileWorkFlow implements IProfileWorkFlow {
  constructor(
    public id?: number,
    public userCode?: string,
    public userName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public forwardFlag?: string,
    public userType?: string,
    public remarks?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public vendorId?: number,
    public vendorCode?: string,
    public vendorShortName?: string,
    public deliveryTermMasterId?: number,
    public payTermMasterId?: number,
    public shipmentTermMasterId?: number,
    public currencyMasterId?: number,
    public orderAllowed?: boolean
  ) {}
}
