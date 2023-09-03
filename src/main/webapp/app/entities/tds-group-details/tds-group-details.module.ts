import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TdsGroupDetailsComponent,
  TdsGroupDetailsDetailComponent,
  TdsGroupDetailsUpdateComponent,
  TdsGroupDetailsDeletePopupComponent,
  TdsGroupDetailsDeleteDialogComponent,
  tdsGroupDetailsRoute,
  tdsGroupDetailsPopupRoute
} from './';

const ENTITY_STATES = [...tdsGroupDetailsRoute, ...tdsGroupDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TdsGroupDetailsComponent,
    TdsGroupDetailsDetailComponent,
    TdsGroupDetailsUpdateComponent,
    TdsGroupDetailsDeleteDialogComponent,
    TdsGroupDetailsDeletePopupComponent
  ],
  entryComponents: [
    TdsGroupDetailsComponent,
    TdsGroupDetailsUpdateComponent,
    TdsGroupDetailsDeleteDialogComponent,
    TdsGroupDetailsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTdsGroupDetailsModule {}
