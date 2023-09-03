import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { paymentRequestFormPopRoute, paymentRequestFormRoute } from 'app/paymentrequest/payment-request-form/payment-request-form.route';
import { PaymentRequestFormComponent } from 'app/paymentrequest/payment-request-form/payment-request-form.component';
import { PaymentRequestFormUpdateComponent } from 'app/paymentrequest/payment-request-form/payment-request-form-update.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { DragDropDirective } from 'app/paymentrequest/payment-request-form/drag-drop.directive';
import { Ng2CompleterModule } from 'ng2-completer';
import {
  PaymentRequestFormDeleteDialogComponent,
  PaymentRequestFormDeletePopComponent
} from 'app/paymentrequest/payment-request-form/payment-request-form-delete-dialog.component';
import { PaymentRequestFormDetailsComponent } from 'app/paymentrequest/payment-request-form/payment-request-form-details.component';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...paymentRequestFormRoute, ...paymentRequestFormPopRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    Ng2CompleterModule,
    SnotifyModule
  ],
  declarations: [
    DragDropDirective,
    PaymentRequestFormComponent,
    PaymentRequestFormDetailsComponent,
    PaymentRequestFormUpdateComponent,
    PaymentRequestFormDeleteDialogComponent,
    PaymentRequestFormDeletePopComponent
  ],
  entryComponents: [
    PaymentRequestFormComponent,
    PaymentRequestFormUpdateComponent,
    PaymentRequestFormDeleteDialogComponent,
    PaymentRequestFormDeletePopComponent
  ]
})
export class VamaniportalPaymentRequestFormModule {}
