import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { paymentRequestFormPopRoute, paymentRequestFormRoute } from 'app/paymentrequest/payment-request-form/payment-request-form.route';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { Ng2CompleterModule } from 'ng2-completer';
import { PaymentRequestFinanceComponent } from 'app/paymentrequest/payment-request-finance/payment-request-finance.component';
import { PaymentRequestFinanceUpdateComponent } from 'app/paymentrequest/payment-request-finance/payment-request-finance-update.component';
import {
  paymentRequestFinancePopRoute,
  paymentRequestFinanceRoute
} from 'app/paymentrequest/payment-request-finance/payment-request-finance.route';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...paymentRequestFinancePopRoute, ...paymentRequestFinanceRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    Ng2CompleterModule,
    SnotifyModule
  ],
  declarations: [PaymentRequestFinanceComponent, PaymentRequestFinanceUpdateComponent],
  entryComponents: [PaymentRequestFinanceComponent, PaymentRequestFinanceUpdateComponent]
})
export class VamaniportalPaymentRequestFinanceModule {}
