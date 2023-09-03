import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { JobWorkFollowupComponent } from './job-work-followup.component';
import { JobWorkFollowupDetailComponent } from './job-work-followup-detail.component';
import { JobWorkFollowupUpdateComponent } from './job-work-followup-update.component';
import { JobWorkFollowupDeletePopupComponent, JobWorkFollowupDeleteDialogComponent } from './job-work-followup-delete-dialog.component';
import { jobWorkFollowupRoute, jobWorkFollowupPopupRoute } from './job-work-followup.route';
import { JobWorkFollowupSearchComponent } from 'app/finance/job-work-followup/job-work-followup-search.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { JobWorkFollowupScheduleComponent } from 'app/finance/job-work-followup/job-work-followup-schedule.component';
import { CalendarCreatorService } from 'app/finance/job-work-followup/job-work-followup-calendarService.service';

const ENTITY_STATES = [...jobWorkFollowupRoute, ...jobWorkFollowupPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [
    JobWorkFollowupComponent,
    JobWorkFollowupDetailComponent,
    JobWorkFollowupUpdateComponent,
    JobWorkFollowupDeleteDialogComponent,
    JobWorkFollowupDeletePopupComponent,
    JobWorkFollowupSearchComponent,
    JobWorkFollowupScheduleComponent
  ],
  entryComponents: [
    JobWorkFollowupComponent,
    JobWorkFollowupUpdateComponent,
    JobWorkFollowupDeleteDialogComponent,
    JobWorkFollowupDeletePopupComponent,
    JobWorkFollowupSearchComponent,
    JobWorkFollowupScheduleComponent
  ]
  // providers:[CalendarCreatorService]
})
export class VamaniportalJobWorkFollowupModule {}
