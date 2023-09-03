import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesOrderClosing } from './sales-order-closing.model';
import { SalesOrderClosingService } from './sales-order-closing.service';

@Component({
  selector: 'jhi-sales-order-closing-delete-dialog',
  templateUrl: './sales-order-closing-delete-dialog.component.html'
})
export class SalesOrderClosingDeleteDialogComponent {
  salesOrderClosing: ISalesOrderClosing;

  constructor(
    protected salesOrderClosingService: SalesOrderClosingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.salesOrderClosingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'salesOrderClosingListModification',
        content: 'Deleted an salesOrderClosing'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sales-order-closing-delete-popup',
  template: ''
})
export class SalesOrderClosingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ salesOrderClosing }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SalesOrderClosingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.salesOrderClosing = salesOrderClosing;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sales-order-closing', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sales-order-closing', { outlets: { popup: null } }]);
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
