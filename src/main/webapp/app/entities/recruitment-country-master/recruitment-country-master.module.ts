import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RecruitmentCountryMasterComponent,
  RecruitmentCountryMasterDetailComponent,
  RecruitmentCountryMasterUpdateComponent,
  RecruitmentCountryMasterDeletePopupComponent,
  RecruitmentCountryMasterDeleteDialogComponent,
  recruitmentCountryMasterRoute,
  recruitmentCountryMasterPopupRoute
} from './';

const ENTITY_STATES = [...recruitmentCountryMasterRoute, ...recruitmentCountryMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RecruitmentCountryMasterComponent,
    RecruitmentCountryMasterDetailComponent,
    RecruitmentCountryMasterUpdateComponent,
    RecruitmentCountryMasterDeleteDialogComponent,
    RecruitmentCountryMasterDeletePopupComponent
  ],
  entryComponents: [
    RecruitmentCountryMasterComponent,
    RecruitmentCountryMasterUpdateComponent,
    RecruitmentCountryMasterDeleteDialogComponent,
    RecruitmentCountryMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRecruitmentCountryMasterModule {}
