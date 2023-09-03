import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingGroupDetails } from 'app/shared/model/costing-group-details.model';
import { CostingGroupDetailsService } from './costing-group-details.service';

@Component({
  selector: 'jhi-costing-group-details-delete-dialog',
  templateUrl: './costing-group-details-delete-dialog.component.html'
})
export class CostingGroupDetailsDeleteDialogComponent {
  costingGroupDetails: ICostingGroupDetails;

  constructor(
    protected costingGroupDetailsService: CostingGroupDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingGroupDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingGroupDetailsListModification',
        content: 'Deleted an costingGroupDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-group-details-delete-popup',
  template: ''
})
export class CostingGroupDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingGroupDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingGroupDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.costingGroupDetails = costingGroupDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-group-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-group-details', { outlets: { popup: null } }]);
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
