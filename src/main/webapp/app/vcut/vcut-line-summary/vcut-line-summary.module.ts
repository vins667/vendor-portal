import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { VcutLineSummaryComponent } from './vcut-line-summary.component';
import { vcutLineSummaryRoute, vcutLineSummaryPopupRoute } from './vcut-line-summary.route';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';

import { FusionChartsModule } from 'angular-fusioncharts';
import * as Widgets from 'fusioncharts/fusioncharts.widgets';

// Load FusionCharts
import * as FusionCharts from 'fusioncharts';
// Load Charts module
import * as Charts from 'fusioncharts/fusioncharts.charts';
// Load fusion theme
import * as FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import { PieChartZommComponent } from './pie-chart-zomm.component';
import { GaugeChartZommComponent } from './gauge-chart-zomm.component';
import { StacksChartZommComponent } from './stacks-chart-zomm.component';
import { MultiSeriesChartZommComponent } from './multi-series-chart-zomm.component';
import { LineChartZommComponent } from './line-chart-zomm.component';
import { DefectsChartZommComponent } from './defects-chart-zomm.component';
import { DefectsObChartZommComponent } from './defects-ob-chart-zomm.component';
import { VcutStyleHeatComponent } from './vcut-style-heat.component';

// Add dependencies to FusionChartsModule
FusionChartsModule.fcRoot(FusionCharts, Charts, FusionTheme, Widgets);

const ENTITY_STATES = [...vcutLineSummaryRoute, ...vcutLineSummaryPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), OwlDateTimeModule, OwlNativeDateTimeModule, FusionChartsModule],
  declarations: [
    VcutLineSummaryComponent,
    PieChartZommComponent,
    GaugeChartZommComponent,
    StacksChartZommComponent,
    MultiSeriesChartZommComponent,
    LineChartZommComponent,
    DefectsChartZommComponent,
    DefectsObChartZommComponent,
    VcutStyleHeatComponent
  ],
  entryComponents: [
    VcutLineSummaryComponent,
    PieChartZommComponent,
    GaugeChartZommComponent,
    StacksChartZommComponent,
    MultiSeriesChartZommComponent,
    LineChartZommComponent,
    DefectsChartZommComponent,
    DefectsObChartZommComponent,
    VcutStyleHeatComponent
  ]
})
export class VamaniportalVcutLineSummaryModule {}
