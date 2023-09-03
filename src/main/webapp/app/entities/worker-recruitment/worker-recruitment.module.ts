import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import {
  WorkerRecruitmentComponent,
  WorkerRecruitmentDetailComponent,
  WorkerRecruitmentDeletePopupComponent,
  WorkerRecruitmentDeleteDialogComponent,
  workerRecruitmentRoute,
  workerRecruitmentPopupRoute,
  WorkerRecruitmentUpdateComponent,
  AadharScanComponent
} from './';
import { WebcamModule } from 'ngx-webcam';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FormsModule } from '@angular/forms';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
const ENTITY_STATES = [...workerRecruitmentRoute, ...workerRecruitmentPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    FormsModule,
    NgSelectModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    WebcamModule,
    TextMaskModule,
    SnotifyModule
  ],
  declarations: [
    WorkerRecruitmentComponent,
    WorkerRecruitmentDetailComponent,
    WorkerRecruitmentUpdateComponent,
    WorkerRecruitmentDeleteDialogComponent,
    WorkerRecruitmentDeletePopupComponent,
    AadharScanComponent
  ],
  entryComponents: [
    WorkerRecruitmentComponent,
    WorkerRecruitmentUpdateComponent,
    WorkerRecruitmentDeleteDialogComponent,
    WorkerRecruitmentDeletePopupComponent,
    AadharScanComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalWorkerRecruitmentModule {}
