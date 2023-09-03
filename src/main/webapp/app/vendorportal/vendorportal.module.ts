import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.VendorportalCountryModule)
      },
      {
        path: 'document-master',
        loadChildren: () => import('./document-master/document-master.module').then(m => m.VendorportalDocumentMasterModule)
      },
      {
        path: 'organization-type',
        loadChildren: () => import('./organization-type/organization-type.module').then(m => m.VendorportalOrganizationTypeModule)
      },
      {
        path: 'state',
        loadChildren: () => import('./state/state.module').then(m => m.VendorportalStateModule)
      },
      {
        path: 'transaction-nature',
        loadChildren: () => import('./transaction-nature/transaction-nature.module').then(m => m.VendorportalTransactionNatureModule)
      },
      {
        path: 'turnover-master',
        loadChildren: () => import('./turnover-master/turnover-master.module').then(m => m.VendorportalTurnoverMasterModule)
      },
      {
        path: 'vend-type-master',
        loadChildren: () => import('./vend-type-master/vend-type-master.module').then(m => m.VendorportalVendTypeMasterModule)
      },
      {
        path: 'vend-sub-type-master',
        loadChildren: () => import('./vend-sub-type-master/vend-sub-type-master.module').then(m => m.VendorportalVendSubTypeMasterModule)
      },
      {
        path: 'buyer-master',
        loadChildren: () => import('./buyer-master/buyer-master.module').then(m => m.VendorportalBuyerMasterModule)
      },
      {
        path: 'category-master',
        loadChildren: () => import('./category-master/category-master.module').then(m => m.VendorportalCategoryMasterModule)
      },
      {
        path: 'del-place-master',
        loadChildren: () => import('./del-place-master/del-place-master.module').then(m => m.VendorportalDelPlaceMasterModule)
      },
      {
        path: 'pay-term-master',
        loadChildren: () => import('./pay-term-master/pay-term-master.module').then(m => m.VendorportalPayTermMasterModule)
      },
      {
        path: 'currency-master',
        loadChildren: () => import('./currency-master/currency-master.module').then(m => m.VendorportalCurrencyMasterModule)
      },
      {
        path: 'tax-term-master',
        loadChildren: () => import('./tax-term-master/tax-term-master.module').then(m => m.VendorportalTaxTermMasterModule)
      },
      {
        path: 'template-master',
        loadChildren: () => import('./template-master/template-master.module').then(m => m.VendorportalTemplateMasterModule)
      },
      {
        path: 'quotation-master',
        loadChildren: () => import('./quotation-master/quotation-master.module').then(m => m.VendorportalQuotationMasterModule)
      },
      {
        path: 'vendors',
        loadChildren: () => import('../vendorportal/vendors/vendors.module').then(m => m.VamaniportalVendorsModule)
      },
      {
        path: 'vendor-audit-ques-master',
        loadChildren: () =>
          import('./vendor-audit-ques-master/vendor-audit-ques-master.module').then(m => m.VamaniportalVendorAuditQuesMasterModule)
      },
      {
        path: 'audit-ques-buyer-mapping',
        loadChildren: () =>
          import('./audit-ques-buyer-mapping/audit-ques-buyer-mapping.module').then(m => m.VamaniportalAuditQuesBuyerMappingModule)
      },
      {
        path: 'vendor-buyer-audit-linking',
        loadChildren: () =>
          import('./vendor-buyer-audit-linking/vendor-buyer-audit-linking.module').then(m => m.VamaniportalVendorBuyerAuditLinkingModule)
      },
      {
        path: 'audit-group-master',
        loadChildren: () => import('./audit-group-master/audit-group-master.module').then(m => m.VamaniportalAuditGroupMasterModule)
      },
      {
        path: 'vendor-buyer-audit',
        loadChildren: () => import('./vendor-buyer-audit/vendor-buyer-audit.module').then(m => m.VamaniportalVendorBuyerAuditModule)
      },
      {
        path: 'delivery-term-master',
        loadChildren: () => import('./delivery-term-master/delivery-term-master.module').then(m => m.VendorportalDeliveryTermMasterModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalVendorportalModule {}
