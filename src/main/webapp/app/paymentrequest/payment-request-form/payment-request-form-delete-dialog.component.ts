import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IPaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { PaymentRequestFormService } from 'app/paymentrequest/payment-request-form/payment-request-form.service';

@Component({
  selector: 'jhi-payment-request-form-delete-dialog',
  templateUrl: './payment-request-form-delete-dialog.component.html'
})
export class PaymentRequestFormDeleteDialogComponent {
  paymentRequestForms: IPaymentRequestForm;

  constructor(
    protected paymentRequestFormService: PaymentRequestFormService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.paymentRequestFormService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'paymentRequestFormListModification',
        content: 'Deleted an Payment Request Form'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-payment-request-form-delete-popup',
  template: ''
})
export class PaymentRequestFormDeletePopComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ paymentRequestForm }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PaymentRequestFormDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.paymentRequestForms = paymentRequestForm;
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
