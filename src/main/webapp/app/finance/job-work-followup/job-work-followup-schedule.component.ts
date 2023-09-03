import { Component, Input, OnInit } from '@angular/core';
import { FinfinancialyearService } from 'app/shared/db2/service/finfinancialyear.service';
import { DatePipe } from '@angular/common';
import { IJobWorkFollowupSchedule, JobWorkFollowupSchedule } from 'app/finance/job-work-followup/job-work-followup-schedule.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JobWorkFollowupService } from 'app/finance/job-work-followup/job-work-followup.service';
import { IMonthlyBean } from 'app/finance/job-work-followup/monthly-bean.model';
import * as moment from 'moment';
import { SnotifyService } from 'ng-snotify';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';
import { IJobWorkFollowup } from 'app/finance/job-work-followup/job-work-followup.model';

@Component({
  selector: 'jhi-job-work-followup-schedule',
  templateUrl: './job-work-followup-schedule-component.html'
})
export class JobWorkFollowupScheduleComponent implements OnInit {
  @Input() jobWorkFollowup: IJobWorkFollowup;
  jobWorkFollowupSchedule: IJobWorkFollowupSchedule;
  monthlyBeans: IMonthlyBean[] = [];
  chooseDays: any = [];
  chooseDaysSecond: any = [];
  years?: any;
  isSaving = false;

  constructor(
    protected finFinancialyearService: FinfinancialyearService,
    protected jobWorkFollowupService: JobWorkFollowupService,
    protected snotifyService: SnotifyService,
    public activeModal: NgbActiveModal,
    public datePipe: DatePipe
  ) {
    this.jobWorkFollowupSchedule = new JobWorkFollowupSchedule();
    this.jobWorkFollowupSchedule.respReminder = 3;
    this.jobWorkFollowupSchedule.level1Reminder = 2;
    this.jobWorkFollowupSchedule.level2Reminder = 1;
  }

  ngOnInit() {
    this.years = [];
    this.finFinancialyearService.query().subscribe(years => {
      years.body.forEach(year => {
        this.years.push({
          label:
            year.longdescription +
            '(' +
            this.datePipe.transform(new Date(year.fromdate), 'dd/MMM/yyyy') +
            '-' +
            this.datePipe.transform(new Date(year.todate), 'dd/MMM/yyyy') +
            ')',
          value: year.id.code
        });
      });
      this.jobWorkFollowupSchedule.finYear = String(years.body[0].id.code);
      this.yearChange();
    });
  }

  yearChange(): void {
    if (this.jobWorkFollowupSchedule.finYear) {
      this.jobWorkFollowupService
        .fetchMonths(this.jobWorkFollowupSchedule.finYear, this.jobWorkFollowup.id)
        .subscribe(jobWorkFollowupSchedule => {
          this.jobWorkFollowupSchedule.schType = String(jobWorkFollowupSchedule.body.schType);
          this.chooseDate();
          this.jobWorkFollowupSchedule.onDate = String(jobWorkFollowupSchedule.body.onDate);
          this.jobWorkFollowupSchedule.onDateSecond = String(jobWorkFollowupSchedule.body.onDateSecond);
          this.jobWorkFollowupSchedule.respReminder = jobWorkFollowupSchedule.body.respReminder;
          this.jobWorkFollowupSchedule.level1Reminder = jobWorkFollowupSchedule.body.level1Reminder;
          this.jobWorkFollowupSchedule.level2Reminder = jobWorkFollowupSchedule.body.level2Reminder;
          this.monthlyBeans = jobWorkFollowupSchedule.body.monthlyBeans;
        });
    }
  }

