import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { MmrReportComponent, mmrReportRoute } from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...mmrReportRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [MmrReportComponent],
  entryComponents: [MmrReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMmrReportModule {}
