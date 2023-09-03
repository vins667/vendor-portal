import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE, HomeComponent } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild([HOME_ROUTE]), SnotifyModule],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService],
  declarations: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalHomeModule {}
