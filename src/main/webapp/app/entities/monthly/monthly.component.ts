import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { MonthlyService } from './monthly.service';
import { ISalaryBean } from 'app/shared/model/salary-bean.model';
import { IMonthly } from 'app/shared/model/monthly.model';
import { SALARY_ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

@Component({
  selector: 'jhi-monthly-salary',
  templateUrl: './monthly.component.html',
  encapsulation: ViewEncapsulation.None
})
export class MonthlyComponent implements OnInit {
  currentAccount: any;
  salaryBeans: ISalaryBean[];
  error: any;
  success: any;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  routeSub: any;
  type: string;
  selectedMonthly: IMonthly;

  constructor(
    protected monthlyService: MonthlyService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private service: MonthlyService
  ) {
    this.itemsPerPage = SALARY_ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.monthlyService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ISalaryBean[]>) => this.paginateMonthly(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/monthly-salary'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/monthly-salary',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
  }

  trackId(index: number, item: ISalaryBean) {
    return item.monthYear;
  }

  sort() {
    const result = ['year' + ',' + 'desc'];
    result.push('month,desc');
    return result;
  }

  protected paginateMonthly(data: ISalaryBean[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.salaryBeans = data;
    if (this.salaryBeans[0]) {
      this.service.find(this.salaryBeans[0].monthYear).subscribe(monthly => {
        this.selectedMonthly = monthly.body;
      });
    }
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  viewDetails(salaryBean: ISalaryBean) {
    if (salaryBean.monthYear) {
      this.service.find(salaryBean.monthYear).subscribe(monthly => {
        this.selectedMonthly = monthly.body;
      });
    }
  }
}
