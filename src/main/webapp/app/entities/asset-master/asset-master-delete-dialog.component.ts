import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetMaster } from 'app/shared/model/asset-master.model';
import { AssetMasterService } from './asset-master.service';

@Component({
  selector: 'jhi-asset-master-delete-dialog',
  templateUrl: './asset-master-delete-dialog.component.html'
})
export class AssetMasterDeleteDialogComponent {
  assetMaster: IAssetMaster;

  constructor(
    protected assetMasterService: AssetMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetMasterListModification',
        content: 'Deleted an assetMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-master-delete-popup',
  template: ''
})
export class AssetMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetMaster = assetMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/asset-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/asset-master', { outlets: { popup: null } }]);
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
