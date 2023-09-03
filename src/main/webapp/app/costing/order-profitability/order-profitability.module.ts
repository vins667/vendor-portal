import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OrderProfitabilityComponent } from './order-profitability.component';
import { userPlantRoute, userPlantPopupRoute } from './order-profitability.route';
import { SnotifyModule } from 'ng-snotify';
import { Ng2CompleterModule } from 'ng2-completer';

const ENTITY_STATES = [...userPlantRoute, ...userPlantPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, Ng2CompleterModule],
  declarations: [OrderProfitabilityComponent],
  entryComponents: [OrderProfitabilityComponent]
})
export class VamaniportalOrderProfitabilityModule {}
