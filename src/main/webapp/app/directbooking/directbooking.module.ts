import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'direct-booking-entry',
        loadChildren: () => import('./direct-booking-entry/direct-booking-entry.module').then(m => m.VamaniportalDirectBookingEntryModule)
      },
      {
        path: 'direct-booking-approval-entry',
        loadChildren: () =>
          import('./direct-booking-approval-entry/direct-booking-approval-entry.module').then(
            m => m.VamaniportalDirectBookingApprovalEntryModule
          )
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalDirectbookingModule {}
