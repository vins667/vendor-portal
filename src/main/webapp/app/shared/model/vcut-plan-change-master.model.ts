import { Moment } from 'moment';

export interface IVcutPlanChangeMaster {
  id?: number;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VcutPlanChangeMaster implements IVcutPlanChangeMaster {
  constructor(
    public id?: number,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
