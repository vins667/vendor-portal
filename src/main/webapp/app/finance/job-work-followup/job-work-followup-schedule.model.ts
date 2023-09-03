import { IMonthlyBean } from 'app/finance/job-work-followup/monthly-bean.model';

export interface IJobWorkFollowupSchedule {
  finYear?: string;
  schType?: string;
  onDate?: string;
  onDateSecond?: string;
  respReminder?: number;
  level1Reminder?: number;
  level2Reminder?: number;
  jobWorkFollowupId?: number;
  monthlyBeans?: IMonthlyBean[];
}

export class JobWorkFollowupSchedule {
  constructor(
    public finYear?: string,
    public schType?: string,
    public onDate?: string,
    public onDateSecond?: string,
    public respReminder?: number,
    public level1Reminder?: number,
    public level2Reminder?: number,
    public jobWorkFollowupId?: number,
    public monthlyBeans?: IMonthlyBean[]
  ) {}
}
