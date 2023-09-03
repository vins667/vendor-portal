export interface IProductionorderSearch {
  companycode?: string;
  countercode?: string;
  code?: string;
  operationcode?: string;
  requesttype?: string;
  page?: any;
  size?: number;
  pageNo?: number;
}

export class ProductionorderSearch implements IProductionorderSearch {
  constructor(
    public companycode?: string,
    public countercode?: string,
    public code?: string,
    public operationcode?: string,
    public requesttype?: string,
    public page?: any,
    public size?: number,
    public pageNo?: number
  ) {}
}
