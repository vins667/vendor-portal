import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TdsGroupMasterComponent,
  TdsGroupMasterDetailComponent,
  TdsGroupMasterUpdateComponent,
  TdsGroupMasterDeletePopupComponent,
  TdsGroupMasterDeleteDialogComponent,
  tdsGroupMasterRoute,
  tdsGroupMasterPopupRoute
} from './';

const ENTITY_STATES = [...tdsGroupMasterRoute, ...tdsGroupMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TdsGroupMasterComponent,
    TdsGroupMasterDetailComponent,
    TdsGroupMasterUpdateComponent,
    TdsGroupMasterDeleteDialogComponent,
    TdsGroupMasterDeletePopupComponent
  ],
  entryComponents: [
    TdsGroupMasterComponent,
    TdsGroupMasterUpdateComponent,
    TdsGroupMasterDeleteDialogComponent,
    TdsGroupMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsGroupMasterModule {}
