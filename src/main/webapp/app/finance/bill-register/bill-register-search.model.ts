export interface IBillRegisterSearch {
  flag?: string;
  billType?: string;
  invoiceCode?: string;
  dateType?: string;
  invoiceDateFrom?: any;
  invoiceDateTo?: any;
  supplierName?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class BillRegisterSearch implements IBillRegisterSearch {
  constructor(
    public flag?: string,
    public billType?: string,
    public invoiceCode?: string,
    public dateType?: string,
    public invoiceDateFrom?: any,
    public invoiceDateTo?: any,
    public supplierName?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
