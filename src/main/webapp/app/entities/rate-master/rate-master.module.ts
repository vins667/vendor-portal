import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {
  RateMasterComponent,
  RateMasterDetailComponent,
  RateMasterUpdateComponent,
  RateMasterDeletePopupComponent,
  RateMasterDeleteDialogComponent,
  rateMasterRoute,
  rateMasterPopupRoute
} from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...rateMasterRoute, ...rateMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    RateMasterComponent,
    RateMasterDetailComponent,
    RateMasterUpdateComponent,
    RateMasterDeleteDialogComponent,
    RateMasterDeletePopupComponent
  ],
  entryComponents: [RateMasterComponent, RateMasterUpdateComponent, RateMasterDeleteDialogComponent, RateMasterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRateMasterModule {}
