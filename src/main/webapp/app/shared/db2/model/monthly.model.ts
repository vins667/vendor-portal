import { Days } from 'app/shared/db2/model/days.model';

export interface IMonthly {
  month?: string;
  daysBean?: Days;
}
export class Monthly implements IMonthly {
  constructor(public month?: string, public daysBean?: Days) {}
}
