import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IRelationMaster } from 'app/shared/model/relation-master.model';
import { RelationMasterService } from './relation-master.service';

@Component({
  selector: 'jhi-relation-master-delete-dialog',
  templateUrl: './relation-master-delete-dialog.component.html'
})
export class RelationMasterDeleteDialogComponent {
  relationMaster: IRelationMaster;

  constructor(
    protected relationMasterService: RelationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.relationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'relationMasterListModification',
        content: 'Deleted an relationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-relation-master-delete-popup',
  template: ''
})
export class RelationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ relationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(RelationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.relationMaster = relationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/relation-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/relation-master', { outlets: { popup: null } }]);
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
