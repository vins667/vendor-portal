import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RecruitmentCityMasterComponent,
  RecruitmentCityMasterDetailComponent,
  RecruitmentCityMasterUpdateComponent,
  RecruitmentCityMasterDeletePopupComponent,
  RecruitmentCityMasterDeleteDialogComponent,
  recruitmentCityMasterRoute,
  recruitmentCityMasterPopupRoute
} from './';

const ENTITY_STATES = [...recruitmentCityMasterRoute, ...recruitmentCityMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RecruitmentCityMasterComponent,
    RecruitmentCityMasterDetailComponent,
    RecruitmentCityMasterUpdateComponent,
    RecruitmentCityMasterDeleteDialogComponent,
    RecruitmentCityMasterDeletePopupComponent
  ],
  entryComponents: [
    RecruitmentCityMasterComponent,
    RecruitmentCityMasterUpdateComponent,
    RecruitmentCityMasterDeleteDialogComponent,
    RecruitmentCityMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRecruitmentCityMasterModule {}
