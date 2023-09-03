import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { LeaveMasterHrApprovalComponent, leaveMasterHrRoute, leaveMasterHrPopupRoute, HrMapLocationComponent } from './';
import { AgmCoreModule } from '@agm/core';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

const ENTITY_STATES = [...leaveMasterHrRoute, ...leaveMasterHrPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDlcZrUXZdRqJ2eb8pk4OGn_1ku190Vcnw'
    })
  ],
  declarations: [LeaveMasterHrApprovalComponent, HrMapLocationComponent],
  entryComponents: [LeaveMasterHrApprovalComponent, HrMapLocationComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalLeaveMasterHrModule {}
