import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IOperationMaster } from 'app/shared/model/operation-master.model';
import { OperationMasterService } from './operation-master.service';

@Component({
  selector: 'jhi-operation-master-delete-dialog',
  templateUrl: './operation-master-delete-dialog.component.html'
})
export class OperationMasterDeleteDialogComponent {
  operationMaster: IOperationMaster;

  constructor(
    protected operationMasterService: OperationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.operationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'operationMasterListModification',
        content: 'Deleted an operationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-operation-master-delete-popup',
  template: ''
})
export class OperationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ operationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OperationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.operationMaster = operationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/operation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/operation-master', { outlets: { popup: null } }]);
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
