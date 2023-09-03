import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { OrderpartnerDocumentComponent } from './orderpartner-document.component';
import { OrderpartnerDocumentUpdateComponent } from './orderpartner-document-update.component';
import { orderpartnerDocumentRoute } from './orderpartner-document.route';
import { Ng2CompleterModule } from 'ng2-completer';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SnotifyModule } from 'ng-snotify';
import { OrderpartnerDocumentPopupComponent } from './orderpartner-document-popup.component';
import { OrderpartnerDocumentDetailComponent } from './orderpartner-document-detail.component';
import { OrderpartnerDragDropDirective } from './orderpartner-drag-drop.directive';

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(orderpartnerDocumentRoute),
    Ng2CompleterModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    SnotifyModule
  ],
  declarations: [
    OrderpartnerDragDropDirective,
    OrderpartnerDocumentComponent,
    OrderpartnerDocumentUpdateComponent,
    OrderpartnerDocumentPopupComponent,
    OrderpartnerDocumentDetailComponent
  ],
  entryComponents: [OrderpartnerDocumentPopupComponent]
})
export class VamaniportalOrderpartnerDocumentModule {}
