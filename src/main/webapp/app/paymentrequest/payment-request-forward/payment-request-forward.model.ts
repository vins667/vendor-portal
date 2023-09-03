export interface IPaymentRequestForward {
  id?: number;
  empCode?: string;
  empName?: string;
  flag?: string;
  forwardCode?: string;
  forwardName?: string;
  createdBy?: string;
  createdDate?: any;
}

export class PaymentRequestForward implements IPaymentRequestForward {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public flag?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public createdBy?: string,
    public createdDate?: any
  ) {}
}
