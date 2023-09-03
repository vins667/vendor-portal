import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ConveyanceMasterProcessService } from './conveyance-master-process.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IMaster } from 'app/shared/model/master.modal';
import * as FileSaver from 'file-saver';

export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-conveyance-master-process',
  templateUrl: './conveyance-master-process.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ConveyanceMasterProcessComponent implements OnInit, OnDestroy {
  isDownload = false;
  currentAccount: any;
  conveyanceMasters: IConveyanceMaster[];
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
  leaveDateFrom: any;
  leaveDateTo: any;
  conveyanceSearchMaster: ConveyanceSearchMaster;
  processFlow: boolean;
  processIds: number[];
  master: IMaster;
  masters: IMaster[];
  controlNo: string;

  constructor(
    protected conveyanceMasterHrService: ConveyanceMasterProcessService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.page = 1;
    });
  }

  search() {
    if (this.conveyanceSearchMaster.status === 'P') {
      this.conveyanceSearchMaster.size = ITEMS_PER_PAGE;
      this.conveyanceSearchMaster.pageNo = 0;
      this.page = 1;
      this.conveyanceMasterHrService
        .query(this.conveyanceSearchMaster)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasterHrs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    } else if (this.conveyanceSearchMaster.status === 'A' && this.controlNo && this.controlNo.length > 0) {
      this.conveyanceMasterHrService
        .fetchControlsByCont(this.controlNo)
        .subscribe(
          (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasterHrs(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  download() {
    if (this.controlNo && this.controlNo.length > 0) {
      this.conveyanceMasterHrService.downloadPdf(this.controlNo).subscribe(
        res => {
          FileSaver.saveAs(res, 'ConveyanceReport.xlsx');
          this.isDownload = false;
        },
        res => {
          this.isDownload = false;
        }
      );
    }
  }

  loadAllNew() {
    this.conveyanceSearchMaster.size = this.itemsPerPage;
    this.conveyanceSearchMaster.pageNo = this.page - 1;
    this.conveyanceMasterHrService
      .query(this.conveyanceSearchMaster)
      .subscribe(
        (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasterHrs(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAll() {
    const dt = new Date();
    const month = dt.getMonth(),
      year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.leaveDateFrom = FirstDay;
    this.leaveDateFrom.setHours(0, 0, 0, 0);
    this.leaveDateTo = LastDay;
    this.leaveDateTo.setHours(0, 0, 0, 0);
    if (this.conveyanceSearchMaster === null || this.conveyanceSearchMaster.status === undefined) {
      this.conveyanceSearchMaster.status = 'P';
    }
    this.search();
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/conveyance-master-hr'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/conveyance-master-hr',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.conveyanceSearchMaster = new ConveyanceSearchMaster();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInConveyanceMasterHrs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConveyanceMaster) {
    return item.id;
  }

  registerChangeInConveyanceMasterHrs() {
    this.eventSubscriber = this.eventManager.subscribe('conveyanceMasterHrListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateConveyanceMasterHrs(data: IConveyanceMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.conveyanceMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  changeConveyance(conveyanceMaster: IConveyanceMaster) {
    if (conveyanceMaster && conveyanceMaster.processFlow && conveyanceMaster.processFlow === true) {
      conveyanceMaster.processFlow = false;
    } else {
      conveyanceMaster.processFlow = true;
    }
  }

  changeAllConveyance() {
    if (this.processFlow && this.processFlow === true) {
      this.processFlow = false;
      this.conveyanceMasters.forEach(conveyanceMaster => {
        conveyanceMaster.processFlow = false;
      });
    } else {
      this.processFlow = true;
      this.conveyanceMasters.forEach(conveyanceMaster => {
        conveyanceMaster.processFlow = true;
      });
    }
  }

  save() {
    this.processIds = [];
    if (this.conveyanceMasters && this.conveyanceMasters.length > 0) {
      let ctr = 0;
      this.conveyanceMasters.forEach(conveyanceMaster => {
        ++ctr;
        if (conveyanceMaster.processFlow && conveyanceMaster.processFlow === true) {
          this.processIds.push(conveyanceMaster.id);
        }
      });
      if (ctr === this.conveyanceMasters.length && this.processIds.length > 0) {
        this.conveyanceMasterHrService.save(this.processIds).subscribe((res: HttpResponse<IMaster>) => {
          this.master = res.body;
          this.conveyanceMasters = [];
          this.snotifyService.success('Process done successfully. Control No:' + res.body.id, '', toastConfig);
        });
      } else {
        this.snotifyService.error('No date choose for generating control no!!!', '', toastConfig);
      }
    } else {
      this.snotifyService.error('No date found!!!', '', toastConfig);
    }
  }

  fetchControl() {
    this.conveyanceMasters = [];
    if (this.conveyanceSearchMaster.status === 'A') {
      this.masters = [];
      this.conveyanceMasterHrService.fetchControls().subscribe((res: HttpResponse<IMaster[]>) => {
        this.masters = res.body;
      });
    } else {
      this.masters = [];
    }
  }
}
