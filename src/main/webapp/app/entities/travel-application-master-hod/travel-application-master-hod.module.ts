import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelApplicationMasterHodComponent } from './travel-application-master-hod.component';
import { TravelApplicationMasterUpdateHodComponent } from './travel-application-master-update-hod.component';
import { travelApplicationMasterHodRoute } from './travel-application-master-hod.route';
import { SnotifyModule } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...travelApplicationMasterHodRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [TravelApplicationMasterHodComponent, TravelApplicationMasterUpdateHodComponent],
  entryComponents: [TravelApplicationMasterHodComponent, TravelApplicationMasterUpdateHodComponent]
})
export class VamaniportalTravelApplicationMasterHodModule {}
