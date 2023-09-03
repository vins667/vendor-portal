import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VcutStylePlanUploadService } from './vcut-style-plan-upload.service';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';
import { DATE_TIME_FORMAT, DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import * as moment from 'moment';
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
  selector: 'jhi-vcut-style-plan-upload',
  templateUrl: './vcut-style-plan-upload.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VcutStylePlanUploadComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vcutStylePlanUploads: IVcutStylePlanUpload[];
  search: VcutStylePlanUploadSearch;
  eventSubscriber: Subscription;
  leaveDateFrom: any;
  leaveDateTo: any;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected vcutStylePlanUploadService: VcutStylePlanUploadService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.search = new VcutStylePlanUploadSearch();
  }

  loadPage(page?: number): void {
    if (this.leaveDateFrom !== null && this.leaveDateTo !== null) {
      const leaveDate = this.leaveDateFrom != null ? moment(this.leaveDateFrom, DATE_TIME_FORMAT).startOf('day') : null;
      this.search.planDate = leaveDate != null && leaveDate.isValid() ? leaveDate.format(DATE_FORMAT) : null;
      const leaveDateTo = this.leaveDateTo != null ? moment(this.leaveDateTo, DATE_TIME_FORMAT).startOf('day') : null;
      this.search.planDateTo = leaveDateTo != null && leaveDateTo.isValid() ? leaveDateTo.format(DATE_FORMAT) : null;

      const pageToLoad: number = page || this.page;

      this.search.size = this.itemsPerPage;
      this.search.pageNo = pageToLoad - 1;
      const sort = this.sort();
      if (sort && sort.length > 0) {
        this.search.sort = sort[0].split(',')[0];
        this.search.sortType = sort[0].split(',')[1];
      } else {
        this.search.sort = 'id';
        this.search.sortType = 'desc';
      }

      this.vcutStylePlanUploadService
        .queryCustom(this.search)
        .subscribe((res: HttpResponse<IVcutStylePlanUpload[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
    }
  }

  ngOnInit(): void {
    const dt = new Date();
    const month = dt.getMonth();
    const year = dt.getFullYear();
    const FirstDay = new Date(year, month, 1);
    const LastDay = new Date(year, month + 1, 0);
    this.leaveDateFrom = FirstDay;
    this.leaveDateFrom.setHours(0, 0, 0, 0);
    this.leaveDateTo = LastDay;
    this.leaveDateTo.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInVcutStylePlanUploads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVcutStylePlanUpload): number {
    return item.id!;
  }

  registerChangeInVcutStylePlanUploads() {
    this.eventSubscriber = this.eventManager.subscribe('vcutStylePlanUploadListModification', response => this.loadPage());
  }

  sort() {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IVcutStylePlanUpload[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.vcutStylePlanUploads = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
