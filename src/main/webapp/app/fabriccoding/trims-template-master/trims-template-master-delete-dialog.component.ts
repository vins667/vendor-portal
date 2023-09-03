import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';
import { TrimsTemplateMasterService } from './trims-template-master.service';

@Component({
  selector: 'jhi-trims-template-master-delete-dialog',
  templateUrl: './trims-template-master-delete-dialog.component.html'
})
export class TrimsTemplateMasterDeleteDialogComponent {
  trimsTemplateMaster: ITrimsTemplateMaster;

  constructor(
    protected trimsTemplateMasterService: TrimsTemplateMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trimsTemplateMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'trimsTemplateMasterListModification',
        content: 'Deleted an trimsTemplateMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-trims-template-master-delete-popup',
  template: ''
})
export class TrimsTemplateMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trimsTemplateMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TrimsTemplateMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.trimsTemplateMaster = trimsTemplateMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/trims-template-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/trims-template-master', { outlets: { popup: null } }]);
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
