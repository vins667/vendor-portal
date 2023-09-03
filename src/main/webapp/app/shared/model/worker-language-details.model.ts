import { Moment } from 'moment';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerLanguageDetails {
  id?: number;
  languageMasterId?: number;
  motherTongue?: string;
  createdBy?: string;
  createdDate?: Moment;
  workerJoining?: IWorkerJoining;
}

export class WorkerLanguageDetails implements IWorkerLanguageDetails {
  constructor(
    public id?: number,
    public languageMasterId?: number,
    public motherTongue?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public workerJoining?: IWorkerJoining
  ) {}
}
