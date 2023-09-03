import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { tdsComputationReportRoute } from './tds-computation-report.route';
import { TdsComputationReportComponent } from './tds-computation-report.component';
const ENTITY_STATES = [...tdsComputationReportRoute];

@NgModule({
  imports: [
    VamaniportalSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    SnotifyModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [TdsComputationReportComponent],
  entryComponents: [TdsComputationReportComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalTdsComputationReportModule {}
