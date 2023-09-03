import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutPlanRecieptStitchComponent } from './cut-plan-reciept-stitch.component';
import { CutPlanRecieptStitchUpdateComponent } from './cut-plan-reciept-stitch-update.component';
import { cutPlanRecieptStitchRoute } from './cut-plan-reciept-stitch.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(cutPlanRecieptStitchRoute), Ng2CompleterModule, SnotifyModule],
  declarations: [CutPlanRecieptStitchComponent, CutPlanRecieptStitchUpdateComponent],
  entryComponents: []
})
export class VamaniportalCutPlanRecieptStitchModule {}
