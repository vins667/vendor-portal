import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';
import { VcutOperationIssueMasterService } from './vcut-operation-issue-master.service';

@Component({
  selector: 'jhi-vcut-operation-issue-master-delete-dialog',
  templateUrl: './vcut-operation-issue-master-delete-dialog.component.html'
})
export class VcutOperationIssueMasterDeleteDialogComponent {
  vcutOperationIssueMaster: IVcutOperationIssueMaster;

  constructor(
    protected vcutOperationIssueMasterService: VcutOperationIssueMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutOperationIssueMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutOperationIssueMasterListModification',
        content: 'Deleted an vcutOperationIssueMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-operation-issue-master-delete-popup',
  template: ''
})
export class VcutOperationIssueMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutOperationIssueMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutOperationIssueMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutOperationIssueMaster = vcutOperationIssueMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-operation-issue-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-operation-issue-master', { outlets: { popup: null } }]);
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
