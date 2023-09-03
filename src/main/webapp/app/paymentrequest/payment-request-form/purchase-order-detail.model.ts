export interface IPurchaseOrderDetail {
  paymentMethodcode?: string;
  paymentMethodDescription?: string;
  poBasicValue?: number;
  poGstValue?: number;
}

export class PurchaseOrderDetail implements IPurchaseOrderDetail {
  constructor(
    public paymentMethodcode?: string,
    public paymentMethodDescription?: string,
    public poBasicValue?: number,
    public poGstValue?: number
  ) {}
}
