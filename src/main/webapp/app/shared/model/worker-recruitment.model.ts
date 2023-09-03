import { Moment } from 'moment';
import { IBankMaster } from 'app/shared/model/bank-master.model';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';
import { IDepartmentMaster } from 'app/shared/model//department-master.model';

export interface IWorkerRecruitment {
  id?: number;
  aadharNo?: string;
  name?: string;
  dob?: any;
  fatherName?: string;
  address?: string;
  panNo?: string;
  bankBranch?: string;
  bankAccNo?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  fileName?: string;
  corespondAddress?: string;
  bankMaster?: IBankMaster;
  designationMaster?: IDesignationMaster;
  departmentMaster?: IDepartmentMaster;
  bankMasterId?: number;
  designationMasterId?: number;
  departmentMasterId?: number;
  factoryCode?: string;
}

export class WorkerRecruitment implements IWorkerRecruitment {
  constructor(
    public id?: number,
    public aadharNo?: string,
    public name?: string,
    public dob?: Moment,
    public fatherName?: string,
    public address?: string,
    public panNo?: string,
    public bankBranch?: string,
    public bankAccNo?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public fileName?: string,
    public corespondAddress?: string,
    public bankMaster?: IBankMaster,
    public designationMaster?: IDesignationMaster,
    public departmentMaster?: IDepartmentMaster,
    public bankMasterId?: number,
    public designationMasterId?: number,
    public departmentMasterId?: number,
    public factoryCode?: string
  ) {}
}
