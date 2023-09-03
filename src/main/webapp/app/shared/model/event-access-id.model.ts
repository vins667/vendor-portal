import { Moment } from 'moment';

export interface IEventAccessId {
  cardNo?: string;
  accessCardNo?: string;
}

export class EventAccessId implements IEventAccessId {
  constructor(public cardNo?: string, public accessCardNo?: string) {}
}
