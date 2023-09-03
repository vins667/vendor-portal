import { Moment } from 'moment';
import { IPollMaster } from 'app/shared/model//poll-master.model';
import { IPollDetails } from 'app/shared/model//poll-details.model';

export interface IPollEmployeeDetails {
  id?: number;
  createdBy?: string;
  createdDate?: Moment;
  pollMaster?: IPollMaster;
  pollDetails?: IPollDetails;
}

export class PollEmployeeDetails implements IPollEmployeeDetails {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public pollMaster?: IPollMaster,
    public pollDetails?: IPollDetails
  ) {}
}
