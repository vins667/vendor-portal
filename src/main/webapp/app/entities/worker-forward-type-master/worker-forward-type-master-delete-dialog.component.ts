import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from './worker-forward-type-master.service';

@Component({
  selector: 'jhi-worker-forward-type-master-delete-dialog',
  templateUrl: './worker-forward-type-master-delete-dialog.component.html'
})
export class WorkerForwardTypeMasterDeleteDialogComponent {
  workerForwardTypeMaster: IWorkerForwardTypeMaster;

  constructor(
    protected workerForwardTypeMasterService: WorkerForwardTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workerForwardTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'workerForwardTypeMasterListModification',
        content: 'Deleted an workerForwardTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-worker-forward-type-master-delete-popup',
  template: ''
})
export class WorkerForwardTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerForwardTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WorkerForwardTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.workerForwardTypeMaster = workerForwardTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
