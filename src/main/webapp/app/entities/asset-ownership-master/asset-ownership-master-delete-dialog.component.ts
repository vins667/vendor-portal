import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';
import { AssetOwnershipMasterService } from './asset-ownership-master.service';

@Component({
  selector: 'jhi-asset-ownership-master-delete-dialog',
  templateUrl: './asset-ownership-master-delete-dialog.component.html'
})
export class AssetOwnershipMasterDeleteDialogComponent {
  assetOwnershipMaster: IAssetOwnershipMaster;

  constructor(
    protected assetOwnershipMasterService: AssetOwnershipMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetOwnershipMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetOwnershipMasterListModification',
        content: 'Deleted an assetOwnershipMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-ownership-master-delete-popup',
  template: ''
})
export class AssetOwnershipMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetOwnershipMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetOwnershipMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetOwnershipMaster = assetOwnershipMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/asset-ownership-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/asset-ownership-master', { outlets: { popup: null } }]);
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
