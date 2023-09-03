import { Moment } from 'moment';

export interface IDoctorSchedule {
  id?: number;
  unitId?: number;
  days?: string;
  timing?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class DoctorSchedule implements IDoctorSchedule {
  constructor(
    public id?: number,
    public unitId?: number,
    public days?: string,
    public timing?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
