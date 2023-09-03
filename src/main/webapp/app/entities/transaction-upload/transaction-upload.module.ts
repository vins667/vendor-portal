import { NgModule } from '@angular/core';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { TextMaskModule } from 'angular2-text-mask';
import { transactionUploadRoute } from 'app/entities/transaction-upload/transaction-upload.route';
import { TransactionUploadComponent } from 'app/entities/transaction-upload/transaction-upload.component';
import { TransactionUploadDragDropDirective } from 'app/entities/transaction-upload/transaction-upload-drag-drop.directive';
import { TransactionUploadDetailsComponent } from 'app/entities/transaction-upload/transaction-upload-details.component';
import { TransactionUploadUpdateComponent } from 'app/entities/transaction-upload/transaction-upload-update.component';

const ENTITY_STATES = [...transactionUploadRoute];

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
    TransactionUploadDragDropDirective,
    TransactionUploadComponent,
    TransactionUploadDetailsComponent,
    TransactionUploadUpdateComponent
  ],
  entryComponents: [TransactionUploadComponent, TransactionUploadDetailsComponent, TransactionUploadUpdateComponent],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniPortalTransactionUploadModule {}
