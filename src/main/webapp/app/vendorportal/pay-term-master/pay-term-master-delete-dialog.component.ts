import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';
import { PayTermMasterService } from './pay-term-master.service';

@Component({
  selector: 'jhi-pay-term-master-delete-dialog',
  templateUrl: './pay-term-master-delete-dialog.component.html'
})
export class PayTermMasterDeleteDialogComponent {
  payTermMaster: IPayTermMaster;

  constructor(
    protected payTermMasterService: PayTermMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.payTermMasterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'payTermMasterListModification',
        content: 'Deleted an payTermMaster'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pay-term-master-delete-popup',
  template: ''
})
export class PayTermMasterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ payTermMaster }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PayTermMasterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.payTermMaster = payTermMaster;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/pay-term-master', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/pay-term-master', { outlets: { popup: null } }]);
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
