import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  FabricCreationMasterComponent,
  FabricCreationMasterDetailComponent,
  FabricCreationMasterUpdateComponent,
  FabricCreationMasterDeletePopupComponent,
  FabricCreationMasterDeleteDialogComponent,
  FabricContentSearchComponent,
  fabricCreationMasterRoute,
  fabricCreationMasterPopupRoute
} from './';

const ENTITY_STATES = [...fabricCreationMasterRoute, ...fabricCreationMasterPopupRoute];
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    FabricCreationMasterComponent,
    FabricCreationMasterDetailComponent,
    FabricCreationMasterUpdateComponent,
    FabricCreationMasterDeleteDialogComponent,
    FabricCreationMasterDeletePopupComponent,
    FabricContentSearchComponent
  ],
  entryComponents: [
    FabricCreationMasterComponent,
    FabricCreationMasterUpdateComponent,
    FabricCreationMasterDeleteDialogComponent,
    FabricCreationMasterDeletePopupComponent,
    FabricContentSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalFabricCreationMasterModule {}
