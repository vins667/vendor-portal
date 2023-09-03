import { Moment } from 'moment';
import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { ITermsConditionDetails } from './terms-condition-details.model';

export interface ITermsConditionMaster {
  id?: number;
  applicableDate?: string;
  closedDate?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  reportTypeMaster?: IReportTypeMaster;
  termsConditionDetails?: ITermsConditionDetails[];
}

export class TermsConditionMaster implements ITermsConditionMaster {
  constructor(
    public id?: number,
    public applicableDate?: string,
    public closedDate?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public reportTypeMaster?: IReportTypeMaster,
    public termsConditionDetails?: ITermsConditionDetails[]
  ) {}
}
