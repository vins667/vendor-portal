export interface ITermsofshippingId {
  companycode?: string;
  code?: string;
}

export class TermsofshippingId implements ITermsofshippingId {
  constructor(public companycode?: string, public code?: string) {}
}
