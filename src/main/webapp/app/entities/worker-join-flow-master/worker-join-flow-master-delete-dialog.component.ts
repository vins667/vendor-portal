import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';
import { WorkerJoinFlowMasterService } from './worker-join-flow-master.service';

@Component({
  selector: 'jhi-worker-join-flow-master-delete-dialog',
  templateUrl: './worker-join-flow-master-delete-dialog.component.html'
})
export class WorkerJoinFlowMasterDeleteDialogComponent {
  workerJoinFlowMaster: IWorkerJoinFlowMaster;

  constructor(
    protected workerJoinFlowMasterService: WorkerJoinFlowMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workerJoinFlowMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'workerJoinFlowMasterListModification',
        content: 'Deleted an workerJoinFlowMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-worker-join-flow-master-delete-popup',
  template: ''
})
export class WorkerJoinFlowMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerJoinFlowMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WorkerJoinFlowMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.workerJoinFlowMaster = workerJoinFlowMaster;
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
