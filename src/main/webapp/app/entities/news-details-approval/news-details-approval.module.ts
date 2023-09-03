import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CKEditorModule } from 'ng2-ckeditor';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  NewsDetailsApprovalComponent,
  NewsDetailsApprovalDetailComponent,
  newsDetailsApprovalRoute,
  newsDetailsApprovalPopupRoute
} from './';

const ENTITY_STATES = [...newsDetailsApprovalRoute, ...newsDetailsApprovalPopupRoute];
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), ReactiveFormsModule, CKEditorModule, SnotifyModule],
  declarations: [NewsDetailsApprovalComponent, NewsDetailsApprovalDetailComponent],
  entryComponents: [NewsDetailsApprovalComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalNewsDetailsApprovalModule {}
