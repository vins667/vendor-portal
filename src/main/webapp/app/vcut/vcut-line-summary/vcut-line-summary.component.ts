import { Component, OnInit, OnDestroy, NgZone } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { timer } from 'rxjs/observable/timer';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IVcutFactorySummary } from 'app/shared/model/vcut-factory-summary.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VcutLineSummaryService } from './vcut-line-summary.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import * as moment from 'moment';
import { IVcutTvDefectBreakup } from 'app/shared/model/vcut-tv-defect-breakup.model';
import { IVcutTvDefectBreakupSummary } from 'app/shared/model/vcut-tv-defect-breakup-summary.model';
import { Master } from 'app/shared/model/master.modal';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PieChartZommComponent } from './pie-chart-zomm.component';
import { GaugeChartZommComponent } from './gauge-chart-zomm.component';
import { StacksChartZommComponent } from './stacks-chart-zomm.component';
import { MultiSeriesChartZommComponent } from './multi-series-chart-zomm.component';
import { LineChartZommComponent } from './line-chart-zomm.component';
import { DefectsChartZommComponent } from './defects-chart-zomm.component';
import { DefectsObChartZommComponent } from './defects-ob-chart-zomm.component';
import { isMoment } from 'moment';
import { VcutStyleHeatComponent } from './vcut-style-heat.component';
import { IVcutTvImageSummary } from 'app/shared/model/vcut-tv-image-summary.model';

// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'MMMM DD, YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-vcut-line-summary',
  templateUrl: './vcut-line-summary.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VcutLineSummaryComponent implements OnInit, OnDestroy {
  currentAccount: any;
  isWait: boolean;
  vcutFactorySummary: IVcutFactorySummary;
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  filterDateFrom: any;
  filterMaxDateFrom: any;
  isbarCum = true;
  isbarFilter: any;
  isOnline = true;
  isRefresh = true;
  styles: string[];
  isbarHourly = true;

  width = '100%';
  height = 300;
  type = 'pie2d';
  dataFormat = 'json';
  data: any;
  dataSource: any;
  counter: any;
  subscription = Subscription.EMPTY;

  widthMultiSeries = '100%';
  heightMultiSeries = 300;
  typeMultiSeries = 'mscolumn3dlinedy';
  dataFormatMultiSeries = 'json';
  dataCatgSeries: any;
  dataCatg: any;
  dataTarget: any;
  dataActual: any;
  dataPlannedEff: any;
  dataActualEff: any;
  dataSMV: any;
  dataFTP: number;
  dataREC: number;
  dataALT: number;
  dataREJ: number;
  dataSourceMultiSeries: any;

  widthGauge = '100%';
  heightGauge = 240;
  typeGauge = 'angulargauge';
  dataFormatGauge = 'json';
  dataSourceGauge: any;
  totPlanEff = 0;
  totAchievedEff = 0;
  totColor: any;

  widthStacks = '100%';
  heightStacks = 300;
  typeStacks = 'pyramid';
  dataFormatStacks = 'json';
  dataSourceStacks: any;

  widthLine = '100%';
  heightLine = 300;
  typeLine = 'msline';
  dataFormatLine = 'json';
  dataSourceLine: any;

  widthDefects = '100%';
  heightDefects = 300;
  typeDefects = 'stackedcolumn3d';
  dataFormatDefects = 'json';
  dataSourceDefects: any;

  widthDefectsOB = '100%';
  heightDefectsOB = 300;
  typeDefectsOB = 'stackedcolumn3d';
  dataFormatDefectsOB = 'json';
  dataSourceDefectsOB: any;

  handler: any;
  handlerLegend: any;
  initMessage: any;
  iMsg: string;
  clickPlotMsg: string;
  attached: boolean;
  chartObj: any;
  message: any;

  previousLine: any;

  defectCatg: any;
  defectsDataSet: any;

  defectOBCatg: any;
  defectsOBDataSet: any;

  protected ngbModalRef: NgbModalRef;

  constructor(
    protected vcutFactorySummaryService: VcutLineSummaryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private zone: NgZone,
    private modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.previousLine = undefined;
    if (this.vcutFactorySummary) {
      let index = 0;
      this.data = [];

      this.dataCatg = [];
      this.dataCatgSeries = [];
      this.dataTarget = [];
      this.dataActual = [];
      this.dataPlannedEff = [];
      this.dataActualEff = [];
      this.dataSMV = [];
      this.dataFTP = 0;
      this.dataREC = 0;
      this.dataALT = 0;
      this.dataREJ = 0;
      this.totColor = [];
      let totOriginalQuantity = 0;
      let totQuantity = 0;
      let totAchieved = 0;
      if (this.isbarHourly) {
        if (this.vcutFactorySummary.hourlyFactoryBreakups) {
          let tempIndex = 0;
          const tempDataCatgSeries = [];
          const tempDataPlannedEff = [];
          const tempDataActualEff = [];
          this.vcutFactorySummary.hourlyFactoryBreakups.forEach(hourlyFactoryBreakup => {
            if (hourlyFactoryBreakup.activeHour !== 'Z') {
              tempDataCatgSeries.push({ label: hourlyFactoryBreakup.id });
              tempDataPlannedEff.push({ value: hourlyFactoryBreakup.efficiencyPlan.toFixed() });
              tempDataActualEff.push({ value: hourlyFactoryBreakup.efficiencyActual.toFixed() });
            }
            ++tempIndex;
          });
          if (this.vcutFactorySummary.hourlyFactoryBreakups.length === tempIndex) {
            this.renderLineChart(tempDataCatgSeries, tempDataPlannedEff, tempDataActualEff, 'mscolumn3d');
          }
        }
      }
      if (this.vcutFactorySummary.vcutFactorySummaries) {
        this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
          ++index;
          this.data.push({
            label: vcutFactorySummary.line,
            value: vcutFactorySummary.quantity,
            valuePosition: 'inside'
          });

          this.dataCatgSeries.push({ label: vcutFactorySummary.line });
          this.dataTarget.push({ value: vcutFactorySummary.quantity });
          this.dataActual.push({ value: vcutFactorySummary.achieved });
          this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
          this.dataActualEff.push({ value: vcutFactorySummary.achEff.toFixed() });
          const fttRateActual =
            ((vcutFactorySummary.rejected + vcutFactorySummary.alter + vcutFactorySummary.rectified) * 100) /
            (vcutFactorySummary.ftt + vcutFactorySummary.rejected + vcutFactorySummary.alter + vcutFactorySummary.rectified);
          this.dataSMV.push({ value: fttRateActual.toFixed(2) });
        });
        if (this.vcutFactorySummary.vcutFactorySummaries.length === index) {
          this.renderPieChart(this.data);
          this.renderMultiSeries(this.dataCatgSeries, this.dataTarget, this.dataActual, this.dataSMV, 'Line');
          if (!this.isbarHourly) {
            this.renderLineChart(this.dataCatgSeries, this.dataPlannedEff, this.dataActualEff, 'msline');
          }
        }
        this.dataCatg.push({ label: this.vcutFactorySummary.factory });
        this.dataFTP += this.vcutFactorySummary.ftt;
        this.dataREC += this.vcutFactorySummary.rectified;
        this.dataALT += this.vcutFactorySummary.alter;
        this.dataREJ += this.vcutFactorySummary.rejected;
        totOriginalQuantity += Number(((this.vcutFactorySummary.quantity / this.vcutFactorySummary.planEff) * 100).toFixed(0));
        totQuantity += this.vcutFactorySummary.quantity;
        totAchieved += this.vcutFactorySummary.achieved;

        this.renderStackChart(this.dataCatg, this.dataFTP, this.dataREC, this.dataALT, this.dataREJ);

        if (totOriginalQuantity > 0) {
          this.totPlanEff = Number(this.vcutFactorySummary.planEff.toFixed());
          this.totAchievedEff = Number(this.vcutFactorySummary.achEff.toFixed());
          const minValue = ((this.totPlanEff * 80) / 100).toFixed();
          const maxValue = ((this.totPlanEff * 95) / 100).toFixed();
          this.totColor = [
            {
              minvalue: '0',
              maxvalue: minValue,
              code: '#F2726F'
            },
            {
              minvalue: minValue,
              maxvalue: maxValue,
              code: '#FFC533'
            },
            {
              minvalue: maxValue,
              maxvalue: '100',
              code: '#62B58F'
            }
          ];
          this.renderGaugeChart(this.totPlanEff, this.totAchievedEff, this.totColor);
        }

        // Multi Stacked Chart
        if (this.vcutFactorySummary.vcutFactorySummaries) {
          this.defectCatg = [];
          this.defectsDataSet = [];
          const defectMap = new Map();
          let data = [];
          let desc = 0;
          this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
            ++desc;
            this.defectCatg.push({ label: vcutFactorySummary.line });
            data.push(...vcutFactorySummary.vcutTvDefectBreakups.map(x => x.descriptionLocal));
            data = data.filter((elem, i, arr) => {
              if (arr.indexOf(elem) === i) {
                return elem;
              }
            });
          });
          if (desc === this.vcutFactorySummary.vcutFactorySummaries.length) {
            if (data.length > 0) {
              let ctr = 0;
              this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
                const dataTemp = vcutFactorySummary.vcutTvDefectBreakups.map(x => x.descriptionLocal);
                const dataValueTemp = vcutFactorySummary.vcutTvDefectBreakups.map(x => x.defectCount);
                data.forEach(dataT => {
                  if (dataTemp) {
                    if (dataTemp.includes(dataT)) {
                      if (defectMap.has(dataT)) {
                        const dataMap = defectMap.get(dataT);
                        dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      } else {
                        const dataMap = [];
                        dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      }
                    } else {
                      if (defectMap.has(dataT)) {
                        const dataMap = defectMap.get(dataT);
                        dataMap.push({ value: 0, showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      } else {
                        const dataMap = [];
                        dataMap.push({ value: 0, showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      }
                    }
                  } else {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  }
                });
                ++ctr;
              });
              if (ctr === this.vcutFactorySummary.vcutFactorySummaries.length && defectMap.size === data.length) {
                let mapCtr = 0;
                for (const [key, value] of defectMap) {
                  ++mapCtr;
                  this.defectsDataSet.push({ seriesname: key, data: value });
                }
                if (mapCtr === defectMap.size) {
                  if (this.defectsDataSet && this.defectsDataSet.length > 0) {
                    this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                  } else {
                    this.defectsDataSet = [];
                    this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
                    this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                  }
                }
              }
            } else {
              this.defectsDataSet = [];
              this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
              this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
            }
          }
        } else {
          this.defectCatg = [{ label: 'No Data' }];
          this.defectsDataSet = [];
          this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
          this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
        }

        // Multi Stacked Chart OB
        if (this.vcutFactorySummary.vcutFactorySummaries) {
          this.defectOBCatg = [];
          this.defectsOBDataSet = [];
          const defectMap = new Map();
          let data = [];
          let desc = 0;
          this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
            ++desc;
            this.defectOBCatg.push({ label: vcutFactorySummary.line });
            data.push(...vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.descriptionLocal));
            data = data.filter((elem, i, arr) => {
              if (arr.indexOf(elem) === i) {
                return elem;
              }
            });
          });
          if (desc === this.vcutFactorySummary.vcutFactorySummaries.length) {
            if (data.length > 0) {
              let ctr = 0;
              this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
                const dataTemp = vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.descriptionLocal);
                const dataValueTemp = vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.defectCount);
                data.forEach(dataT => {
                  if (dataTemp) {
                    if (dataTemp.includes(dataT)) {
                      if (defectMap.has(dataT)) {
                        const dataMap = defectMap.get(dataT);
                        dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      } else {
                        const dataMap = [];
                        dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      }
                    } else {
                      if (defectMap.has(dataT)) {
                        const dataMap = defectMap.get(dataT);
                        dataMap.push({ value: 0, showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      } else {
                        const dataMap = [];
                        dataMap.push({ value: 0, showValue: '0' });
                        defectMap.set(dataT, dataMap);
                      }
                    }
                  } else {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  }
                });
                ++ctr;
              });
              if (ctr === this.vcutFactorySummary.vcutFactorySummaries.length && defectMap.size === data.length) {
                let mapCtr = 0;
                for (const [key, value] of defectMap) {
                  ++mapCtr;
                  this.defectsOBDataSet.push({ seriesname: key, data: value });
                }
                if (mapCtr === defectMap.size) {
                  if (this.defectsOBDataSet && this.defectsOBDataSet.length > 0) {
                    this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                  } else {
                    this.defectsOBDataSet = [];
                    this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
                    this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                  }
                }
              }
            } else {
              this.defectsOBDataSet = [];
              this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
              this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
            }
          }
        } else {
          this.defectOBCatg = [{ label: 'No Data' }];
          this.defectsOBDataSet = [];
          this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
          this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
        }
      } else {
        this.data = [];
        this.dataCatg = [];
        this.dataCatgSeries = [];
        this.dataTarget = [];
        this.dataActual = [];
        this.dataPlannedEff = [];
        this.dataActualEff = [];
        this.dataSMV = [];
        this.dataFTP = 0;
        this.dataREC = 0;
        this.dataALT = 0;
        this.dataREJ = 0;
        this.totColor = [];
        this.totPlanEff = 0;
        this.totAchievedEff = 0;
        this.totColor = [
          {
            minvalue: '0',
            maxvalue: 50,
            code: '#F2726F'
          },
          {
            minvalue: 50,
            maxvalue: 70,
            code: '#FFC533'
          },
          {
            minvalue: 70,
            maxvalue: '100',
            code: '#62B58F'
          }
        ];

        this.renderPieChart(this.data);
        this.renderGaugeChart(this.totPlanEff, this.totAchievedEff, this.totColor);
        this.renderMultiSeries(this.dataCatgSeries, this.dataTarget, this.dataActual, this.dataSMV, 'Line');
        this.renderLineChart(this.dataCatgSeries, this.dataPlannedEff, this.dataActualEff, 'msline');
        this.renderStackChart(this.dataCatg, this.dataFTP, this.dataREC, this.dataALT, this.dataREJ);

        this.defectCatg = [{ label: 'No Data' }];
        this.defectsDataSet = [];
        this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
        this.renderDefectsChart(this.defectCatg, this.defectsDataSet);

        this.defectOBCatg = [{ label: 'No Data' }];
        this.defectsOBDataSet = [];
        this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
        this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
      }
    }
  }

  ngOnInit() {
    this.filterDateFrom = new Date();
    this.filterDateFrom.setHours(0, 0, 0, 0);
    this.isWait = true;
    this.vcutFactorySummaryService.query().subscribe(
      (res: HttpResponse<IVcutFactorySummary>) => {
        this.vcutFactorySummary = res.body;
        // this.subscribeRefresh();
        this.isWait = false;
        this.loadAll();
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  ngOnDestroy() {
    /*if (this.subscription) {
      this.subscription.unsubscribe();
    }*/
  }

  protected onError(errorMessage: string) {
    this.isWait = false;
    this.jhiAlertService.error(errorMessage, null, null);
  }

  attachEvent() {
    this.handler = this.dataplotClickHandler.bind(this);
    this.handlerLegend = this.legendItemClickedHandler.bind(this);
    this.initMessage = '';
    this.message = this.clickPlotMsg;
    this.attached = true;
    this.chartObj.addEventListener('dataplotClick', this.handler);
    this.chartObj.addEventListener('LegendItemClicked', this.handlerLegend);
  }

  initialized($event) {
    this.chartObj = $event.chart;
    this.attachEvent();
  }

  renderPieChart(dataValue: any) {
    this.dataSource = {
      chart: {
        dataplotClick: this.handler,
        placeValuesInside: '1',
        formatNumberScale: '0',
        plottooltext: '<span style="font-weight: 500">Line: </span>$label <br> <span style="font-weight: 500">Target: </span>$datavalue',
        legendposition: 'bottom',
        interactiveLegend: '1',
        enableMultiSlicing: '0',
        showLabels: '0',
        showLegend: '1',
        showValues: '0',
        showpercentvalues: '0',
        showToolTipShadow: '1',
        theme: 'fusion',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        toolTipBgAlpha: '100',
        use3DLighting: '1',
        valuePosition: 'inside'
      },
      data: dataValue
    };
  }

  getMessage(dataObj) {
    return `${dataObj.categoryLabel}`;
  }

  getDateSetMessage(dataObj) {
    return `${dataObj.datasetName}`;
  }

  dataplotClickHandler(eventObj, dataObj) {
    this.zone.run(() => {
      if (this.previousLine) {
        if (this.previousLine === this.getMessage(dataObj)) {
          this.previousLine = undefined;
          this.renderMultiSeriesData();
        } else {
          this.previousLine = this.getMessage(dataObj);
          this.renderMultiSeriesData();
        }
      } else {
        this.previousLine = this.getMessage(dataObj);
        this.renderMultiSeriesData();
      }
    });
  }

  legendItemClickedHandler(eventObj, dataObj) {
    this.zone.run(() => {
      if (this.previousLine) {
        if (this.previousLine === this.getDateSetMessage(dataObj)) {
          this.previousLine = undefined;
          this.renderMultiSeriesData();
        } else {
          this.previousLine = this.getDateSetMessage(dataObj);
          this.renderMultiSeriesData();
        }
      } else {
        this.previousLine = this.getDateSetMessage(dataObj);
        this.renderMultiSeriesData();
      }
    });
  }

  expendCollapse(vcutFactorySummary: IVcutFactorySummary, expend: boolean) {
    vcutFactorySummary.extend = expend;
  }

  renderMultiSeriesData() {
    if (this.previousLine) {
      this.dataCatgSeries = [];
      this.dataCatg = [];
      this.dataTarget = [];
      this.dataActual = [];
      this.dataPlannedEff = [];
      this.dataActualEff = [];
      this.dataSMV = [];
      this.dataFTP = 0;
      this.dataREC = 0;
      this.dataALT = 0;
      this.dataREJ = 0;
      this.totColor = [];
      this.styles = [];

      this.totColor = [];
      let index = 0;
      this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummaryStyle => {
        ++index;
        if (this.previousLine === vcutFactorySummaryStyle.line) {
          if (this.isbarFilter) {
            // Multi Stacked Chart Issue Wise
            if (vcutFactorySummaryStyle.vcutTvDefectBreakups) {
              this.defectCatg = [];
              this.defectsDataSet = [];
              let defectCount = 0;
              vcutFactorySummaryStyle.vcutTvDefectBreakups.forEach((vcutTvDefectBreakup: IVcutTvDefectBreakup) => {
                const dataSet = [];
                let defectSummaryCount = 0;
                vcutTvDefectBreakup.summaries.forEach((vcutTvDefectBreakupSummary: IVcutTvDefectBreakupSummary) => {
                  ++defectSummaryCount;
                  if (defectCount === 0) {
                    if (
                      this.isbarFilter === vcutTvDefectBreakupSummary.style &&
                      ((vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                        !vcutTvDefectBreakupSummary.activeHour)
                    ) {
                      this.defectCatg.push({ label: vcutTvDefectBreakupSummary.hours });
                    }
                  }

                  if (
                    this.isbarFilter === vcutTvDefectBreakupSummary.style &&
                    ((vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                      !vcutTvDefectBreakupSummary.activeHour)
                  ) {
                    if (this.isbarCum) {
                      if (vcutTvDefectBreakupSummary.countDefectCum > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      }
                    } else {
                      if (vcutTvDefectBreakupSummary.countDefect > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      }
                    }
                  }
                });
                if (defectSummaryCount === vcutTvDefectBreakup.summaries.length) {
                  ++defectCount;
                  this.defectsDataSet.push({ seriesname: vcutTvDefectBreakup.descriptionLocal, data: dataSet });
                }
              });
              if (defectCount === vcutFactorySummaryStyle.vcutTvDefectBreakups.length) {
                if (this.defectsDataSet && this.defectsDataSet.length > 0) {
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                } else {
                  this.defectsDataSet = [];
                  this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                }
              }
            }

            // Multi Stacked Chart OB Wise
            if (vcutFactorySummaryStyle.vcutTvDefectOBBreakups) {
              this.defectOBCatg = [];
              this.defectsOBDataSet = [];
              let defectCount = 0;
              vcutFactorySummaryStyle.vcutTvDefectOBBreakups.forEach((vcutTvDefectBreakup: IVcutTvDefectBreakup) => {
                const dataSet = [];
                let defectSummaryCount = 0;
                vcutTvDefectBreakup.summaries.forEach((vcutTvDefectBreakupSummary: IVcutTvDefectBreakupSummary) => {
                  ++defectSummaryCount;
                  if (defectCount === 0) {
                    if (
                      this.isbarFilter === vcutTvDefectBreakupSummary.style &&
                      ((vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                        !vcutTvDefectBreakupSummary.activeHour)
                    ) {
                      this.defectOBCatg.push({ label: vcutTvDefectBreakupSummary.hours });
                    }
                  }

                  if (
                    this.isbarFilter === vcutTvDefectBreakupSummary.style &&
                    ((vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                      !vcutTvDefectBreakupSummary.activeHour)
                  ) {
                    if (this.isbarCum) {
                      if (vcutTvDefectBreakupSummary.countDefectCum > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      }
                    } else {
                      if (vcutTvDefectBreakupSummary.countDefect > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      }
                    }
                  }
                });
                if (defectSummaryCount === vcutTvDefectBreakup.summaries.length) {
                  ++defectCount;
                  this.defectsOBDataSet.push({ seriesname: vcutTvDefectBreakup.descriptionLocal, data: dataSet });
                }
              });
              if (defectCount === vcutFactorySummaryStyle.vcutTvDefectOBBreakups.length) {
                if (this.defectsOBDataSet && this.defectsOBDataSet.length > 0) {
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                } else {
                  this.defectsOBDataSet = [];
                  this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                }
              }
            }
          } else {
            // Multi Stacked Chart Issue Wise
            if (vcutFactorySummaryStyle.vcutTvDefectBreakups) {
              this.defectCatg = [];
              this.defectsDataSet = [];
              let defectCount = 0;
              vcutFactorySummaryStyle.vcutTvDefectBreakups.forEach((vcutTvDefectBreakup: IVcutTvDefectBreakup) => {
                const dataSet = [];
                let defectSummaryCount = 0;
                vcutTvDefectBreakup.summaries.forEach((vcutTvDefectBreakupSummary: IVcutTvDefectBreakupSummary) => {
                  ++defectSummaryCount;
                  if (defectCount === 0) {
                    if (
                      (vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                      !vcutTvDefectBreakupSummary.activeHour
                    ) {
                      this.defectCatg.push({ label: vcutTvDefectBreakupSummary.hours });
                    }
                  }

                  if (
                    (vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                    !vcutTvDefectBreakupSummary.activeHour
                  ) {
                    if (this.isbarCum) {
                      if (vcutTvDefectBreakupSummary.countDefectCum > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      }
                    } else {
                      if (vcutTvDefectBreakupSummary.countDefect > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      }
                    }
                  }
                });
                if (defectSummaryCount === vcutTvDefectBreakup.summaries.length) {
                  ++defectCount;
                  this.defectsDataSet.push({ seriesname: vcutTvDefectBreakup.descriptionLocal, data: dataSet });
                }
              });
              if (defectCount === vcutFactorySummaryStyle.vcutTvDefectBreakups.length) {
                if (this.defectsDataSet && this.defectsDataSet.length > 0) {
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                } else {
                  this.defectsDataSet = [];
                  this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                }
              }
            }

            // Multi Stacked Chart Operation Wise Wise
            if (vcutFactorySummaryStyle.vcutTvDefectOBBreakups) {
              this.defectOBCatg = [];
              this.defectsOBDataSet = [];
              let defectCount = 0;
              vcutFactorySummaryStyle.vcutTvDefectOBBreakups.forEach((vcutTvDefectBreakup: IVcutTvDefectBreakup) => {
                const dataSet = [];
                let defectSummaryCount = 0;
                vcutTvDefectBreakup.summaries.forEach((vcutTvDefectBreakupSummary: IVcutTvDefectBreakupSummary) => {
                  ++defectSummaryCount;
                  if (defectCount === 0) {
                    if (
                      (vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                      !vcutTvDefectBreakupSummary.activeHour
                    ) {
                      this.defectOBCatg.push({ label: vcutTvDefectBreakupSummary.hours });
                    }
                  }

                  if (
                    (vcutTvDefectBreakupSummary.activeHour && vcutTvDefectBreakupSummary.activeHour !== 'Z') ||
                    !vcutTvDefectBreakupSummary.activeHour
                  ) {
                    if (this.isbarCum) {
                      if (vcutTvDefectBreakupSummary.countDefectCum > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefectCum, showValue: '0' });
                      }
                    } else {
                      if (vcutTvDefectBreakupSummary.countDefect > 0) {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      } else {
                        dataSet.push({ value: vcutTvDefectBreakupSummary.countDefect, showValue: '0' });
                      }
                    }
                  }
                });
                if (defectSummaryCount === vcutTvDefectBreakup.summaries.length) {
                  ++defectCount;
                  this.defectsOBDataSet.push({ seriesname: vcutTvDefectBreakup.descriptionLocal, data: dataSet });
                }
              });
              if (defectCount === vcutFactorySummaryStyle.vcutTvDefectOBBreakups.length) {
                if (this.defectsOBDataSet && this.defectsOBDataSet.length > 0) {
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                } else {
                  this.defectsOBDataSet = [];
                  this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                }
              }
            }
          }

          vcutFactorySummaryStyle.vcutFactorySummaries.forEach(vcutFactorySummary => {
            this.styles.push(vcutFactorySummary.style);
            vcutFactorySummary.extend = true;
            this.dataCatg.push({ label: vcutFactorySummary.line });
            this.dataFTP += vcutFactorySummary.ftt;
            this.dataREC += vcutFactorySummary.rectified;
            this.dataALT += vcutFactorySummary.alter;
            this.dataREJ += vcutFactorySummary.rejected;

            this.totPlanEff = Number(vcutFactorySummary.planEff.toFixed());
            this.totAchievedEff = Number(vcutFactorySummary.achEff.toFixed());

            if (this.isbarFilter && this.isbarFilter === vcutFactorySummary.style) {
              let indexBreak = 0;
              vcutFactorySummary.vcutFactoryLineBreakups.forEach(factoryBreakup => {
                ++indexBreak;
                if (factoryBreakup.activeHour !== 'Z') {
                  this.dataCatgSeries.push({ label: factoryBreakup.id + '' });
                  if (this.isbarCum) {
                    this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
                    this.dataActualEff.push({ value: factoryBreakup.efficiencyActual.toFixed() });

                    this.dataTarget.push({ value: factoryBreakup.cumPlan });
                    this.dataActual.push({ value: factoryBreakup.cumActual });

                    this.dataSMV.push({ value: factoryBreakup.dhuRateActual.toFixed(2) });
                  } else {
                    this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
                    this.dataActualEff.push({ value: factoryBreakup.efficiencyPlan.toFixed() });

                    this.dataTarget.push({ value: factoryBreakup.hourPlan });
                    this.dataActual.push({ value: factoryBreakup.hourActual });

                    this.dataSMV.push({ value: factoryBreakup.dhuRatePlan.toFixed(2) });
                  }
                }
              });
              if (indexBreak === vcutFactorySummary.vcutFactoryLineBreakups.length) {
                this.renderMultiSeries(this.dataCatgSeries, this.dataTarget, this.dataActual, this.dataSMV, 'Hour');
                this.renderLineChart(this.dataCatgSeries, this.dataPlannedEff, this.dataActualEff, 'msline');
              }
            }

            if (!this.isbarFilter) {
              let indexBreak = 0;
              vcutFactorySummary.vcutFactoryLineBreakups.forEach(factoryBreakup => {
                ++indexBreak;
                if (factoryBreakup.activeHour !== 'Z') {
                  this.dataCatgSeries.push({ label: factoryBreakup.id + '' });
                  if (this.isbarCum) {
                    this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
                    this.dataActualEff.push({ value: factoryBreakup.efficiencyActual.toFixed() });

                    this.dataTarget.push({ value: factoryBreakup.cumPlan });
                    this.dataActual.push({ value: factoryBreakup.cumActual });

                    this.dataSMV.push({ value: factoryBreakup.dhuRateActual.toFixed(2) });
                  } else {
                    this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
                    this.dataActualEff.push({ value: factoryBreakup.efficiencyPlan.toFixed() });

                    this.dataTarget.push({ value: factoryBreakup.hourPlan });
                    this.dataActual.push({ value: factoryBreakup.hourActual });

                    this.dataSMV.push({ value: factoryBreakup.dhuRatePlan.toFixed(2) });
                  }
                }
              });
              if (indexBreak === vcutFactorySummary.vcutFactoryLineBreakups.length) {
                this.renderMultiSeries(this.dataCatgSeries, this.dataTarget, this.dataActual, this.dataSMV, 'Hour');
                this.renderLineChart(this.dataCatgSeries, this.dataPlannedEff, this.dataActualEff, 'msline');
              }
            }
          });
        } else {
          vcutFactorySummaryStyle.extend = undefined;
        }
      });
      if (index === this.vcutFactorySummary.vcutFactorySummaries.length) {
        this.renderStackChart(this.dataCatg, this.dataFTP, this.dataREC, this.dataALT, this.dataREJ);

        if (this.totPlanEff > 0) {
          const minValue = ((this.totPlanEff * 80) / 100).toFixed();
          const maxValue = ((this.totPlanEff * 95) / 100).toFixed();
          this.totColor = [
            {
              minvalue: '0',
              maxvalue: minValue,
              code: '#F2726F'
            },
            {
              minvalue: minValue,
              maxvalue: maxValue,
              code: '#FFC533'
            },
            {
              minvalue: maxValue,
              maxvalue: '100',
              code: '#62B58F'
            }
          ];
          this.renderGaugeChart(this.totPlanEff, this.totAchievedEff, this.totColor);
        }
      }
    } else {
      let index = 0;
      this.data = [];

      this.dataCatg = [];
      this.dataCatgSeries = [];
      this.dataTarget = [];
      this.dataActual = [];
      this.dataPlannedEff = [];
      this.dataActualEff = [];
      this.dataSMV = [];
      this.dataFTP = 0;
      this.dataREC = 0;
      this.dataALT = 0;
      this.dataREJ = 0;
      this.totColor = [];
      let totOriginalQuantity = 0;
      let totQuantity = 0;
      let totAchieved = 0;

      if (this.isbarHourly) {
        if (this.vcutFactorySummary.hourlyFactoryBreakups) {
          let tempIndex = 0;
          const tempDataCatgSeries = [];
          const tempDataPlannedEff = [];
          const tempDataActualEff = [];
          this.vcutFactorySummary.hourlyFactoryBreakups.forEach(hourlyFactoryBreakup => {
            if (hourlyFactoryBreakup.activeHour !== 'Z') {
              tempDataCatgSeries.push({ label: hourlyFactoryBreakup.id });
              tempDataPlannedEff.push({ value: hourlyFactoryBreakup.efficiencyPlan.toFixed() });
              tempDataActualEff.push({ value: hourlyFactoryBreakup.efficiencyActual.toFixed() });
            }
            ++tempIndex;
          });
          if (this.vcutFactorySummary.hourlyFactoryBreakups.length === tempIndex) {
            this.renderLineChart(tempDataCatgSeries, tempDataPlannedEff, tempDataActualEff, 'mscolumn3d');
          }
        }
      }

      this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
        vcutFactorySummary.extend = undefined;
        ++index;
        this.data.push({
          label: vcutFactorySummary.line,
          value: vcutFactorySummary.quantity,
          valuePosition: 'inside'
        });
        this.dataCatgSeries.push({ label: vcutFactorySummary.line });
        this.dataTarget.push({ value: vcutFactorySummary.quantity });
        this.dataActual.push({ value: vcutFactorySummary.achieved });
        const fttRateActual =
          ((vcutFactorySummary.rejected + vcutFactorySummary.alter + vcutFactorySummary.rectified) * 100) /
          (vcutFactorySummary.ftt + vcutFactorySummary.rejected + vcutFactorySummary.alter + vcutFactorySummary.rectified);
        this.dataSMV.push({ value: fttRateActual.toFixed(2) });

        this.dataPlannedEff.push({ value: vcutFactorySummary.planEff.toFixed() });
        this.dataActualEff.push({ value: vcutFactorySummary.achEff.toFixed() });
      });
      if (index === this.vcutFactorySummary.vcutFactorySummaries.length) {
        this.renderMultiSeries(this.dataCatgSeries, this.dataTarget, this.dataActual, this.dataSMV, 'Line');
        if (!this.isbarHourly) {
          this.renderLineChart(this.dataCatgSeries, this.dataPlannedEff, this.dataActualEff, 'msline');
        }
      }
      this.dataCatg.push({ label: this.vcutFactorySummary.factory });
      this.dataFTP += this.vcutFactorySummary.ftt;
      this.dataREC += this.vcutFactorySummary.rectified;
      this.dataALT += this.vcutFactorySummary.alter;
      this.dataREJ += this.vcutFactorySummary.rejected;
      totOriginalQuantity += Number(((this.vcutFactorySummary.quantity / this.vcutFactorySummary.planEff) * 100).toFixed(0));
      totQuantity += this.vcutFactorySummary.quantity;
      totAchieved += this.vcutFactorySummary.achieved;

      this.renderStackChart(this.dataCatg, this.dataFTP, this.dataREC, this.dataALT, this.dataREJ);

      if (totOriginalQuantity > 0) {
        this.totPlanEff = Number(this.vcutFactorySummary.planEff.toFixed());
        this.totAchievedEff = Number(this.vcutFactorySummary.achEff.toFixed());
        const minValue = ((this.totPlanEff * 80) / 100).toFixed();
        const maxValue = ((this.totPlanEff * 95) / 100).toFixed();
        this.totColor = [
          {
            minvalue: '0',
            maxvalue: minValue,
            code: '#F2726F'
          },
          {
            minvalue: minValue,
            maxvalue: maxValue,
            code: '#FFC533'
          },
          {
            minvalue: maxValue,
            maxvalue: '100',
            code: '#62B58F'
          }
        ];
        this.renderGaugeChart(this.totPlanEff, this.totAchievedEff, this.totColor);
      }

      // Multi Stacked Chart
      if (this.vcutFactorySummary.vcutFactorySummaries) {
        this.defectCatg = [];
        this.defectsDataSet = [];
        const defectMap = new Map();
        let data = [];
        let desc = 0;
        this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
          ++desc;
          this.defectCatg.push({ label: vcutFactorySummary.line });
          data.push(...vcutFactorySummary.vcutTvDefectBreakups.map(x => x.descriptionLocal));
          data = data.filter((elem, i, arr) => {
            if (arr.indexOf(elem) === i) {
              return elem;
            }
          });
        });
        if (desc === this.vcutFactorySummary.vcutFactorySummaries.length) {
          if (data.length > 0) {
            let ctr = 0;
            this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
              const dataTemp = vcutFactorySummary.vcutTvDefectBreakups.map(x => x.descriptionLocal);
              const dataValueTemp = vcutFactorySummary.vcutTvDefectBreakups.map(x => x.defectCount);
              data.forEach(dataT => {
                if (dataTemp) {
                  if (dataTemp.includes(dataT)) {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  } else {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  }
                } else {
                  if (defectMap.has(dataT)) {
                    const dataMap = defectMap.get(dataT);
                    dataMap.push({ value: 0, showValue: '0' });
                    defectMap.set(dataT, dataMap);
                  } else {
                    const dataMap = [];
                    dataMap.push({ value: 0, showValue: '0' });
                    defectMap.set(dataT, dataMap);
                  }
                }
              });
              ++ctr;
            });
            if (ctr === this.vcutFactorySummary.vcutFactorySummaries.length && defectMap.size === data.length) {
              let mapCtr = 0;
              for (const [key, value] of defectMap) {
                ++mapCtr;
                this.defectsDataSet.push({ seriesname: key, data: value });
              }
              if (mapCtr === defectMap.size) {
                if (this.defectsDataSet && this.defectsDataSet.length > 0) {
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                } else {
                  this.defectsDataSet = [];
                  this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
                }
              }
            }
          } else {
            this.defectsDataSet = [];
            this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
            this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
          }
        }
      } else {
        this.defectCatg = [{ label: 'No Data' }];
        this.defectsDataSet = [];
        this.defectsDataSet.push({ seriesname: 'No Data', data: [] });
        this.renderDefectsChart(this.defectCatg, this.defectsDataSet);
      }

      // Multi Stacked Chart OB
      if (this.vcutFactorySummary.vcutFactorySummaries) {
        this.defectOBCatg = [];
        this.defectsOBDataSet = [];
        const defectMap = new Map();
        let data = [];
        let desc = 0;
        this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
          ++desc;
          this.defectOBCatg.push({ label: vcutFactorySummary.line });
          data.push(...vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.descriptionLocal));
          data = data.filter((elem, i, arr) => {
            if (arr.indexOf(elem) === i) {
              return elem;
            }
          });
        });
        if (desc === this.vcutFactorySummary.vcutFactorySummaries.length) {
          if (data.length > 0) {
            let ctr = 0;
            this.vcutFactorySummary.vcutFactorySummaries.forEach(vcutFactorySummary => {
              const dataTemp = vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.descriptionLocal);
              const dataValueTemp = vcutFactorySummary.vcutTvDefectOBBreakups.map(x => x.defectCount);
              data.forEach(dataT => {
                if (dataTemp) {
                  if (dataTemp.includes(dataT)) {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: dataValueTemp[dataTemp.indexOf(dataT)], showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  } else {
                    if (defectMap.has(dataT)) {
                      const dataMap = defectMap.get(dataT);
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    } else {
                      const dataMap = [];
                      dataMap.push({ value: 0, showValue: '0' });
                      defectMap.set(dataT, dataMap);
                    }
                  }
                } else {
                  if (defectMap.has(dataT)) {
                    const dataMap = defectMap.get(dataT);
                    dataMap.push({ value: 0, showValue: '0' });
                    defectMap.set(dataT, dataMap);
                  } else {
                    const dataMap = [];
                    dataMap.push({ value: 0, showValue: '0' });
                    defectMap.set(dataT, dataMap);
                  }
                }
              });
              ++ctr;
            });
            if (ctr === this.vcutFactorySummary.vcutFactorySummaries.length && defectMap.size === data.length) {
              let mapCtr = 0;
              for (const [key, value] of defectMap) {
                ++mapCtr;
                this.defectsOBDataSet.push({ seriesname: key, data: value });
              }
              if (mapCtr === defectMap.size) {
                if (this.defectsOBDataSet && this.defectsOBDataSet.length > 0) {
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                } else {
                  this.defectsOBDataSet = [];
                  this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
                  this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
                }
              }
            }
          } else {
            this.defectsOBDataSet = [];
            this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
            this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
          }
        }
      } else {
        this.defectOBCatg = [{ label: 'No Data' }];
        this.defectsOBDataSet = [];
        this.defectsOBDataSet.push({ seriesname: 'No Data', data: [] });
        this.renderDefectsOBChart(this.defectOBCatg, this.defectsOBDataSet);
      }
    }
  }

  renderMultiSeries(dataCatg: any, dataTarget: any, dataActual: any, dataSMV: any, label: string) {
    this.dataSourceMultiSeries = {
      chart: {
        showValues: '1',
        interactiveLegend: '0',
        showAlternateHGridColor: '1',
        showToolTipShadow: '1',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        toolTipBgAlpha: '100',
        plottooltext: '<b>' + label + ': </b>$label<br><b>$seriesName: </b>$dataValue',
        use3DLighting: '1',
        theme: 'fusion'
      },
      categories: [
        {
          category: dataCatg
        }
      ],
      dataset: [
        {
          seriesname: 'Target',
          data: dataTarget
        },
        {
          seriesname: 'Actual',
          data: dataActual
        },
        {
          seriesname: 'DHU Rate',
          renderas: 'line',
          parentyaxis: 'S',
          data: dataSMV
        }
      ]
    };
  }

  renderGaugeChart(planEff: number, achEff: number, totColor: any) {
    this.dataSourceGauge = {
      chart: {
        bgAlpha: '100',
        showBorder: '0',
        showLegend: '1',
        baseFontColor: '#fff',
        baseFontSize: '12',
        lowerlimit: '0',
        upperlimit: '100',
        showvalue: '1',
        numbersuffix: '%',
        theme: 'candy',
        showToolTipShadow: '1',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        use3DLighting: '1',
        toolTipBgAlpha: '100'
      },
      colorrange: {
        color: totColor
      },
      dials: {
        dial: [
          {
            value: achEff,
            tooltext: '<b>Achieved Efficiency: </b> ' + achEff
          }
        ]
      },
      trendpoints: {
        point: [
          {
            startvalue: planEff,
            displayvalue: 'Planned Efficiency(' + planEff + '%)',
            thickness: '2',
            color: '#E15A26',
            usemarker: '1',
            markerbordercolor: '#E15A26',
            markertooltext: planEff + '%'
          }
        ]
      }
    };
  }

  renderStackChart(dataCatg: any, dataFTP: any, dataREC: any, dataALT: any, dataREJ: any) {
    this.dataSourceStacks = {
      chart: {
        interactiveLegend: '0',
        placeValuesInside: '1',
        plottooltext:
          '<span style="font-weight: 500">$label: </span> $value<br/><span style="font-weight: 500">Percentage: </span> $percentvalue',
        legendposition: 'bottom',
        enableMultiSlicing: '0',
        showLabels: '0',
        showLegend: '1',
        showValues: '1',
        showpercentvalues: '1',
        showPercentInToolTip: '1',
        showToolTipShadow: '1',
        theme: 'fusion',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        toolTipBgAlpha: '100',
        use3DLighting: '1',
        valuePosition: 'inside',
        is2d: '0',
        palettecolors: '89C7AB, E9BA42, F93A09, DC7170'
      },
      data: [
        {
          label: 'FTP - ' + dataFTP,
          value: dataFTP,
          isSliced: '1'
        },
        {
          label: 'REC - ' + dataREC,
          value: dataREC,
          isSliced: '1'
        },
        {
          label: 'ALT - ' + dataALT,
          value: dataALT,
          isSliced: '1'
        },
        {
          label: 'REJ - ' + dataREJ,
          value: dataREJ,
          isSliced: '1'
        }
      ]
    };
  }

  renderLineChart(dataCatg: any, dataTarget: any, dataActual: any, typeLine: any) {
    this.typeLine = typeLine;
    if (this.typeLine === 'mscolumn3d') {
      this.dataSourceLine = {
        chart: {
          showhovereffect: '1',
          numbersuffix: '%',
          drawcrossline: '1',
          showValues: '1',
          toolTipBorderColor: '#666666',
          toolTipBgColor: '#efefef',
          toolTipBgAlpha: '100',
          interactiveLegend: '0',
          plottooltext: '<span style="font-weight: 500">$seriesName: </span>$dataValue',
          use3DLighting: '1',
          theme: 'fusion'
        },
        categories: [
          {
            category: dataCatg
          }
        ],
        dataset: [
          {
            seriesname: 'Planned Eff.',
            data: dataTarget
          },
          {
            seriesname: 'Actual Eff.',
            data: dataActual
          }
        ]
      };
    } else {
      this.dataSourceLine = {
        chart: {
          showhovereffect: '1',
          numbersuffix: '%',
          drawcrossline: '1',
          toolTipBorderColor: '#666666',
          toolTipBgColor: '#efefef',
          toolTipBgAlpha: '100',
          interactiveLegend: '0',
          plottooltext: '<span style="font-weight: 500">$seriesName: </span>$dataValue',
          theme: 'fusion'
        },
        categories: [
          {
            category: dataCatg
          }
        ],
        dataset: [
          {
            seriesname: 'Planned',
            data: dataTarget
          },
          {
            seriesname: 'Actual',
            data: dataActual
          }
        ]
      };
    }
  }

  changeBarCum(val: boolean) {
    this.isbarCum = val;
    this.renderMultiSeriesData();
  }

  changeBarHourly(val: boolean) {
    this.isbarHourly = val;
    this.renderMultiSeriesData();
  }

  changeOnline(val: boolean) {
    this.isOnline = val;
    const d = new Date();
    if (val === undefined) {
      d.setDate(d.getDate() - 1);
      this.filterDateFrom = moment(d);
      this.filterMaxDateFrom = d;
      this.refreshScreen();
      // this.unsubscribeRefresh();
    } else {
      this.filterDateFrom = moment(d);
      this.filterMaxDateFrom = d;
      this.refreshScreen();
      // this.subscribeRefresh();
    }
  }

  /*changeRefresh(val: boolean) {
    this.isRefresh = val;
    if (val === true) {
      this.subscribeRefresh();
    } else {
      this.unsubscribeRefresh();
    }
  }*/

  refreshScreen() {
    this.isWait = true;
    const master = new Master();
    master.id = this.vcutFactorySummary.activeFactory;
    if (isMoment(this.filterDateFrom)) {
      master.desc = this.filterDateFrom.format(DATE_FORMAT);
    } else {
      master.desc = moment(this.filterDateFrom).format(DATE_FORMAT);
    }

    this.vcutFactorySummaryService.findWithDate(master).subscribe(
      (res: HttpResponse<IVcutFactorySummary>) => {
        this.vcutFactorySummary = res.body;
        this.isWait = false;
        this.loadAll();
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  changeFactory(factory) {
    if (this.vcutFactorySummary.activeFactory !== factory) {
      this.isWait = true;
      this.isWait = true;
      const master = new Master();
      master.id = factory;
      if (isMoment(this.filterDateFrom)) {
        master.desc = this.filterDateFrom.format(DATE_FORMAT);
      } else {
        master.desc = moment(this.filterDateFrom).format(DATE_FORMAT);
      }
      this.vcutFactorySummaryService.findWithDate(master).subscribe(
        (res: HttpResponse<IVcutFactorySummary>) => {
          this.vcutFactorySummary = res.body;
          this.isWait = false;
          this.loadAll();
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  renderDefectsChart(defectCatg: any, defectsDataSet: any) {
    this.dataSourceDefects = {
      chart: {
        showsum: '1',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        toolTipBgAlpha: '100',
        legendposition: 'right',
        legendItemFontSize: '13',
        plottooltext:
          '<span style="font-weight: 500">Type: </span>$seriesName{br}<span style="font-weight: 500">Defects: </span>$dataValue',
        stack100percent: '0',
        theme: 'fusion'
      },
      categories: [
        {
          category: defectCatg
        }
      ],
      dataset: defectsDataSet
    };
  }

  renderDefectsOBChart(defectCatg: any, defectsDataSet: any) {
    this.dataSourceDefectsOB = {
      chart: {
        showsum: '1',
        toolTipBorderColor: '#666666',
        toolTipBgColor: '#efefef',
        toolTipBgAlpha: '100',
        legendposition: 'right',
        legendItemFontSize: '13',
        plottooltext:
          '<span style="font-weight: 500">Type: </span>$seriesName{br}<span style="font-weight: 500">Defects: </span>$dataValue',
        stack100percent: '0',
        theme: 'fusion'
      },
      categories: [
        {
          category: defectCatg
        }
      ],
      dataset: defectsDataSet
    };
  }

  styleBarFilter(style) {
    this.isbarFilter = style;
    this.renderMultiSeriesData();
  }

  zoomPieChart() {
    this.ngbModalRef = this.modalService.open(PieChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSource = this.dataSource;
  }

  zoomGaugeChart() {
    this.ngbModalRef = this.modalService.open(GaugeChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceGauge = this.dataSourceGauge;
  }

  zoomStacksChart() {
    this.ngbModalRef = this.modalService.open(StacksChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceStacks = this.dataSourceStacks;
  }

  zoomMultiSeriesChart() {
    this.ngbModalRef = this.modalService.open(MultiSeriesChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceMultiSeries = this.dataSourceMultiSeries;
  }

  zoomLineChart() {
    this.ngbModalRef = this.modalService.open(LineChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceLine = this.dataSourceLine;
  }

  zoomDefectsChart() {
    this.ngbModalRef = this.modalService.open(DefectsChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceDefects = this.dataSourceDefects;
  }

  zoomDefectsOBChart() {
    this.ngbModalRef = this.modalService.open(DefectsObChartZommComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
    this.ngbModalRef.componentInstance.dataSourceDefectsOB = this.dataSourceDefectsOB;
  }

  displayStyleHeat(id) {
    this.vcutFactorySummaryService.findDefects(id).subscribe((vcutTvImageSummary: HttpResponse<IVcutTvImageSummary>) => {
      this.ngbModalRef = this.modalService.open(VcutStyleHeatComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'sxlModal'
      });
      this.ngbModalRef.componentInstance.vcutTvImageSummary = vcutTvImageSummary.body;
    });
  }

  /*subscribeRefresh() {
    this.counter = 60;
    this.subscription = timer(0, 1000).subscribe(timerm => {
      --this.counter;
      if (this.counter === 0) {
        this.isWait = true;
        const master = new Master();
        master.id = this.vcutFactorySummary.activeFactory;
        if (isMoment(this.filterDateFrom)) {
          master.desc = this.filterDateFrom.format(DATE_FORMAT);
        } else {
          master.desc = moment(this.filterDateFrom).format(DATE_FORMAT);
        }

        this.vcutFactorySummaryService.findWithDate(master).subscribe(
          (res: HttpResponse<IVcutFactorySummary>) => {
            this.vcutFactorySummary = res.body;
            this.isWait = false;
            this.counter = 60;
            this.renderMultiSeriesData();
          },
          (res: HttpErrorResponse) => this.onError(res.message)
        );
      } else if (this.counter < 0) {
        this.counter = 0;
      }
    });
  }

  unsubscribeRefresh() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }*/
}