  chooseDate(): void {
    if (this.jobWorkFollowupSchedule.schType === 'M') {
      this.chooseDays = [];
      this.chooseDays.push({ label: '1st Day of Month', value: '1' });
      this.chooseDays.push({ label: '2st Day of Month', value: '2' });
      this.chooseDays.push({ label: '3st Day of Month', value: '3' });
      this.chooseDays.push({ label: '4st Day of Month', value: '4' });
      this.chooseDays.push({ label: '5st Day of Month', value: '5' });
      this.chooseDays.push({ label: '15th Day of Month', value: '15' });
      this.chooseDays.push({ label: 'Last Day of Month', value: '31' });
    } else if (this.jobWorkFollowupSchedule.schType === 'W') {
      this.chooseDays = [];
      this.chooseDays.push({ label: 'Every Sunday', value: '0' });
      this.chooseDays.push({ label: 'Every Monday', value: '1' });
      this.chooseDays.push({ label: 'Every Tuesday', value: '2' });
      this.chooseDays.push({ label: 'Every Wednesday', value: '3' });
      this.chooseDays.push({ label: 'Every Thursday', value: '4' });
      this.chooseDays.push({ label: 'Every Friday', value: '5' });
      this.chooseDays.push({ label: 'Every Saturday', value: '6' });
    } else if (this.jobWorkFollowupSchedule.schType === 'Q') {
      this.chooseDays = [];
      this.chooseDays.push({ label: '1st Day of Month', value: '1' });
      this.chooseDays.push({ label: '2st Day of Month', value: '2' });
      this.chooseDays.push({ label: '3st Day of Month', value: '3' });
      this.chooseDays.push({ label: '4st Day of Month', value: '4' });
      this.chooseDays.push({ label: '5st Day of Month', value: '5' });
      this.chooseDays.push({ label: '15th Day of Month', value: '15' });
      this.chooseDays.push({ label: 'Last Day of Month', value: '31' });
    } else if (this.jobWorkFollowupSchedule.schType === 'F') {
      this.chooseDays = [];
      this.chooseDays.push({ label: '1st Day of Month', value: '1' });
      this.chooseDays.push({ label: '2st Day of Month', value: '2' });
      this.chooseDays.push({ label: '3st Day of Month', value: '3' });
      this.chooseDays.push({ label: '4st Day of Month', value: '4' });
      this.chooseDays.push({ label: '5st Day of Month', value: '5' });
      this.chooseDays.push({ label: '10st Day of Month', value: '10' });
      this.chooseDays.push({ label: '15th Day of Month', value: '15' });

      this.chooseDaysSecond = [];
      this.chooseDaysSecond.push({ label: '16st Day of Month', value: '16' });
      this.chooseDaysSecond.push({ label: '17st Day of Month', value: '17' });
      this.chooseDaysSecond.push({ label: '18st Day of Month', value: '18' });
      this.chooseDaysSecond.push({ label: '19st Day of Month', value: '19' });
      this.chooseDaysSecond.push({ label: '20st Day of Month', value: '20' });
      this.chooseDaysSecond.push({ label: '25st Day of Month', value: '25' });
      this.chooseDaysSecond.push({ label: 'Last Day of Month', value: '31' });
    }
    this.jobWorkFollowupSchedule.onDate = undefined;
    this.monthlyBeans.forEach(monthyBean => {
      monthyBean.daysBeans.forEach(dayBean => {
        dayBean.selectDay = false;
      });
    });
  }

