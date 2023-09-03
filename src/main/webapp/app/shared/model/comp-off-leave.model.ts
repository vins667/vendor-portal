import { Moment } from 'moment';

export interface ICompOffLeave {
  id?: number;
  compOffDate?: Moment;
  compOffDateView?: string;
  balance?: number;
}

export class CompOffLeave implements ICompOffLeave {
  constructor(public id?: number, public compOffDate?: Moment, public compOffDateView?: string, public balance?: number) {}
}
