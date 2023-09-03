import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  DepartmentMasterComponent,
  DepartmentMasterDetailComponent,
  DepartmentMasterUpdateComponent,
  DepartmentMasterDeletePopupComponent,
  DepartmentMasterDeleteDialogComponent,
  departmentMasterRoute,
  departmentMasterPopupRoute
} from './';

const ENTITY_STATES = [...departmentMasterRoute, ...departmentMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DepartmentMasterComponent,
    DepartmentMasterDetailComponent,
    DepartmentMasterUpdateComponent,
    DepartmentMasterDeleteDialogComponent,
    DepartmentMasterDeletePopupComponent
  ],
  entryComponents: [
    DepartmentMasterComponent,
    DepartmentMasterUpdateComponent,
    DepartmentMasterDeleteDialogComponent,
    DepartmentMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalDepartmentMasterModule {}
