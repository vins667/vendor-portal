import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMMRMaster } from 'app/shared/model/mmr-master.model';
import { MMRMasterService } from './mmr-master.service';

@Component({
  selector: 'jhi-mmr-master-delete-dialog',
  templateUrl: './mmr-master-delete-dialog.component.html'
})
export class MMRMasterDeleteDialogComponent {
  mMRMaster: IMMRMaster;

  constructor(protected mMRMasterService: MMRMasterService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMRMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMRMasterListModification',
        content: 'Deleted an mMRMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mmr-master-delete-popup',
  template: ''
})
export class MMRMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMRMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMRMasterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMRMaster = mMRMaster;
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
