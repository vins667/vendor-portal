import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  PayTermMasterComponent,
  PayTermMasterDetailComponent,
  PayTermMasterUpdateComponent,
  PayTermMasterDeletePopupComponent,
  PayTermMasterDeleteDialogComponent,
  payTermMasterRoute,
  payTermMasterPopupRoute
} from './';

const ENTITY_STATES = [...payTermMasterRoute, ...payTermMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PayTermMasterComponent,
    PayTermMasterDetailComponent,
    PayTermMasterUpdateComponent,
    PayTermMasterDeleteDialogComponent,
    PayTermMasterDeletePopupComponent
  ],
  entryComponents: [
    PayTermMasterComponent,
    PayTermMasterUpdateComponent,
    PayTermMasterDeleteDialogComponent,
    PayTermMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalPayTermMasterModule {}
