import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';
import { AssetWarrantyTypeMasterService } from './asset-warranty-type-master.service';

@Component({
  selector: 'jhi-asset-warranty-type-master-delete-dialog',
  templateUrl: './asset-warranty-type-master-delete-dialog.component.html'
})
export class AssetWarrantyTypeMasterDeleteDialogComponent {
  assetWarrantyTypeMaster: IAssetWarrantyTypeMaster;

  constructor(
    protected assetWarrantyTypeMasterService: AssetWarrantyTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetWarrantyTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetWarrantyTypeMasterListModification',
        content: 'Deleted an assetWarrantyTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-warranty-type-master-delete-popup',
  template: ''
})
export class AssetWarrantyTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetWarrantyTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetWarrantyTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetWarrantyTypeMaster = assetWarrantyTypeMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['asset-warranty-type-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['asset-warranty-type-master', { outlets: { popup: null } }]);
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
