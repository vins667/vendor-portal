export interface IPurchaseorderId {
  companycode?: string;
  countercode?: string;
  code?: string;
}

export class PurchaseorderId implements IPurchaseorderId {
  constructor(public companycode?: string, public countercode?: string, public code?: string) {}
}
