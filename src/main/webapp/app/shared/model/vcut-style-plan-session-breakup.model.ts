import { Moment } from 'moment';
import { IVcutStylePlanSessionBreakupId } from './vcut-style-plan-session-breakup-id.model';

export interface IVcutStylePlanSessionBreakup {
  id?: IVcutStylePlanSessionBreakupId;
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
}

export class VcutStylePlanSessionBreakup implements IVcutStylePlanSessionBreakup {
  constructor(
    public id?: IVcutStylePlanSessionBreakupId,
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
    public sessionId?: number
  ) {}
}
