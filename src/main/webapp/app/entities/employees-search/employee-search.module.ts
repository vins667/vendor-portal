import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { EmployeeSearchComponent, employeeSearchRoute, employeeSearchPopupRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PopoverModule } from 'ngx-smart-popover';

const ENTITY_STATES = [...employeeSearchRoute, ...employeeSearchPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PopoverModule],
  declarations: [EmployeeSearchComponent],
  entryComponents: [EmployeeSearchComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEmployeeSearchModule {}
