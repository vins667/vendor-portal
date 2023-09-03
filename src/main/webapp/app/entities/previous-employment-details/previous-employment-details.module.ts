import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { OwlNativeDateTimeModule, OwlDateTimeModule } from 'ng-pick-datetime';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  PreviousEmploymentDetailsComponent,
  PreviousEmploymentDetailsDetailComponent,
  PreviousEmploymentDetailsUpdateComponent,
  PreviousEmploymentDetailsDeletePopupComponent,
  PreviousEmploymentDetailsDeleteDialogComponent,
  previousEmploymentDetailsRoute,
  previousEmploymentDetailsPopupRoute
} from './';
const ENTITY_STATES = [...previousEmploymentDetailsRoute, ...previousEmploymentDetailsPopupRoute];
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    PreviousEmploymentDetailsComponent,
    PreviousEmploymentDetailsDetailComponent,
    PreviousEmploymentDetailsUpdateComponent,
    PreviousEmploymentDetailsDeleteDialogComponent,
    PreviousEmploymentDetailsDeletePopupComponent
  ],
  entryComponents: [
    PreviousEmploymentDetailsComponent,
    PreviousEmploymentDetailsUpdateComponent,
    PreviousEmploymentDetailsDeleteDialogComponent,
    PreviousEmploymentDetailsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalPreviousEmploymentDetailsModule {}
