import { Moment } from 'moment';

export interface IDesignationMaster {
  id?: number;
  designationCode?: string;
  designationName?: string;
  flowType?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class DesignationMaster implements IDesignationMaster {
  constructor(
    public id?: number,
    public designationCode?: string,
    public designationName?: string,
    public flowType?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
