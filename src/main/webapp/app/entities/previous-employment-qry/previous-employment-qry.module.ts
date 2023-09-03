import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PreviousEmploymentQryComponent, PreviousEmploymentQryDetailComponent, previousEmploymentQryRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...previousEmploymentQryRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [PreviousEmploymentQryComponent, PreviousEmploymentQryDetailComponent],
  entryComponents: [PreviousEmploymentQryComponent, PreviousEmploymentQryDetailComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPreviousEmploymentQryModule {}
