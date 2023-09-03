import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetDocumentMaster } from 'app/shared/model/asset-document-master.model';
import { AssetDocumentMasterService } from './asset-document-master.service';

@Component({
  selector: 'jhi-asset-document-master-delete-dialog',
  templateUrl: './asset-document-master-delete-dialog.component.html'
})
export class AssetDocumentMasterDeleteDialogComponent {
  assetDocumentMaster: IAssetDocumentMaster;

  constructor(
    protected assetDocumentMasterService: AssetDocumentMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetDocumentMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetDocumentMasterListModification',
        content: 'Deleted an assetDocumentMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-document-master-delete-popup',
  template: ''
})
export class AssetDocumentMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetDocumentMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetDocumentMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetDocumentMaster = assetDocumentMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/asset-document-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/asset-document-master', { outlets: { popup: null } }]);
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
