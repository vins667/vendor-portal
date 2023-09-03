import { Moment } from 'moment';

export interface IMonthlyNewsData {
  id?: number;
  fileName?: string;
  createdBy?: string;
  createdDate?: Moment;
  closedDate?: Moment;
}

export class MonthlyNewsData implements IMonthlyNewsData {
  constructor(
    public id?: number,
    public fileName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public closedDate?: Moment
  ) {}
}
