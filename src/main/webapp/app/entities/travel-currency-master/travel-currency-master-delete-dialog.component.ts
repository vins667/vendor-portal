import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelCurrencyMaster } from 'app/shared/model/travel-currency-master.model';
import { TravelCurrencyMasterService } from './travel-currency-master.service';

@Component({
  selector: 'jhi-travel-currency-master-delete-dialog',
  templateUrl: './travel-currency-master-delete-dialog.component.html'
})
export class TravelCurrencyMasterDeleteDialogComponent {
  travelCurrencyMaster: ITravelCurrencyMaster;

  constructor(
    protected travelCurrencyMasterService: TravelCurrencyMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelCurrencyMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelCurrencyMasterListModification',
        content: 'Deleted an travelCurrencyMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-currency-master-delete-popup',
  template: ''
})
export class TravelCurrencyMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelCurrencyMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelCurrencyMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.travelCurrencyMaster = travelCurrencyMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-currency-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-currency-master', { outlets: { popup: null } }]);
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
