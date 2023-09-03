import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';
import { AssetLocationMasterService } from './asset-location-master.service';

@Component({
  selector: 'jhi-asset-location-master-delete-dialog',
  templateUrl: './asset-location-master-delete-dialog.component.html'
})
export class AssetLocationMasterDeleteDialogComponent {
  assetLocationMaster: IAssetLocationMaster;

  constructor(
    protected assetLocationMasterService: AssetLocationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetLocationMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetLocationMasterListModification',
        content: 'Deleted an assetLocationMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-location-master-delete-popup',
  template: ''
})
export class AssetLocationMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetLocationMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetLocationMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetLocationMaster = assetLocationMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/asset-location-master', { outlets: { popup: null } }]);
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/asset-location-master', { outlets: { popup: null } }]);
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
