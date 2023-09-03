import { Moment } from 'moment';

export interface IBillRegisterDetails {
  id?: number;
  companycode?: string;
  divisioncode?: string;
  invoicetypecode?: string;
  invoicedate?: Moment;
  billtype?: string;
  code?: string;
  style?: string;
  customercode?: string;
  customername?: string;
  grossvalue?: number;
  quantity?: number;
  price?: number;
  submitdate?: Moment;
  receiveDate?: Moment;
  status?: string;
  perpcsrate?: number;
  shipmentMode?: string;
}

export class BillRegisterDetails implements IBillRegisterDetails {
  constructor(
    public id?: number,
    public companycode?: string,
    public divisioncode?: string,
    public invoicetypecode?: string,
    public invoicedate?: Moment,
    public billtype?: string,
    public code?: string,
    public style?: string,
    public customercode?: string,
    public customername?: string,
    public grossvalue?: number,
    public quantity?: number,
    public price?: number,
    public submitdate?: Moment,
    public receiveDate?: Moment,
    public status?: string,
    public perpcsrate?: number,
    public shipmentMode?: string
  ) {}
}
