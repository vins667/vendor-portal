import { Moment } from 'moment';

export interface IEvent {
  id?: number;
  eventFrom?: Moment;
  eventTo?: Moment;
  eventTitle?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class Event implements IEvent {
  constructor(
    public id?: number,
    public eventFrom?: Moment,
    public eventTo?: Moment,
    public eventTitle?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
