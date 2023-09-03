import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { GstReconciliationUploadService } from './gst-reconciliation-upload.service';
import { HttpErrorResponse } from '@angular/common/http';
import * as FileSaver from 'file-saver';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-gst-reconciliation-upload',
  templateUrl: './gst-reconciliation-upload.component.html',
  styleUrls: ['./gst-reconciliation-upload.component.scss']
})
export class GstReconciliationUploadComponent implements OnInit {
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
    protected jhiAlertService: JhiAlertService,
    protected gstReconciliationUploadService: GstReconciliationUploadService
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
    if (this.selectedFiles!.length > 0) {
      this.isWait = true;
      this.isSaving = true;
      this.selectedFiles.forEach(fileList => {
        this.currentFileUpload.push(fileList.item(0));
      });
      this.gstReconciliationUploadService.upload(this.currentFileUpload).subscribe(
        () => {
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

  download(): any {
    this.gstReconciliationUploadService.download().subscribe(
      res => {
        FileSaver.saveAs(res, 'sample.xlsx');
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  deleteAttachment(index) {
    this.files.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }
}
