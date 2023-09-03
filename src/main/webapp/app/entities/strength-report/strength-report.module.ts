import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { StrengthReportComponent, strengthReportRoute, strengthReportPopupRoute } from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...strengthReportRoute, ...strengthReportPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [StrengthReportComponent],
  entryComponents: [StrengthReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalStrengthReportModule {}
