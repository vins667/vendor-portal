import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  PollMasterComponent,
  PollMasterDetailComponent,
  PollMasterUpdateComponent,
  PollMasterDeletePopupComponent,
  PollMasterDeleteDialogComponent,
  pollMasterRoute,
  pollMasterPopupRoute
} from './';
import { DualListBoxModule } from 'ng2-dual-list-box';

const ENTITY_STATES = [...pollMasterRoute, ...pollMasterPopupRoute];
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    ReactiveFormsModule,
    SnotifyModule,
    DualListBoxModule.forRoot()
  ],
  declarations: [
    PollMasterComponent,
    PollMasterDetailComponent,
    PollMasterUpdateComponent,
    PollMasterDeleteDialogComponent,
    PollMasterDeletePopupComponent
  ],
  entryComponents: [PollMasterComponent, PollMasterUpdateComponent, PollMasterDeleteDialogComponent, PollMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalPollMasterModule {}
