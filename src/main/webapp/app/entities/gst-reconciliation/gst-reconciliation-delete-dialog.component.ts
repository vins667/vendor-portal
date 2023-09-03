import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { GstReconciliationService } from './gst-reconciliation.service';

@Component({
  selector: 'jhi-gst-reconciliation-delete-dialog',
  templateUrl: './gst-reconciliation-delete-dialog.component.html'
})
export class GstReconciliationDeleteDialogComponent {
  gstReconciliation: IGstReconciliation;

  constructor(
    protected gstReconciliationService: GstReconciliationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gstReconciliationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gstReconciliationListModification',
        content: 'Deleted an gstReconciliation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gst-reconciliation-delete-popup',
  template: ''
})
export class GstReconciliationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstReconciliation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GstReconciliationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gstReconciliation = gstReconciliation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/gst-reconciliation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/gst-reconciliation', { outlets: { popup: null } }]);
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
