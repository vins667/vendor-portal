import { Moment } from 'moment';
import { ILeaveTypeMaster } from 'app/shared/model//leave-type-master.model';
import { ILeaveSubTypeMaster } from 'app/shared/model//leave-sub-type-master.model';
import { IUser } from 'app/core/user/user.model';

export interface ILeaveEntryHr {
  id?: number;
  userCode?: IUser;
  leaveDateFrom?: Moment;
  leaveDateTo?: Moment;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  hodApprovedBy?: string;
  hodApprovedDate?: Moment;
  hrApprovedBy?: string;
  hrApprovedDate?: Moment;
  reason?: string;
  noDays?: number;
  leaveTimeFrom?: Moment;
  leaveTimeTo?: Moment;
  hodRemarks?: string;
  hrRemarks?: string;
  missPunchType?: string;
  processFlag?: string;
  leaveTypeMaster?: ILeaveTypeMaster;
  leaveSubTypeMaster?: ILeaveSubTypeMaster;
}

export class LeaveEntryHr implements ILeaveEntryHr {
  constructor(
    public id?: number,
    public userCode?: IUser,
    public leaveDateFrom?: Moment,
    public leaveDateTo?: Moment,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public hodApprovedBy?: string,
    public hodApprovedDate?: Moment,
    public hrApprovedBy?: string,
    public hrApprovedDate?: Moment,
    public reason?: string,
    public noDays?: number,
    public leaveTimeFrom?: Moment,
    public leaveTimeTo?: Moment,
    public hodRemarks?: string,
    public hrRemarks?: string,
    public missPunchType?: string,
    public processFlag?: string,
    public leaveTypeMaster?: ILeaveTypeMaster,
    public leaveSubTypeMaster?: ILeaveSubTypeMaster
  ) {}
}
