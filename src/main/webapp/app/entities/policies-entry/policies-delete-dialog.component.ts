import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPolicies } from 'app/shared/model/policies.model';
import { PoliciesEntryService } from './policies-entry.service';

@Component({
  selector: 'jhi-policies-delete-dialog',
  templateUrl: './policies-delete-dialog.component.html'
})
export class PoliciesDeleteDialogComponent {
  policies: IPolicies;

  constructor(
    protected policiesService: PoliciesEntryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.policiesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'policiesListModification',
        content: 'Deleted an policies'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-policies-delete-popup',
  template: ''
})
export class PoliciesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ policies }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PoliciesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.policies = policies;
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
