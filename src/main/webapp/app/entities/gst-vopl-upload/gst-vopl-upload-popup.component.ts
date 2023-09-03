import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { GstVoplUploadService } from './gst-vopl-upload.service';
import { IGstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-gst-vopl-upload-popup',
  templateUrl: './gst-vopl-upload-popup.component.html',
  styleUrls: ['./gst-vopl-upload-popup.component.scss']
})
export class GstVoplUploadPopupComponent implements OnInit {
  gstVoplUpload: IGstVoplUpload;
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
    protected gstVoplUploadService: GstVoplUploadService
  ) {}

  ngOnInit() {
    this.isWait = false;
    this.isSaving = false;
  }

  previousState() {
    window.history.back();
  }

  uploadFiles(event) {
    alert('hiiii: ' + event);
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
      this.gstVoplUploadService.upload(this.currentFileUpload).subscribe(res => {
        this.gstVoplUpload = res.body;
        this.eventManager.broadcast({ name: 'vopllist', content: this.gstVoplUpload });
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
