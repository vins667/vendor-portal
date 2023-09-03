import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IDesignationMaster } from 'app/shared/model/designation-master.model';
import { DesignationMasterService } from './designation-master.service';

@Component({
  selector: 'jhi-designation-master-delete-dialog',
  templateUrl: './designation-master-delete-dialog.component.html'
})
export class DesignationMasterDeleteDialogComponent {
  designationMaster: IDesignationMaster;

  constructor(
    protected designationMasterService: DesignationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.designationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'designationMasterListModification',
        content: 'Deleted an designationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-designation-master-delete-popup',
  template: ''
})
export class DesignationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ designationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DesignationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.designationMaster = designationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/designation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/designation-master', { outlets: { popup: null } }]);
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
