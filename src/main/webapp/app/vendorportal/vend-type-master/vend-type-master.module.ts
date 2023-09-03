import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  VendTypeMasterComponent,
  VendTypeMasterDetailComponent,
  VendTypeMasterUpdateComponent,
  VendTypeMasterDeletePopupComponent,
  VendTypeMasterDeleteDialogComponent,
  vendTypeMasterRoute,
  vendTypeMasterPopupRoute
} from './index';

const ENTITY_STATES = [...vendTypeMasterRoute, ...vendTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    VendTypeMasterComponent,
    VendTypeMasterDetailComponent,
    VendTypeMasterUpdateComponent,
    VendTypeMasterDeleteDialogComponent,
    VendTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    VendTypeMasterComponent,
    VendTypeMasterUpdateComponent,
    VendTypeMasterDeleteDialogComponent,
    VendTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalVendTypeMasterModule {}
