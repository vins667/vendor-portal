import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IQuotationMaster } from 'app/shared/model/quotation-master.model';
import { QuotationMasterService } from './quotation-master.service';

@Component({
  selector: 'jhi-quotation-master-delete-dialog',
  templateUrl: './quotation-master-delete-dialog.component.html'
})
export class QuotationMasterDeleteDialogComponent {
  quotationMaster: IQuotationMaster;

  constructor(
    protected quotationMasterService: QuotationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.quotationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'quotationMasterListModification',
        content: 'Deleted an quotationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-quotation-master-delete-popup',
  template: ''
})
export class QuotationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ quotationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(QuotationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.quotationMaster = quotationMaster;
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
