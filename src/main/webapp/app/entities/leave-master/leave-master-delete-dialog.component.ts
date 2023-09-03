import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { LeaveMasterService } from './leave-master.service';

@Component({
  selector: 'jhi-leave-master-delete-dialog',
  templateUrl: './leave-master-delete-dialog.component.html'
})
export class LeaveMasterDeleteDialogComponent {
  leaveMaster: ILeaveMaster;

  constructor(
    protected leaveMasterService: LeaveMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.leaveMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'leaveMasterListModification',
        content: 'Deleted an leaveMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-leave-master-delete-popup',
  template: ''
})
export class LeaveMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ leaveMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LeaveMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.leaveMaster = leaveMaster;
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
