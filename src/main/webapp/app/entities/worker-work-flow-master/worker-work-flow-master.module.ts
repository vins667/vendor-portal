import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  WorkerWorkFlowMasterComponent,
  WorkerWorkFlowMasterDetailComponent,
  WorkerWorkFlowMasterUpdateComponent,
  WorkerWorkFlowMasterDeletePopupComponent,
  WorkerWorkFlowMasterDeleteDialogComponent,
  WorkerWorkFlowSearchComponent,
  workerWorkFlowMasterRoute,
  workerWorkFlowMasterPopupRoute
} from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
const ENTITY_STATES = [...workerWorkFlowMasterRoute, ...workerWorkFlowMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    WorkerWorkFlowMasterComponent,
    WorkerWorkFlowMasterDetailComponent,
    WorkerWorkFlowMasterUpdateComponent,
    WorkerWorkFlowMasterDeleteDialogComponent,
    WorkerWorkFlowMasterDeletePopupComponent,
    WorkerWorkFlowSearchComponent
  ],
  entryComponents: [
    WorkerWorkFlowMasterComponent,
    WorkerWorkFlowMasterUpdateComponent,
    WorkerWorkFlowMasterDeleteDialogComponent,
    WorkerWorkFlowMasterDeletePopupComponent,
    WorkerWorkFlowSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalWorkerWorkFlowMasterModule {}
