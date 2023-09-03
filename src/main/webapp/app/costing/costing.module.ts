import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalItemAvgActualModule } from 'app/costing/item-avg-actual/item-avg-actual.module';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'order-profitability',
        loadChildren: () => import('./order-profitability/order-profitability.module').then(m => m.VamaniportalOrderProfitabilityModule)
      },
      {
        path: 'product-act-avg',
        loadChildren: () => import('./item-avg-actual/item-avg-actual.module').then(m => m.VamaniportalItemAvgActualModule)
      }
    ])
  ],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalCostingDetailsModule {}
