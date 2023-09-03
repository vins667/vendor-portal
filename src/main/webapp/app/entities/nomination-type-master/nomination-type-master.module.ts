import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  NominationTypeMasterComponent,
  NominationTypeMasterDetailComponent,
  NominationTypeMasterUpdateComponent,
  NominationTypeMasterDeletePopupComponent,
  NominationTypeMasterDeleteDialogComponent,
  nominationTypeMasterRoute,
  nominationTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...nominationTypeMasterRoute, ...nominationTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    NominationTypeMasterComponent,
    NominationTypeMasterDetailComponent,
    NominationTypeMasterUpdateComponent,
    NominationTypeMasterDeleteDialogComponent,
    NominationTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    NominationTypeMasterComponent,
    NominationTypeMasterUpdateComponent,
    NominationTypeMasterDeleteDialogComponent,
    NominationTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalNominationTypeMasterModule {}
