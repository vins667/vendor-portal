import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {
  LeaveMasterApprovalComponent,
  leaveMasterHodRoute,
  leaveMasterHodPopupRoute,
  LeaveMasterRemarksDetailsUpdateComponent,
  MapLocationComponent
} from './';
import { PopoverModule } from 'ngx-smart-popover';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { AgmCoreModule } from '@agm/core';

const ENTITY_STATES = [...leaveMasterHodRoute, ...leaveMasterHodPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    PopoverModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDlcZrUXZdRqJ2eb8pk4OGn_1ku190Vcnw'
    })
  ],
  declarations: [LeaveMasterApprovalComponent, LeaveMasterRemarksDetailsUpdateComponent, MapLocationComponent],
  entryComponents: [LeaveMasterApprovalComponent, LeaveMasterRemarksDetailsUpdateComponent, MapLocationComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalLeaveMasterHodModule {}
