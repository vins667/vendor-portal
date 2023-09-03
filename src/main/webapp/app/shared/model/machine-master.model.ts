import { Moment } from 'moment';

export interface IMachineMaster {
  id?: number;
  machineName?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class MachineMaster implements IMachineMaster {
  constructor(
    public id?: number,
    public machineName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
