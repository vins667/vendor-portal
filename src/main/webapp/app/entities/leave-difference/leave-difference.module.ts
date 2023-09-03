import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { LeaveDifferenceComponent, leaveDifferenceRoute, leaveDifferencePopupRoute } from './';

const ENTITY_STATES = [...leaveDifferenceRoute, ...leaveDifferencePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [LeaveDifferenceComponent],
  entryComponents: [LeaveDifferenceComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalLeaveDifferenceModule {}
