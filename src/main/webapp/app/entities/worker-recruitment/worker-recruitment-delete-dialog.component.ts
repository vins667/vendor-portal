import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { WorkerRecruitmentService } from './worker-recruitment.service';

@Component({
  selector: 'jhi-worker-recruitment-delete-dialog',
  templateUrl: './worker-recruitment-delete-dialog.component.html'
})
export class WorkerRecruitmentDeleteDialogComponent {
  workerRecruitment: IWorkerRecruitment;

  constructor(
    protected workerRecruitmentService: WorkerRecruitmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workerRecruitmentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'workerRecruitmentListModification',
        content: 'Deleted an workerRecruitment'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-worker-recruitment-delete-popup',
  template: ''
})
export class WorkerRecruitmentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerRecruitment }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WorkerRecruitmentDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.workerRecruitment = workerRecruitment;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/worker-recruitment', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/worker-recruitment', { outlets: { popup: null } }]);
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
