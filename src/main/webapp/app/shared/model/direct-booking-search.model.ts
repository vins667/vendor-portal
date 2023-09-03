export interface IDirectBookingSearch {
  id?: number;
  bookingDateFrom?: any;
  bookingDateTo?: any;
  billDate?: any;
  customersuppliercode?: string;
  billNo?: string;
  company?: string;
  division?: string;
  businessunit?: string;
  findocumentcode?: string;
  flag?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class DirectBookingSearch implements IDirectBookingSearch {
  constructor(
    public id?: number,
    public bookingDateFrom?: any,
    public bookingDateTo?: any,
    public billDate?: any,
    public customersuppliercode?: string,
    public billNo?: string,
    public company?: string,
    public division?: string,
    public businessunit?: string,
    public findocumentcode?: string,
    public flag?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
