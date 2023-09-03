import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';
import { TdsComputationService } from './tds-computation.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-tds-computation-update',
  templateUrl: './tds-computation-update.component.html',
  styles: [
    `
      .tooltip-inner {
        width: 700px !important;
      }
    `
  ]
})
export class TdsComputationUpdateComponent implements OnInit {
  tdsComputation: ITdsComputation;
  isSaving: boolean;
  isWait = false;

  constructor(protected tdsComputationService: TdsComputationService, protected snotifyService: SnotifyService) {}

  ngOnInit() {
    this.isSaving = false;
    this.tdsComputationService.find().subscribe(tdsComputation => {
      this.tdsComputation = tdsComputation.body;
      if (this.tdsComputation && this.tdsComputation.groupMasterBeans) {
        this.tdsComputation.groupMasterBeans.forEach(groupMaster => {
          let totalVal = 0;
          let ctr = 0;
          groupMaster.tdsGroupDetailsBean.forEach(tdsGroupDetail => {
            ++ctr;
            if (tdsGroupDetail.amount) {
              totalVal += tdsGroupDetail.amount;
            }
          });
          if (ctr === groupMaster.tdsGroupDetailsBean.length) {
            groupMaster.totalAmount = totalVal;
          }
        });
      }
    });
  }

  processSingle(tdsComputation: ITdsComputation) {
    this.snotifyService.confirm('Are you sure to process ' + tdsComputation.name + '?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.processOne(toast, tdsComputation), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  processOne(toast, tdsComputation: ITdsComputation) {
    this.snotifyService.remove(toast.id);
    this.isWait = true;
    this.subscribeToSaveResponse(this.tdsComputationService.process(tdsComputation.id));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<any>>) {
    result.subscribe((res: HttpResponse<any>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.ngOnInit();
    this.snotifyService.success('Process run successfully!', '', toastConfig);
    this.isWait = false;
  }

  protected onSaveError(res: HttpHeaders) {
    this.isWait = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  changeExpend(mmrDepartment) {
    if (mmrDepartment.expend === true) {
      mmrDepartment.expend = false;
    } else {
      mmrDepartment.expend = true;
    }
  }
}
