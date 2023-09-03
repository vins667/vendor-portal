import { Moment } from 'moment';

export interface IBillRegisterImportDetails {
  id?: number;
  companycode?: string;
  countercode?: string;
  code?: string;
  orderdate?: any;
  projectcode?: string;
  summarizeddescription?: string;
  userprimaryuomcode?: string;
  quantity?: number;
  price?: number;
  grossvalue?: number;
  submitdate?: Moment;
  receiveDate?: Moment;
  shipmentMode?: string;
}

export class BillRegisterImportDetails implements IBillRegisterImportDetails {
  constructor(
    public id?: number,
    public companycode?: string,
    public countercode?: string,
    public code?: string,
    public orderdate?: any,
    public projectcode?: string,
    public summarizeddescription?: string,
    public userprimaryuomcode?: string,
    public quantity?: number,
    public price?: number,
    public grossvalue?: number,
    public submitdate?: Moment,
    public receiveDate?: Moment,
    public shipmentMode?: string
  ) {}
}
