import { Moment } from 'moment';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';
import { IDepartmentMaster } from 'app/shared/model/department-master.model';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { IWorkerFamilyDetails } from 'app/shared/model/worker-family-details.model';
import { IWorkerJobsDetails } from 'app/shared/model/worker-jobs-details.model';
import { IWorkerLanguageDetails } from 'app/shared/model/worker-language-details.model';
import { IWorkerNominationDetails } from 'app/shared/model/worker-nomination-details.model';
import { IBankMaster } from 'app/shared/model/bank-master.model';
import { IWorkerAddressDetails } from 'app/shared/model/worker-address-details.model';
import { IWorkerEducationDetails } from 'app/shared/model/worker-education-details.model';
import { IWorkerReferenceDetails } from 'app/shared/model/worker-reference-details.model';
import { IWorkerDocumentDetails } from 'app/shared/model/worker-document-details.model';
import { IWorkerWorkFlowBean } from 'app/shared/model/worker-work-flow-bean.model';

export interface IWorkerJoining {
  id?: number;
  name?: string;
  guardianName?: string;
  motherName?: string;
  mobileNo?: string;
  esiNo?: string;
  uanNo?: string;
  permanentAddress?: string;
  localAddress?: string;
  supervisorName?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  maritalStatus?: string;
  joinDate?: Moment;
  dob?: any;
  aadharNo?: string;
  panNo?: string;
  bankBranch?: string;
  bankAccNo?: string;
  gender?: string;
  grade?: string;
  ctc?: number;
  designationMasterId?: number;
  departmentMasterId?: number;
  subdeptId?: string;
  email?: string;
  catCode?: string;
  flCode?: string;
  foodCode?: string;
  gCode?: string;
  secCode?: string;
  swCode?: string;
  wCode?: string;
  sftCode?: string;
  salMode?: string;
  punch?: string;
  ot?: string;
  sftType?: string;
  payCode?: string;
  cardNo?: string;
  status?: string;
  allowEntry?: boolean;
  bankMasterId?: number;
  bankMaster?: IBankMaster;
  designationMaster?: IDesignationMaster;
  departmentMaster?: IDepartmentMaster;
  workerRecruitment?: IWorkerRecruitment;
  workerWorkFlowBean?: IWorkerWorkFlowBean;
  workerFamilyDetails?: IWorkerFamilyDetails[];
  workerJobsDetails?: IWorkerJobsDetails[];
  workerLanguageDetails?: IWorkerLanguageDetails[];
  workerNominationDetails?: IWorkerNominationDetails[];
  workerAddressDetails?: IWorkerAddressDetails[];
  workerEducationDetails?: IWorkerEducationDetails[];
  workerReferenceDetails?: IWorkerReferenceDetails[];
  workerDocumentDetails?: IWorkerDocumentDetails[];
}

export class WorkerJoining implements IWorkerJoining {
  constructor(
    public id?: number,
    public name?: string,
    public guardianName?: string,
    public motherName?: string,
    public mobileNo?: string,
    public esiNo?: string,
    public uanNo?: string,
    public permanentAddress?: string,
    public localAddress?: string,
    public supervisorName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public maritalStatus?: string,
    public joinDate?: Moment,
    public dob?: Moment,
    public aadharNo?: string,
    public panNo?: string,
    public bankBranch?: string,
    public bankAccNo?: string,
    public gender?: string,
    public grade?: string,
    public ctc?: number,
    public designationMasterId?: number,
    public departmentMasterId?: number,
    public subdeptId?: string,
    public email?: string,
    public catCode?: string,
    public flCode?: string,
    public foodCode?: string,
    public gCode?: string,
    public secCode?: string,
    public swCode?: string,
    public wCode?: string,
    public sftCode?: string,
    public salMode?: string,
    public punch?: string,
    public ot?: string,
    public sftType?: string,
    public status?: string,
    public allowEntry?: boolean,
    public bankMasterId?: number,
    public bankMaster?: IBankMaster,
    public designationMaster?: IDesignationMaster,
    public departmentMaster?: IDepartmentMaster,
    public workerRecruitment?: IWorkerRecruitment,
    public workerWorkFlowBean?: IWorkerWorkFlowBean,
    public workerFamilyDetails?: IWorkerFamilyDetails[],
    public workerJobsDetails?: IWorkerJobsDetails[],
    public workerLanguageDetails?: IWorkerLanguageDetails[],
    public workerNominationDetails?: IWorkerNominationDetails[],
    public workerAddressDetails?: IWorkerAddressDetails[],
    public workerEducationDetails?: IWorkerEducationDetails[],
    public workerReferenceDetails?: IWorkerReferenceDetails[],
    public workerDocumentDetails?: IWorkerDocumentDetails[]
  ) {}
}
