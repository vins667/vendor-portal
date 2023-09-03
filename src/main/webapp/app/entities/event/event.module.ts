import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { EVENT_ROUTE, EventComponent, EventOthersComponent } from './';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(EVENT_ROUTE),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    OwlDateTimeModule,
    OwlNativeDateTimeModule
  ],
  declarations: [EventComponent, EventOthersComponent],
  entryComponents: [EventOthersComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEventModule {}
