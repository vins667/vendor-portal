import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanIssueStitchComponent } from './cut-plan-issue-stitch.component';
import { CutPlanIssueStitchDetailComponent } from './cut-plan-issue-stitch-detail.component';
import { CutPlanIssueStitchUpdateComponent } from './cut-plan-issue-stitch-update.component';
import { CutPlanIssueStitchDeleteDialogComponent } from './cut-plan-issue-stitch-delete-dialog.component';
import { cutPlanIssueStitchRoute } from './cut-plan-issue-stitch.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(cutPlanIssueStitchRoute), Ng2CompleterModule, SnotifyModule],
  declarations: [
    CutPlanIssueStitchComponent,
    CutPlanIssueStitchDetailComponent,
    CutPlanIssueStitchUpdateComponent,
    CutPlanIssueStitchDeleteDialogComponent
  ],
  entryComponents: [CutPlanIssueStitchDeleteDialogComponent]
})
export class VamaniportalCutPlanIssueStitchModule {}
