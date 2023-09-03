import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MonthlyNewsDataComponent, MonthlyNewsDataUpdateComponent, monthlyNewsDataRoute } from './';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

const ENTITY_STATES = [...monthlyNewsDataRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MonthlyNewsDataComponent, MonthlyNewsDataUpdateComponent],
  entryComponents: [MonthlyNewsDataComponent, MonthlyNewsDataUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMonthlyNewsDataModule {}
