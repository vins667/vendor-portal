import { Moment } from 'moment';

export interface IMMRSearchBean {
  monthYear?: Moment;
  factory?: string;
}

export class MMRSearchBean implements IMMRSearchBean {
  constructor(public monthYear?: Moment, public factory?: string) {}
}
