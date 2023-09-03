import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { SnotifyService } from 'ng-snotify';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IOrderpartnerDocument } from 'app/orderpartner/orderpartner-document/orderpartner-document.model';
import { OrderpartnerDocumentService } from 'app/orderpartner/orderpartner-document/orderpartner-document.service';
import { OrderpartnerDocumentPopupComponent } from './orderpartner-document-popup.component';
import { Observable, Subscription } from 'rxjs';
import * as FileSaver from 'file-saver';
import { IOrderpartnerUpload } from 'app/orderpartner/orderpartner-document/orderpartner-upload.model';
import { PopupComponent } from 'app/shared/popup/popup.component';
import { HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-orderpartner-document-update',
  templateUrl: './orderpartner-document-update.component.html'
})
export class OrderpartnerDocumentUpdateComponent implements OnInit {
  isSaving: boolean;
  orderpartnerDocument: IOrderpartnerDocument;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;
  constructor(
    protected orderpartnerDocumentService: OrderpartnerDocumentService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected jhiAlertService: JhiAlertService
  ) {}

  ngOnInit(): void {
    this.registerChangeInOrderpartnerFilter();
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderpartnerDocument }) => {
      this.orderpartnerDocument = orderpartnerDocument;
    });
  }

  registerChangeInOrderpartnerFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('selectedorderpartnerDocumentUploads', () => {
      this.isSaving = true;
      this.orderpartnerDocumentService
        .find(this.orderpartnerDocument.customersuppliertype, this.orderpartnerDocument.customersuppliercode)
        .subscribe(orderpartnerDocument => {
          this.orderpartnerDocument = orderpartnerDocument.body;
          this.isSaving = false;
        });
    });
  }

  popup(orderpartnerDocument: IOrderpartnerDocument) {
    this.ngbModalRef = this.modalService.open(OrderpartnerDocumentPopupComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
    this.ngbModalRef.componentInstance.orderpartnerDocument = orderpartnerDocument;
  }
  previousState() {
    window.history.back();
  }

  download(orderpartnerUpload: IOrderpartnerUpload): any {
    this.isSaving = true;
    this.orderpartnerDocumentService.download(orderpartnerUpload.id).subscribe(
      res => {
        this.isSaving = false;
        FileSaver.saveAs(res, `${orderpartnerUpload.originalFileName}`);
      },
      res => {
        this.isSaving = false;
        this.onError(res.message);
      }
    );
  }

  pdf(orderpartnerUpload: IOrderpartnerUpload): any {
    this.isSaving = true;
    this.orderpartnerDocumentService.download(orderpartnerUpload.id).subscribe(
      res => {
        this.isSaving = false;
        const file = new Blob([res], { type: 'application/pdf' });
        const content = window.URL.createObjectURL(file);
        const modalRef = this.modalService.open(PopupComponent as Component, {
          size: 'lg',
          backdrop: 'static',
          windowClass: 'xlModal'
        });
        modalRef.componentInstance.content = content;
      },
      res => {
        this.isSaving = false;
        this.onError(res.message);
      }
    );
  }

  save(): void {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.orderpartnerDocumentService.save(this.orderpartnerDocument));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderpartnerDocument>>) {
    result.subscribe((res: HttpResponse<IOrderpartnerDocument>) => this.onSaveSuccess(res), () => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<IOrderpartnerDocument>) {
    this.isSaving = false;
    this.snotifyService.success('Data save successfully', toastConfig);
    this.orderpartnerDocument = res.body;
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Data not save ', toastConfig);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
