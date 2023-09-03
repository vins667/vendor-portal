import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';
import { AssetSoftTypeMasterService } from './asset-soft-type-master.service';

@Component({
  selector: 'jhi-asset-soft-type-master-delete-dialog',
  templateUrl: './asset-soft-type-master-delete-dialog.component.html'
})
export class AssetSoftTypeMasterDeleteDialogComponent {
  assetSoftTypeMaster: IAssetSoftTypeMaster;

  constructor(
    protected assetSoftTypeMasterService: AssetSoftTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetSoftTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetSoftTypeMasterListModification',
        content: 'Deleted an assetSoftTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-soft-type-master-delete-popup',
  template: ''
})
export class AssetSoftTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetSoftTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetSoftTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetSoftTypeMaster = assetSoftTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['asset-soft-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['asset-soft-type-master', { outlets: { popup: null } }]);
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
