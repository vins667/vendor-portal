import { Moment } from 'moment';

export interface ISmsRegistration {
  id?: number;
  cardNo?: string;
  mobileNumber?: string;
  otp?: string;
  createdBy?: string;
  createdDate?: Moment;
  errorMessage?: string;
  exist?: boolean;
}

export class SmsRegistration implements ISmsRegistration {
  constructor(
    public id?: number,
    public cardNo?: string,
    public mobileNumber?: string,
    public otp?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public errorMessage?: string,
    public exist?: boolean
  ) {}
}
