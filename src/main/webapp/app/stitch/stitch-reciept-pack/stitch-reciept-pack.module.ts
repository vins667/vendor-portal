import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { StitchRecieptPackComponent } from './stitch-reciept-pack.component';
import { StitchRecieptPackUpdateComponent } from './stitch-reciept-pack-update.component';
import { cutPlanRecieptStitchRoute } from './stitch-reciept-pack.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { SnotifyModule } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(cutPlanRecieptStitchRoute), Ng2CompleterModule, SnotifyModule],
  declarations: [StitchRecieptPackComponent, StitchRecieptPackUpdateComponent],
  entryComponents: []
})
export class VamaniportalStitchRecieptPackModule {}
