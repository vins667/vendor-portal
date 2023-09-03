import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { VcutStylePlanUploadService } from './vcut-style-plan-upload.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-vcut-style-plan-upload-detail',
  templateUrl: './vcut-style-plan-upload-detail.component.html',
  styleUrls: ['./vcut-style-plan-upload-detail.component.scss']
})
export class VcutStylePlanUploadDetailComponent implements OnInit {
  vcutStylePlanUpload: IVcutStylePlanUpload;
  editForm = this.fb.group({});
  isWait: boolean;
  isSaving: boolean;

  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];

  constructor(
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected snotifyService: SnotifyService,
    protected vcutStylePlanUploadService: VcutStylePlanUploadService
  ) {}

  ngOnInit() {
    this.isWait = false;
    this.isSaving = false;
  }

  previousState() {
    window.history.back();
  }

  uploadFile(event) {
    this.selectedFiles.push(event);
    for (let index = 0; index < event.length; index++) {
      const element = event[index];
      this.files.push(element.name);
    }
  }

  save() {
    if (this.selectedFiles != null) {
      this.isWait = true;
      this.isSaving = true;
      this.selectedFiles.forEach(fileList => {
        this.currentFileUpload.push(fileList.item(0));
      });
      this.vcutStylePlanUploadService.upload(this.currentFileUpload).subscribe(
        (cutStylePlanUpload: HttpResponse<IVcutStylePlanUpload>) => {
          this.snotifyService.success('Data upload successfully', '', toastConfig);
          this.isWait = false;
          this.isSaving = false;
          this.files = [];
          this.selectedFiles = [];
          this.currentFileUpload = [];
        },
        (httpErrorResponse: HttpErrorResponse) => {
          this.snotifyService.error(httpErrorResponse.headers.get('x-vamaniportalapp-error'), '', toastConfig);
          this.isWait = false;
          this.isSaving = false;
          this.files = [];
          this.selectedFiles = [];
          this.currentFileUpload = [];
        }
      );
    } else {
      this.snotifyService.error('No file attach', '', toastConfig);
    }
  }
  deleteAttachment(index) {
    this.files.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }
}
