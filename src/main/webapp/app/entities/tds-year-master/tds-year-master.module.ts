import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TdsYearMasterComponent,
  TdsYearMasterDetailComponent,
  TdsYearMasterUpdateComponent,
  TdsYearMasterDeletePopupComponent,
  TdsYearMasterDeleteDialogComponent,
  tdsYearMasterRoute,
  tdsYearMasterPopupRoute
} from './';

const ENTITY_STATES = [...tdsYearMasterRoute, ...tdsYearMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TdsYearMasterComponent,
    TdsYearMasterDetailComponent,
    TdsYearMasterUpdateComponent,
    TdsYearMasterDeleteDialogComponent,
    TdsYearMasterDeletePopupComponent
  ],
  entryComponents: [
    TdsYearMasterComponent,
    TdsYearMasterUpdateComponent,
    TdsYearMasterDeleteDialogComponent,
    TdsYearMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsYearMasterModule {}
