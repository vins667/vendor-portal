import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { MatTabsModule } from '@angular/material/tabs';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { SignaturePadModule } from '@ng-plus/signature-pad';
import {
  WorkerJoiningComponent,
  WorkerJoiningDetailComponent,
  WorkerJoiningUpdateComponent,
  WorkerJoiningDeletePopupComponent,
  WorkerJoiningDeleteDialogComponent,
  WorkerJoinFlowDetailsUpdateComponent,
  workerJoiningRoute,
  workerJoiningPopupRoute
} from './';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';

const ENTITY_STATES = [...workerJoiningRoute, ...workerJoiningPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    FormsModule,
    NgSelectModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    MatTabsModule,
    NgbModule,
    SnotifyModule,
    SignaturePadModule
  ],
  declarations: [
    WorkerJoiningComponent,
    WorkerJoiningDetailComponent,
    WorkerJoiningUpdateComponent,
    WorkerJoiningDeleteDialogComponent,
    WorkerJoiningDeletePopupComponent,
    WorkerJoinFlowDetailsUpdateComponent
  ],
  entryComponents: [
    WorkerJoiningComponent,
    WorkerJoiningUpdateComponent,
    WorkerJoiningDeleteDialogComponent,
    WorkerJoiningDeletePopupComponent,
    WorkerJoinFlowDetailsUpdateComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalWorkerJoiningModule {}
