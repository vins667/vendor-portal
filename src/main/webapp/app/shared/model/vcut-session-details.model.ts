import { Moment } from 'moment';
import { IVcutSessionMaster } from 'app/shared/model//vcut-session-master.model';

export interface IVcutSessionDetails {
  id?: number;
  startTime?: string;
  endTime?: string;
  type?: string;
  duration?: number;
  order?: number;
  cumulativeMins?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  planQuantity?: number;
  displayFlag?: string;
  sessionId?: number;
  vcutStylePlanUploadId?: number;
}

export class VcutSessionDetails implements IVcutSessionDetails {
  constructor(
    public id?: number,
    public startTime?: string,
    public endTime?: string,
    public type?: string,
    public duration?: number,
    public order?: number,
    public cumulativeMins?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public planQuantity?: number,
    public displayFlag?: string,
    public sessionId?: number,
    public vcutStylePlanUploadId?: number
  ) {}
}
