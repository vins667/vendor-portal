import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IEmployeeInformationUpdateAprv {
  id?: number;
  correspondenceAddress?: string;
  mobileNumber?: string;
  imagePath?: string;
  oldCorrespondenceAddress?: string;
  oldMobileNumber?: string;
  oldImagePath?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  flag?: string;
  userCode?: IUser;
}

export class EmployeeInformationUpdateAprv implements IEmployeeInformationUpdateAprv {
  constructor(
    public id?: number,
    public correspondenceAddress?: string,
    public mobileNumber?: string,
    public imagePath?: string,
    public oldCorrespondenceAddress?: string,
    public oldMobileNumber?: string,
    public oldImagePath?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public flag?: string,
    public userCode?: IUser
  ) {}
}
