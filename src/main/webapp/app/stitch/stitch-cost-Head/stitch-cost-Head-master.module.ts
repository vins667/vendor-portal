import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StitchCostHeadMasterComponent } from './stitch-cost-Head-master.component';
import { stitchCostHeadMasterRoute } from './stitch-cost-Head-master.route';

const ENTITY_STATES = [...stitchCostHeadMasterRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), NgbModule.forRoot()],
  declarations: [
    StitchCostHeadMasterComponent
  ],
  entryComponents: [StitchCostHeadMasterComponent]
})
export class VamaniportalStitchCostHeadMasterModule {}
