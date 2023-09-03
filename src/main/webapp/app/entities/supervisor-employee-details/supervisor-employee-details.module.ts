import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { SupervisorEmployeeDetailsUpdateComponent, supervisorEmployeeDetailsRoute } from './';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...supervisorEmployeeDetailsRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    OwlDateTimeModule,
    OwlNativeDateTimeModule
  ],

  declarations: [SupervisorEmployeeDetailsUpdateComponent],
  entryComponents: [SupervisorEmployeeDetailsUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalSupervisorEmployeeDetailsModule {}
