import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  EducationTypeMasterComponent,
  EducationTypeMasterDetailComponent,
  EducationTypeMasterUpdateComponent,
  EducationTypeMasterDeletePopupComponent,
  EducationTypeMasterDeleteDialogComponent,
  educationTypeMasterRoute,
  educationTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...educationTypeMasterRoute, ...educationTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EducationTypeMasterComponent,
    EducationTypeMasterDetailComponent,
    EducationTypeMasterUpdateComponent,
    EducationTypeMasterDeleteDialogComponent,
    EducationTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    EducationTypeMasterComponent,
    EducationTypeMasterUpdateComponent,
    EducationTypeMasterDeleteDialogComponent,
    EducationTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEducationTypeMasterModule {}
