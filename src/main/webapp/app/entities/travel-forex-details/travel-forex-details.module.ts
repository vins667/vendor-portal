import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelForexDetailsComponent } from './travel-forex-details.component';
import { TravelForexDetailsDetailComponent } from './travel-forex-details-detail.component';
import { TravelForexDetailsUpdateComponent } from './travel-forex-details-update.component';
import {
  TravelForexDetailsDeletePopupComponent,
  TravelForexDetailsDeleteDialogComponent
} from './travel-forex-details-delete-dialog.component';
import { travelForexDetailsRoute, travelForexDetailsPopupRoute } from './travel-forex-details.route';

const ENTITY_STATES = [...travelForexDetailsRoute, ...travelForexDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TravelForexDetailsComponent,
    TravelForexDetailsDetailComponent,
    TravelForexDetailsUpdateComponent,
    TravelForexDetailsDeleteDialogComponent,
    TravelForexDetailsDeletePopupComponent
  ],
  entryComponents: [
    TravelForexDetailsComponent,
    TravelForexDetailsUpdateComponent,
    TravelForexDetailsDeleteDialogComponent,
    TravelForexDetailsDeletePopupComponent
  ]
})
export class VamaniportalTravelForexDetailsModule {}
