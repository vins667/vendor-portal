import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaymentRequestReportComponent } from 'app/paymentrequest/payment-request-report/payment-request-report.component';
import { paymentRequestReportRoute } from 'app/paymentrequest/payment-request-report/payment-request-report.route';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...paymentRequestReportRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [PaymentRequestReportComponent],
  entryComponents: [PaymentRequestReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalPaymentRequestReportModule {}
