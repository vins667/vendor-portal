import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';
import { TrimsCreationMasterService } from './trims-creation-master.service';

@Component({
  selector: 'jhi-trims-creation-master-delete-dialog',
  templateUrl: './trims-creation-master-delete-dialog.component.html'
})
export class TrimsCreationMasterDeleteDialogComponent {
  trimsCreationMaster: ITrimsCreationMaster;

  constructor(
    protected trimsCreationMasterService: TrimsCreationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trimsCreationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'trimsCreationMasterListModification',
        content: 'Deleted an trimsCreationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-trims-creation-master-delete-popup',
  template: ''
})
export class TrimsCreationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimsCreationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TrimsCreationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.trimsCreationMaster = trimsCreationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/trims-creation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/trims-creation-master', { outlets: { popup: null } }]);
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
