import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  WorkerJoinFlowMasterComponent,
  WorkerJoinFlowMasterDetailComponent,
  WorkerJoinFlowMasterUpdateComponent,
  WorkerJoinFlowMasterDeletePopupComponent,
  WorkerJoinFlowMasterDeleteDialogComponent,
  workerJoinFlowMasterRoute,
  workerJoinFlowMasterPopupRoute,
  WorkerJoinFlowSearchComponent
} from './';

const ENTITY_STATES = [...workerJoinFlowMasterRoute, ...workerJoinFlowMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WorkerJoinFlowMasterComponent,
    WorkerJoinFlowMasterDetailComponent,
    WorkerJoinFlowMasterUpdateComponent,
    WorkerJoinFlowMasterDeleteDialogComponent,
    WorkerJoinFlowMasterDeletePopupComponent,
    WorkerJoinFlowSearchComponent
  ],
  entryComponents: [
    WorkerJoinFlowMasterComponent,
    WorkerJoinFlowMasterUpdateComponent,
    WorkerJoinFlowMasterDeleteDialogComponent,
    WorkerJoinFlowMasterDeletePopupComponent,
    WorkerJoinFlowSearchComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalWorkerJoinFlowMasterModule {}
