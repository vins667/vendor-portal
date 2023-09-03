import { Moment } from 'moment';

export interface ILeaveTypeMaster {
  id?: number;
  leaveCode?: string;
  leaveName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class LeaveTypeMaster implements ILeaveTypeMaster {
  constructor(
    public id?: number,
    public leaveCode?: string,
    public leaveName?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
