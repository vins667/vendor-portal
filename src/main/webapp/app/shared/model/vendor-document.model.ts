import { Moment } from 'moment';

export interface IVendorDocument {
  id?: number;
  documentId?: number;
  vendorId?: number;
  documentFilePath?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  dataFlag?: string;
  tableType?: string;
}

export class VendorDocument implements IVendorDocument {
  constructor(
    public id?: number,
    public documentId?: number,
    public vendorId?: number,
    public documentFilePath?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public dataFlag?: string,
    public tableType?: string
  ) {}
}
