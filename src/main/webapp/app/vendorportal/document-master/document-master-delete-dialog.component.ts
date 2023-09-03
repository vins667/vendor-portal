import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IDocumentMaster } from 'app/shared/model/document-master.model';
import { DocumentMasterService } from './document-master.service';

@Component({
  selector: 'jhi-document-master-delete-dialog',
  templateUrl: './document-master-delete-dialog.component.html'
})
export class DocumentMasterDeleteDialogComponent {
  documentMaster: IDocumentMaster;

  constructor(
    protected documentMasterService: DocumentMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.documentMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'documentMasterListModification',
        content: 'Deleted an documentMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-document-master-delete-popup',
  template: ''
})
export class DocumentMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ documentMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DocumentMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.documentMaster = documentMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
