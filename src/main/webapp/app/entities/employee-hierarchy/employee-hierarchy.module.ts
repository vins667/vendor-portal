import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { EmployeeHierarchyComponent, employeeHierarchyRoute } from './';

const ENTITY_STATES = [...employeeHierarchyRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [EmployeeHierarchyComponent],
  entryComponents: [EmployeeHierarchyComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEmployeeHierarchyModule {}
