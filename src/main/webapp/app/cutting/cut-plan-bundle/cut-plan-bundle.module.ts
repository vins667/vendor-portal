import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanBundleComponent } from './cut-plan-bundle.component';
import { CutPlanBundleUpdateComponent } from './cut-plan-bundle-update.component';
import { cutPlanBundleRoute, cutPlanBundlePopupRoute } from './cut-plan-bundle.route';
import { CutSchBundleProductionorderSelectionComponent } from './cut-sch-bundle-productionorder-selection.component';
import { SnotifyModule } from 'ng-snotify';
import { CutPlanBundlePrintComponent } from './cut-plan-bundle-print.component';
import { TreeviewModule } from 'ngx-treeview';

const ENTITY_STATES = [...cutPlanBundleRoute, ...cutPlanBundlePopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, TreeviewModule.forRoot()],
  declarations: [
    CutPlanBundleComponent,
    CutPlanBundleUpdateComponent,
    CutSchBundleProductionorderSelectionComponent,
    CutPlanBundlePrintComponent
  ],
  entryComponents: [
    CutPlanBundleComponent,
    CutPlanBundleUpdateComponent,
    CutSchBundleProductionorderSelectionComponent,
    CutPlanBundlePrintComponent
  ]
})
export class VamaniportalCutPlanBundleModule {}
