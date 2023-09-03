import { Moment } from 'moment';
export interface IMmrReport {
  id?: number;
  dateFrom?: Moment;
  dateTo?: Moment;
  factoryCode?: string;
}

export class MmrReport implements IMmrReport {
  constructor(public id?: number, public dateFrom?: Moment, public dateTo?: Moment, public factoryCode?: string) {}
}
