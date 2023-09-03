export interface ISalesOrderClosingSearch {
  status?: string;
  code?: string;
  shippedPercentage?: number;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}
export class SalesOrderClosingSearch implements ISalesOrderClosingSearch {
  constructor(
    public status?: string,
    public code?: string,
    public shippedPercentage?: number,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
