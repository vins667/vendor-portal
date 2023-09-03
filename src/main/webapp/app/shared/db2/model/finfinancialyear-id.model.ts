export interface IFinfinancialyearId {
  companycode?: string;
  code?: bigint;
}
export class FinancialyearId implements IFinfinancialyearId {
  constructor(public companycode?: string, public code?: bigint) {}
}
