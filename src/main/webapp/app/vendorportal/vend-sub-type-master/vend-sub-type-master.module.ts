import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  VendSubTypeMasterComponent,
  VendSubTypeMasterDetailComponent,
  VendSubTypeMasterUpdateComponent,
  VendSubTypeMasterDeletePopupComponent,
  VendSubTypeMasterDeleteDialogComponent,
  vendSubTypeMasterRoute,
  vendSubTypeMasterPopupRoute
} from './index';

const ENTITY_STATES = [...vendSubTypeMasterRoute, ...vendSubTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VendSubTypeMasterComponent,
    VendSubTypeMasterDetailComponent,
    VendSubTypeMasterUpdateComponent,
    VendSubTypeMasterDeleteDialogComponent,
    VendSubTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    VendSubTypeMasterComponent,
    VendSubTypeMasterUpdateComponent,
    VendSubTypeMasterDeleteDialogComponent,
    VendSubTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalVendSubTypeMasterModule {}
