export interface IFinbusinessunitId {
  companycode?: string;
  code?: string;
}

export class FinbusinessunitId implements IFinbusinessunitId {
  constructor(public companycode?: string, public code?: string) {}
}
