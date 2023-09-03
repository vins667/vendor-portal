import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import {
  TermsConditionMasterComponent,
  TermsConditionMasterDetailComponent,
  TermsConditionMasterUpdateComponent,
  TermsConditionMasterDeletePopupComponent,
  TermsConditionMasterDeleteDialogComponent,
  termsConditionMasterRoute,
  termsConditionMasterPopupRoute
} from './';

const ENTITY_STATES = [...termsConditionMasterRoute, ...termsConditionMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    TermsConditionMasterComponent,
    TermsConditionMasterDetailComponent,
    TermsConditionMasterUpdateComponent,
    TermsConditionMasterDeleteDialogComponent,
    TermsConditionMasterDeletePopupComponent
  ],
  entryComponents: [
    TermsConditionMasterComponent,
    TermsConditionMasterUpdateComponent,
    TermsConditionMasterDeleteDialogComponent,
    TermsConditionMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalTermsConditionMasterModule {}
