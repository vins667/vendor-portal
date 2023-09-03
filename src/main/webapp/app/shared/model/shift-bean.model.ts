import { Moment } from 'moment';

export interface IShiftBean {
  todate?: Moment;
}
export class ShiftBean implements IShiftBean {
  constructor(public todate?: Moment) {}
}
