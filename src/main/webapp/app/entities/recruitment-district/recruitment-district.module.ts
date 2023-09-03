import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RecruitmentDistrictComponent,
  RecruitmentDistrictDetailComponent,
  RecruitmentDistrictUpdateComponent,
  RecruitmentDistrictDeletePopupComponent,
  RecruitmentDistrictDeleteDialogComponent,
  recruitmentDistrictRoute,
  recruitmentDistrictPopupRoute
} from './';

const ENTITY_STATES = [...recruitmentDistrictRoute, ...recruitmentDistrictPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RecruitmentDistrictComponent,
    RecruitmentDistrictDetailComponent,
    RecruitmentDistrictUpdateComponent,
    RecruitmentDistrictDeleteDialogComponent,
    RecruitmentDistrictDeletePopupComponent
  ],
  entryComponents: [
    RecruitmentDistrictComponent,
    RecruitmentDistrictUpdateComponent,
    RecruitmentDistrictDeleteDialogComponent,
    RecruitmentDistrictDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRecruitmentDistrictModule {}
