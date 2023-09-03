import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelLuggageDetailsComponent } from './travel-luggage-details.component';
import { TravelLuggageDetailsDetailComponent } from './travel-luggage-details-detail.component';
import { TravelLuggageDetailsUpdateComponent } from './travel-luggage-details-update.component';
import {
  TravelLuggageDetailsDeletePopupComponent,
  TravelLuggageDetailsDeleteDialogComponent
} from './travel-luggage-details-delete-dialog.component';
import { travelLuggageDetailsRoute, travelLuggageDetailsPopupRoute } from './travel-luggage-details.route';

const ENTITY_STATES = [...travelLuggageDetailsRoute, ...travelLuggageDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TravelLuggageDetailsComponent,
    TravelLuggageDetailsDetailComponent,
    TravelLuggageDetailsUpdateComponent,
    TravelLuggageDetailsDeleteDialogComponent,
    TravelLuggageDetailsDeletePopupComponent
  ],
  entryComponents: [
    TravelLuggageDetailsComponent,
    TravelLuggageDetailsUpdateComponent,
    TravelLuggageDetailsDeleteDialogComponent,
    TravelLuggageDetailsDeletePopupComponent
  ]
})
export class VamaniportalTravelLuggageDetailsModule {}
