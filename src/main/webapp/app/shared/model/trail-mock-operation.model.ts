import { Moment } from 'moment';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { IOperationMaster } from 'app/shared/model/operation-master.model';
import { IMachineMaster } from 'app/shared/model/machine-master.model';

export interface ITrailMockOperation {
  id?: number;
  splMachineKnowledge?: string;
  achiveRating?: number;
  result?: string;
  grade?: string;
  remarks?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  snls?: boolean;
  dnls?: boolean;
  ol?: boolean;
  gradeDescription?: string;
  timeTaken?: string;
  allowEntry?: boolean;
  workerRecruitment?: IWorkerRecruitment;
  operationMasters?: IOperationMaster[];
  machineMasters?: IMachineMaster[];
}

export class TrailMockOperation implements ITrailMockOperation {
  constructor(
    public id?: number,
    public splMachineKnowledge?: string,
    public achiveRating?: number,
    public result?: string,
    public grade?: string,
    public remarks?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public snls?: boolean,
    public dnls?: boolean,
    public ol?: boolean,
    public gradeDescription?: string,
    public timeTaken?: string,
    public allowEntry?: boolean,
    public workerRecruitment?: IWorkerRecruitment,
    public operationMasters?: IOperationMaster[],
    public machineMasters?: IMachineMaster[]
  ) {
    this.snls = this.snls || false;
    this.dnls = this.dnls || false;
    this.ol = this.ol || false;
  }
}
