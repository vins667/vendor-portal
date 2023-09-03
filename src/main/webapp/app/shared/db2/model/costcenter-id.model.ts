export interface ICostcenterId {
  companycode?: string;
  code?: string;
}

export class CostcenterId implements ICostcenterId {
  constructor(public companycode?: string, public code?: string) {}
}
