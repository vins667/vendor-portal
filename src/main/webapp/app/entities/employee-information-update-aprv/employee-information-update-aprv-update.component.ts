import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IEmployeeInformationUpdateAprv } from 'app/shared/model/employee-information-update-aprv.model';
import { EmployeeInformationUpdateAprvService } from './employee-information-update-aprv.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { IMessage } from 'app/shared/model/message.model';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-employee-information-update-aprv-update',
  templateUrl: './employee-information-update-aprv-update.component.html'
})
export class EmployeeInformationUpdateAprvUpdateComponent implements OnInit {
  employeeInformationUpdateAprv: IEmployeeInformationUpdateAprv;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected employeeInformationUpdateAprvService: EmployeeInformationUpdateAprvService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ employeeInformationUpdateAprv }) => {
      this.employeeInformationUpdateAprv = employeeInformationUpdateAprv;
      this.createdDate =
        this.employeeInformationUpdateAprv.createdDate != null
          ? this.employeeInformationUpdateAprv.createdDate.format(DATE_TIME_FORMAT)
          : null;
      this.lastUpdatedDate =
        this.employeeInformationUpdateAprv.lastUpdatedDate != null
          ? this.employeeInformationUpdateAprv.lastUpdatedDate.format(DATE_TIME_FORMAT)
          : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  approve() {
    this.snotifyService.confirm('Are you sure to approve?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.approveReject(toast, 'C'), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  reject() {
    this.snotifyService.confirm('Are you sure to reject?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.approveReject(toast, 'R'), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  approveReject(toast, flag: string) {
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.employeeInformationUpdateAprvService.approval(this.employeeInformationUpdateAprv.id, flag));
  }
}
