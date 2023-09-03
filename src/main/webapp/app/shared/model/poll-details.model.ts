import { Moment } from 'moment';
import { IPollMaster } from 'app/shared/model//poll-master.model';

export interface IPollDetails {
  id?: number;
  pollOption?: string;
  createdBy?: string;
  createdDate?: Moment;
  pollMaster?: IPollMaster;
  index?: number;
}

export class PollDetails implements IPollDetails {
  constructor(
    public id?: number,
    public pollOption?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public pollMaster?: IPollMaster,
    public index?: number
  ) {}
}