  selectDate(): void {
    if (this.jobWorkFollowupSchedule.schType === 'M' && this.jobWorkFollowupSchedule.onDate) {
      this.monthlyBeans.forEach(monthyBean => {
        if (Number(this.jobWorkFollowupSchedule.onDate) === 31) {
          let index = 0;
          monthyBean.daysBeans.forEach(dayBean => {
            ++index;
            if (index === monthyBean.daysBeans.length) {
              dayBean.selectDay = true;
            } else {
              dayBean.selectDay = false;
            }
          });
        } else {
          monthyBean.daysBeans.forEach(dayBean => {
            if (Number(this.jobWorkFollowupSchedule.onDate) === dayBean.day) {
              dayBean.selectDay = true;
            } else {
              dayBean.selectDay = false;
            }
          });
        }
      });
    } else if (this.jobWorkFollowupSchedule.schType === 'W' && this.jobWorkFollowupSchedule.onDate) {
      this.monthlyBeans.forEach(monthyBean => {
        monthyBean.daysBeans.forEach(dayBean => {
          const myMomentObject = moment(dayBean.date, 'DD-MMM-yy');
          if (Number(this.jobWorkFollowupSchedule.onDate) === myMomentObject.day()) {
            dayBean.selectDay = true;
          } else {
            dayBean.selectDay = false;
          }
        });
      });
    } else if (this.jobWorkFollowupSchedule.schType === 'Q' && this.jobWorkFollowupSchedule.onDate) {
      this.monthlyBeans.forEach(monthyBean => {
        if (
          monthyBean.month.startsWith('Apr') ||
          monthyBean.month.startsWith('Jul') ||
          monthyBean.month.startsWith('Oct') ||
          monthyBean.month.startsWith('Jan')
        ) {
          if (Number(this.jobWorkFollowupSchedule.onDate) === 31) {
            let index = 0;
            monthyBean.daysBeans.forEach(dayBean => {
              ++index;
              if (index === monthyBean.daysBeans.length) {
                dayBean.selectDay = true;
              } else {
                dayBean.selectDay = false;
              }
            });
          } else {
            monthyBean.daysBeans.forEach(dayBean => {
              if (Number(this.jobWorkFollowupSchedule.onDate) === dayBean.day) {
                dayBean.selectDay = true;
              } else {
                dayBean.selectDay = false;
              }
            });
          }
        } else {
          monthyBean.daysBeans.forEach(dayBean => {
            dayBean.selectDay = false;
          });
        }
      });
    } else if (
      this.jobWorkFollowupSchedule.schType === 'F' &&
      this.jobWorkFollowupSchedule.onDate &&
      this.jobWorkFollowupSchedule.onDateSecond
    ) {
      this.monthlyBeans.forEach(monthyBean => {
        monthyBean.daysBeans.forEach(dayBean => {
          if (Number(this.jobWorkFollowupSchedule.onDate) === dayBean.day) {
            dayBean.selectDay = true;
          } else {
            dayBean.selectDay = false;
          }
        });
      });
      this.monthlyBeans.forEach(monthyBean => {
        if (Number(this.jobWorkFollowupSchedule.onDateSecond) === 31) {
          let index = 0;
          monthyBean.daysBeans.forEach(dayBean => {
            ++index;
            if (index === monthyBean.daysBeans.length) {
              dayBean.selectDay = true;
            } else if (Number(this.jobWorkFollowupSchedule.onDate) !== dayBean.day) {
              dayBean.selectDay = false;
            }
          });
        } else {
          monthyBean.daysBeans.forEach(dayBean => {
            if (Number(this.jobWorkFollowupSchedule.onDateSecond) === dayBean.day) {
              dayBean.selectDay = true;
            } else if (Number(this.jobWorkFollowupSchedule.onDate) !== dayBean.day) {
              dayBean.selectDay = false;
            }
          });
        }
      });
    }
  }

  save(): void {
    if (this.validate() === true) {
      this.isSaving = true;
      this.jobWorkFollowupSchedule.monthlyBeans = this.monthlyBeans;
      this.jobWorkFollowupSchedule.jobWorkFollowupId = this.jobWorkFollowup.id;
      this.subscribeToSaveResponse(this.jobWorkFollowupService.saveSchedule(this.jobWorkFollowupSchedule));
    }
  }

  validate(): any {
    if (!this.jobWorkFollowupSchedule.finYear) {
      this.snotifyService.error('Financial year must not blank.', 'Error', toastConfig);
      return false;
    } else if (!this.jobWorkFollowupSchedule.schType) {
      this.snotifyService.error('Type must not blank.', 'Error', toastConfig);
      return false;
    } else if (!this.jobWorkFollowupSchedule.onDate) {
      this.snotifyService.error('Day must not blank.', 'Error', toastConfig);
      return false;
    } else if (!this.jobWorkFollowupSchedule.respReminder) {
      this.snotifyService.error('Responsible reminder must not blank.', 'Error', toastConfig);
      return false;
    } else if (!this.jobWorkFollowupSchedule.level1Reminder) {
      this.snotifyService.error('Level 1 Reminder must not blank.', 'Error', toastConfig);
      return false;
    } else if (!this.jobWorkFollowupSchedule.onDate) {
      this.snotifyService.error('Level 2 Reminder must not blank.', 'Error', toastConfig);
      return false;
    } else {
      return true;
    }
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobWorkFollowupSchedule>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', 'Success', toastConfig);
    this.activeModal.dismiss('cancel');
  }

  protected onSaveError() {
    this.snotifyService.success('Not Save', 'Success', toastConfig);
    this.isSaving = false;
  }
}
