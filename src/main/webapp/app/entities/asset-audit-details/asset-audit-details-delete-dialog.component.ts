import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { AssetAuditDetailsService } from './asset-audit-details.service';

@Component({
  selector: 'jhi-asset-audit-details-delete-dialog',
  templateUrl: './asset-audit-details-delete-dialog.component.html'
})
export class AssetAuditDetailsDeleteDialogComponent {
  assetAuditDetails: IAssetAuditDetails;

  constructor(
    protected assetAuditDetailsService: AssetAuditDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.assetAuditDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'assetAuditDetailsListModification',
        content: 'Deleted an assetAuditDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-asset-audit-details-delete-popup',
  template: ''
})
export class AssetAuditDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assetAuditDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AssetAuditDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.assetAuditDetails = assetAuditDetails;
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
