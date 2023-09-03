import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { BuyerCostingComponent } from './buyer-costing.component';
import { BuyerCostingDetailComponent } from './buyer-costing-detail.component';
import { BuyerCostingUpdateComponent } from './buyer-costing-update.component';
import { BuyerCostingDeletePopupComponent, BuyerCostingDeleteDialogComponent } from './buyer-costing-delete-dialog.component';
import { buyerCostingRoute, buyerCostingPopupRoute } from './buyer-costing.route';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const ENTITY_STATES = [...buyerCostingRoute, ...buyerCostingPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), NgbModule.forRoot()],
  declarations: [
    BuyerCostingComponent,
    BuyerCostingDetailComponent,
    BuyerCostingUpdateComponent,
    BuyerCostingDeleteDialogComponent,
    BuyerCostingDeletePopupComponent
  ],
  entryComponents: [BuyerCostingComponent, BuyerCostingUpdateComponent, BuyerCostingDeleteDialogComponent, BuyerCostingDeletePopupComponent]
})
export class VamaniportalBuyerCostingModule {}
