import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  MenuMasterComponent,
  MenuMasterDetailComponent,
  MenuMasterUpdateComponent,
  MenuMasterDeletePopupComponent,
  MenuMasterDeleteDialogComponent,
  menuMasterRoute,
  menuMasterPopupRoute
} from './';

const ENTITY_STATES = [...menuMasterRoute, ...menuMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MenuMasterComponent,
    MenuMasterDetailComponent,
    MenuMasterUpdateComponent,
    MenuMasterDeleteDialogComponent,
    MenuMasterDeletePopupComponent
  ],
  entryComponents: [MenuMasterComponent, MenuMasterUpdateComponent, MenuMasterDeleteDialogComponent, MenuMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMenuMasterModule {}
