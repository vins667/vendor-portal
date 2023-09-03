import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaymentRequestForward } from '../payment-request-forward.model';
import { PaymentRequestForwardService } from '../service/payment-request-forward.service';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  templateUrl: './payment-request-forward-delete-dialog.component.html'
})
export class PaymentRequestForwardDeleteDialogComponent {
  paymentRequestForward?: IPaymentRequestForward;

  constructor(
    protected paymentRequestForwardService: PaymentRequestForwardService,
    protected activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymentRequestForwardService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'paymentRequestForwardListModification',
        content: 'Deleted an Payment Request Forward'
      });
      this.activeModal.close('deleted');
    });
  }
}
