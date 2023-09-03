import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';
import { WorkerWorkFlowMasterService } from './worker-work-flow-master.service';

@Component({
  selector: 'jhi-worker-work-flow-master-delete-dialog',
  templateUrl: './worker-work-flow-master-delete-dialog.component.html'
})
export class WorkerWorkFlowMasterDeleteDialogComponent {
  workerWorkFlowMaster: IWorkerWorkFlowMaster;

  constructor(
    protected workerWorkFlowMasterService: WorkerWorkFlowMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.workerWorkFlowMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'workerWorkFlowMasterListModification',
        content: 'Deleted an workerWorkFlowMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-worker-work-flow-master-delete-popup',
  template: ''
})
export class WorkerWorkFlowMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ workerWorkFlowMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WorkerWorkFlowMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.workerWorkFlowMaster = workerWorkFlowMaster;
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
