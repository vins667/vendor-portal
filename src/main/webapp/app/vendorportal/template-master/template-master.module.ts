import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import {
  TemplateMasterComponent,
  TemplateMasterDetailComponent,
  TemplateMasterUpdateComponent,
  TemplateMasterDeletePopupComponent,
  TemplateMasterDeleteDialogComponent,
  templateMasterRoute,
  templateMasterPopupRoute
} from './';

const ENTITY_STATES = [...templateMasterRoute, ...templateMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [
    TemplateMasterComponent,
    TemplateMasterDetailComponent,
    TemplateMasterUpdateComponent,
    TemplateMasterDeleteDialogComponent,
    TemplateMasterDeletePopupComponent
  ],
  entryComponents: [
    TemplateMasterComponent,
    TemplateMasterUpdateComponent,
    TemplateMasterDeleteDialogComponent,
    TemplateMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VendorportalTemplateMasterModule {}
