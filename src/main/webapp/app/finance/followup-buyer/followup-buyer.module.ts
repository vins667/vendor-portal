import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { FollowupBuyerComponent } from './followup-buyer.component';
import { FollowupBuyerDetailComponent } from './followup-buyer-detail.component';
import { FollowupBuyerUpdateComponent } from './followup-buyer-update.component';
import { FollowupBuyerDeletePopupComponent, FollowupBuyerDeleteDialogComponent } from './followup-buyer-delete-dialog.component';
import { followupBuyerRoute, followupBuyerPopupRoute } from './followup-buyer.route';

const ENTITY_STATES = [...followupBuyerRoute, ...followupBuyerPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FollowupBuyerComponent,
    FollowupBuyerDetailComponent,
    FollowupBuyerUpdateComponent,
    FollowupBuyerDeleteDialogComponent,
    FollowupBuyerDeletePopupComponent
  ],
  entryComponents: [
    FollowupBuyerComponent,
    FollowupBuyerUpdateComponent,
    FollowupBuyerDeleteDialogComponent,
    FollowupBuyerDeletePopupComponent
  ]
})
export class VamaniportalFollowupBuyerModule {}
