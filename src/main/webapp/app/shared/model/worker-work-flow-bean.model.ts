import { IWorkerWorkFlow } from './worker-work-flow.model';

export interface IWorkerWorkFlowBean {
  id?: number;
  mockId?: number;
  empCode?: string;
  empName?: string;
  allowEntry?: boolean;
  authType?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  userType?: string;
  recStatus?: string;
  workerWorkFlows?: IWorkerWorkFlow[];
}

export class WorkerWorkFlowBean implements IWorkerWorkFlowBean {
  constructor(
    public id?: number,
    public mockId?: number,
    public empCode?: string,
    public empName?: string,
    public allowEntry?: boolean,
    public authType?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public userType?: string,
    public recStatus?: string,
    public workerWorkFlows?: IWorkerWorkFlow[]
  ) {}
}
