import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { LeavePendingReportComponent, leavePendingReportRoute, leavePendingReportPopupRoute } from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...leavePendingReportRoute, ...leavePendingReportPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [LeavePendingReportComponent],
  entryComponents: [LeavePendingReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalLeavePendingReportModule {}
