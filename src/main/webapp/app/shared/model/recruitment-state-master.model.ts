import { Moment } from 'moment';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';

export interface IRecruitmentStateMaster {
  id?: number;
  code?: string;
  description?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  recruitmentCountryMaster?: IRecruitmentCountryMaster;
}

export class RecruitmentStateMaster implements IRecruitmentStateMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public recruitmentCountryMaster?: IRecruitmentCountryMaster
  ) {}
}
