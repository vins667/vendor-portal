import { Moment } from 'moment';

export interface IJobWorkFollowupBuyerModel {
  id?: number;
  buyercode?: string;
  buyername?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
}
export class JobWorkFollowupBuyerModel {
  public id?: number;
  public buyercode?: string;
  public buyername?: string;
  public flag?: string;
  public createdBy?: string;
  public createdDate?: Moment;
}
