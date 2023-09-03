import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobWorkFollowup } from './job-work-followup.model';
import { JobWorkFollowupService } from './job-work-followup.service';

@Component({
  selector: 'jhi-job-work-followup-delete-dialog',
  templateUrl: './job-work-followup-delete-dialog.component.html'
})
export class JobWorkFollowupDeleteDialogComponent {
  jobWorkFollowup: IJobWorkFollowup;

  constructor(
    protected jobWorkFollowupService: JobWorkFollowupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.jobWorkFollowupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'jobWorkFollowupListModification',
        content: 'Deleted an jobWorkFollowup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-job-work-followup-delete-popup',
  template: ''
})
export class JobWorkFollowupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ jobWorkFollowup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(JobWorkFollowupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.jobWorkFollowup = jobWorkFollowup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/job-work-followup', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/job-work-followup', { outlets: { popup: null } }]);
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
