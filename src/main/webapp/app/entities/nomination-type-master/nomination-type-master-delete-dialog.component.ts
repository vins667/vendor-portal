import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';
import { NominationTypeMasterService } from './nomination-type-master.service';

@Component({
  selector: 'jhi-nomination-type-master-delete-dialog',
  templateUrl: './nomination-type-master-delete-dialog.component.html'
})
export class NominationTypeMasterDeleteDialogComponent {
  nominationTypeMaster: INominationTypeMaster;

  constructor(
    protected nominationTypeMasterService: NominationTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.nominationTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'nominationTypeMasterListModification',
        content: 'Deleted an nominationTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-nomination-type-master-delete-popup',
  template: ''
})
export class NominationTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nominationTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(NominationTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.nominationTypeMaster = nominationTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/nomination-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/nomination-type-master', { outlets: { popup: null } }]);
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
