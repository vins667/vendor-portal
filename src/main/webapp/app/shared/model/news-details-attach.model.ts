import { Moment } from 'moment';

export interface INewsDetailsAttach {
  id?: number;
  attachFile?: string;
  attachDisplayFile?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class NewsDetailsAttach implements INewsDetailsAttach {
  constructor(
    public id?: number,
    public attachFile?: string,
    public attachDisplayFile?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
