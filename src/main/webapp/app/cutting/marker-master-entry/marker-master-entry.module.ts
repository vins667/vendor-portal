import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { MarkerMasterEntryComponent } from './marker-master-entry.component';
import { MarkerMasterEntryDetailComponent } from './marker-master-entry-detail.component';
import { MarkerMasterEntryUpdateComponent } from './marker-master-entry-update.component';
import {
  MarkerMasterEntryDeletePopupComponent,
  MarkerMasterEntryDeleteDialogComponent
} from './marker-master-entry-delete-dialog.component';
import { markerMasterEntryRoute, markerMasterEntryPopupRoute } from './marker-master-entry.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...markerMasterEntryRoute, ...markerMasterEntryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2CompleterModule, SnotifyModule],
  declarations: [
    MarkerMasterEntryComponent,
    MarkerMasterEntryDetailComponent,
    MarkerMasterEntryUpdateComponent,
    MarkerMasterEntryDeleteDialogComponent,
    MarkerMasterEntryDeletePopupComponent
  ],
  entryComponents: [
    MarkerMasterEntryComponent,
    MarkerMasterEntryUpdateComponent,
    MarkerMasterEntryDeleteDialogComponent,
    MarkerMasterEntryDeletePopupComponent
  ]
})
export class VamaniportalMarkerMasterEntryModule {}
