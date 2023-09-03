import { Moment } from 'moment';

export interface IVcutStyleImage {
  id?: number;
  style?: string;
  frontImage?: string;
  backImage?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VcutStyleImage implements IVcutStyleImage {
  constructor(
    public id?: number,
    public style?: string,
    public frontImage?: string,
    public backImage?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
