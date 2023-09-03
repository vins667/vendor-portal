import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { TdsQueryComponent, TdsQueryUpdateComponent, tdsQueryRoute, tdsQueryPopupRoute, TdsDeclarationUploadQryUpdateComponent } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { UiSwitchModule } from 'ngx-ui-switch';

const ENTITY_STATES = [...tdsQueryRoute, ...tdsQueryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, SnotifyModule, RouterModule.forChild(ENTITY_STATES), UiSwitchModule],
  declarations: [TdsQueryComponent, TdsQueryUpdateComponent, TdsDeclarationUploadQryUpdateComponent],
  entryComponents: [TdsQueryComponent, TdsQueryUpdateComponent, TdsDeclarationUploadQryUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalTdsQueryModule {}
