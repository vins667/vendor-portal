import { Moment } from 'moment';

export interface IStrengthReportBean {
  id?: number;
  factory?: string;
  subComp?: string;
  line?: string;
  dateFrom?: Moment;
}

export class StrengthReportBean implements IStrengthReportBean {
  constructor(public id?: number, public factory?: string, public subComp?: string, public line?: string, public dateFrom?: Moment) {}
}
