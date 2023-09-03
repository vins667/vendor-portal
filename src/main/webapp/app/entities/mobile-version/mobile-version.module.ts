import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  MobileVersionComponent,
  MobileVersionDetailComponent,
  MobileVersionUpdateComponent,
  MobileVersionDeletePopupComponent,
  MobileVersionDeleteDialogComponent,
  mobileVersionRoute,
  mobileVersionPopupRoute
} from './';

const ENTITY_STATES = [...mobileVersionRoute, ...mobileVersionPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MobileVersionComponent,
    MobileVersionDetailComponent,
    MobileVersionUpdateComponent,
    MobileVersionDeleteDialogComponent,
    MobileVersionDeletePopupComponent
  ],
  entryComponents: [
    MobileVersionComponent,
    MobileVersionUpdateComponent,
    MobileVersionDeleteDialogComponent,
    MobileVersionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMobileVersionModule {}
