import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IPollMaster } from 'app/shared/model/poll-master.model';
import { IHolidayMaster } from 'app/shared/model/holiday-master.model';
import { IAttendance } from 'app/shared/model/attendance.model';
import { ILeaveStatus } from 'app/shared/model/leave-status.model';
import { IQuotes } from 'app/shared/model/quotes.model';
import { IMonthlyNewsData } from 'app/shared/model/monthly-news-data.model';

export interface IDashboard {
  birthdayList?: IEmployeeView[];
  anniversaryList?: IEmployeeView[];
  pollMaster?: IPollMaster;
  holidayMastersList?: IHolidayMaster[];
  attendanceList?: IAttendance[];
  leaveStatusList?: ILeaveStatus[];
  quotes?: IQuotes;
  monthlyNews?: IMonthlyNewsData;
}

export class Dashboard implements IDashboard {
  constructor(
    public birthdayList?: IEmployeeView[],
    public anniversaryList?: IEmployeeView[],
    public pollMaster?: IPollMaster,
    public holidayMastersList?: IHolidayMaster[],
    public attendanceList?: IAttendance[],
    public leaveStatusList?: ILeaveStatus[],
    public quotes?: IQuotes,
    public monthlyNews?: IMonthlyNewsData
  ) {}
}
