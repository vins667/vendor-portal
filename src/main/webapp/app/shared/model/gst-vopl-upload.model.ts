import { Moment } from 'moment';

export interface IGstVoplUpload {
  id?: number;
  vVchno?: string;
  vVchdate?: Moment;
  vGstin?: string;
  vSupplierCode?: string;
  vSupplierName?: string;
  vInvoiceno?: string;
  vInvoicedate?: Moment;
  vInvamt?: number;
  vInvnet?: number;
  vCgst?: number;
  vSgst?: number;
  vIgst?: number;
  uploadDate?: Moment;
  confirmDate?: Moment;
  status?: string;
}

export class GstVoplUpload implements IGstVoplUpload {
  constructor(
    public id?: number,
    public vVchno?: string,
    public vVchdate?: Moment,
    public vGstin?: string,
    public vSupplierCode?: string,
    public vSupplierName?: string,
    public vInvoiceno?: string,
    public vInvoicedate?: Moment,
    public vInvamt?: number,
    public vInvnet?: number,
    public vCgst?: number,
    public vSgst?: number,
    public vIgst?: number,
    public uploadDate?: Moment,
    public confirmDate?: Moment,
    public status?: string
  ) {}
}
