import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { QlikDashboardComponent } from './qlik-dashboard.component';
import { QLIK_DASHBOARD_ROUTE } from './qlik-dashboard.route';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild([QLIK_DASHBOARD_ROUTE]), SnotifyModule],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService],
  declarations: [QlikDashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalQlikDashboardModule {}
