import { NgModule } from '@angular/core';
import { VamaniportalSharedLibsModule } from './shared-libs.module';
import { JhiAlertComponent } from './alert/alert.component';
import { JhiAlertErrorComponent } from './alert/alert-error.component';
import { JhiLoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { MyNumberPipe } from './pipe/my-number.pipe';
import { DecimalPipe } from '@angular/common';
import { PopupViewerComponent } from 'app/shared/popup/popup-viewer.component';
import { PopupComponent } from 'app/shared/popup/popup.component';
import { PopupPdfComponent } from 'app/shared/popup-pdf/popup-pdf.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { MultiSelectModule } from 'primeng/multiselect';

@NgModule({
  imports: [VamaniportalSharedLibsModule, NgxExtendedPdfViewerModule],
  declarations: [
    JhiAlertComponent,
    JhiAlertErrorComponent,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    MyNumberPipe,
    PopupComponent,
    PopupPdfComponent,
    PopupViewerComponent
  ],
  entryComponents: [JhiLoginModalComponent, PopupComponent, PopupPdfComponent, PopupViewerComponent],
  providers: [DecimalPipe],
  exports: [
    VamaniportalSharedLibsModule,
    JhiAlertComponent,
    JhiAlertErrorComponent,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    MyNumberPipe,
    NgxExtendedPdfViewerModule,
    MultiSelectModule
  ]
})
export class VamaniportalSharedModule {}
