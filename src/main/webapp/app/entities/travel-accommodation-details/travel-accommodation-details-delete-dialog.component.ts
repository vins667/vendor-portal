import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { TravelAccommodationDetailsService } from './travel-accommodation-details.service';

@Component({
  selector: 'jhi-travel-accommodation-details-delete-dialog',
  templateUrl: './travel-accommodation-details-delete-dialog.component.html'
})
export class TravelAccommodationDetailsDeleteDialogComponent {
  travelAccommodationDetails: ITravelAccommodationDetails;

  constructor(
    protected travelAccommodationDetailsService: TravelAccommodationDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelAccommodationDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelAccommodationDetailsListModification',
        content: 'Deleted an travelAccommodationDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-accommodation-details-delete-popup',
  template: ''
})
export class TravelAccommodationDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelAccommodationDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelAccommodationDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.travelAccommodationDetails = travelAccommodationDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-accommodation-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-accommodation-details', { outlets: { popup: null } }]);
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
