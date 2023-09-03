import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { BuyerMasterService } from './buyer-master.service';

@Component({
  selector: 'jhi-buyer-master-delete-dialog',
  templateUrl: './buyer-master-delete-dialog.component.html'
})
export class BuyerMasterDeleteDialogComponent {
  buyerMaster: IBuyerMaster;

  constructor(
    protected buyerMasterService: BuyerMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.buyerMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'buyerMasterListModification',
        content: 'Deleted an buyerMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-buyer-master-delete-popup',
  template: ''
})
export class BuyerMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buyerMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BuyerMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.buyerMaster = buyerMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/buyer-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/buyer-master', { outlets: { popup: null } }]);
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
