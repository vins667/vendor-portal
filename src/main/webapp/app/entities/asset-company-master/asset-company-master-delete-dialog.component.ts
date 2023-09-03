import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';
import { AssetCompanyMasterService } from './asset-company-master.service';

@Component({
  selector: 'jhi-asset-company-master-delete-dialog',
  templateUrl: './asset-company-master-delete-dialog.component.html'
})
export class AssetCompanyMasterDeleteDialogComponent {
  assetCompanyMaster: IAssetCompanyMaster;

  constructor(
    protected assetCompanyMasterService: AssetCompanyMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetCompanyMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetCompanyMasterListModification',
        content: 'Deleted an assetCompanyMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-company-master-delete-popup',
  template: ''
})
export class AssetCompanyMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetCompanyMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetCompanyMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetCompanyMaster = assetCompanyMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/asset-company-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/asset-company-master', { outlets: { popup: null } }]);
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
