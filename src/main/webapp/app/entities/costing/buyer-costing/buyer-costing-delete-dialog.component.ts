import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBuyerCosting } from 'app/shared/model/buyer-costing.model';
import { BuyerCostingService } from './buyer-costing.service';

@Component({
  selector: 'jhi-buyer-costing-delete-dialog',
  templateUrl: './buyer-costing-delete-dialog.component.html'
})
export class BuyerCostingDeleteDialogComponent {
  buyerCosting: IBuyerCosting;

  constructor(
    protected buyerCostingService: BuyerCostingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.buyerCostingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'buyerCostingListModification',
        content: 'Deleted an buyerCosting'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-buyer-costing-delete-popup',
  template: ''
})
export class BuyerCostingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buyerCosting }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BuyerCostingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.buyerCosting = buyerCosting;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/buyer-costing', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/buyer-costing', { outlets: { popup: null } }]);
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
