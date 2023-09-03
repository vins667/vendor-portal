import { IOrderpartnerbankId } from './orderpartnerbank-id.model';

export interface IOrderpartnerbank {
  id?: IOrderpartnerbankId;
  bankbankcountrycode?: string;
  bankcode?: string;
  bankbranchcode?: string;
  externalbankcode?: string;
  cincode?: string;
  currentaccountid?: string;
  currencycode?: string;
  accountowner?: string;
  bban?: string;
  bic?: string;
  iban?: string;
  priority?: number;
  directdebit?: boolean;
  absuniqueid?: number;
}

export class Orderpartnerbank implements IOrderpartnerbank {
  constructor(
    public id?: IOrderpartnerbankId,
    public bankbankcountrycode?: string,
    public bankcode?: string,
    public bankbranchcode?: string,
    public externalbankcode?: string,
    public cincode?: string,
    public currentaccountid?: string,
    public currencycode?: string,
    public accountowner?: string,
    public bban?: string,
    public bic?: string,
    public iban?: string,
    public priority?: number,
    public directdebit?: boolean,
    public absuniqueid?: number
  ) {}
}
