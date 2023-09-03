import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TravelApplicationMasterHrComponent, TravelApplicationMasterHrUpdateComponent, travelApplicationMasterHrRoute } from './';
import { TravelApplicationMasterHrPopupComponent } from './travel-application-master-hr-popup.component';

const ENTITY_STATES = [...travelApplicationMasterHrRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [TravelApplicationMasterHrComponent, TravelApplicationMasterHrUpdateComponent, TravelApplicationMasterHrPopupComponent],
  entryComponents: [TravelApplicationMasterHrComponent, TravelApplicationMasterHrUpdateComponent, TravelApplicationMasterHrPopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalTravelApplicationMasterHrModule {}
