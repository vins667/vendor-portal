import { Moment } from 'moment';

export interface IIgnoreSoftwareMaster {
  id?: number;
  swName?: string;
  swPublisher?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class IgnoreSoftwareMaster implements IIgnoreSoftwareMaster {
  constructor(
    public id?: number,
    public swName?: string,
    public swPublisher?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
