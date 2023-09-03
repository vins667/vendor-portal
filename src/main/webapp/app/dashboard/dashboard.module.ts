import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { DASHBOARD_ROUTE, DashboardComponent, NewsLetterComponent } from './';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { PdfViewerModule } from 'ng2-pdf-viewer';
@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(DASHBOARD_ROUTE), OwlDateTimeModule, OwlNativeDateTimeModule, PdfViewerModule],
  declarations: [DashboardComponent, NewsLetterComponent],
  entryComponents: [NewsLetterComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalDashboardModule {}
