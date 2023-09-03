import { Moment } from 'moment';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerJobsDetails {
  id?: number;
  companyName?: string;
  fromDate?: Moment;
  toDate?: Moment;
  exp?: number;
  designation?: string;
  reasonLeavig?: string;
  createdBy?: string;
  createdDate?: Moment;
  workerJoining?: IWorkerJoining;
}

export class WorkerJobsDetails implements IWorkerJobsDetails {
  constructor(
    public id?: number,
    public companyName?: string,
    public fromDate?: Moment,
    public toDate?: Moment,
    public exp?: number,
    public designation?: string,
    public reasonLeavig?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public workerJoining?: IWorkerJoining
  ) {}
}
