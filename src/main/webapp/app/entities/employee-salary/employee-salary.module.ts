import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { EmployeeSalaryComponent } from 'app/entities/employee-salary/employee-salary.component';
import { employeeSalaryRoute } from 'app/entities/employee-salary/employee-salary.route';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OwlDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...employeeSalaryRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    ReactiveFormsModule,
    FormsModule,
    OwlDateTimeModule
  ],
  declarations: [EmployeeSalaryComponent],
  entryComponents: [EmployeeSalaryComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalEmployeeSalaryModule {}
