import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITemplateMaster } from 'app/shared/model/template-master.model';
import { TemplateMasterService } from './template-master.service';

@Component({
  selector: 'jhi-template-master-delete-dialog',
  templateUrl: './template-master-delete-dialog.component.html'
})
export class TemplateMasterDeleteDialogComponent {
  templateMaster: ITemplateMaster;

  constructor(
    protected templateMasterService: TemplateMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.templateMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'templateMasterListModification',
        content: 'Deleted an templateMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-template-master-delete-popup',
  template: ''
})
export class TemplateMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ templateMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TemplateMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.templateMaster = templateMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/template-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/template-master', { outlets: { popup: null } }]);
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
