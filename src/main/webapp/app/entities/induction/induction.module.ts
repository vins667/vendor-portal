import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { InductionDetailComponent, inductionRoute, inductionPopupRoute } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { MatTabsModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';
const ENTITY_STATES = [...inductionRoute, ...inductionPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), PdfViewerModule, SnotifyModule, MatTabsModule, CdkTableModule],
  declarations: [InductionDetailComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalInductionModule {}
