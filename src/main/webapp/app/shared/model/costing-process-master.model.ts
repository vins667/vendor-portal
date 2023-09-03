import { Moment } from 'moment';

export interface ICostingProcessMaster {
  id?: number;
  processcode?: string;
  processdesc?: string;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
}

export class CostingProcessMaster implements ICostingProcessMaster {
  constructor(
    public id?: number,
    public processcode?: string,
    public processdesc?: string,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment
  ) {}
}
