import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanMrkrEntryComponent } from './cut-plan-mrkr-entry.component';
import { CutPlanMrkrEntryDetailComponent } from './cut-plan-mrkr-entry-detail.component';
import { CutPlanMrkrEntryUpdateComponent } from './cut-plan-mrkr-entry-update.component';
import { CutPlanMrkrEntryDeletePopupComponent, CutPlanMrkrEntryDeleteDialogComponent } from './cut-plan-mrkr-entry-delete-dialog.component';
import { cutPlanMrkrEntryRoute, cutPlanMrkrEntryPopupRoute } from './cut-plan-mrkr-entry.route';
import { CutSchMrkrProductionorderSelectionComponent } from './cut-sch-mrkr-productionorder-selection.component';
import { CutPlanMrkrSuggestionsSelectionComponent } from './cut-plan-mrkr-suggestions-selection.component';
import { SnotifyModule } from 'ng-snotify';
import { CutPlanMrkrMasterSelectionComponent } from './cut-plan-mrkr-master-selection.component';

const ENTITY_STATES = [...cutPlanMrkrEntryRoute, ...cutPlanMrkrEntryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    CutPlanMrkrEntryComponent,
    CutPlanMrkrEntryDetailComponent,
    CutPlanMrkrEntryUpdateComponent,
    CutPlanMrkrEntryDeleteDialogComponent,
    CutPlanMrkrEntryDeletePopupComponent,
    CutSchMrkrProductionorderSelectionComponent,
    CutPlanMrkrSuggestionsSelectionComponent,
    CutPlanMrkrMasterSelectionComponent
  ],
  entryComponents: [
    CutPlanMrkrEntryComponent,
    CutPlanMrkrEntryUpdateComponent,
    CutPlanMrkrEntryDeleteDialogComponent,
    CutPlanMrkrEntryDeletePopupComponent,
    CutSchMrkrProductionorderSelectionComponent,
    CutPlanMrkrSuggestionsSelectionComponent,
    CutPlanMrkrMasterSelectionComponent
  ]
})
export class VamaniportalCutPlanMrkrEntryModule {}
