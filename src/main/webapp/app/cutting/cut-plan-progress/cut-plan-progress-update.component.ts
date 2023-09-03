import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanProgressService } from './cut-plan-progress.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { MarkerMasterEntryService } from 'app/cutting/marker-master-entry/marker-master-entry.service';
import { IMarkerMasterEntry, MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
import { IMarkerBean } from 'app/shared/db2/model/marker-bean.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IBalance } from 'app/shared/db2/model/balance.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import * as moment from 'moment';
import { ICutPlanProgressEntry } from 'app/shared/model/cut-plan-progress-entry.model';
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'MMMM DD, YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-cut-plan-progress-update',
  templateUrl: './cut-plan-progress-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class CutPlanProgressUpdateComponent implements OnInit {
  isSaving: boolean;
  isProcess: boolean;
  search?: IMaster;
  cutPlanEntry: ICutPlanEntry;
  destinations?: IMaster[];
  colors?: IMaster[];
  fullitemkeydecoders: IFullitemkeydecoder[];
  eventSubscriber?: Subscription;
  markerMasterEntity: IMarkerMasterEntry;
  protected ngbModalRef: NgbModalRef;
  markerBean?: IMarkerBean;
  activeTab?: string;

  constructor(
    protected cutPlanEntryService: CutPlanProgressService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected markerMasterEntryService: MarkerMasterEntryService
  ) {
    this.search = new Master();
    this.fullitemkeydecoders = [];
  }

  ngOnInit() {
    this.isSaving = false;
    this.isProcess = false;
    this.activatedRoute.data.subscribe(({ cutPlanEntry }) => {
      this.cutPlanEntry = cutPlanEntry;
      if (this.cutPlanEntry && this.cutPlanEntry.cutPlanProgressEntryBeans && this.cutPlanEntry.cutPlanProgressEntryBeans.length > 0) {
        this.activeTab = this.cutPlanEntry.cutPlanProgressEntryBeans[0].operationCode;
      }
      if (cutPlanEntry.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.markerMasterEntry;
        this.markerBean = cutPlanEntry.markerBean;
      } else {
        this.markerMasterEntity = new MarkerMasterEntry();
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save(cutPlanProgressEntryBean: ICutPlanProgressEntry) {
    if (this.validate() === true) {
      this.activeTab = cutPlanProgressEntryBean.operationCode;
      this.isSaving = true;
      this.cutPlanEntry.markerMasterEntry = this.markerMasterEntity;
      this.cutPlanEntry.markerBean = this.markerBean;
      this.cutPlanEntry.cutPlanProgressEntry = cutPlanProgressEntryBean;
      if (this.cutPlanEntry.id !== undefined) {
        this.subscribeToSaveResponse(this.cutPlanEntryService.update(this.cutPlanEntry));
      }
    }
  }

  validate(): any {
    if (!this.markerBean || !this.markerMasterEntity) {
      this.snotifyService.error('Detail entry not exist!', '', toastConfig);
      return false;
    } else {
      return true;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICutPlanEntry>>) {
    result.subscribe(data => this.onSaveSuccess(data), () => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<ICutPlanEntry>) {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', '', toastConfig);
    this.cutPlanEntryService.find(result.body.id).subscribe(cutPlanEntry => {
      this.cutPlanEntry = cutPlanEntry.body;
      if (cutPlanEntry.body.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.body.markerMasterEntry;
        this.markerBean = cutPlanEntry.body.markerBean;
      }
    });
    // this.previousState();
  }

  calculateTotal(variable: string): number {
    let value = 0;
    if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'orderQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.orderQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'plannedQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.plannedQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'sizeQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.sizeQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'pliesQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        if (markerEntryDetail.pliesQty) {
          value += markerEntryDetail.pliesQty;
        }
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'actualPliesQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        if (markerEntryDetail.pliesQty) {
          value += markerEntryDetail.actualPliesQty;
        }
      });
    }
    return value;
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  markerCollapse(val?: boolean, markerBeans?: IMarkerBean): void {
    markerBeans.collapse = val;
  }

  moveUp(balance: IBalance, index): void {
    if (index > 0) {
      const tmp = this.markerBean.balances[index - 1];
      this.markerBean.balances[index - 1] = this.markerBean.balances[index];
      this.markerBean.balances[index] = tmp;
    }
  }

  moveDown(balance: IBalance, index): void {
    if (index < this.markerBean.balances.length) {
      const tmp = this.markerBean.balances[index + 1];
      this.markerBean.balances[index + 1] = this.markerBean.balances[index];
      this.markerBean.balances[index] = tmp;
    }
  }

  changeDate(cutPlanProgressEntryBean: ICutPlanProgressEntry): void {
    if (cutPlanProgressEntryBean.startDate && cutPlanProgressEntryBean.endDate && cutPlanProgressEntryBean.noCutters) {
      const startDate = moment(cutPlanProgressEntryBean.startDate)
        .seconds(0)
        .milliseconds(0);
      const endDate = moment(cutPlanProgressEntryBean.endDate)
        .seconds(0)
        .milliseconds(0);
      const minutes = endDate.diff(startDate, 'minutes');
      cutPlanProgressEntryBean.totalHour = (Number((Number(minutes) / 60).toFixed(2)) * Number(cutPlanProgressEntryBean.noCutters)).toFixed(
        2
      );
      this.cutPlanEntry.cutPlanProgressEntryBeans.forEach((cutPlanProgressEntryTemp, index) => {
        if (
          cutPlanProgressEntryBean.operationCode === cutPlanProgressEntryTemp.operationCode &&
          index === this.cutPlanEntry.cutPlanProgressEntryBeans.length - 1
        ) {
          cutPlanProgressEntryBean.lastProgress = 'Y';
        }
      });
    }
  }

  calculateRollDetails(balance: IBalance): void {
    if (balance.actualRollQty && this.markerMasterEntity.length) {
      balance.actualNoPlies = Math.floor(Number(balance.actualRollQty) / Number(this.markerMasterEntity.length));
      balance.actualEndBits = Number(
        (Number(balance.actualRollQty) - Number(balance.actualNoPlies) * Number(this.markerMasterEntity.length)).toFixed(3)
      );
      let noPlies = 0;
      let index = 0;
      this.markerBean.balances.forEach(balanceTemp => {
        noPlies += balanceTemp.actualNoPlies;
        ++index;
      });
      if (index === this.markerBean.balances.length) {
        this.cutPlanEntry.actualNoPlies = noPlies;
        this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
          markerEntryDetail.actualPliesQty = this.cutPlanEntry.actualNoPlies * markerEntryDetail.sizeQty;
        });
      }
    }
  }

  callPost(cutPlanProgressEntryBean: ICutPlanProgressEntry): void {
    this.snotifyService.confirm('Are you sure to post Cut Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.post(cutPlanProgressEntryBean, toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  post(cutPlanProgressEntryBean: ICutPlanProgressEntry, toast): void {
    this.snotifyService.remove(toast.id);
    this.activeTab = cutPlanProgressEntryBean.operationCode;
    this.isProcess = true;
    this.cutPlanEntryService.post(cutPlanProgressEntryBean.id).subscribe(cutPlanEntry => {
      this.cutPlanEntry = cutPlanEntry.body;
      this.isProcess = false;
      if (cutPlanEntry.body.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.body.markerMasterEntry;
        this.markerBean = cutPlanEntry.body.markerBean;
      }
      this.snotifyService.success('Post successfully!', '', toastConfig);
    });
  }
}
