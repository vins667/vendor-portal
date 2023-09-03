import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderpartnerDocument, IOrderpartnerDocument } from './orderpartner-document.model';
import { OrderpartnerDocumentService } from './orderpartner-document.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-orderpartner-document-popup',
  templateUrl: './orderpartner-document-popup.component.html'
})
export class OrderpartnerDocumentPopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;
  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];
  orderpartnerDocument: OrderpartnerDocument;
  documentType: string;
  extn: string;
  constructor(
    protected modalService: NgbModal,
    public activeModal: NgbActiveModal,
    protected orderpartnerDocumentService: OrderpartnerDocumentService,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnDestroy(): void {
    this.ngbModalRef = null;
  }

  ngOnInit() {
    this.documentType = 'BANK_DETAILS';
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  uploadFile(event) {
    const file = event.target.files[0];
    const fileName = file.name;
    this.extn = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);

    this.selectedFiles.push(event.target.files);
    this.files.push(event.target.files[0].name);
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
    this.subscribeToSaveResponse(
      this.orderpartnerDocumentService.create(this.orderpartnerDocument, this.documentType, this.currentFileUpload)
    );
  }

  /* getLaoded() {
    this.orderpartnerDocumentService.customQuery().subscribe((res: HttpResponse<IOrderpartnerDocument>) => {
      this.orderpartnerDocument = res.body;
    });
  } */
  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderpartnerDocument>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.snotifyService.success('File uploaded successfully', toastConfig);
    this.eventManager.broadcast({ name: 'selectedorderpartnerDocumentUploads' });
    this.activeModal.dismiss('cancel');
  }

  protected onSaveError() {
    this.snotifyService.error('File not uploaded ', toastConfig);
  }
}
