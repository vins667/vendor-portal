import { Moment } from 'moment';
import { IEventAccessId } from './event-access-id.model';

export interface IEventAccess {
  id?: IEventAccessId;
  accessName?: string;
  flag?: string;
}

export class EventAccess implements IEventAccess {
  constructor(public id?: IEventAccessId, public accessName?: string, public flag?: string) {}
}
