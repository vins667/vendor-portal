import { Moment } from 'moment';

export interface ILeaveSearch {
  empCode?: string;
  hodCode?: string;
  dateType?: string;
  factory?: string;
  leaveDateFrom?: Moment;
  leaveDateTo?: Moment;
  leaveStatus?: string;
  size?: number;
  pageNo?: number;
}

export class LeaveSearch implements ILeaveSearch {
  constructor(
    public empCode?: string,
    public hodCode?: string,
    public dateType?: string,
    public factory?: string,
    public leaveDateFrom?: Moment,
    public leaveDateTo?: Moment,
    public leaveStatus?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
