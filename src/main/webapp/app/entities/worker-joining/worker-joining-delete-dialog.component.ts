import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IWorkerJoining } from 'app/shared/model/worker-joining.model';
import { WorkerJoiningService } from './worker-joining.service';

@Component({
  selector: 'jhi-worker-joining-delete-dialog',
  templateUrl: './worker-joining-delete-dialog.component.html'
})
export class WorkerJoiningDeleteDialogComponent {
  workerJoining: IWorkerJoining;

  constructor(
    protected workerJoiningService: WorkerJoiningService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workerJoiningService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'workerJoiningListModification',
        content: 'Deleted an workerJoining'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-worker-joining-delete-popup',
  template: ''
})
export class WorkerJoiningDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerJoining }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WorkerJoiningDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.workerJoining = workerJoining;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/worker-joining', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/worker-joining', { outlets: { popup: null } }]);
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
