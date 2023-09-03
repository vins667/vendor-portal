import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bill-register',
        loadChildren: () => import('./bill-register/bill-register.module').then(m => m.VamaniportalBillRegisterModule)
      },
      {
        path: 'project-closings',
        loadChildren: () => import('./sales-order-closing/sales-order-closing.module').then(m => m.VamaniportalSalesOrderClosingModule)
      },
      {
        path: 'followup-buyers',
        loadChildren: () => import('./followup-buyer/followup-buyer.module').then(m => m.VamaniportalFollowupBuyerModule)
      },
      {
        path: 'job-work-followups',
        loadChildren: () => import('./job-work-followup/job-work-followup.module').then(m => m.VamaniportalJobWorkFollowupModule)
      },
      {
        path: 'job-work-followup-details',
        loadChildren: () =>
          import('./job-work-followup-details/job-work-followup-details.module').then(m => m.VamaniportalJobWorkFollowupDetailsModule)
      },
      {
        path: 'bank-reconcilation-upload',
        loadChildren: () =>
          import('./bank-reconciliation-upload/bank-reconciliation-upload.module').then(m => m.VamaniportalBankReconciliationUploadModule)
      },
      {
        path: 'gst-reconcilation-upload',
        loadChildren: () =>
          import('./gst-reconciliation-upload/gst-reconciliation-upload.module').then(m => m.VamaniportalGstReconciliationUploadModule)
      },
      {
        path: 'bill-register-import',
        loadChildren: () => import('./bill-register-import/bill-register-import.module').then(m => m.VamaniportalBillRegisterImportModule)
      },
      {
        path: 'payment-advice',
        loadChildren: () => import('./fin-payment-advice/fin-payment-advice.module').then(m => m.VamaniportalFinPaymentAdviceModule)
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalFianceModule {}
