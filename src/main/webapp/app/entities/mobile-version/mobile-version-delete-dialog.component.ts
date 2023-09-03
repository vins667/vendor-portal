import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMobileVersion } from 'app/shared/model/mobile-version.model';
import { MobileVersionService } from './mobile-version.service';

@Component({
  selector: 'jhi-mobile-version-delete-dialog',
  templateUrl: './mobile-version-delete-dialog.component.html'
})
export class MobileVersionDeleteDialogComponent {
  mobileVersion: IMobileVersion;

  constructor(
    protected mobileVersionService: MobileVersionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mobileVersionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mobileVersionListModification',
        content: 'Deleted an mobileVersion'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mobile-version-delete-popup',
  template: ''
})
export class MobileVersionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mobileVersion }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MobileVersionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mobileVersion = mobileVersion;
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
