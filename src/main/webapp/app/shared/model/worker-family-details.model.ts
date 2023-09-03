import { Moment } from 'moment';
import { IRelationMaster } from 'app/shared/model//relation-master.model';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerFamilyDetails {
  id?: number;
  name?: string;
  age?: number;
  createdBy?: string;
  createdDate?: Moment;
  dob?: Moment;
  occupationMasterId?: number;
  dependency?: string;
  relationMaster?: IRelationMaster;
  workerJoining?: IWorkerJoining;
}

export class WorkerFamilyDetails implements IWorkerFamilyDetails {
  constructor(
    public id?: number,
    public name?: string,
    public age?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public dob?: Moment,
    public occupationMasterId?: number,
    public dependency?: string,
    public relationMaster?: IRelationMaster,
    public workerJoining?: IWorkerJoining
  ) {}
}
