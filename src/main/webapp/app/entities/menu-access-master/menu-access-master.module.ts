import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TreeviewModule } from 'ngx-treeview';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import {
  MenuAccessMasterComponent,
  MenuAccessMasterDetailComponent,
  MenuAccessMasterUpdateComponent,
  MenuAccessMasterDeletePopupComponent,
  MenuAccessMasterDeleteDialogComponent,
  menuAccessMasterRoute,
  menuAccessMasterPopupRoute
} from './';

const ENTITY_STATES = [...menuAccessMasterRoute, ...menuAccessMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, TreeviewModule.forRoot()],
  declarations: [
    MenuAccessMasterComponent,
    MenuAccessMasterDetailComponent,
    MenuAccessMasterUpdateComponent,
    MenuAccessMasterDeleteDialogComponent,
    MenuAccessMasterDeletePopupComponent
  ],
  entryComponents: [
    MenuAccessMasterComponent,
    MenuAccessMasterUpdateComponent,
    MenuAccessMasterDeleteDialogComponent,
    MenuAccessMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalMenuAccessMasterModule {}
