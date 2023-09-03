import { Moment } from 'moment';
import { IDeliveryChallan } from 'app/shared/model//delivery-challan.model';

export interface IDeliveryChallanDetails {
  id?: number;
  productName?: string;
  triffCode?: string;
  quantity?: number;
  taxper?: number;
  rate?: number;
  taxval?: number;
  amount?: number;
  totalAmt?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class DeliveryChallanDetails implements IDeliveryChallanDetails {
  constructor(
    public id?: number,
    public productName?: string,
    public triffCode?: string,
    public quantity?: number,
    public taxper?: number,
    public rate?: number,
    public taxval?: number,
    public amount?: number,
    public totalAmt?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
