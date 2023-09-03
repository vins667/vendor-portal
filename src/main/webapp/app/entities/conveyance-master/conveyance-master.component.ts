import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceMasterService } from './conveyance-master.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import * as _moment from 'moment';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE, OwlDateTimeComponent, OwlDateTimeFormats } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { FormControl } from '@angular/forms';
import { Moment } from 'moment';
import { IConveyanceSearchMaster, ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
const moment = (_moment as any).default ? (_moment as any).default : _moment;
export const MY_MOMENT_DATE_TIME_FORMATS: OwlDateTimeFormats = {
  parseInput: 'MM/YYYY',
  fullPickerInput: 'l LT',
  datePickerInput: 'MMM/YYYY',
  timePickerInput: 'LT',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-conveyance-master',
  templateUrl: './conveyance-master.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_DATE_TIME_FORMATS }
  ]
})
export class ConveyanceMasterComponent implements OnInit, OnDestroy {
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
  public fromDate = new FormControl(moment());
  conveyanceSearchMaster: IConveyanceSearchMaster;
  constructor(
    protected conveyanceMasterService: ConveyanceMasterService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
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

  chosenYearHandler1(normalizedYear: Moment) {
    const ctrlValue = this.fromDate.value;
    ctrlValue.year(normalizedYear.year());
    this.fromDate.setValue(ctrlValue);
  }

  chosenMonthHandler1(normalizedMonth: Moment, datepicker: OwlDateTimeComponent<Moment>) {
    const ctrlValue = this.fromDate.value;
    ctrlValue.month(normalizedMonth.month());
    this.fromDate.setValue(ctrlValue);
    datepicker.close();
  }

  search() {
    this.conveyanceSearchMaster.size = ITEMS_PER_PAGE;
    this.conveyanceSearchMaster.pageNo = 0;
    this.page = 1;
    this.conveyanceSearchMaster.conveyanceDate = this.fromDate.value;
    this.conveyanceMasterService
      .queryCustom(this.conveyanceSearchMaster)
      .subscribe(
        (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.conveyanceSearchMaster.size = this.itemsPerPage;
    this.conveyanceSearchMaster.pageNo = this.page - 1;
    this.conveyanceSearchMaster.conveyanceDate = this.fromDate.value;
    this.conveyanceMasterService
      .queryCustom(this.conveyanceSearchMaster)
      .subscribe(
        (res: HttpResponse<IConveyanceMaster[]>) => this.paginateConveyanceMasters(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAll() {
    this.search();
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/conveyance-master'], {
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
      '/conveyance-master',
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
    this.registerChangeInConveyanceMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConveyanceMaster) {
    return item.id;
  }

  registerChangeInConveyanceMasters() {
    this.eventSubscriber = this.eventManager.subscribe('conveyanceMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateConveyanceMasters(data: IConveyanceMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.conveyanceMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
