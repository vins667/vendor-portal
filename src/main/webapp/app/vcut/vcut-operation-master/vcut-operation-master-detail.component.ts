import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { VcutOperationMasterService } from './vcut-operation-master.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-vcut-operation-master-detail',
  templateUrl: './vcut-operation-master-detail.component.html',
  styleUrls: ['./vcut-operation-master-detail.component.scss']
})
export class VcutOperationMasterDetailComponent implements OnInit {
  vcutOperationMaster: IVcutOperationMaster;
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
    protected vcutOperationMasterService: VcutOperationMasterService
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
      this.vcutOperationMasterService.upload(this.currentFileUpload).subscribe(
        (cutOperation: HttpResponse<IVcutOperationMaster>) => {
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
