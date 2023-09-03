import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { paymentRequestFormPopRoute, paymentRequestFormRoute } from 'app/paymentrequest/payment-request-form/payment-request-form.route';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { Ng2CompleterModule } from 'ng2-completer';
import { PaymentRequestApprovalComponent } from 'app/paymentrequest/payment-request-approval/payment-request-approval.component';
import { PaymentRequestApprovalUpdateComponent } from 'app/paymentrequest/payment-request-approval/payment-request-approval-update.component';
import {
  paymentRequestApprovalPopRoute,
  paymentRequestApprovalRoute
} from 'app/paymentrequest/payment-request-approval/payment-request-approval.route';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...paymentRequestApprovalRoute, ...paymentRequestApprovalPopRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    Ng2CompleterModule,
    SnotifyModule
  ],
  declarations: [PaymentRequestApprovalComponent, PaymentRequestApprovalUpdateComponent],
  entryComponents: [PaymentRequestApprovalComponent, PaymentRequestApprovalUpdateComponent]
})
export class VamaniportalPaymentRequestApprovalModule {}
