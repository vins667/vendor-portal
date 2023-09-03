import { Moment } from 'moment';

export interface ICostingGroupMaster {
  id?: number;
  code?: string;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class CostingGroupMaster implements ICostingGroupMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
