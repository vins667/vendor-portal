import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { DirectBookingEntryComponent } from './direct-booking-entry.component';
import { DirectBookingEntryDetailComponent } from './direct-booking-entry-detail.component';
import { DirectBookingEntryUpdateComponent } from './direct-booking-entry-update.component';
import { DirectBookingEntryDeleteDialogComponent } from './direct-booking-entry-delete-dialog.component';
import { directBookingEntryRoute } from './direct-booking-entry.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(directBookingEntryRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule
  ],
  declarations: [
    DirectBookingEntryComponent,
    DirectBookingEntryDetailComponent,
    DirectBookingEntryUpdateComponent,
    DirectBookingEntryDeleteDialogComponent
  ],
  entryComponents: [DirectBookingEntryDeleteDialogComponent]
})
export class VamaniportalDirectBookingEntryModule {}
