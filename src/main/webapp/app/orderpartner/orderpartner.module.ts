import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'view-orderpartner',
        loadChildren: () =>
          import('./orderpartner-document/orderpartner-document.module').then(m => m.VamaniportalOrderpartnerDocumentModule)
      }
    ])
  ],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalOrderpartnerModule {}
