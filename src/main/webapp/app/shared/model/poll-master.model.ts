import { Moment } from 'moment';
import { IPollDetails } from 'app/shared/model//poll-details.model';
import { IFactoryMaster } from 'app/shared/model//factory-master.model';

export interface IPollMaster {
  id?: number;
  pollText?: string;
  endDate?: Moment;
  flag?: string;
  mailFlag?: boolean;
  notificationFlag?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  approvedBy?: string;
  approvedDate?: Moment;
  pollDetails?: IPollDetails[];
  factoryMasters?: IFactoryMaster[];
}

export class PollMaster implements IPollMaster {
  constructor(
    public id?: number,
    public pollText?: string,
    public endDate?: Moment,
    public flag?: string,
    public mailFlag?: boolean,
    public notificationFlag?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public pollDetails?: IPollDetails[],
    public factoryMasters?: IFactoryMaster[]
  ) {
    this.mailFlag = this.mailFlag || false;
    this.notificationFlag = this.notificationFlag || false;
  }
}
