import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ConveyanceReportComponent, conveyanceReportRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...conveyanceReportRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ConveyanceReportComponent],
  entryComponents: [ConveyanceReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalConveyanceReportModule {}
