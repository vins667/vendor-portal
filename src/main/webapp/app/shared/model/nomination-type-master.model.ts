import { Moment } from 'moment';

export interface INominationTypeMaster {
  id?: number;
  code?: string;
  description?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class NominationTypeMaster implements INominationTypeMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
