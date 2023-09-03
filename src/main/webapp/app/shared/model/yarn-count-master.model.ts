import { Moment } from 'moment';

export interface IYarnCountMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastCreatedBy?: string;
  lastCreatedDate?: Moment;
}

export class YarnCountMaster implements IYarnCountMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastCreatedBy?: string,
    public lastCreatedDate?: Moment
  ) {}
}
