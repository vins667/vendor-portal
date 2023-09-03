export interface IProductionorderId {
  companycode?: string;
  code?: string;
}

export class ProductionorderId implements IProductionorderId {
  constructor(public companycode?: string, public code?: string) {}
}
