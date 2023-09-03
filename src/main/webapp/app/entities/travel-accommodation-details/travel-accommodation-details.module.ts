import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelAccommodationDetailsComponent } from './travel-accommodation-details.component';
import { TravelAccommodationDetailsDetailComponent } from './travel-accommodation-details-detail.component';
import { TravelAccommodationDetailsUpdateComponent } from './travel-accommodation-details-update.component';
import {
  TravelAccommodationDetailsDeletePopupComponent,
  TravelAccommodationDetailsDeleteDialogComponent
} from './travel-accommodation-details-delete-dialog.component';
import { travelAccommodationDetailsRoute, travelAccommodationDetailsPopupRoute } from './travel-accommodation-details.route';

const ENTITY_STATES = [...travelAccommodationDetailsRoute, ...travelAccommodationDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TravelAccommodationDetailsComponent,
    TravelAccommodationDetailsDetailComponent,
    TravelAccommodationDetailsUpdateComponent,
    TravelAccommodationDetailsDeleteDialogComponent,
    TravelAccommodationDetailsDeletePopupComponent
  ],
  entryComponents: [
    TravelAccommodationDetailsComponent,
    TravelAccommodationDetailsUpdateComponent,
    TravelAccommodationDetailsDeleteDialogComponent,
    TravelAccommodationDetailsDeletePopupComponent
  ]
})
export class VamaniportalTravelAccommodationDetailsModule {}
