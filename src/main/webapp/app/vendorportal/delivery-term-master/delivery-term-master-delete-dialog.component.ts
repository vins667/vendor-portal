import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { DeliveryTermMasterService } from './delivery-term-master.service';

@Component({
  selector: 'jhi-delivery-term-master-delete-dialog',
  templateUrl: './delivery-term-master-delete-dialog.component.html'
})
export class DeliveryTermMasterDeleteDialogComponent {
  deliveryTermMaster: IDeliveryTermMaster;

  constructor(
    protected deliveryTermMasterService: DeliveryTermMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.deliveryTermMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'deliveryTermMasterListModification',
        content: 'Deleted an deliveryTermMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-delivery-term-master-delete-popup',
  template: ''
})
export class DeliveryTermMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ deliveryTermMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DeliveryTermMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.deliveryTermMaster = deliveryTermMaster;
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
