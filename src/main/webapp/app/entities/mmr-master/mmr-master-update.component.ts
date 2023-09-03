import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMMRMaster } from 'app/shared/model/mmr-master.model';
import { MMRMasterService } from './mmr-master.service';
import * as _moment from 'moment';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { MMRSearchBean } from 'app/shared/model/mmr-search-bean.model';
import { toastConfig } from 'app/core/toast/toast-config';
const moment = (_moment as any).default ? (_moment as any).default : _moment;
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-mmr-master-update',
  templateUrl: './mmr-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class MMRMasterUpdateComponent implements OnInit {
  mMRMaster: IMMRMaster;
  isSaving: boolean;
  monthYear: string;
  createdDate: string;
  lastUpdatedDate: string;
  fact: string;
  totalSal: number;
  totalPcs: number;
  dateFrom: any;
  constructor(
    protected mMRMasterService: MMRMasterService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService
  ) {}

  getTotalDeatils() {
    this.totalSal = 0;
    this.totalPcs = 0;
    if (this.mMRMaster && this.mMRMaster.mmrDepartmentBean) {
      this.mMRMaster.mmrDepartmentBean.forEach(deaprtmentMaster => {
        let totalVal1 = 0;
        let totalVal2 = 0;
        let ctr = 0;
        deaprtmentMaster.mmrDesignationBean.forEach(designationDetail => {
          ++ctr;
          if (designationDetail.salary) {
            totalVal1 += designationDetail.salary;
          }

          if (designationDetail.pcsRate) {
            totalVal2 += designationDetail.pcsRate;
          }
        });
        if (ctr === deaprtmentMaster.mmrDesignationBean.length) {
          deaprtmentMaster.totalSal = totalVal1;
          deaprtmentMaster.totalPcs = totalVal2;
          this.totalSal += totalVal1;
          this.totalPcs += totalVal2;
        }
      });
    }
  }

  openConfirmationDialog() {
    this.snotifyService.confirm('Are you want to copy with previous data?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.override(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  override(toast) {
    const mmrSearchBean = new MMRSearchBean();
    mmrSearchBean.factory = this.mMRMaster.factory;
    mmrSearchBean.monthYear = this.dateFrom != null ? moment(this.dateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.mMRMasterService.copyPrevious(mmrSearchBean).subscribe(
      res => {
        if (res.body.exist !== undefined && res.body.exist === false) {
          this.snotifyService.remove(toast.id);
          this.snotifyService.error('No Data found!', '', toastConfig);
        } else {
          this.snotifyService.remove(toast.id);
          this.mMRMaster = res.body;
          this.getTotalDeatils();
        }
      },
      error => {
        this.snotifyService.remove(toast.id);
      }
    );
  }

  getFactory() {
    const mmrSearchBean = new MMRSearchBean();
    mmrSearchBean.factory = this.mMRMaster.factory;
    mmrSearchBean.monthYear = this.dateFrom != null ? moment(this.dateFrom, DATE_TIME_FORMAT).startOf('day') : null;
    this.mMRMasterService.customFind(mmrSearchBean).subscribe(res => {
      this.mMRMaster = res.body;
      this.getTotalDeatils();
      this.mMRMaster.factory = mmrSearchBean.factory;
      this.mMRMaster.monthYear = moment(mmrSearchBean.monthYear, DATE_TIME_FORMAT).startOf('day');
      if (res.body.exist !== undefined && res.body.exist === false) {
        mmrSearchBean.factory = this.mMRMaster.factory;
        this.openConfirmationDialog();
      }
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.totalPcs = 0;
    this.totalSal = 0;
    this.activatedRoute.data.subscribe(({ mMRMaster }) => {
      this.mMRMaster = mMRMaster;
      this.dateFrom = this.mMRMaster.monthYear != null ? moment(this.mMRMaster.monthYear) : null;
      this.createdDate = this.mMRMaster.createdDate != null ? this.mMRMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate = this.mMRMaster.lastUpdatedDate != null ? this.mMRMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
      this.getTotalDeatils();
    });
  }

  previousState() {
    window.history.back();
  }

  amountCalculate1() {
    if (this.mMRMaster && this.mMRMaster.mmrDepartmentBean) {
      this.mMRMaster.mmrDepartmentBean.forEach(deaprtmentMaster => {
        let totalVal = 0;
        let ctr = 0;
        deaprtmentMaster.mmrDesignationBean.forEach(designationDetail => {
          ++ctr;
          if (designationDetail.salary) {
            totalVal += designationDetail.salary;
            this.totalSal = totalVal;
            deaprtmentMaster.totalSal = totalVal;
          }
        });
        if (ctr === deaprtmentMaster.mmrDesignationBean.length) {
          deaprtmentMaster.totalSal = totalVal;
        }
      });
    }
  }

  amountCalculate2() {
    if (this.mMRMaster && this.mMRMaster.mmrDepartmentBean) {
      this.mMRMaster.mmrDepartmentBean.forEach(deaprtmentMaster => {
        let totalVal = 0;
        let ctr = 0;
        deaprtmentMaster.mmrDesignationBean.forEach(designationDetail => {
          ++ctr;
          if (designationDetail.pcsRate) {
            totalVal += designationDetail.pcsRate;
            deaprtmentMaster.totalPcs = totalVal;
          }
        });
        if (ctr === deaprtmentMaster.mmrDesignationBean.length) {
          deaprtmentMaster.totalPcs = totalVal;
        }
      });
    }
  }

  save() {
    this.isSaving = true;
    this.mMRMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.mMRMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.mMRMaster.id !== undefined && this.mMRMaster.id != null) {
      this.subscribeToSaveResponse(this.mMRMasterService.update(this.mMRMaster));
    } else {
      this.subscribeToSaveResponse(this.mMRMasterService.create(this.mMRMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMMRMaster>>) {
    result.subscribe((res: HttpResponse<IMMRMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    // this.snotifyService.success('Save successfully!!!', '', toastConfig);
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  changeExpend(groupMasterBean) {
    if (groupMasterBean.expend === true) {
      groupMasterBean.expend = false;
    } else {
      groupMasterBean.expend = true;
    }
  }
}
