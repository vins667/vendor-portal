import { Moment } from 'moment';

export interface IAttendance {
  attendanceDate?: Moment;
  inTime?: string;
  outTime?: string;
  msg?: string;
  flag?: string;
}

export class Attendance implements IAttendance {
  constructor(public attendanceDate?: Moment, public inTime?: string, public outTime?: string, public msg?: string, public flag?: string) {}
}
