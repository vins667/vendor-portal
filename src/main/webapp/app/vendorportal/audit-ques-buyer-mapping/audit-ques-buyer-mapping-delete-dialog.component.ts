import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';
import { AuditQuesBuyerMappingService } from './audit-ques-buyer-mapping.service';

@Component({
    selector: 'jhi-audit-ques-buyer-mapping-delete-dialog',
    templateUrl: './audit-ques-buyer-mapping-delete-dialog.component.html'
})
export class AuditQuesBuyerMappingDeleteDialogComponent {
    auditQuesBuyerMapping: IAuditQuesBuyerMapping;

    constructor(
        protected auditQuesBuyerMappingService: AuditQuesBuyerMappingService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditQuesBuyerMappingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'auditQuesBuyerMappingListModification',
                content: 'Deleted an auditQuesBuyerMapping'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-ques-buyer-mapping-delete-popup',
    template: ''
})
export class AuditQuesBuyerMappingDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ auditQuesBuyerMapping }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AuditQuesBuyerMappingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.auditQuesBuyerMapping = auditQuesBuyerMapping;
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
