import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';
import { VendorBuyerAuditLinkingService } from './vendor-buyer-audit-linking.service';

@Component({
    selector: 'jhi-vendor-buyer-audit-linking-delete-dialog',
    templateUrl: './vendor-buyer-audit-linking-delete-dialog.component.html'
})
export class VendorBuyerAuditLinkingDeleteDialogComponent {
    vendorBuyerAuditLinking: IVendorBuyerAuditLinking;

    constructor(
        protected vendorBuyerAuditLinkingService: VendorBuyerAuditLinkingService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vendorBuyerAuditLinkingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vendorBuyerAuditLinkingListModification',
                content: 'Deleted an vendorBuyerAuditLinking'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vendor-buyer-audit-linking-delete-popup',
    template: ''
})
export class VendorBuyerAuditLinkingDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vendorBuyerAuditLinking }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VendorBuyerAuditLinkingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.vendorBuyerAuditLinking = vendorBuyerAuditLinking;
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
