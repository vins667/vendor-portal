import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';
import { GstGovtUploadService } from './gst-govt-upload.service';
import { IGstGovtUpload } from 'app/shared/model/gst-govt-upload.model';

@Component({
  selector: 'jhi-gst-govt-upload-popup',
  templateUrl: './gst-govt-upload-popup.component.html',
  styleUrls: ['./gst-govt-upload-popup.component.scss']
})
export class GstGovtUploadPopupComponent implements OnInit {
  gstGovtUpload: IGstGovtUpload;
  editForm = this.fb.group({});
  isWait: boolean;
  isSaving: boolean;

  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];

  constructor(
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected gstGovtUploadService: GstGovtUploadService
  ) {}

  ngOnInit() {
    this.isWait = false;
    this.isSaving = false;
  }

  previousState() {
    window.history.back();
  }

  uploadFiles(event) {
    this.selectedFiles.push(event);
    for (let index = 0; index < event.length; index++) {
      const element = event[index];
      this.files.push(element.name);
    }
  }

  loadExcel() {
    if (this.selectedFiles != null) {
      this.isWait = true;
      this.isSaving = true;
      this.selectedFiles.forEach(fileList => {
        this.currentFileUpload.push(fileList.item(0));
      });
      this.gstGovtUploadService.upload(this.currentFileUpload).subscribe(res => {
        this.gstGovtUpload = res.body;
        // this.eventManager.broadcast({ name: 'vopllist', content: this.gstGovtUpload });
        this.isWait = false;
        this.isSaving = false;
        this.files = [];
        this.selectedFiles = [];
        this.currentFileUpload = [];
        // this.activeModal.dismiss('cancel');
      });
    } else {
      this.snotifyService.error('No file attach', '', toastConfig);
    }
  }

  save() {}
  deleteAttachment(index) {
    this.files.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }
}
