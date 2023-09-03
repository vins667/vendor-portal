import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';
import { VendorAuditQuesMasterService } from './vendor-audit-ques-master.service';

@Component({
    selector: 'jhi-vendor-audit-ques-master-delete-dialog',
    templateUrl: './vendor-audit-ques-master-delete-dialog.component.html'
})
export class VendorAuditQuesMasterDeleteDialogComponent {
    vendorAuditQuesMaster: IVendorAuditQuesMaster;

    constructor(
        protected vendorAuditQuesMasterService: VendorAuditQuesMasterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vendorAuditQuesMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vendorAuditQuesMasterListModification',
                content: 'Deleted an vendorAuditQuesMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vendor-audit-ques-master-delete-popup',
    template: ''
})
export class VendorAuditQuesMasterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vendorAuditQuesMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VendorAuditQuesMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.vendorAuditQuesMaster = vendorAuditQuesMaster;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vendor-audit-ques-master', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vendor-audit-ques-master', { outlets: { popup: null } }]);
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
