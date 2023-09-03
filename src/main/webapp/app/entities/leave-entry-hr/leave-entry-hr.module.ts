import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  LeaveEntryHrComponent,
  LeaveEntryHrDetailComponent,
  LeaveEntryHrUpdateComponent,
  LeaveEntryHrDeletePopupComponent,
  LeaveEntryHrDeleteDialogComponent,
  leaveEntryHrRoute,
  leaveEntryHrPopupRoute,
  EmployeeSearchComponent
} from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
const ENTITY_STATES = [...leaveEntryHrRoute, ...leaveEntryHrPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    LeaveEntryHrComponent,
    LeaveEntryHrDetailComponent,
    LeaveEntryHrUpdateComponent,
    LeaveEntryHrDeleteDialogComponent,
    LeaveEntryHrDeletePopupComponent,
    EmployeeSearchComponent
  ],
  entryComponents: [
    LeaveEntryHrComponent,
    LeaveEntryHrUpdateComponent,
    LeaveEntryHrDeleteDialogComponent,
    LeaveEntryHrDeletePopupComponent,
    EmployeeSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalLeaveEntryHrModule {}
