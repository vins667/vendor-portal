import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MMRMasterComponent,
  MMRMasterDetailComponent,
  MMRMasterUpdateComponent,
  MMRMasterDeletePopupComponent,
  MMRMasterDeleteDialogComponent,
  mMRMasterRoute,
  mMRMasterPopupRoute
} from './';

const ENTITY_STATES = [...mMRMasterRoute, ...mMRMasterPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [
    MMRMasterComponent,
    MMRMasterDetailComponent,
    MMRMasterUpdateComponent,
    MMRMasterDeleteDialogComponent,
    MMRMasterDeletePopupComponent
  ],
  entryComponents: [MMRMasterComponent, MMRMasterUpdateComponent, MMRMasterDeleteDialogComponent, MMRMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalMMRMasterModule {}
