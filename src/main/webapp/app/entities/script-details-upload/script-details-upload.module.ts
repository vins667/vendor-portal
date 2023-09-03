import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
import { ScriptDetailsDragDropDirective } from './script-details-drag-drop.directive';
import { scriptDetailsUploadPopupRoute, scriptDetailsUploadRoute } from 'app/entities/script-details-upload/script-details-upload.route';
import { ScriptDetailsUploadComponent } from 'app/entities/script-details-upload/script-details-upload-component';
import { ScriptDetailsUploadDetailComponent } from 'app/entities/script-details-upload/script-details-upload-detail.component';
import {
  ScriptDetailsUploadDeleteDialogComponent,
  ScriptDetailsUploadPopupComponent
} from 'app/entities/script-details-upload/script-details-upload-delete-dialog.component';
import { ScriptDetailsUploadUpdateComponent } from 'app/entities/script-details-upload/script-details-upload-update.component';

const ENTITY_STATES = [...scriptDetailsUploadRoute, ...scriptDetailsUploadPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    TextMaskModule
  ],
  declarations: [
    ScriptDetailsDragDropDirective,
    ScriptDetailsUploadComponent,
    ScriptDetailsUploadDetailComponent,
    ScriptDetailsUploadDeleteDialogComponent,
    ScriptDetailsUploadPopupComponent,
    ScriptDetailsUploadUpdateComponent
  ],
  entryComponents: [
    ScriptDetailsUploadComponent,
    ScriptDetailsUploadDetailComponent,
    ScriptDetailsUploadDeleteDialogComponent,
    ScriptDetailsUploadPopupComponent,
    ScriptDetailsUploadUpdateComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniPortalScriptDetailsModule {}
