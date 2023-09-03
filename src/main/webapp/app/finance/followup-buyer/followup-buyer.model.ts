import { Moment } from 'moment';

export interface IFollowupBuyer {
  id?: number;
  buyercode?: string;
  buyername?: string;
  flag?: string;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  size?: number;
}

export class FollowupBuyer implements IFollowupBuyer {
  constructor(
    public id?: number,
    public buyercode?: string,
    public buyername?: string,
    public flag?: string,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public size?: number
  ) {}
}
