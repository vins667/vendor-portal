import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import { CountdownModule } from 'ngx-countdown';
import {
  PasswordStrengthBarComponent,
  RegisterComponent,
  PasswordComponent,
  PasswordResetInitComponent,
  SettingsComponent,
  accountState
} from './';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(accountState), TextMaskModule, CountdownModule],
  declarations: [RegisterComponent, PasswordComponent, PasswordStrengthBarComponent, PasswordResetInitComponent, SettingsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAccountModule {}
