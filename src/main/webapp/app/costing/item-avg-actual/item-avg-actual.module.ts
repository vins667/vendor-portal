import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { ItemAvgActualComponent } from './item-avg-actual.component';
import { itemAvgActualRoute, itemAvgActualPopupRoute } from './item-avg-actual.route';
import { SnotifyModule } from 'ng-snotify';
import { Ng2CompleterModule } from 'ng2-completer';

const ENTITY_STATES = [...itemAvgActualRoute, ...itemAvgActualPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, Ng2CompleterModule],
  declarations: [ItemAvgActualComponent],
  entryComponents: [ItemAvgActualComponent]
})
export class VamaniportalItemAvgActualModule {}
