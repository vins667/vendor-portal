import { Moment } from 'moment';
import { IVcutSessionDetails } from './vcut-session-details.model';

export interface IVcutSessionMaster {
  id?: number;
  planName?: string;
  dayStartTime?: Moment;
  hours?: number;
  minutes?: number;
  totalMinsPerDay?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  vcutSessionDetails?: IVcutSessionDetails[];
}

export class VcutSessionMaster implements IVcutSessionMaster {
  constructor(
    public id?: number,
    public planName?: string,
    public dayStartTime?: Moment,
    public hours?: number,
    public minutes?: number,
    public totalMinsPerDay?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public vcutSessionDetails?: IVcutSessionDetails[]
  ) {}
}
