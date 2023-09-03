import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { DeliveryChallanService } from './delivery-challan.service';

@Component({
  selector: 'jhi-delivery-challan-delete-dialog',
  templateUrl: './delivery-challan-delete-dialog.component.html'
})
export class DeliveryChallanDeleteDialogComponent {
  deliveryChallan: IDeliveryChallan;

  constructor(
    protected deliveryChallanService: DeliveryChallanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.deliveryChallanService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'deliveryChallanListModification',
        content: 'Deleted an deliveryChallan'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-delivery-challan-delete-popup',
  template: ''
})
export class DeliveryChallanDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ deliveryChallan }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DeliveryChallanDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.deliveryChallan = deliveryChallan;
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
