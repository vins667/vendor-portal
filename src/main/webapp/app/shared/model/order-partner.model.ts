export interface IOrderPartner {
  numberid?: string;
  customersuppliercode?: string;
  legalname1?: string;
}
export class OrderPartner implements IOrderPartner {
  constructor(public numberid?: string, public customersuppliercode?: string, public legalname1?: string) {}
}
