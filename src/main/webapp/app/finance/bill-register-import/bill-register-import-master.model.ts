import { Moment } from 'moment';
import { IBillRegisterImportDetails } from './bill-register-import-details.model';

export interface IBillRegisterImportMaster {
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
  currencycode?: string;
  currencyrate?: number;
  totalQuantity?: number;
  totalValue?: number;
  remarks?: string;
  submitDate?: Moment;
  receiveDate?: Moment;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  received?: boolean;
  submitted?: boolean;
  billRegisterDetailsBeans?: IBillRegisterImportDetails[];
}

export class BillRegisterImportMaster implements IBillRegisterImportMaster {
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
    public currencycode?: string,
    public currencyrate?: number,
    public totalQuantity?: number,
    public totalValue?: number,
    public remarks?: string,
    public submitDate?: Moment,
    public receiveDate?: Moment,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public received?: boolean,
    public submitted?: boolean,
    public billRegisterDetailsBeans?: IBillRegisterImportDetails[]
  ) {}
}
