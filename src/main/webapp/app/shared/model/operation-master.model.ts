import { Moment } from 'moment';

export interface IOperationMaster {
  id?: number;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class OperationMaster implements IOperationMaster {
  constructor(
    public id?: number,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
