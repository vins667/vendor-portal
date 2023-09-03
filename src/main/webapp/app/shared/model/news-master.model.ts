import { Moment } from 'moment';

export interface INewsMaster {
  id?: number;
  newsName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class NewsMaster implements INewsMaster {
  constructor(public id?: number, public newsName?: string, public flag?: string, public createdBy?: string, public createdDate?: Moment) {}
}
