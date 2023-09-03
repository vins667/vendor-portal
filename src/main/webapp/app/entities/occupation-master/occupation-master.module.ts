import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  OccupationMasterComponent,
  OccupationMasterDetailComponent,
  OccupationMasterUpdateComponent,
  OccupationMasterDeletePopupComponent,
  OccupationMasterDeleteDialogComponent,
  occupationMasterRoute,
  occupationMasterPopupRoute
} from './';

const ENTITY_STATES = [...occupationMasterRoute, ...occupationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OccupationMasterComponent,
    OccupationMasterDetailComponent,
    OccupationMasterUpdateComponent,
    OccupationMasterDeleteDialogComponent,
    OccupationMasterDeletePopupComponent
  ],
  entryComponents: [
    OccupationMasterComponent,
    OccupationMasterUpdateComponent,
    OccupationMasterDeleteDialogComponent,
    OccupationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalOccupationMasterModule {}
