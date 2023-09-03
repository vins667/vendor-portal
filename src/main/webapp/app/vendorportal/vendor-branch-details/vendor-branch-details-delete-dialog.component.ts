import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';
import { VendorBranchDetailsService } from './vendor-branch-details.service';

@Component({
  selector: 'jhi-vendor-branch-details-delete-dialog',
  templateUrl: './vendor-branch-details-delete-dialog.component.html'
})
export class VendorBranchDetailsDeleteDialogComponent {
  vendorBranchDetails: IVendorBranchDetails;

  constructor(
    protected vendorBranchDetailsService: VendorBranchDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vendorBranchDetailsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vendorBranchDetailsListModification',
        content: 'Deleted an vendorBranchDetails'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vendor-branch-details-delete-popup',
  template: ''
})
export class VendorBranchDetailsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendorBranchDetails }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VendorBranchDetailsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vendorBranchDetails = vendorBranchDetails;
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
