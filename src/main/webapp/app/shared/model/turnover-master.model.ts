import { Moment } from 'moment';

export interface ITurnoverMaster {
  id?: number;
  turnoverRange?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class TurnoverMaster implements ITurnoverMaster {
  constructor(
    public id?: number,
    public turnoverRange?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
