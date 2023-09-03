import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {
  LeaveMasterComponent,
  LeaveMasterDetailComponent,
  LeaveMasterUpdateComponent,
  LeaveMasterDeletePopupComponent,
  LeaveMasterDeleteDialogComponent,
  leaveMasterRoute,
  leaveMasterPopupRoute,
  LeaveRemarksDetailsUpdateComponent,
  LeaveMobileAttendanceComponent,
  LeaveMobileMapLocationComponent
} from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { AgmCoreModule } from '@agm/core';
import { PopoverModule } from 'ngx-smart-popover';

const ENTITY_STATES = [...leaveMasterRoute, ...leaveMasterPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule,
    PopoverModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDlcZrUXZdRqJ2eb8pk4OGn_1ku190Vcnw'
    })
  ],
  declarations: [
    LeaveMasterComponent,
    LeaveMasterDetailComponent,
    LeaveMasterUpdateComponent,
    LeaveMasterDeleteDialogComponent,
    LeaveMasterDeletePopupComponent,
    LeaveRemarksDetailsUpdateComponent,
    LeaveMobileAttendanceComponent,
    LeaveMobileMapLocationComponent
  ],
  entryComponents: [
    LeaveMasterComponent,
    LeaveMasterUpdateComponent,
    LeaveMasterDeleteDialogComponent,
    LeaveMasterDeletePopupComponent,
    LeaveRemarksDetailsUpdateComponent,
    LeaveMobileAttendanceComponent,
    LeaveMobileMapLocationComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalLeaveMasterModule {}
