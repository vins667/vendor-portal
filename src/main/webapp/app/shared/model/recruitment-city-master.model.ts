import { Moment } from 'moment';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';

export interface IRecruitmentCityMaster {
  id?: number;
  code?: string;
  description?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  recruitmentDistrict?: IRecruitmentDistrict;
}

export class RecruitmentCityMaster implements IRecruitmentCityMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public recruitmentDistrict?: IRecruitmentDistrict
  ) {}
}
