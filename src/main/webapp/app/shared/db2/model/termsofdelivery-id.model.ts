export interface ITermsofdeliveryId {
  companycode?: string;
  code?: string;
}

export class TermsofdeliveryId implements ITermsofdeliveryId {
  constructor(public companycode?: string, public code?: string) {}
}
