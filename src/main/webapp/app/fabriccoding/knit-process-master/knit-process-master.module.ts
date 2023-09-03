import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  KnitProcessMasterComponent,
  KnitProcessMasterDetailComponent,
  KnitProcessMasterUpdateComponent,
  KnitProcessMasterDeletePopupComponent,
  KnitProcessMasterDeleteDialogComponent,
  knitProcessMasterRoute,
  knitProcessMasterPopupRoute
} from './';

const ENTITY_STATES = [...knitProcessMasterRoute, ...knitProcessMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    KnitProcessMasterComponent,
    KnitProcessMasterDetailComponent,
    KnitProcessMasterUpdateComponent,
    KnitProcessMasterDeleteDialogComponent,
    KnitProcessMasterDeletePopupComponent
  ],
  entryComponents: [
    KnitProcessMasterComponent,
    KnitProcessMasterUpdateComponent,
    KnitProcessMasterDeleteDialogComponent,
    KnitProcessMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalKnitProcessMasterModule {}
