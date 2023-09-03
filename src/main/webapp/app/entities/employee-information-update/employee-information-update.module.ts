import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import {
  EmployeeInformationUpdateComponent,
  EmployeeInformationUpdateDetailComponent,
  EmployeeInformationUpdateUpdateComponent,
  EmployeeInformationUpdateDeletePopupComponent,
  EmployeeInformationUpdateDeleteDialogComponent,
  employeeInformationUpdateRoute,
  employeeInformationUpdatePopupRoute
} from './';

const ENTITY_STATES = [...employeeInformationUpdateRoute, ...employeeInformationUpdatePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    EmployeeInformationUpdateComponent,
    EmployeeInformationUpdateDetailComponent,
    EmployeeInformationUpdateUpdateComponent,
    EmployeeInformationUpdateDeleteDialogComponent,
    EmployeeInformationUpdateDeletePopupComponent
  ],
  entryComponents: [
    EmployeeInformationUpdateComponent,
    EmployeeInformationUpdateUpdateComponent,
    EmployeeInformationUpdateDeleteDialogComponent,
    EmployeeInformationUpdateDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalEmployeeInformationUpdateModule {}
