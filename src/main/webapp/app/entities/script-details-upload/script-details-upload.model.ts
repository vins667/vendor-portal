import { Moment } from 'moment';

export interface IScriptDetailsUpload {
  id?: number;
  sNo?: string;
  shippingBillNo?: string;
  invoiceNo?: string;
  shippingBillDate?: any;
  scrollNo?: string;
  portCode?: string;
  sanctionedValue?: number;
  fobInFc?: number;
  fobInInr?: number;
  brcNumber?: string;
  customerName?: string;
  entryDate?: any;
  brcRealisedValue?: number;
  ifscCode?: string;
  scriptNo?: string;
  scriptAmount?: number;
  createdby?: string;
  createddate?: Moment;
}
export class ScriptDetailsUpload implements IScriptDetailsUpload {
  constructor(
    public id?: number,
    public sNo?: string,
    public shippingBillNo?: string,
    public invoiceNo?: string,
    public shippingBillDate?: any,
    public scrollNo?: string,
    public portCode?: string,
    public sanctionedValue?: number,
    public fobInFc?: number,
    public fobInInr?: number,
    public brcNumber?: string,
    public customerName?: string,
    public entryDate?: any,
    public brcRealisedValue?: number,
    public ifscCode?: string,
    public scriptNo?: string,
    public scriptAmount?: number,
    public createdby?: string,
    public createddate?: Moment
  ) {}
}
