import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { AgmCoreModule } from '@agm/core';
import {
  MobileAttendanceComponent,
  MobileAttendanceDetailComponent,
  MobileAttendanceUpdateComponent,
  MobileAttendanceDeletePopupComponent,
  MobileAttendanceDeleteDialogComponent,
  MobileMapLocationComponent,
  mobileAttendanceRoute,
  mobileAttendancePopupRoute
} from './';

const ENTITY_STATES = [...mobileAttendanceRoute, ...mobileAttendancePopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDlcZrUXZdRqJ2eb8pk4OGn_1ku190Vcnw'
    })
  ],
  declarations: [
    MobileAttendanceComponent,
    MobileAttendanceDetailComponent,
    MobileAttendanceUpdateComponent,
    MobileAttendanceDeleteDialogComponent,
    MobileAttendanceDeletePopupComponent,
    MobileMapLocationComponent
  ],
  entryComponents: [
    MobileAttendanceComponent,
    MobileAttendanceUpdateComponent,
    MobileAttendanceDeleteDialogComponent,
    MobileAttendanceDeletePopupComponent,
    MobileMapLocationComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMobileAttendanceModule {}
