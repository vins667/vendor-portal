import { IAttendance } from './attendance.model';

export interface ISupervisorEmployeeDetails {
  id?: number;
  cardNo?: string;
  attendanceList?: IAttendance[];
}

export class SupervisorEmployeeDetails implements ISupervisorEmployeeDetails {
  constructor(public id?: number, public cardNo?: string, public attendanceList?: IAttendance[]) {}
}
