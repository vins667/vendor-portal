import { Moment } from 'moment';

export interface IMobileVersion {
  id?: number;
  version?: string;
  mobileType?: string;
  closedDate?: Moment;
  createdDate?: Moment;
}

export class MobileVersion implements IMobileVersion {
  constructor(
    public id?: number,
    public version?: string,
    public mobileType?: string,
    public closedDate?: Moment,
    public createdDate?: Moment
  ) {}
}
