import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  WorkerForwardTypeMasterComponent,
  WorkerForwardTypeMasterDetailComponent,
  WorkerForwardTypeMasterUpdateComponent,
  WorkerForwardTypeMasterDeletePopupComponent,
  WorkerForwardTypeMasterDeleteDialogComponent,
  workerForwardTypeMasterRoute,
  workerForwardTypeMasterPopupRoute
} from './';

const ENTITY_STATES = [...workerForwardTypeMasterRoute, ...workerForwardTypeMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WorkerForwardTypeMasterComponent,
    WorkerForwardTypeMasterDetailComponent,
    WorkerForwardTypeMasterUpdateComponent,
    WorkerForwardTypeMasterDeleteDialogComponent,
    WorkerForwardTypeMasterDeletePopupComponent
  ],
  entryComponents: [
    WorkerForwardTypeMasterComponent,
    WorkerForwardTypeMasterUpdateComponent,
    WorkerForwardTypeMasterDeleteDialogComponent,
    WorkerForwardTypeMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalWorkerForwardTypeMasterModule {}
