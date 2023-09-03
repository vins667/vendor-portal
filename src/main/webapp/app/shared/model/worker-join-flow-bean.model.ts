import { IWorkerJoinFlowDetails } from './worker-join-flow-details.model';

export interface IWorkerJoinFlowBean {
  id?: number;
  joiningId?: number;
  empCode?: string;
  empName?: string;
  allowEntry?: boolean;
  authType?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  userType?: string;
  recStatus?: string;
  workerJoinFlowDetails?: IWorkerJoinFlowDetails[];
}

export class WorkerJoinFlowBean implements IWorkerJoinFlowBean {
  constructor(
    public id?: number,
    public joiningId?: number,
    public empCode?: string,
    public empName?: string,
    public allowEntry?: boolean,
    public authType?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public userType?: string,
    public recStatus?: string,
    public workerJoinFlowDetails?: IWorkerJoinFlowDetails[]
  ) {}
}
