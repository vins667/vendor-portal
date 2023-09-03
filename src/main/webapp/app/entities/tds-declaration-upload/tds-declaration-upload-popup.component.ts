import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TdsDeclarationUpload, ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { TdsDeclarationUploadService } from './tds-declaration-upload.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-tds-declaration-upload-popup',
  templateUrl: './tds-declaration-upload-popup.component.html'
})
export class TdsDeclarationUploadPopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;
  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];
  tdsDeclarationUpload: TdsDeclarationUpload;
  extn: string;
  constructor(
    protected modalService: NgbModal,
    public activeModal: NgbActiveModal,
    protected tdsDeclarationUploadService: TdsDeclarationUploadService,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnDestroy(): void {
    this.ngbModalRef = null;
  }

  ngOnInit() {}

  close() {
    this.activeModal.dismiss('cancel');
  }

  uploadFile(event) {
    const file = event.target.files[0];
    const fileName = file.name;
    this.extn = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
    if (this.extn !== null && this.extn !== 'pdf') {
      this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
    } else {
      this.selectedFiles.push(event.target.files);
      this.files.push(event.target.files[0].name);
    }
  }

  deleteAttachment(index: number) {
    this.files.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }

  save() {
    if (this.selectedFiles != null) {
      this.selectedFiles.forEach(fileList => {
        this.currentFileUpload.push(fileList.item(0));
      });
    }
    this.subscribeToSaveResponse(this.tdsDeclarationUploadService.create(this.tdsDeclarationUpload, this.currentFileUpload));
    this.eventManager.broadcast({ name: 'selectedtdsDeclarationUpload' });
  }

  getLaoded() {
    this.tdsDeclarationUploadService.customQuery().subscribe((res: HttpResponse<ITdsDeclarationUpload>) => {
      this.tdsDeclarationUpload = res.body;
    });
  }
  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsDeclarationUpload>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.snotifyService.success('File uploaded successfully', toastConfig);
    this.activeModal.dismiss('cancel');
  }

  protected onSaveError() {
    this.snotifyService.error('File not uploaded ', toastConfig);
  }
}
