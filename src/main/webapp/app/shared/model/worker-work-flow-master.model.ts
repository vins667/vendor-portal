import { Moment } from 'moment';
import { IWorkerForwardTypeMaster } from 'app/shared/model//worker-forward-type-master.model';

export interface IWorkerWorkFlowMaster {
  id?: number;
  empCode?: string;
  empName?: string;
  empCodeName?: string;
  forwardCode?: string;
  forwardName?: string;
  forwardCodeName?: string;
  forwardType?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  workerForwardTypeMaster?: IWorkerForwardTypeMaster;
}

export class WorkerWorkFlowMaster implements IWorkerWorkFlowMaster {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public forwardType?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public workerForwardTypeMaster?: IWorkerForwardTypeMaster
  ) {}
}
