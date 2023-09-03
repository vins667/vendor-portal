import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'packing-line-issue',
        loadChildren: () => import('./packing-line-issue/packing-line-issue.module').then(m => m.VamaniportalPackingLineIssueModule)
      },
      {
        path: 'packing-production-entry',
        loadChildren: () =>
          import('./packing-production-entry/packing-production-entry.module').then(m => m.VamaniportalPackingProductionEntryModule)
      }
    ])
  ],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPackingModule {}
