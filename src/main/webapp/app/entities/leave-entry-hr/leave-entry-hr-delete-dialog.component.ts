import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ILeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { LeaveEntryHrService } from './leave-entry-hr.service';

@Component({
  selector: 'jhi-leave-entry-hr-delete-dialog',
  templateUrl: './leave-entry-hr-delete-dialog.component.html'
})
export class LeaveEntryHrDeleteDialogComponent {
  leaveEntryHr: ILeaveEntryHr;

  constructor(
    protected leaveEntryHrService: LeaveEntryHrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.leaveEntryHrService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'leaveEntryHrListModification',
        content: 'Deleted an leaveEntryHr'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-leave-entry-hr-delete-popup',
  template: ''
})
export class LeaveEntryHrDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ leaveEntryHr }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LeaveEntryHrDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.leaveEntryHr = leaveEntryHr;
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
