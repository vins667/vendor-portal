import { Moment } from 'moment';
import { ILeaveMaster } from 'app/shared/model//leave-master.model';

export interface ILeaveMasterRemarksDetails {
  id?: number;
  empCode?: string;
  empName?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  status?: string;
  createdDate?: Moment;
  leaveMaster?: ILeaveMaster;
}

export class LeaveMasterRemarksDetails implements ILeaveMasterRemarksDetails {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public remarks?: string,
    public status?: string,
    public createdDate?: Moment,
    public leaveMaster?: ILeaveMaster
  ) {}
}
