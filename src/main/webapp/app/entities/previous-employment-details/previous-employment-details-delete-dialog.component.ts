import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';
import { PreviousEmploymentDetailsService } from './previous-employment-details.service';

@Component({
  selector: 'jhi-previous-employment-details-delete-dialog',
  templateUrl: './previous-employment-details-delete-dialog.component.html'
})
export class PreviousEmploymentDetailsDeleteDialogComponent {
  previousEmploymentDetails: IPreviousEmploymentDetails;

  constructor(
    protected previousEmploymentDetailsService: PreviousEmploymentDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.previousEmploymentDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'previousEmploymentDetailsListModification',
        content: 'Deleted an previousEmploymentDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-previous-employment-details-delete-popup',
  template: ''
})
export class PreviousEmploymentDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ previousEmploymentDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PreviousEmploymentDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.previousEmploymentDetails = previousEmploymentDetails;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/previous-employment-details', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/previous-employment-details', { outlets: { popup: null } }]);
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
