import { IDaysBean } from 'app/finance/job-work-followup/days-bean.model';

export interface IMonthlyBean {
  month?: string;
  daysBeans?: IDaysBean[];
}

export class MonthlyBean implements IMonthlyBean {
  constructor(public month?: string, public daysBeans?: IDaysBean[]) {}
}
