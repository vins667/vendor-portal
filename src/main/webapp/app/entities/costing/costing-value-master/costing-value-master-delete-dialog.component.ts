import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingValueMaster } from 'app/shared/model/costing-value-master.model';
import { CostingValueMasterService } from './costing-value-master.service';

@Component({
  selector: 'jhi-costing-value-master-delete-dialog',
  templateUrl: './costing-value-master-delete-dialog.component.html'
})
export class CostingValueMasterDeleteDialogComponent {
  costingValueMaster: ICostingValueMaster;

  constructor(
    protected costingValueMasterService: CostingValueMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingValueMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingValueMasterListModification',
        content: 'Deleted an costingValueMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-value-master-delete-popup',
  template: ''
})
export class CostingValueMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingValueMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingValueMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.costingValueMaster = costingValueMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-value-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-value-master', { outlets: { popup: null } }]);
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
