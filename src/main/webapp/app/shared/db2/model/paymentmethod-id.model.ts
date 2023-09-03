export interface IPaymentmethodId {
  companycode?: string;
  code?: string;
}

export class PaymentmethodId implements IPaymentmethodId {
  constructor(public companycode?: string, public code?: string) {}
}
