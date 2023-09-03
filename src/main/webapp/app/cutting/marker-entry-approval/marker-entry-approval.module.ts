import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';
import { MarkerEntryApprovalRoute } from './marker-entry-approval.route';
import { MarkerEntryApprovalComponent } from './marker-entry-approval.component';

const ENTITY_STATES = [...MarkerEntryApprovalRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), Ng2CompleterModule, SnotifyModule],
  declarations: [MarkerEntryApprovalComponent],
  entryComponents: [MarkerEntryApprovalComponent]
})
export class VamaniportalMarkerEntryApprovalModule {}
