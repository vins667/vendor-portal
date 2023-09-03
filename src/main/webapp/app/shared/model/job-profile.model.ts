import { Moment } from 'moment';

export interface IJobProfile {
  id?: number;
  department?: string;
  designation?: string;
  departmentDesc?: string;
  designationDesc?: string;
  fileName?: string;
  filePath?: string;
  url?: any;
  ordering?: number;
  activeProfile?: boolean;
  createdBy?: string;
  createdDate?: Moment;
}

export class JobProfile implements IJobProfile {
  constructor(
    public id?: number,
    public department?: string,
    public designation?: string,
    public departmentDesc?: string,
    public designationDesc?: string,
    public fileName?: string,
    public filePath?: string,
    public url?: any,
    public ordering?: number,
    public activeProfile?: boolean,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
