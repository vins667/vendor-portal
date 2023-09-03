import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import './vendor';
import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VamaniportalCoreModule } from 'app/core/core.module';
import { VamaniportalAppRoutingModule } from './app-routing.module';
import { VamaniportalHomeModule } from './home/home.module';
import { VamaniportalEntityModule } from './entities/entity.module';
import { VamaniportalPaymentrequestModule } from './paymentrequest/paymentrequest.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { VamaniportalJobpostingModule } from 'app/jobpostings/jobpostings.module';
import { VamaniportalFabriccodingModule } from 'app/fabriccoding/fabriccoding.module';
import { VamaniportalVendorportalModule } from 'app/vendorportal/vendorportal.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { DoctorScheduleComponent, MainFooterComponent, MobileComponent, UserDetailComponent } from 'app/layouts';
import { MenuDetailComponent } from 'app/layouts/navbar/menu-detail.component';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { VcutVendorportalModule } from 'app/vcut/vcut.module';
import { VamaniportalQlikDashboardModule } from 'app/qlik-dashboards/qlik-dashboard.module';
import { VamaniportalCostingModule } from './entities/costing/costing.module';
import { VamaniportalDirectbookingModule } from 'app/directbooking/directbooking.module';
import { VamaniportalCuttingModule } from './cutting/cutting.module';
import { VamaniportalPackingModule } from 'app/packing/packing.module';
import { VamaniportalCostingDetailsModule } from './costing/costing.module';
import { VamaniportalStitchingModule } from 'app/stitch/stitch.module';
import { VamaniportalFianceModule } from 'app/finance/finance.module';
import { VamaniportalOrderpartnerModule } from 'app/orderpartner/orderpartner.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    VamaniportalSharedModule,
    VamaniportalCoreModule,
    VamaniportalHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    VamaniportalEntityModule,
    VamaniportalJobpostingModule,
    VamaniportalFabriccodingModule,
    VamaniportalVendorportalModule,
    VamaniportalQlikDashboardModule,
    VcutVendorportalModule,
    VamaniportalCostingModule,
    VamaniportalCuttingModule,
    VamaniportalDirectbookingModule,
    VamaniportalCostingDetailsModule,
    VamaniportalStitchingModule,
    VamaniportalPackingModule,
    VamaniportalFianceModule,
    VamaniportalPaymentrequestModule,
    VamaniportalOrderpartnerModule,
    VamaniportalAppRoutingModule,
    SnotifyModule
  ],
  declarations: [
    JhiMainComponent,
    NavbarComponent,
    UserDetailComponent,
    MenuDetailComponent,
    DoctorScheduleComponent,
    MobileComponent,
    ErrorComponent,
    PageRibbonComponent,
    FooterComponent,
    MainFooterComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationInterceptor,
      multi: true
    },
    { provide: 'SnotifyToastConfig', useValue: ToastDefaults },
    SnotifyService
  ],
  bootstrap: [JhiMainComponent]
})
export class VamaniportalAppModule {
  constructor(private dpConfig: NgbDatepickerConfig) {
    this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
  }
}
