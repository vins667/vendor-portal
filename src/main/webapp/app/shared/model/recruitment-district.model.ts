import { Moment } from 'moment';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';

export interface IRecruitmentDistrict {
  id?: number;
  code?: string;
  description?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  recruitmentStateMaster?: IRecruitmentStateMaster;
}

export class RecruitmentDistrict implements IRecruitmentDistrict {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public recruitmentStateMaster?: IRecruitmentStateMaster
  ) {}
}
