import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  DelPlaceMasterComponent,
  DelPlaceMasterDetailComponent,
  DelPlaceMasterUpdateComponent,
  DelPlaceMasterDeletePopupComponent,
  DelPlaceMasterDeleteDialogComponent,
  delPlaceMasterRoute,
  delPlaceMasterPopupRoute
} from './';

const ENTITY_STATES = [...delPlaceMasterRoute, ...delPlaceMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DelPlaceMasterComponent,
    DelPlaceMasterDetailComponent,
    DelPlaceMasterUpdateComponent,
    DelPlaceMasterDeleteDialogComponent,
    DelPlaceMasterDeletePopupComponent
  ],
  entryComponents: [
    DelPlaceMasterComponent,
    DelPlaceMasterUpdateComponent,
    DelPlaceMasterDeleteDialogComponent,
    DelPlaceMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalDelPlaceMasterModule {}
