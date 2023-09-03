import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  InstituteMasterComponent,
  InstituteMasterDetailComponent,
  InstituteMasterUpdateComponent,
  InstituteMasterDeletePopupComponent,
  InstituteMasterDeleteDialogComponent,
  instituteMasterRoute,
  instituteMasterPopupRoute
} from './';

const ENTITY_STATES = [...instituteMasterRoute, ...instituteMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    InstituteMasterComponent,
    InstituteMasterDetailComponent,
    InstituteMasterUpdateComponent,
    InstituteMasterDeleteDialogComponent,
    InstituteMasterDeletePopupComponent
  ],
  entryComponents: [
    InstituteMasterComponent,
    InstituteMasterUpdateComponent,
    InstituteMasterDeleteDialogComponent,
    InstituteMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalInstituteMasterModule {}
