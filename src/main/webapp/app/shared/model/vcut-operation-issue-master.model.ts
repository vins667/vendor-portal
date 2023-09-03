import { Moment } from 'moment';

export interface IVcutOperationIssueMaster {
  id?: number;
  description?: string;
  descriptionLocal?: string;
  type?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VcutOperationIssueMaster implements IVcutOperationIssueMaster {
  constructor(
    public id?: number,
    public description?: string,
    public descriptionLocal?: string,
    public type?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
