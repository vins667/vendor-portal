import { Moment } from 'moment';

export interface IRateMaster {
  id?: number;
  rate?: number;
  startDate?: Moment;
  endDate?: Moment;
  vehicleType?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class RateMaster implements IRateMaster {
  constructor(
    public id?: number,
    public rate?: number,
    public vehicleType?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
