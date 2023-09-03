import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CKEditorModule } from 'ng2-ckeditor';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { DualListBoxModule } from 'ng2-dual-list-box';
import {
  NewsDetailsComponent,
  NewsDetailsUpdateComponent,
  NewsDetailsDeletePopupComponent,
  NewsDetailsDeleteDialogComponent,
  newsDetailsRoute,
  NewsDetailsPopupPreviewComponent,
  newsDetailsPopupRoute
} from './';

const ENTITY_STATES = [...newsDetailsRoute, ...newsDetailsPopupRoute];
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    ReactiveFormsModule,
    CKEditorModule,
    SnotifyModule,
    DualListBoxModule.forRoot()
  ],
  declarations: [
    NewsDetailsComponent,
    NewsDetailsUpdateComponent,
    NewsDetailsDeleteDialogComponent,
    NewsDetailsDeletePopupComponent,
    NewsDetailsPopupPreviewComponent
  ],
  entryComponents: [
    NewsDetailsComponent,
    NewsDetailsUpdateComponent,
    NewsDetailsDeleteDialogComponent,
    NewsDetailsDeletePopupComponent,
    NewsDetailsPopupPreviewComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalNewsDetailsModule {}
