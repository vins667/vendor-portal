import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { VendTypeMasterService } from './vend-type-master.service';

@Component({
  selector: 'jhi-vend-type-master-delete-dialog',
  templateUrl: './vend-type-master-delete-dialog.component.html'
})
export class VendTypeMasterDeleteDialogComponent {
  vendTypeMaster: IVendTypeMaster;

  constructor(
    protected vendTypeMasterService: VendTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.vendTypeMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'vendTypeMasterListModification',
        content: 'Deleted an vendTypeMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-vend-type-master-delete-popup',
  template: ''
})
export class VendTypeMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendTypeMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VendTypeMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.vendTypeMaster = vendTypeMaster;
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
