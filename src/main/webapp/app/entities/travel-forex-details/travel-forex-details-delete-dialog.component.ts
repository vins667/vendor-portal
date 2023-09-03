import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelForexDetails } from 'app/shared/model/travel-forex-details.model';
import { TravelForexDetailsService } from './travel-forex-details.service';

@Component({
  selector: 'jhi-travel-forex-details-delete-dialog',
  templateUrl: './travel-forex-details-delete-dialog.component.html'
})
export class TravelForexDetailsDeleteDialogComponent {
  travelForexDetails: ITravelForexDetails;

  constructor(
    protected travelForexDetailsService: TravelForexDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelForexDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelForexDetailsListModification',
        content: 'Deleted an travelForexDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-forex-details-delete-popup',
  template: ''
})
export class TravelForexDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelForexDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelForexDetailsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.travelForexDetails = travelForexDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-forex-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-forex-details', { outlets: { popup: null } }]);
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
