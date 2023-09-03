import { Moment } from 'moment';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerReferenceDetails {
  id?: number;
  name?: string;
  organization?: string;
  designation?: string;
  contactNumber?: string;
  contacted?: string;
  createdDate?: Moment;
  createdBy?: string;
  workerJoining?: IWorkerJoining;
}

export class WorkerReferenceDetails implements IWorkerReferenceDetails {
  constructor(
    public id?: number,
    public name?: string,
    public organization?: string,
    public designation?: string,
    public contactNumber?: string,
    public contacted?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public workerJoining?: IWorkerJoining
  ) {}
}
