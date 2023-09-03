import { Moment } from 'moment';
import { IJobWorkFollowup } from 'app/finance/job-work-followup/job-work-followup.model';

export interface IJobWorkFollowupDetails {
  id?: number;
  finYear?: number;
  jobWorkDate?: any;
  submitDate?: any;
  submitBy?: string;
  remarks?: string;
  jobWorkFollowup?: IJobWorkFollowup;
}

export class JobWorkFollowupDetails implements IJobWorkFollowupDetails {
  constructor(
    public id?: number,
    public finYear?: number,
    public jobWorkDate?: any,
    public submitDate?: any,
    public submitBy?: string,
    public remarks?: string,
    public jobWorkFollowup?: IJobWorkFollowup
  ) {}
}
