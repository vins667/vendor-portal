export interface IBillRegisterImportSearch {
  flag?: string;
  billType?: string;
  selectedItems?: any;
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

export class BillRegisterImportSearch implements IBillRegisterImportSearch {
  constructor(
    public flag?: string,
    public billType?: string,
    public selectedItems?: any,
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
