import { Moment } from 'moment';
import { IBillRegisterDetails } from 'app/finance/bill-register/bill-register-details.model';

export interface IBillRegisterMaster {
  id?: number;
  company?: string;
  division?: string;
  businessunitcompanycode?: string;
  businessunitcode?: string;
  billtype?: string;
  billnumber?: string;
  billdate?: Moment;
  customersuppliertype?: string;
  customersuppliercode?: string;
  customersuppliername?: string;
  remarks?: string;
  totalQuantity?: number;
  totalValue?: number;
  submitDate?: Moment;
  receiveDate?: Moment;
  queryFlag?: boolean;
  queryRemarks?: string;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  received?: boolean;
  submitted?: boolean;
  billRegisterDetailsBeans?: IBillRegisterDetails[];
}

export class BillRegisterMaster implements IBillRegisterMaster {
  constructor(
    public id?: number,
    public company?: string,
    public division?: string,
    public businessunitcompanycode?: string,
    public businessunitcode?: string,
    public billtype?: string,
    public billnumber?: string,
    public billdate?: Moment,
    public customersuppliertype?: string,
    public customersuppliercode?: string,
    public customersuppliername?: string,
    public remarks?: string,
    public totalQuantity?: number,
    public totalValue?: number,
    public submitDate?: Moment,
    public receiveDate?: Moment,
    public queryFlag?: boolean,
    public queryRemarks?: string,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public received?: boolean,
    public submitted?: boolean,
    public billRegisterDetailsBeans?: IBillRegisterDetails[]
  ) {}
}
