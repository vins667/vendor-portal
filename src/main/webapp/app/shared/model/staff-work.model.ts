export interface IStaffWork {
  swCode?: number;
  swDesc?: string;
}
export class StaffWork implements IStaffWork {
  constructor(public swCode?: number, public swDesc?: string) {}
}
