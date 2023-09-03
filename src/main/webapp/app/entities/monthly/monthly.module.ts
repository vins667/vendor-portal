import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { monthlyRoute } from 'app/entities/monthly/monthly.route';
import { MonthlyComponent, MonthlyDetailComponent } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

const ENTITY_STATES = [...monthlyRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [MonthlyComponent, MonthlyDetailComponent],
  entryComponents: [MonthlyComponent, MonthlyDetailComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalMonthlyModule {}
