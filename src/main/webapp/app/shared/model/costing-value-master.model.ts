import { Moment } from 'moment';

export interface ICostingValueMaster {
  id?: number;
  processname?: string;
  valuetype?: string;
  value?: string;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
}

export class CostingValueMaster implements ICostingValueMaster {
  constructor(
    public id?: number,
    public processname?: string,
    public valuetype?: string,
    public value?: string,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment
  ) {}
}
