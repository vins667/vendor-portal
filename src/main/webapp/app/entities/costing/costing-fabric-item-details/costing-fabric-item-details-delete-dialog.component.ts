import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';
import { CostingFabricItemDetailsService } from './costing-fabric-item-details.service';

@Component({
  selector: 'jhi-costing-fabric-item-details-delete-dialog',
  templateUrl: './costing-fabric-item-details-delete-dialog.component.html'
})
export class CostingFabricItemDetailsDeleteDialogComponent {
  costingFabricItemDetails: ICostingFabricItemDetails;

  constructor(
    protected costingFabricItemDetailsService: CostingFabricItemDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.costingFabricItemDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'costingFabricItemDetailsListModification',
        content: 'Deleted an costingFabricItemDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-costing-fabric-item-details-delete-popup',
  template: ''
})
export class CostingFabricItemDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ costingFabricItemDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CostingFabricItemDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.costingFabricItemDetails = costingFabricItemDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/costing-fabric-item-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/costing-fabric-item-details', { outlets: { popup: null } }]);
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
