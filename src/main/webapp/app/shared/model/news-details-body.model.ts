import { Moment } from 'moment';

export interface INewsDetailsBody {
  id?: number;
  newsBody?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class NewsDetailsBody implements INewsDetailsBody {
  constructor(public id?: number, public newsBody?: string, public createdBy?: string, public createdDate?: Moment) {}
}
