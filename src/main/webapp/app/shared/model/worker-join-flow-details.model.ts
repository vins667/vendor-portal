import { Moment } from 'moment';

export interface IWorkerJoinFlowDetails {
  id?: number;
  joiningId?: number;
  empCode?: string;
  empName?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  authType?: string;
  authDate?: Moment;
}

export class WorkerJoinFlowDetails implements IWorkerJoinFlowDetails {
  constructor(
    public id?: number,
    public joiningId?: number,
    public empCode?: string,
    public empName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public authType?: string,
    public authDate?: Moment
  ) {}
}
