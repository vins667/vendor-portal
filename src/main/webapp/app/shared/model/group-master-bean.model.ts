import { Moment } from 'moment';
import { ITdsGroupDetails } from './tds-group-details.model';
import { IPreviousEmploymentDetailBean } from './previous-employment-detail-bean.model';
export interface IGroupMasterBeans {
  id?: number;
  year?: number;
  groupCode?: string;
  groupDescription?: string;
  groupLimit?: number;
  groupOrder?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  totalAmount?: number;
  exemptAmount?: number;
  expend?: boolean;
  previousEmploymentDetailBeans?: IPreviousEmploymentDetailBean[];
  tdsGroupDetailsBean?: ITdsGroupDetails[];
}

export class GroupMasterBeans implements IGroupMasterBeans {
  constructor(
    public id?: number,
    public year?: number,
    public groupCode?: string,
    public groupDescription?: string,
    public groupLimit?: number,
    public groupOrder?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public totalAmount?: number,
    public exemptAmount?: number,
    public expend?: boolean,
    public tdsGroupDetailsBean?: ITdsGroupDetails[],
    public previousEmploymentDetailBeans?: IPreviousEmploymentDetailBean[]
  ) {}
}
