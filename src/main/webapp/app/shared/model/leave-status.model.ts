export interface ILeaveStatus {
  leaveType?: string;
  leaveBalance?: number;
}
export class LeaveStatus implements ILeaveStatus {
  constructor(public leaveType?: string, public leaveBalance?: number) {}
}
