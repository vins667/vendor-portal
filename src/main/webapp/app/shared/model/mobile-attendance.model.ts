import { Moment } from 'moment';

export interface IMobileAttendance {
  id?: number;
  cardNo?: string;
  attendanceDate?: Moment;
  latitude?: string;
  longitude?: string;
  fileName?: string;
  remarks?: string;
  attendanceType?: string;
  factoryCode?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class MobileAttendance implements IMobileAttendance {
  constructor(
    public id?: number,
    public cardNo?: string,
    public attendanceDate?: Moment,
    public latitude?: string,
    public longitude?: string,
    public fileName?: string,
    public remarks?: string,
    public attendanceType?: string,
    public factoryCode?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
