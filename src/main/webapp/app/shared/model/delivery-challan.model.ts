import { Moment } from 'moment';
import { IDeliveryChallanDetails } from './delivery-challan-details.model';
export interface IDeliveryChallan {
  id?: number;
  bUniqueid?: any;
  factCode?: string;
  factDescription?: string;
  fAddressLine1?: string;
  fAddressLine2?: string;
  fAddressLine3?: string;
  fAddressLine4?: string;
  fAddressLine5?: string;
  postalCode?: string;
  town?: string;
  district?: string;
  stateCode?: string;
  fGSTNumber?: string;
  showAddress1?: string;
  showAddress2?: string;
  suppliercode?: number;
  bLegalname1?: string;
  bAddressLine1?: string;
  bAddressLine2?: string;
  bAddressLine3?: string;
  bAddressLine4?: string;
  bAddressLine5?: string;
  bPostalCode?: string;
  bTown?: string;
  bDistrict?: string;
  bState?: string;
  bStateCode?: string;
  state?: string;
  bGSTNumber?: string;
  challanType?: string;
  challanDate?: string;
  eWayBillNo?: string;
  eWayBillDate?: string;
  expReturnDate?: string;
  acReturnDate?: string;
  remarks?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  approvedBy?: string;
  approvedDate?: Moment;
  deliveryChallanDetails?: IDeliveryChallanDetails[];
  taxper?: number;
  index?: number;
  triffCode?: string;
}

export class DeliveryChallan implements IDeliveryChallan {
  constructor(
    public id?: number,
    public bUniqueid?: any,
    public factCode?: string,
    public factDescription?: string,
    public fAddressLine1?: string,
    public fAddressLine2?: string,
    public fAddressLine3?: string,
    public fAddressLine4?: string,
    public fAddressLine5?: string,
    public postalCode?: string,
    public town?: string,
    public district?: string,
    public stateCode?: string,
    public fGSTNumber?: string,
    public showAddress1?: string,
    public showAddress2?: string,
    public suppliercode?: number,
    public bLegalname1?: string,
    public bAddressLine1?: string,
    public bAddressLine2?: string,
    public bAddressLine3?: string,
    public bAddressLine4?: string,
    public bAddressLine5?: string,
    public bPostalCode?: string,
    public bTown?: string,
    public bDistrict?: string,
    public bState?: string,
    public bStateCode?: string,
    public state?: string,
    public bGSTNumber?: string,
    public challanType?: string,
    public challanDate?: string,
    public eWayBillNo?: string,
    public eWayBillDate?: string,
    public expReturnDate?: string,
    public acReturnDate?: string,
    public remarks?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public deliveryChallanDetails?: IDeliveryChallanDetails[],
    public taxper?: number,
    public index?: number,
    public triffCode?: string
  ) {}
}
