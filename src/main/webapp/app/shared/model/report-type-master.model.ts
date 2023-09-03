import { Moment } from 'moment';

export interface IReportTypeMaster {
  id?: number;
  code?: string;
  desc?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class ReportTypeMaster implements IReportTypeMaster {
  constructor(
    public id?: number,
    public code?: string,
    public desc?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
