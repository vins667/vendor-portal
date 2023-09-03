import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { MatSelectModule } from '@angular/material/select';
import { CdkTableModule } from '@angular/cdk/table';
import { ReactiveFormsModule } from '@angular/forms';
import { TextMaskModule } from 'angular2-text-mask';
import {
  TrailMockOperationComponent,
  TrailMockOperationDetailComponent,
  TrailMockOperationUpdateComponent,
  TrailMockOperationDeletePopupComponent,
  TrailMockOperationDeleteDialogComponent,
  trailMockOperationRoute,
  trailMockOperationPopupRoute
} from './';
import { WorkerWorkFlowUpdateComponent } from './worker-work-flow-update.component';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { DualListBoxModule } from 'ng2-dual-list-box';

const ENTITY_STATES = [...trailMockOperationRoute, ...trailMockOperationPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    MatSelectModule,
    CdkTableModule,
    ReactiveFormsModule,
    TextMaskModule,
    SnotifyModule,
    DualListBoxModule.forRoot()
  ],
  declarations: [
    TrailMockOperationComponent,
    TrailMockOperationDetailComponent,
    TrailMockOperationUpdateComponent,
    TrailMockOperationDeleteDialogComponent,
    TrailMockOperationDeletePopupComponent,
    WorkerWorkFlowUpdateComponent
  ],
  entryComponents: [
    TrailMockOperationComponent,
    TrailMockOperationUpdateComponent,
    TrailMockOperationDeleteDialogComponent,
    TrailMockOperationDeletePopupComponent,
    WorkerWorkFlowUpdateComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalTrailMockOperationModule {}
