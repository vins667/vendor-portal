import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { JobWorkFollowupDetailsComponent } from './job-work-followup-details.component';
import { JobWorkFollowupDetailsUpdateComponent } from './job-work-followup-details-update.component';
import { jobWorkFollowupDetailsRoute, jobWorkFollowupDetailsPopupRoute } from './job-work-followup-details.route';
import { OwlDateTimeModule } from 'ng-pick-datetime';

const ENTITY_STATES = [...jobWorkFollowupDetailsRoute, ...jobWorkFollowupDetailsPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule],
  declarations: [JobWorkFollowupDetailsComponent, JobWorkFollowupDetailsUpdateComponent],
  entryComponents: [JobWorkFollowupDetailsComponent, JobWorkFollowupDetailsUpdateComponent]
})
export class VamaniportalJobWorkFollowupDetailsModule {}
