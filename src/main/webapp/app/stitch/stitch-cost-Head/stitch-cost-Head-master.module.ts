import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StitchCostHeadMasterComponent } from './stitch-cost-Head-master.component';
import { stitchCostHeadMasterRoute } from './stitch-cost-Head-master.route';
import { SnotifyModule, ToastDefaults, SnotifyService } from 'ng-snotify';

const ENTITY_STATES = [...stitchCostHeadMasterRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES),SnotifyModule, NgbModule.forRoot()],
  declarations: [
    StitchCostHeadMasterComponent
  ],
  entryComponents: [StitchCostHeadMasterComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalStitchCostHeadMasterModule {}
