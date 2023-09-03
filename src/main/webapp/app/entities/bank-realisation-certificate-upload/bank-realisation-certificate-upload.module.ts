import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
import { BankRealisationCertificateUploadComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.component';
import {
  bankRealisationCertificateUploadPopupRoute,
  bankRealisationCertificateUploadRoute
} from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.route';
import { BankRealisationCertificateUploadDetailComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-detail.component';
import {
  BankRealisationCertificateUploadDeleteDialogComponent,
  BankRealisationCertificateUploadPopupComponent
} from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-delete-dialog.component';
import { BankRealisationCertificateUploadUpdateComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-update.component';
import { BankRealizationDragDropDirective } from './bank-realization-drag-drop.directive';
const ENTITY_STATES = [...bankRealisationCertificateUploadRoute, ...bankRealisationCertificateUploadPopupRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    TextMaskModule
  ],
  declarations: [
    BankRealizationDragDropDirective,
    BankRealisationCertificateUploadComponent,
    BankRealisationCertificateUploadDetailComponent,
    BankRealisationCertificateUploadDeleteDialogComponent,
    BankRealisationCertificateUploadPopupComponent,
    BankRealisationCertificateUploadUpdateComponent
  ],
  entryComponents: [
    BankRealisationCertificateUploadComponent,
    BankRealisationCertificateUploadDetailComponent,
    BankRealisationCertificateUploadDeleteDialogComponent,
    BankRealisationCertificateUploadPopupComponent,
    BankRealisationCertificateUploadUpdateComponent
  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniPortalBankRealizationCertificateModule {}
