import { Moment } from 'moment';

export interface IWorkerWorkFlow {
  id?: number;
  mockId?: number;
  empCode?: string;
  empName?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  authType?: string;
  authDate?: Moment;
}

export class WorkerWorkFlow implements IWorkerWorkFlow {
  constructor(
    public id?: number,
    public mockId?: number,
    public empCode?: string,
    public empName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public authType?: string,
    public authDate?: Moment
  ) {}
}
