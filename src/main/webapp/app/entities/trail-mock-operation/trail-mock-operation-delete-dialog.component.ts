import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITrailMockOperation } from 'app/shared/model/trail-mock-operation.model';
import { TrailMockOperationService } from './trail-mock-operation.service';

@Component({
  selector: 'jhi-trail-mock-operation-delete-dialog',
  templateUrl: './trail-mock-operation-delete-dialog.component.html'
})
export class TrailMockOperationDeleteDialogComponent {
  trailMockOperation: ITrailMockOperation;

  constructor(
    protected trailMockOperationService: TrailMockOperationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trailMockOperationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'trailMockOperationListModification',
        content: 'Deleted an trailMockOperation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-trail-mock-operation-delete-popup',
  template: ''
})
export class TrailMockOperationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trailMockOperation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TrailMockOperationDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.trailMockOperation = trailMockOperation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/trail-mock-operation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/trail-mock-operation', { outlets: { popup: null } }]);
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
