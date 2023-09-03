import { Moment } from 'moment';
import { IMMDepartmentBean } from './mmr-department-bean.model';

export interface IMMRMaster {
  id?: number;
  monthYear?: Moment;
  factory?: string;
  department?: string;
  designation?: string;
  userType?: string;
  salary?: number;
  pcsRate?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  exist?: boolean;
  mmrDepartmentBean?: IMMDepartmentBean[];
}

export class MMRMaster implements IMMRMaster {
  constructor(
    public id?: number,
    public monthYear?: Moment,
    public factory?: string,
    public department?: string,
    public designation?: string,
    public userType?: string,
    public salary?: number,
    public pcsRate?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public exist?: boolean,
    public mmrDepartmentBean?: IMMDepartmentBean[]
  ) {}
}
