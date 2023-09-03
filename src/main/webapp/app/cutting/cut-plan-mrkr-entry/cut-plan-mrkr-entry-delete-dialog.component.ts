import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanMrkrEntryService } from './cut-plan-mrkr-entry.service';

@Component({
  selector: 'jhi-cut-plan-mrkr-entry-delete-dialog',
  templateUrl: './cut-plan-mrkr-entry-delete-dialog.component.html'
})
export class CutPlanMrkrEntryDeleteDialogComponent {
  cutPlanEntry: ICutPlanEntry;

  constructor(
    protected cutPlanEntryService: CutPlanMrkrEntryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.cutPlanEntryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'cutPlanEntryListModification',
        content: 'Deleted an cutPlanEntry'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-cut-plan-mrkr-entry-delete-popup',
  template: ''
})
export class CutPlanMrkrEntryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cutPlanEntry }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CutPlanMrkrEntryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.cutPlanEntry = cutPlanEntry;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/cut-plan-entry', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/cut-plan-entry', { outlets: { popup: null } }]);
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
