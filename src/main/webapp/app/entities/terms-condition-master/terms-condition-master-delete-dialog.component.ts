import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ITermsConditionMaster } from 'app/shared/model/terms-condition-master.model';
import { TermsConditionMasterService } from './terms-condition-master.service';

@Component({
  selector: 'jhi-terms-condition-master-delete-dialog',
  templateUrl: './terms-condition-master-delete-dialog.component.html'
})
export class TermsConditionMasterDeleteDialogComponent {
  termsConditionMaster: ITermsConditionMaster;

  constructor(
    protected termsConditionMasterService: TermsConditionMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.termsConditionMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'termsConditionMasterListModification',
        content: 'Deleted an termsConditionMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-terms-condition-master-delete-popup',
  template: ''
})
export class TermsConditionMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ termsConditionMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TermsConditionMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.termsConditionMaster = termsConditionMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/terms-condition-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/terms-condition-master', { outlets: { popup: null } }]);
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
