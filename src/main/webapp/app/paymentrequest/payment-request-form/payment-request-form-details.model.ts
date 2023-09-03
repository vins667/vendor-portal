export interface IPaymentRequestFormDetails {
  id?: number;
  empCode?: string;
  empName?: string;
  flag?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  createdBy?: string;
  createdDate?: any;
  paymentRequestFormId?: number;
}

export class PaymentRequestFormDetails implements IPaymentRequestFormDetails {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public flag?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public createdBy?: string,
    public createdDate?: any,
    public paymentRequestFormId?: number
  ) {}
}
