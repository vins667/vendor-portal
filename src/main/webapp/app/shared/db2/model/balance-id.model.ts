export interface IBalanceId {
  companycode?: string;
  numberid?: string;
}

export class BalanceId implements IBalanceId {
  constructor(public companycode?: string, public numberid?: string) {}
}
