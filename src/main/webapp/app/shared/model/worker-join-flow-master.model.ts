import { Moment } from 'moment';
import { IWorkerForwardTypeMaster } from 'app/shared/model//worker-forward-type-master.model';

export interface IWorkerJoinFlowMaster {
  id?: number;
  empCode?: string;
  empName?: string;
  empCodeName?: string;
  forwardCode?: string;
  forwardName?: string;
  forwardCodeName?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  workerForwardTypeMaster?: IWorkerForwardTypeMaster;
}

export class WorkerJoinFlowMaster implements IWorkerJoinFlowMaster {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public empCodeName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public forwardCodeName?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public workerForwardTypeMaster?: IWorkerForwardTypeMaster
  ) {}
}
