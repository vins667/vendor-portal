import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { DirectBookingApprovalEntryUpdateComponent } from './direct-booking-approval-entry-update.component';
import { directBookingApprovalEntryRoute } from './direct-booking-approval-entry.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { DirectBookingApprovalEntryComponent } from './direct-booking-approval-entry.component';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(directBookingApprovalEntryRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule
  ],
  declarations: [DirectBookingApprovalEntryComponent, DirectBookingApprovalEntryUpdateComponent],
  entryComponents: []
})
export class VamaniportalDirectBookingApprovalEntryModule {}
