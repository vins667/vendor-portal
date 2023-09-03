import { Component, OnInit } from '@angular/core';
import { IOrderpartnerDocument } from './orderpartner-document.model';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { OrderpartnerDocumentService } from './orderpartner-document.service';
import { HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-orderpartner-document-detail',
  templateUrl: './orderpartner-document-detail.component.html',
  styleUrls: ['./orderpartner-document-detail.component.scss']
})
export class OrderpartnerDocumentDetailComponent implements OnInit {
  OrderpartnerDocument: IOrderpartnerDocument;
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
    protected orderpartnerDocumentService: OrderpartnerDocumentService
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
      this.orderpartnerDocumentService
        .upload(this.currentFileUpload)
        .subscribe((OrderpartnerDocument: HttpResponse<IOrderpartnerDocument>) => {
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
