import { Moment } from 'moment';

export interface IGstReconciliation {
  id?: number;
  unitCode?: string;
  gstin?: string;
  invoiceType?: string;
  invoiceNo?: string;
  invoiceDate?: Moment;
  supplierName?: string;
  state?: string;
  invoiceAmount?: number;
  reverseAmount?: number;
  cgstAmount?: number;
  sgstAmount?: number;
  igstAmount?: number;
  cessAmount?: number;
  location?: string;
  srlNumber?: string;
  status?: string;
  creationDate?: Moment;
  govtInvoiceAmount?: number;
  govtCgstAmount?: number;
  govtSgstAmount?: number;
  govtIgstAmount?: number;
  differenceAmt?: number;
}

export class GstReconciliation implements IGstReconciliation {
  constructor(
    public id?: number,
    public unitCode?: string,
    public gstin?: string,
    public invoiceType?: string,
    public invoiceNo?: string,
    public invoiceDate?: Moment,
    public supplierName?: string,
    public state?: string,
    public invoiceAmount?: number,
    public reverseAmount?: number,
    public cgstAmount?: number,
    public sgstAmount?: number,
    public igstAmount?: number,
    public cessAmount?: number,
    public location?: string,
    public srlNumber?: string,
    public status?: string,
    public creationDate?: Moment,
    public govtInvoiceAmount?: number,
    public govtCgstAmount?: number,
    public govtSgstAmount?: number,
    public govtIgstAmount?: number,
    public differenceAmt?: number
  ) {}
}
