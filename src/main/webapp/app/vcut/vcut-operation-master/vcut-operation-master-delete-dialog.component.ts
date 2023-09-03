import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { VcutOperationMasterService } from './vcut-operation-master.service';

@Component({
  selector: 'jhi-vcut-operation-master-delete-dialog',
  templateUrl: './vcut-operation-master-delete-dialog.component.html'
})
export class VcutOperationMasterDeleteDialogComponent {
  vcutOperationMaster: IVcutOperationMaster;

  constructor(
    protected vcutOperationMasterService: VcutOperationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vcutOperationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vcutOperationMasterListModification',
        content: 'Deleted an vcutOperationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vcut-operation-master-delete-popup',
  template: ''
})
export class VcutOperationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutOperationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VcutOperationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vcutOperationMaster = vcutOperationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/vcut-operation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/vcut-operation-master', { outlets: { popup: null } }]);
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
