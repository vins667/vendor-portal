import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { NEWS_ROUTE, NewsComponent } from './';

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(NEWS_ROUTE)],
  declarations: [NewsComponent],
  entryComponents: [NewsComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalNewsModule {}
