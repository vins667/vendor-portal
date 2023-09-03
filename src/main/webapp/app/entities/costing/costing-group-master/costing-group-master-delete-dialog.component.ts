import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';
import { CostingGroupMasterService } from './costing-group-master.service';

@Component({
  selector: 'jhi-costing-group-master-delete-dialog',
  templateUrl: './costing-group-master-delete-dialog.component.html'
})
export class CostingGroupMasterDeleteDialogComponent {
  costingGroupMaster: ICostingGroupMaster;

  constructor(
    protected costingGroupMasterService: CostingGroupMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingGroupMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingGroupMasterListModification',
        content: 'Deleted an costingGroupMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-group-master-delete-popup',
  template: ''
})
export class CostingGroupMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingGroupMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingGroupMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.costingGroupMaster = costingGroupMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-group-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-group-master', { outlets: { popup: null } }]);
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
