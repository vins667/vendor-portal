import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';
import { AssetSubTypeDetailMasterService } from './asset-sub-type-detail-master.service';

@Component({
  selector: 'jhi-asset-sub-type-detail-master-delete-dialog',
  templateUrl: './asset-sub-type-detail-master-delete-dialog.component.html'
})
export class AssetSubTypeDetailMasterDeleteDialogComponent {
  assetSubTypeDetailMaster: IAssetSubTypeDetailMaster;

  constructor(
    protected assetSubTypeDetailMasterService: AssetSubTypeDetailMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetSubTypeDetailMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetSubTypeDetailMasterListModification',
        content: 'Deleted an assetSubTypeDetailMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-sub-type-detail-master-delete-popup',
  template: ''
})
export class AssetSubTypeDetailMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSubTypeDetailMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetSubTypeDetailMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['asset-sub-type-detail-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['asset-sub-type-detail-master', { outlets: { popup: null } }]);
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
