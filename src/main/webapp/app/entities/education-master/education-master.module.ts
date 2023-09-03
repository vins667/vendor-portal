import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  EducationMasterComponent,
  EducationMasterDetailComponent,
  EducationMasterUpdateComponent,
  EducationMasterDeletePopupComponent,
  EducationMasterDeleteDialogComponent,
  educationMasterRoute,
  educationMasterPopupRoute
} from './';

const ENTITY_STATES = [...educationMasterRoute, ...educationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EducationMasterComponent,
    EducationMasterDetailComponent,
    EducationMasterUpdateComponent,
    EducationMasterDeleteDialogComponent,
    EducationMasterDeletePopupComponent
  ],
  entryComponents: [
    EducationMasterComponent,
    EducationMasterUpdateComponent,
    EducationMasterDeleteDialogComponent,
    EducationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEducationMasterModule {}
