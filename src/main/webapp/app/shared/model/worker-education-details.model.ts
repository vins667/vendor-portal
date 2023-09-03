import { IEducationMaster } from 'app/shared/model//education-master.model';
import { IEducationTypeMaster } from 'app/shared/model//education-type-master.model';
import { IInstituteMaster } from 'app/shared/model//institute-master.model';
import { IRecruitmentCountryMaster } from 'app/shared/model//recruitment-country-master.model';
import { IRecruitmentStateMaster } from 'app/shared/model//recruitment-state-master.model';
import { IRecruitmentDistrict } from 'app/shared/model//recruitment-district.model';
import { IRecruitmentCityMaster } from 'app/shared/model//recruitment-city-master.model';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerEducationDetails {
  id?: number;
  duration?: string;
  passingYear?: string;
  educationMaster?: IEducationMaster;
  educationTypeMaster?: IEducationTypeMaster;
  instituteMaster?: IInstituteMaster;
  recruitmentCountryMaster?: IRecruitmentCountryMaster;
  recruitmentStateMaster?: IRecruitmentStateMaster;
  recruitmentDistrict?: IRecruitmentDistrict;
  recruitmentCityMaster?: IRecruitmentCityMaster;
  workerJoining?: IWorkerJoining;
  recruitmentstatemasters?: IRecruitmentStateMaster[];
  recruitmentdistricts?: IRecruitmentDistrict[];
  recruitmentcitymasters?: IRecruitmentCityMaster[];
}

export class WorkerEducationDetails implements IWorkerEducationDetails {
  constructor(
    public id?: number,
    public duration?: string,
    public passingYear?: string,
    public educationMaster?: IEducationMaster,
    public educationTypeMaster?: IEducationTypeMaster,
    public instituteMaster?: IInstituteMaster,
    public recruitmentCountryMaster?: IRecruitmentCountryMaster,
    public recruitmentStateMaster?: IRecruitmentStateMaster,
    public recruitmentDistrict?: IRecruitmentDistrict,
    public recruitmentCityMaster?: IRecruitmentCityMaster,
    public workerJoining?: IWorkerJoining,
    public recruitmentstatemasters?: IRecruitmentStateMaster[],
    public recruitmentdistricts?: IRecruitmentDistrict[],
    public recruitmentcitymasters?: IRecruitmentCityMaster[]
  ) {}
}
