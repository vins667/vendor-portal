import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelFlightDetailsComponent } from './travel-flight-details.component';
import { TravelFlightDetailsDetailComponent } from './travel-flight-details-detail.component';
import { TravelFlightDetailsUpdateComponent } from './travel-flight-details-update.component';
import {
  TravelFlightDetailsDeletePopupComponent,
  TravelFlightDetailsDeleteDialogComponent
} from './travel-flight-details-delete-dialog.component';
import { travelFlightDetailsRoute, travelFlightDetailsPopupRoute } from './travel-flight-details.route';

const ENTITY_STATES = [...travelFlightDetailsRoute, ...travelFlightDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TravelFlightDetailsComponent,
    TravelFlightDetailsDetailComponent,
    TravelFlightDetailsUpdateComponent,
    TravelFlightDetailsDeleteDialogComponent,
    TravelFlightDetailsDeletePopupComponent
  ],
  entryComponents: [
    TravelFlightDetailsComponent,
    TravelFlightDetailsUpdateComponent,
    TravelFlightDetailsDeleteDialogComponent,
    TravelFlightDetailsDeletePopupComponent
  ]
})
export class VamaniportalTravelFlightDetailsModule {}
