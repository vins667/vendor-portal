import { Moment } from 'moment';

export interface IDateSearch {
  date?: Moment;
}

export class DateSearch implements IDateSearch {
  constructor(public date?: Moment) {}
}
