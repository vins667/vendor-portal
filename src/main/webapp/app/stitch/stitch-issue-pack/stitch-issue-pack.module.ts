import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { StitchIssuePackComponent } from './stitch-issue-pack.component';
import { StitchIssuePackDetailComponent } from './stitch-issue-pack-detail.component';
import { StitchIssuePackUpdateComponent } from './stitch-issue-pack-update.component';
import { StitchIssuePackDeleteDialogComponent } from './stitch-issue-pack-delete-dialog.component';
import { stitchIssuePackRoute } from './stitch-issue-pack.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { StitchIssuePackSelectionComponent } from './stitch-issue-pack-selection.component';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(stitchIssuePackRoute), Ng2CompleterModule, SnotifyModule],
  declarations: [
    StitchIssuePackComponent,
    StitchIssuePackDetailComponent,
    StitchIssuePackUpdateComponent,
    StitchIssuePackDeleteDialogComponent,
    StitchIssuePackSelectionComponent
  ],
  entryComponents: [StitchIssuePackDeleteDialogComponent, StitchIssuePackSelectionComponent]
})
export class VamaniportalStitchIssuePackModule {}
