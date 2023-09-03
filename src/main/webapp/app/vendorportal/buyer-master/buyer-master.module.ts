import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  BuyerMasterComponent,
  BuyerMasterDetailComponent,
  BuyerMasterUpdateComponent,
  BuyerMasterDeletePopupComponent,
  BuyerMasterDeleteDialogComponent,
  buyerMasterRoute,
  buyerMasterPopupRoute
} from './index';

const ENTITY_STATES = [...buyerMasterRoute, ...buyerMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BuyerMasterComponent,
    BuyerMasterDetailComponent,
    BuyerMasterUpdateComponent,
    BuyerMasterDeleteDialogComponent,
    BuyerMasterDeletePopupComponent
  ],
  entryComponents: [BuyerMasterComponent, BuyerMasterUpdateComponent, BuyerMasterDeleteDialogComponent, BuyerMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalBuyerMasterModule {}
