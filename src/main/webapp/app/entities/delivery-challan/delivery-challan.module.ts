import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {
  DeliveryChallanComponent,
  DeliveryChallanDetailComponent,
  DeliveryChallanUpdateComponent,
  DeliveryChallanDeletePopupComponent,
  DeliveryChallanDeleteDialogComponent,
  deliveryChallanRoute,
  deliveryChallanPopupRoute,
  DeliveryChallanFactoryPopupComponent,
  DeliveryChallanBussinessPopupComponent
} from './';
import { DeliveryChallanTariffPopupComponent } from './delivery-challan-tariff-popup.component';

const ENTITY_STATES = [...deliveryChallanRoute, ...deliveryChallanPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule, OwlDateTimeModule, OwlNativeDateTimeModule],
  declarations: [
    DeliveryChallanComponent,
    DeliveryChallanDetailComponent,
    DeliveryChallanUpdateComponent,
    DeliveryChallanDeleteDialogComponent,
    DeliveryChallanDeletePopupComponent,
    DeliveryChallanFactoryPopupComponent,
    DeliveryChallanBussinessPopupComponent,
    DeliveryChallanTariffPopupComponent
  ],
  entryComponents: [
    DeliveryChallanComponent,
    DeliveryChallanUpdateComponent,
    DeliveryChallanDeleteDialogComponent,
    DeliveryChallanDeletePopupComponent,
    DeliveryChallanFactoryPopupComponent,
    DeliveryChallanBussinessPopupComponent,
    DeliveryChallanTariffPopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalDeliveryChallanModule {}
