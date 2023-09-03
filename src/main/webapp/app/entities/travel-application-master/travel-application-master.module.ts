import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TravelApplicationMasterComponent } from './travel-application-master.component';
import { TravelApplicationMasterDetailComponent } from './travel-application-master-detail.component';
import { TravelApplicationMasterUpdateComponent } from './travel-application-master-update.component';
import {
  TravelApplicationMasterDeletePopupComponent,
  TravelApplicationMasterDeleteDialogComponent
} from './travel-application-master-delete-dialog.component';
import { travelApplicationMasterRoute, travelApplicationMasterPopupRoute } from './travel-application-master.route';
import { SnotifyModule } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TravelMasterAttachComponent } from './travel-master-attach.component';

const ENTITY_STATES = [...travelApplicationMasterRoute, ...travelApplicationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    TravelApplicationMasterComponent,
    TravelApplicationMasterDetailComponent,
    TravelApplicationMasterUpdateComponent,
    TravelApplicationMasterDeleteDialogComponent,
    TravelApplicationMasterDeletePopupComponent,
    TravelMasterAttachComponent
  ],
  entryComponents: [
    TravelApplicationMasterComponent,
    TravelApplicationMasterUpdateComponent,
    TravelApplicationMasterDeleteDialogComponent,
    TravelApplicationMasterDeletePopupComponent,
    TravelMasterAttachComponent
  ]
})
export class VamaniportalTravelApplicationMasterModule {}
