import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanEntryComponent } from './cut-plan-entry.component';
import { CutPlanEntryDetailComponent } from './cut-plan-entry-detail.component';
import { CutPlanEntryUpdateComponent } from './cut-plan-entry-update.component';
import { CutPlanEntryDeletePopupComponent, CutPlanEntryDeleteDialogComponent } from './cut-plan-entry-delete-dialog.component';
import { cutPlanEntryRoute, cutPlanEntryPopupRoute } from './cut-plan-entry.route';
import { CutSchProductionorderSelectionComponent } from './cut-sch-productionorder-selection.component';
import { CutPlanSuggestionsSelectionComponent } from './cut-plan-suggestions-selection.component';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...cutPlanEntryRoute, ...cutPlanEntryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    CutPlanEntryComponent,
    CutPlanEntryDetailComponent,
    CutPlanEntryUpdateComponent,
    CutPlanEntryDeleteDialogComponent,
    CutPlanEntryDeletePopupComponent,
    CutSchProductionorderSelectionComponent,
    CutPlanSuggestionsSelectionComponent
  ],
  entryComponents: [
    CutPlanEntryComponent,
    CutPlanEntryUpdateComponent,
    CutPlanEntryDeleteDialogComponent,
    CutPlanEntryDeletePopupComponent,
    CutSchProductionorderSelectionComponent,
    CutPlanSuggestionsSelectionComponent
  ]
})
export class VamaniportalCutPlanEntryModule {}
