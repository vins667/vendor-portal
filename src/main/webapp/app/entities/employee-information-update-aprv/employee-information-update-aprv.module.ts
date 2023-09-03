import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  EmployeeInformationUpdateAprvComponent,
  EmployeeInformationUpdateAprvDetailComponent,
  EmployeeInformationUpdateAprvUpdateComponent,
  employeeInformationUpdateAprvRoute,
  employeeInformationUpdateAprvPopupRoute
} from './';

const ENTITY_STATES = [...employeeInformationUpdateAprvRoute, ...employeeInformationUpdateAprvPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    EmployeeInformationUpdateAprvComponent,
    EmployeeInformationUpdateAprvDetailComponent,
    EmployeeInformationUpdateAprvUpdateComponent
  ],
  entryComponents: [EmployeeInformationUpdateAprvComponent, EmployeeInformationUpdateAprvUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalEmployeeInformationUpdateAprvModule {}
