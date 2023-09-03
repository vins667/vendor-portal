import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { MarkerMasterEntryService } from './marker-master-entry.service';

@Component({
  selector: 'jhi-marker-master-entry-delete-dialog',
  templateUrl: './marker-master-entry-delete-dialog.component.html'
})
export class MarkerMasterEntryDeleteDialogComponent {
  markerMasterEntry: IMarkerMasterEntry;

  constructor(
    protected markerMasterEntryService: MarkerMasterEntryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.markerMasterEntryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'markerMasterEntryListModification',
        content: 'Deleted an markerMasterEntry'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-marker-master-entry-delete-popup',
  template: ''
})
export class MarkerMasterEntryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ markerMasterEntry }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MarkerMasterEntryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.markerMasterEntry = markerMasterEntry;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/marker-master-entry', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/marker-master-entry', { outlets: { popup: null } }]);
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
