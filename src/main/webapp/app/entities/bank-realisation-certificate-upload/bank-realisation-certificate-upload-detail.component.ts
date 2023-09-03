import { Component, OnInit } from '@angular/core';
import { IBankRealisationCertificateUpload } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { BankRealisationCertificateUploadService } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.service';
import { HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-bank-realisation-certificate-upload-detail',
  templateUrl: './bank-realisation-certificate-upload-detail.component.html',
  styleUrls: ['./bank-realisation-certificate-upload-detail.component.scss']
})
export class BankRealisationCertificateUploadDetailComponent implements OnInit {
  bankRealisationCertificateUpload: IBankRealisationCertificateUpload;
  editForm = this.fb.group({});
  isWait: Boolean;
  isSaving: Boolean;

  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];

  constructor(
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected snotifyService: SnotifyService,
    protected bankRealisationCertificateUploadService: BankRealisationCertificateUploadService
  ) {}

  ngOnInit(): void {
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
      this.bankRealisationCertificateUploadService
        .upload(this.currentFileUpload)
        .subscribe((bankRealisationCertificateUpload: HttpResponse<IBankRealisationCertificateUpload>) => {
          this.snotifyService.success('Data upload successfully', '', toastConfig);
          this.isSaving = false;
          this.isWait = false;
          this.files = [];
          this.selectedFiles = [];
          this.currentFileUpload = [];
        });
    } else {
      this.snotifyService.error('No file attach', '', toastConfig);
    }
  }

  deleteAttachment(index) {
    this.files.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }
}
