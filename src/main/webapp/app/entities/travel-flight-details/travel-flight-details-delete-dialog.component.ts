import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { TravelFlightDetailsService } from './travel-flight-details.service';

@Component({
  selector: 'jhi-travel-flight-details-delete-dialog',
  templateUrl: './travel-flight-details-delete-dialog.component.html'
})
export class TravelFlightDetailsDeleteDialogComponent {
  travelFlightDetails: ITravelFlightDetails;

  constructor(
    protected travelFlightDetailsService: TravelFlightDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelFlightDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelFlightDetailsListModification',
        content: 'Deleted an travelFlightDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-flight-details-delete-popup',
  template: ''
})
export class TravelFlightDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelFlightDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelFlightDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.travelFlightDetails = travelFlightDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-flight-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-flight-details', { outlets: { popup: null } }]);
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
