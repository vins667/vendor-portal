import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { StitchLineIssueReportComponent } from './stitch-line-issue-report.component';
import { stitchLineIssueReportRoute } from './stitch-line-issue-report.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...stitchLineIssueReportRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2CompleterModule, SnotifyModule],
  declarations: [StitchLineIssueReportComponent],
  entryComponents: [StitchLineIssueReportComponent]
})
export class VamaniportalStitchLineIssueReportModule {}
