import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { UserPlantComponent } from './user-plant.component';
import { UserPlantDetailComponent } from './user-plant-detail.component';
import { UserPlantUpdateComponent } from './user-plant-update.component';
import { UserPlantDeletePopupComponent, UserPlantDeleteDialogComponent } from './user-plant-delete-dialog.component';
import { userPlantRoute, userPlantPopupRoute } from './user-plant.route';

const ENTITY_STATES = [...userPlantRoute, ...userPlantPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    UserPlantComponent,
    UserPlantDetailComponent,
    UserPlantUpdateComponent,
    UserPlantDeleteDialogComponent,
    UserPlantDeletePopupComponent
  ],
  entryComponents: [UserPlantComponent, UserPlantUpdateComponent, UserPlantDeleteDialogComponent, UserPlantDeletePopupComponent]
})
export class VamaniportalUserPlantModule {}
