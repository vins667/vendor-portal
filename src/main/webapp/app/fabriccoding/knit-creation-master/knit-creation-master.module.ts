import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  KnitCreationMasterComponent,
  KnitCreationMasterDetailComponent,
  KnitCreationMasterUpdateComponent,
  KnitCreationMasterDeletePopupComponent,
  KnitCreationMasterDeleteDialogComponent,
  KnitCreationSearchMasterComponent,
  knitCreationMasterRoute,
  knitCreationMasterPopupRoute
} from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

const ENTITY_STATES = [...knitCreationMasterRoute, ...knitCreationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    KnitCreationMasterComponent,
    KnitCreationMasterDetailComponent,
    KnitCreationMasterUpdateComponent,
    KnitCreationMasterDeleteDialogComponent,
    KnitCreationSearchMasterComponent,
    KnitCreationMasterDeletePopupComponent
  ],
  entryComponents: [
    KnitCreationMasterComponent,
    KnitCreationMasterUpdateComponent,
    KnitCreationMasterDeleteDialogComponent,
    KnitCreationSearchMasterComponent,
    KnitCreationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalKnitCreationMasterModule {}
