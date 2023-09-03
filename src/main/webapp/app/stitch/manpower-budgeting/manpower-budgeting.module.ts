import { NgModule } from '@angular/core';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { ManpowerBudgetingUpdateComponent } from './manpower-budgeting-update.component';
import { ManpowerBudgetingRoutingModule } from './manpower-budgeting-routing.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, ManpowerBudgetingRoutingModule, OwlDateTimeModule, OwlNativeDateTimeModule, SnotifyModule],
  declarations: [ManpowerBudgetingUpdateComponent],
  entryComponents: []
})
export class VamaniPortalManpowerBudgetingModule {}
