export interface IOrderpartnerUpload {
  id?: number;
  companycode?: string;
  customersuppliertype?: string;
  customersuppliercode?: string;
  documentType?: string;
  fileName?: string;
  originalFileName?: string;
  createdBy?: string;
  createdDate?: any;
  lastUpdatedBy?: string;
  lastUpdatedDate?: any;
}

export class OrderpartnerUpload implements IOrderpartnerUpload {
  constructor(
    public id?: number,
    public companycode?: string,
    public customersuppliertype?: string,
    public customersuppliercode?: string,
    public documentType?: string,
    public fileName?: string,
    public originalFileName?: string,
    public createdBy?: string,
    public createdDate?: any,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: any
  ) {}
}
