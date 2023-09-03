import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';
import { MobileAttendanceService } from './mobile-attendance.service';

@Component({
  selector: 'jhi-mobile-attendance-delete-dialog',
  templateUrl: './mobile-attendance-delete-dialog.component.html'
})
export class MobileAttendanceDeleteDialogComponent {
  mobileAttendance: IMobileAttendance;

  constructor(
    protected mobileAttendanceService: MobileAttendanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mobileAttendanceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mobileAttendanceListModification',
        content: 'Deleted an mobileAttendance'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mobile-attendance-delete-popup',
  template: ''
})
export class MobileAttendanceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mobileAttendance }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MobileAttendanceDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
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
