import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { AuditGroupMasterService } from './audit-group-master.service';

@Component({
  selector: 'jhi-audit-group-master-delete-dialog',
  templateUrl: './audit-group-master-delete-dialog.component.html'
})
export class AuditGroupMasterDeleteDialogComponent {
  auditGroupMaster: IAuditGroupMaster;

  constructor(
    protected auditGroupMasterService: AuditGroupMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.auditGroupMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'auditGroupMasterListModification',
        content: 'Deleted an auditGroupMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-audit-group-master-delete-popup',
  template: ''
})
export class AuditGroupMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ auditGroupMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AuditGroupMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.auditGroupMaster = auditGroupMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/audit-group-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/audit-group-master', { outlets: { popup: null } }]);
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
