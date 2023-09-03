import { IPaymentmethodId } from './paymentmethod-id.model';

export interface IPaymentmethod {
  id?: IPaymentmethodId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Paymentmethod implements IPaymentmethod {
  constructor(
    public id?: IPaymentmethodId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
