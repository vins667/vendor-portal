import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { TravelApplicationMasterService } from './travel-application-master.service';

@Component({
  selector: 'jhi-travel-application-master-delete-dialog',
  templateUrl: './travel-application-master-delete-dialog.component.html'
})
export class TravelApplicationMasterDeleteDialogComponent {
  travelApplicationMaster: ITravelApplicationMaster;

  constructor(
    protected travelApplicationMasterService: TravelApplicationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.travelApplicationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'travelApplicationMasterListModification',
        content: 'Deleted an travelApplicationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-travel-application-master-delete-popup',
  template: ''
})
export class TravelApplicationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ travelApplicationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TravelApplicationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.travelApplicationMaster = travelApplicationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/travel-application-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/travel-application-master', { outlets: { popup: null } }]);
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
