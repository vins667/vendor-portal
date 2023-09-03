import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'payment-request-form',
        loadChildren: () => import('./payment-request-form/payment-request-form.module').then(m => m.VamaniportalPaymentRequestFormModule)
      },
      {
        path: 'payment-request-forward',
        loadChildren: () => import('./payment-request-forward/payment-request-forward.module').then(m => m.PaymentRequestForwardModule)
      },
      {
        path: 'payment-request-approval',
        loadChildren: () =>
          import('./payment-request-approval/payment-request-approval.module').then(m => m.VamaniportalPaymentRequestApprovalModule)
      },
      {
        path: 'payment-request-finance',
        loadChildren: () =>
          import('./payment-request-finance/payment-request-finance.module').then(m => m.VamaniportalPaymentRequestFinanceModule)
      },
      {
        path: 'payment-request-report',
        loadChildren: () =>
          import('./payment-request-report/payment-request-report.module').then(m => m.VamaniportalPaymentRequestReportModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPaymentrequestModule {}
