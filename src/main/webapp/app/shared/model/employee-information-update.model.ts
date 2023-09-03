import { Moment } from 'moment';

export interface IEmployeeInformationUpdate {
  id?: number;
  correspondenceAddress?: string;
  mobileNumber?: string;
  imagePath?: any;
  oldCorrespondenceAddress?: string;
  oldMobileNumber?: string;
  oldImagePath?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  flag?: string;
}

export class EmployeeInformationUpdate implements IEmployeeInformationUpdate {
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
    public flag?: string
  ) {}
}
