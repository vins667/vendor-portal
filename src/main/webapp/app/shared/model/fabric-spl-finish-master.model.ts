import { Moment } from 'moment';

export interface IFabricSplFinishMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class FabricSplFinishMaster implements IFabricSplFinishMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
