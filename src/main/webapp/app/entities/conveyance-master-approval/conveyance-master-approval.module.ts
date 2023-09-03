import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

import {
  ConveyanceMasterApprovalComponent,
  ConveyanceMasterApprovalUpdateComponent,
  conveyanceMasterApprovalRoute,
  conveyanceMasterApprovalPopupRoute
} from './';

const ENTITY_STATES = [...conveyanceMasterApprovalRoute, ...conveyanceMasterApprovalPopupRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [ConveyanceMasterApprovalComponent, ConveyanceMasterApprovalUpdateComponent],
  entryComponents: [ConveyanceMasterApprovalComponent, ConveyanceMasterApprovalUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalConveyanceMasterApprovalModule {}
