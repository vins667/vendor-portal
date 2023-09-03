import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { DeliverChallanApprovalComponent, DeliverChallanApprovalUpdateComponent, deliverChallanApprovalRoute } from './';

const ENTITY_STATES = [...deliverChallanApprovalRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [DeliverChallanApprovalComponent, DeliverChallanApprovalUpdateComponent],
  entryComponents: [DeliverChallanApprovalComponent, DeliverChallanApprovalUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalDeliverChallanApprovalModule {}
