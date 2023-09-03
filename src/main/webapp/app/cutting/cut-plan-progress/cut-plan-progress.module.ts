import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanProgressComponent } from './cut-plan-progress.component';
import { CutPlanProgressUpdateComponent } from './cut-plan-progress-update.component';
import { cutPlanProgressRoute, cutPlanProgressPopupRoute } from './cut-plan-progress.route';
import { SnotifyModule } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...cutPlanProgressRoute, ...cutPlanProgressPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [CutPlanProgressComponent, CutPlanProgressUpdateComponent],
  entryComponents: [CutPlanProgressComponent, CutPlanProgressUpdateComponent]
})
export class VamaniportalCutPlanProgressModule {}
