import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ManpowerBudgetingUpdateComponent } from './manpower-budgeting-update.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

const manpowerBudgetingRoute: Routes = [
  {
    path: '',
    component: ManpowerBudgetingUpdateComponent,
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(manpowerBudgetingRoute)],
  exports: [RouterModule]
})
export class ManpowerBudgetingRoutingModule {}
