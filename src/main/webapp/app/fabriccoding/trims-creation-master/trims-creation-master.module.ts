import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  TrimsCreationMasterComponent,
  TrimsCreationMasterDetailComponent,
  TrimsCreationMasterUpdateComponent,
  TrimsCreationMasterDeletePopupComponent,
  TrimsCreationMasterDeleteDialogComponent,
  trimsCreationMasterRoute,
  trimsCreationMasterPopupRoute
} from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

const ENTITY_STATES = [...trimsCreationMasterRoute, ...trimsCreationMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    TrimsCreationMasterComponent,
    TrimsCreationMasterDetailComponent,
    TrimsCreationMasterUpdateComponent,
    TrimsCreationMasterDeleteDialogComponent,
    TrimsCreationMasterDeletePopupComponent
  ],
  entryComponents: [
    TrimsCreationMasterComponent,
    TrimsCreationMasterUpdateComponent,
    TrimsCreationMasterDeleteDialogComponent,
    TrimsCreationMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalTrimsCreationMasterModule {}
