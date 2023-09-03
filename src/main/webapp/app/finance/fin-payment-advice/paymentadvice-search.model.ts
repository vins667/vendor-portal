export interface IPaymentadviceSearch {
  status?: string;
  chequenumber?: string;
  supplier?: string;
  dateType?: string;
  dateFrom?: any;
  dateTo?: any;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class PaymentadviceSearch implements IPaymentadviceSearch {
  constructor(
    public status?: string,
    public chequenumber?: string,
    public supplier?: string,
    public dateType?: string,
    public dateFrom?: any,
    public dateTo?: any,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
