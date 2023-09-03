export interface IPaymentRequestInvoice {
  invoiceNo?: string;
  invoiceDate?: any;
}

export class PaymentRequestInvoice {
  constructor(public invoiceNo?: string, public invoiceDate?: any) {}
}
