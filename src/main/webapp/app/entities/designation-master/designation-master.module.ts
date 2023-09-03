import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  DesignationMasterComponent,
  DesignationMasterDetailComponent,
  DesignationMasterUpdateComponent,
  DesignationMasterDeletePopupComponent,
  DesignationMasterDeleteDialogComponent,
  designationMasterRoute,
  designationMasterPopupRoute
} from './';

const ENTITY_STATES = [...designationMasterRoute, ...designationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DesignationMasterComponent,
    DesignationMasterDetailComponent,
    DesignationMasterUpdateComponent,
    DesignationMasterDeleteDialogComponent,
    DesignationMasterDeletePopupComponent
  ],
  entryComponents: [
    DesignationMasterComponent,
    DesignationMasterUpdateComponent,
    DesignationMasterDeleteDialogComponent,
    DesignationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalDesignationMasterModule {}
