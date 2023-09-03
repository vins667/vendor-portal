import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelCurrencyMasterComponent } from './travel-currency-master.component';
import { TravelCurrencyMasterDetailComponent } from './travel-currency-master-detail.component';
import { TravelCurrencyMasterUpdateComponent } from './travel-currency-master-update.component';
import {
  TravelCurrencyMasterDeletePopupComponent,
  TravelCurrencyMasterDeleteDialogComponent
} from './travel-currency-master-delete-dialog.component';
import { travelCurrencyMasterRoute, travelCurrencyMasterPopupRoute } from './travel-currency-master.route';

const ENTITY_STATES = [...travelCurrencyMasterRoute, ...travelCurrencyMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TravelCurrencyMasterComponent,
    TravelCurrencyMasterDetailComponent,
    TravelCurrencyMasterUpdateComponent,
    TravelCurrencyMasterDeleteDialogComponent,
    TravelCurrencyMasterDeletePopupComponent
  ],
  entryComponents: [
    TravelCurrencyMasterComponent,
    TravelCurrencyMasterUpdateComponent,
    TravelCurrencyMasterDeleteDialogComponent,
    TravelCurrencyMasterDeletePopupComponent
  ]
})
export class VamaniportalTravelCurrencyMasterModule {}
