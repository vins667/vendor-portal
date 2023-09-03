import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { StitchLineIssueComponent } from './stitch-line-issue.component';
import { StitchLineIssueDetailComponent } from './stitch-line-issue-detail.component';
import { StitchLineIssueUpdateComponent } from './stitch-line-issue-update.component';
import { StitchLineIssueDeleteDialogComponent } from './stitch-line-issue-delete-dialog.component';
import { stitchLineIssueRoute } from './stitch-line-issue.route';
import { StitchLineProductionorderSelectionComponent } from './stitch-line-productionorder-selection.component';
import { StitchLineTransferSelectionComponent } from './stitch-line-transfer-selection.component';

import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(stitchLineIssueRoute),
    Ng2CompleterModule,
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule
  ],
  declarations: [
    StitchLineIssueComponent,
    StitchLineIssueDetailComponent,
    StitchLineIssueUpdateComponent,
    StitchLineIssueDeleteDialogComponent,
    StitchLineProductionorderSelectionComponent,
    StitchLineTransferSelectionComponent
  ],
  entryComponents: [StitchLineIssueDeleteDialogComponent, StitchLineProductionorderSelectionComponent, StitchLineTransferSelectionComponent]
})
export class VamaniportalStitchLineIssueModule {}
