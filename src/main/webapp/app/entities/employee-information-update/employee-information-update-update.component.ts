import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IEmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';
import { EmployeeInformationUpdateService } from './employee-information-update.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-employee-information-update-update',
  templateUrl: './employee-information-update-update.component.html'
})
export class EmployeeInformationUpdateUpdateComponent implements OnInit {
  employeeInformationUpdate: IEmployeeInformationUpdate;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  imageFile: File;
  imgURL: any;
  constructor(
    protected employeeInformationUpdateService: EmployeeInformationUpdateService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ employeeInformationUpdate }) => {
      this.employeeInformationUpdate = employeeInformationUpdate;
      this.createdDate =
        this.employeeInformationUpdate.createdDate != null ? this.employeeInformationUpdate.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.employeeInformationUpdate.lastUpdatedDate != null
          ? this.employeeInformationUpdate.lastUpdatedDate.format(DATE_TIME_FORMAT)
          : null;
    });
    if (this.employeeInformationUpdate.id == null) {
      this.employeeInformationUpdateService.customFind().subscribe(res => {
        this.employeeInformationUpdate = res.body;
      });
    }
  }
  uploadImage(event) {
    if (event.target.files.length > 0) {
      const fileName = event.target.files[0].name;
      const fileSize = event.target.files[0].size;
      if (fileSize > 5242880) {
        this.snotifyService.error('File size should not exceed 5MB!!!', '', toastConfig);
        event.target.value = null;
        this.imageFile = null;
      } else {
        if (fileName && fileName.lastIndexOf('.') !== -1) {
          const ext = fileName.substr(fileName.lastIndexOf('.') + 1, fileName.length);
          if (ext.toUpperCase() === 'JPG') {
            if (this.imageFile !== event.target.files[0]) {
              this.imageFile = event.target.files[0];

              const reader = new FileReader();
              reader.readAsDataURL(event.target.files[0]);
              reader.onload = (eventt: ProgressEvent) => {
                this.employeeInformationUpdate.imagePath = (eventt.target as FileReader).result;
              };
            }
          } else {
            this.snotifyService.error('Pdf File Only!!!', '', toastConfig);
            event.target.value = null;
            this.imageFile = null;
            // console.log(event.target);
          }
        } else {
          this.snotifyService.error('Not Valid File!!!', '', toastConfig);
          event.target.value = null;
          this.imageFile = null;
        }
      }
    }
  }
  previousState() {
    window.history.back();
  }

  save() {
    if (
      this.employeeInformationUpdate.correspondenceAddress !== this.employeeInformationUpdate.oldCorrespondenceAddress ||
      this.employeeInformationUpdate.mobileNumber !== this.employeeInformationUpdate.oldMobileNumber ||
      this.employeeInformationUpdate.imagePath
    ) {
      this.isSaving = true;
      this.employeeInformationUpdate.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
      this.employeeInformationUpdate.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
      if (this.employeeInformationUpdate.id !== undefined && this.employeeInformationUpdate.id !== null) {
        this.subscribeToSaveResponse(this.employeeInformationUpdateService.update(this.imageFile, this.employeeInformationUpdate));
      } else {
        this.subscribeToSaveResponse(this.employeeInformationUpdateService.create(this.imageFile, this.employeeInformationUpdate));
      }
    } else {
      this.snotifyService.success('Email Resend Successfully', '', toastConfig);
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeeInformationUpdate>>) {
    result.subscribe(
      (res: HttpResponse<IEmployeeInformationUpdate>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError(res.headers)
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }
}
