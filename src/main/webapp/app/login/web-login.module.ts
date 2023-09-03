import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { WEB_LOGIN_ROUTE, WebLoginComponent } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild([WEB_LOGIN_ROUTE]), SnotifyModule],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService],
  declarations: [WebLoginComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalWebLoginModule {}
