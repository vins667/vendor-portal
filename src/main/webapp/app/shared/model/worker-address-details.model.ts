import { IRecruitmentCountryMaster } from 'app/shared/model//recruitment-country-master.model';
import { IRecruitmentStateMaster } from 'app/shared/model//recruitment-state-master.model';
import { IRecruitmentDistrict } from 'app/shared/model//recruitment-district.model';
import { IRecruitmentCityMaster } from 'app/shared/model//recruitment-city-master.model';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerAddressDetails {
  id?: number;
  addressType?: string;
  addressLine1?: string;
  addressLine2?: string;
  addressLine3?: string;
  addressLine4?: string;
  pinCode?: string;
  telephoneNo?: string;
  mobileNo?: string;
  recruitmentCountryMaster?: IRecruitmentCountryMaster;
  recruitmentStateMaster?: IRecruitmentStateMaster;
  recruitmentDistrict?: IRecruitmentDistrict;
  recruitmentCityMaster?: IRecruitmentCityMaster;
  workerJoining?: IWorkerJoining;
  recruitmentstatemasters?: IRecruitmentStateMaster[];
  recruitmentdistricts?: IRecruitmentDistrict[];
  recruitmentcitymasters?: IRecruitmentCityMaster[];
}

export class WorkerAddressDetails implements IWorkerAddressDetails {
  constructor(
    public id?: number,
    public addressType?: string,
    public addressLine1?: string,
    public addressLine2?: string,
    public addressLine3?: string,
    public addressLine4?: string,
    public pinCode?: string,
    public telephoneNo?: string,
    public mobileNo?: string,
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
