import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  CategoryMasterComponent,
  CategoryMasterDetailComponent,
  CategoryMasterUpdateComponent,
  CategoryMasterDeletePopupComponent,
  CategoryMasterDeleteDialogComponent,
  categoryMasterRoute,
  categoryMasterPopupRoute
} from './';

const ENTITY_STATES = [...categoryMasterRoute, ...categoryMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CategoryMasterComponent,
    CategoryMasterDetailComponent,
    CategoryMasterUpdateComponent,
    CategoryMasterDeleteDialogComponent,
    CategoryMasterDeletePopupComponent
  ],
  entryComponents: [
    CategoryMasterComponent,
    CategoryMasterUpdateComponent,
    CategoryMasterDeleteDialogComponent,
    CategoryMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VendorportalCategoryMasterModule {}
