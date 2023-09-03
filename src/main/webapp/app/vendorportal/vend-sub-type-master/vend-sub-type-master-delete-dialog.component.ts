import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';
import { VendSubTypeMasterService } from './vend-sub-type-master.service';

@Component({
  selector: 'jhi-vend-sub-type-master-delete-dialog',
  templateUrl: './vend-sub-type-master-delete-dialog.component.html'
})
export class VendSubTypeMasterDeleteDialogComponent {
  vendSubTypeMaster: IVendSubTypeMaster;

  constructor(
    protected vendSubTypeMasterService: VendSubTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vendSubTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vendSubTypeMasterListModification',
        content: 'Deleted an vendSubTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vend-sub-type-master-delete-popup',
  template: ''
})
export class VendSubTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendSubTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VendSubTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vendSubTypeMaster = vendSubTypeMaster;
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
