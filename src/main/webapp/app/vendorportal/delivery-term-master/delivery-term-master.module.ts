import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {
  DeliveryTermMasterComponent,
  DeliveryTermMasterDetailComponent,
  DeliveryTermMasterUpdateComponent,
  DeliveryTermMasterDeletePopupComponent,
  DeliveryTermMasterDeleteDialogComponent,
  deliveryTermMasterRoute,
  deliveryTermMasterPopupRoute
} from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...deliveryTermMasterRoute, ...deliveryTermMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DeliveryTermMasterComponent,
    DeliveryTermMasterDetailComponent,
    DeliveryTermMasterUpdateComponent,
    DeliveryTermMasterDeleteDialogComponent,
    DeliveryTermMasterDeletePopupComponent
  ],
  entryComponents: [
    DeliveryTermMasterComponent,
    DeliveryTermMasterUpdateComponent,
    DeliveryTermMasterDeleteDialogComponent,
    DeliveryTermMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalDeliveryTermMasterModule {}
