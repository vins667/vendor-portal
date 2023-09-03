import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRateMaster } from 'app/shared/model/rate-master.model';
import { RateMasterService } from './rate-master.service';

@Component({
  selector: 'jhi-rate-master-delete-dialog',
  templateUrl: './rate-master-delete-dialog.component.html'
})
export class RateMasterDeleteDialogComponent {
  rateMaster: IRateMaster;

  constructor(
    protected rateMasterService: RateMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.rateMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'rateMasterListModification',
        content: 'Deleted an rateMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-rate-master-delete-popup',
  template: ''
})
export class RateMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rateMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RateMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.rateMaster = rateMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/rate-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/rate-master', { outlets: { popup: null } }]);
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
