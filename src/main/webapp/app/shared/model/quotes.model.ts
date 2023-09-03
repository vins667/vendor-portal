import { Moment } from 'moment';

export interface IQuotes {
  id?: number;
  author?: string;
  title?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class Quotes implements IQuotes {
  constructor(public id?: number, public author?: string, public title?: string, public createdBy?: string, public createdDate?: Moment) {}
}
