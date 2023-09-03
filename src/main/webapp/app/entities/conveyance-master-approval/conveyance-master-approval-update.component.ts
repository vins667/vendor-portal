import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ConveyanceMasterApprovalService } from './conveyance-master-approval.service';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { JhiAlertService } from 'ng-jhipster';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IConveyanceMasterDetails } from 'app/shared/model/conveyance-master-details.model';
import * as FileSaver from 'file-saver';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
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
  selector: 'jhi-conveyance-master-approval-update',
  templateUrl: './conveyance-master-approval-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ConveyanceMasterApprovalUpdateComponent implements OnInit {
  isSaving: boolean;
  conveyanceMasters: IConveyanceMaster;
  conveyanceDate: any;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected conveyanceMasterApprovalService: ConveyanceMasterApprovalService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ conveyanceMaster }) => {
      this.conveyanceMasters = conveyanceMaster;
    });
  }

  previousState() {
    window.history.back();
  }

  download(conveyanceMasterDetails: IConveyanceMasterDetails): any {
    this.conveyanceMasterApprovalService.download(conveyanceMasterDetails.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${conveyanceMasterDetails.attachDisplayFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  saveApproved() {
    this.snotifyService.confirm('Are you sure to Approve?', 'Confirm', {
      timeout: 2500,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.save('A', toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  saveReject() {
    this.conveyanceMasters.flag = 'R';
    this.snotifyService.confirm('Are you sure to Reject?', 'Confirm', {
      timeout: 2500,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.save('R', toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  save(flag: string, toast: any) {
    this.conveyanceMasters.flag = flag;
    if (this.conveyanceMasters.id !== undefined) {
      this.conveyanceMasterApprovalService.update(this.conveyanceMasters).subscribe((result: HttpResponse<IConveyanceMaster>) => {
        this.snotifyService.remove(toast.id);
        if (flag === 'A') {
          this.snotifyService.success('Approve successfully!!!', '', toastConfig);
        } else if (flag === 'R') {
          this.snotifyService.success('Rejected successfully!!!', '', toastConfig);
        }
        this.previousState();
      });
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConveyanceMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.snotifyService.success('Approve successfully!!!', '', toastConfig);
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
