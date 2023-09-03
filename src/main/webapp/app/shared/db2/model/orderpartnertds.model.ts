import { IOrderpartnertdsId } from './orderpartnertds-id.model';

export interface IOrderpartnertds {
  id?: IOrderpartnertdsId;
  longdescription?: string;
  value?: number;
  tdsApplicable?: boolean;
}

export class Orderpartnertds implements IOrderpartnertds {
  constructor(public id?: IOrderpartnertdsId, public longdescription?: string, public value?: number, public tdsApplicable?: boolean) {}
}
