import { Moment } from 'moment';

export interface ISalarySearch {
  cardNo?: string;
  dateFrom?: Moment;
  dateTo?: Moment;
  group?: string;
  factory?: string;
  salary?: number;
}

export class SalarySearch implements ISalarySearch {
  constructor(
    public cardNo?: string,
    public dateFrom?: Moment,
    public dateTo?: Moment,
    public group?: string,
    public factory?: string,
    public salary?: number
  ) {}
}
