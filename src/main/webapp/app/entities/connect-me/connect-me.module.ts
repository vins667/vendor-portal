import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { ConnectMeComponent, CONNECT_ME_ROUTE } from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { PopoverModule } from 'ngx-smart-popover';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(CONNECT_ME_ROUTE), PopoverModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [ConnectMeComponent],
  entryComponents: [ConnectMeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalConnectMeModule {}
