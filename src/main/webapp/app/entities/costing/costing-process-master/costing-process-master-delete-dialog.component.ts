import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingProcessMaster } from 'app/shared/model/costing-process-master.model';
import { CostingProcessMasterService } from './costing-process-master.service';

@Component({
  selector: 'jhi-costing-process-master-delete-dialog',
  templateUrl: './costing-process-master-delete-dialog.component.html'
})
export class CostingProcessMasterDeleteDialogComponent {
  costingProcessMaster: ICostingProcessMaster;

  constructor(
    protected costingProcessMasterService: CostingProcessMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingProcessMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingProcessMasterListModification',
        content: 'Deleted an costingProcessMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-process-master-delete-popup',
  template: ''
})
export class CostingProcessMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingProcessMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingProcessMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.costingProcessMaster = costingProcessMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-process-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-process-master', { outlets: { popup: null } }]);
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
