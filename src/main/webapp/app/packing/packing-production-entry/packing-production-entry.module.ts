import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PackingProductionEntryComponent } from './packing-production-entry.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { sewingProductionEntryRoute } from './packing-production-entry.route';
import { PackingProductionEntryUpdateComponent } from './packing-production-entry-update.component';
import { PackingProductionorderSelectionComponent } from './packing-productionorder-selection.component';
import { PackingProductionSelectionComponent } from './packing-production-selection.component';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(sewingProductionEntryRoute), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    PackingProductionEntryComponent,
    PackingProductionEntryUpdateComponent,
    PackingProductionorderSelectionComponent,
    PackingProductionSelectionComponent
  ],
  entryComponents: [PackingProductionorderSelectionComponent, PackingProductionSelectionComponent]
})
export class VamaniportalPackingProductionEntryModule {}
