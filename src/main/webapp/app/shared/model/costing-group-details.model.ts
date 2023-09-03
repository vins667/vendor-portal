import { Moment } from 'moment';
import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';

export interface ICostingGroupDetails {
  id?: number;
  code?: string;
  description?: string;
  valueType?: string;
  masterType?: string;
  ugcType?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  costingGroupMaster?: ICostingGroupMaster;
}

export class CostingGroupDetails implements ICostingGroupDetails {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public valueType?: string,
    public masterType?: string,
    public ugcType?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public costingGroupMaster?: ICostingGroupMaster
  ) {}
}
