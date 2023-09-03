import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelLuggageDetails } from 'app/shared/model/travel-luggage-details.model';
import { TravelLuggageDetailsService } from './travel-luggage-details.service';

@Component({
  selector: 'jhi-travel-luggage-details-delete-dialog',
  templateUrl: './travel-luggage-details-delete-dialog.component.html'
})
export class TravelLuggageDetailsDeleteDialogComponent {
  travelLuggageDetails: ITravelLuggageDetails;

  constructor(
    protected travelLuggageDetailsService: TravelLuggageDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelLuggageDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelLuggageDetailsListModification',
        content: 'Deleted an travelLuggageDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-luggage-details-delete-popup',
  template: ''
})
export class TravelLuggageDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelLuggageDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelLuggageDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.travelLuggageDetails = travelLuggageDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-luggage-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-luggage-details', { outlets: { popup: null } }]);
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
