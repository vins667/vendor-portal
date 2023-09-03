import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {
  VcutSessionMasterComponent,
  VcutSessionMasterDetailComponent,
  VcutSessionMasterUpdateComponent,
  VcutSessionMasterDeletePopupComponent,
  VcutSessionMasterDeleteDialogComponent,
  vcutSessionMasterRoute,
  vcutSessionMasterPopupRoute
} from './';

import { TextMaskModule } from 'angular2-text-mask';

const ENTITY_STATES = [...vcutSessionMasterRoute, ...vcutSessionMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, TextMaskModule],
  declarations: [
    VcutSessionMasterComponent,
    VcutSessionMasterDetailComponent,
    VcutSessionMasterUpdateComponent,
    VcutSessionMasterDeleteDialogComponent,
    VcutSessionMasterDeletePopupComponent
  ],
  entryComponents: [
    VcutSessionMasterComponent,
    VcutSessionMasterUpdateComponent,
    VcutSessionMasterDeleteDialogComponent,
    VcutSessionMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalVcutSessionMasterModule {}
