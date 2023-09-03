import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PackingLineIssueComponent } from './packing-line-issue.component';
import { PackingLineIssueDetailComponent } from './packing-line-issue-detail.component';
import { PackingLineIssueUpdateComponent } from './packing-line-issue-update.component';
import { PackingLineIssueDeleteDialogComponent } from './packing-line-issue-delete-dialog.component';
import { stitchLineIssueRoute } from './packing-line-issue.route';
import { PackingLineProductionorderSelectionComponent } from './packing-line-productionorder-selection.component';
import { PackingLineTransferSelectionComponent } from './packing-line-transfer-selection.component';

import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { PackIssueLineSelectionComponent } from 'app/packing/packing-line-issue/pack-issue-line-selection.component';

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
    PackingLineIssueComponent,
    PackingLineIssueDetailComponent,
    PackingLineIssueUpdateComponent,
    PackingLineIssueDeleteDialogComponent,
    PackingLineProductionorderSelectionComponent,
    PackingLineTransferSelectionComponent,
    PackIssueLineSelectionComponent
  ],
  entryComponents: [
    PackingLineIssueDeleteDialogComponent,
    PackingLineProductionorderSelectionComponent,
    PackingLineTransferSelectionComponent,
    PackIssueLineSelectionComponent
  ]
})
export class VamaniportalPackingLineIssueModule {}
