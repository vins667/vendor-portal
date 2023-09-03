import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';
import { VendorBuyerAuditService } from './vendor-buyer-audit.service';

@Component({
  selector: 'jhi-vendor-buyer-audit-delete-dialog',
  templateUrl: './vendor-buyer-audit-delete-dialog.component.html'
})
export class VendorBuyerAuditDeleteDialogComponent {
  vendorBuyerAudit: IVendorBuyerAudit;

  constructor(
    protected vendorBuyerAuditService: VendorBuyerAuditService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vendorBuyerAuditService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vendorBuyerAuditListModification',
        content: 'Deleted an vendorBuyerAudit'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vendor-buyer-audit-delete-popup',
  template: ''
})
export class VendorBuyerAuditDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendorBuyerAudit }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VendorBuyerAuditDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vendorBuyerAudit = vendorBuyerAudit;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vendor-buyer-audit', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vendor-buyer-audit', { outlets: { popup: null } }]);
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
