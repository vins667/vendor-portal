import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  RecruitmentStateMasterComponent,
  RecruitmentStateMasterDetailComponent,
  RecruitmentStateMasterUpdateComponent,
  RecruitmentStateMasterDeletePopupComponent,
  RecruitmentStateMasterDeleteDialogComponent,
  recruitmentStateMasterRoute,
  recruitmentStateMasterPopupRoute
} from './';

const ENTITY_STATES = [...recruitmentStateMasterRoute, ...recruitmentStateMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RecruitmentStateMasterComponent,
    RecruitmentStateMasterDetailComponent,
    RecruitmentStateMasterUpdateComponent,
    RecruitmentStateMasterDeleteDialogComponent,
    RecruitmentStateMasterDeletePopupComponent
  ],
  entryComponents: [
    RecruitmentStateMasterComponent,
    RecruitmentStateMasterUpdateComponent,
    RecruitmentStateMasterDeleteDialogComponent,
    RecruitmentStateMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalRecruitmentStateMasterModule {}
