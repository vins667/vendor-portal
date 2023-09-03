import { Moment } from 'moment';

export interface IDepartmentMaster {
  id?: number;
  depCode?: string;
  deptCode?: string;
  deptDesc?: string;
  desc1?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class DepartmentMaster implements IDepartmentMaster {
  constructor(
    public id?: number,
    public depCode?: string,
    public deptCode?: string,
    public deptDesc?: string,
    public desc1?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
