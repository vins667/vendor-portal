import { Moment } from 'moment';
import { ILeaveTypeMaster } from 'app/shared/model//leave-type-master.model';

export interface ILeaveSubTypeMaster {
  id?: number;
  subTypeCode?: string;
  subTypeName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  leaveTypeMaster?: ILeaveTypeMaster;
}

export class LeaveSubTypeMaster implements ILeaveSubTypeMaster {
  constructor(
    public id?: number,
    public subTypeCode?: string,
    public subTypeName?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public leaveTypeMaster?: ILeaveTypeMaster
  ) {}
}
