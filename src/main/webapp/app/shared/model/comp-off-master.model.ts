import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface ICompOffMaster {
  id?: number;
  userCode?: IUser;
  compOffDate?: Moment;
  timeFrom?: string;
  timeTo?: string;
  balance?: number;
  availDate?: Moment;
  hodApprovedBy?: string;
  hodApprovedDate?: Moment;
  createdBy?: string;
  createdDate?: Moment;
  remarks?: string;
  flag?: string;
}

export class CompOffMaster implements ICompOffMaster {
  constructor(
    public id?: number,
    public userCode?: IUser,
    public compOffDate?: Moment,
    public timeFrom?: string,
    public timeTo?: string,
    public balance?: number,
    public availDate?: Moment,
    public hodApprovedBy?: string,
    public hodApprovedDate?: Moment,
    public createdBy?: string,
    public createdDate?: Moment,
    public remarks?: string,
    public flag?: string
  ) {}
}
