import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';
import { ITransactionUpload } from 'app/entities/transaction-upload/transaction-upload.model';
import { TransactionUploadService } from 'app/entities/transaction-upload/transaction-upload.service';

@Component({
  selector: 'jhi-transaction-upload-detail',
  templateUrl: './transaction-upload-detail.component.html',
  styleUrls: ['./transaction-upload-details.component.scss']
})
export class TransactionUploadDetailsComponent implements OnInit {
  transactionUpload: ITransactionUpload;
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
    protected transactionUploadService: TransactionUploadService
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
      this.transactionUploadService
        .upload(this.currentFileUpload)
        .subscribe((bankRealisationCertificateUpload: HttpResponse<ITransactionUpload>) => {
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
