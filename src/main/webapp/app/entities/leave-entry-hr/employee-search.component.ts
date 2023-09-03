import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EmployeeViewService } from '../employee-view';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IEmployeeSearch, EmployeeSearch } from 'app/shared/model/employee-search.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'jhi-employee-search-search',
  templateUrl: './employee-search.component.html'
})
export class EmployeeSearchComponent implements OnInit, OnDestroy {
  currentAccount: any;
  employeeViews: IEmployeeView[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  employeesearch: IEmployeeSearch;

  constructor(
    protected employeeViewService: EmployeeViewService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    public activeModal: NgbActiveModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.predicate = 'name';
    });
  }

  displayImage(path) {}

  loadAll() {}

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    if (
      this.employeesearch.empCode !== null ||
      this.employeesearch.name !== null ||
      this.employeesearch.designation !== null ||
      this.employeesearch.department !== null
    ) {
      this.employeesearch.size = ITEMS_PER_PAGE;
      this.employeesearch.pageNo = 0;
      this.page = 0;
      this.employeeViewService
        .queryFactory(this.employeesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeView[]>) => this.paginateEmployeeViews(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    if (this.employeesearch.empCode !== null || this.employeesearch.name !== null) {
      let size = this.totalItems - this.page * ITEMS_PER_PAGE;
      if (size < 0) {
        size = ITEMS_PER_PAGE + size;
      } else {
        size = ITEMS_PER_PAGE;
      }
      this.employeesearch.size = size;
      this.employeesearch.pageNo = this.page - 1;
      this.employeeViewService
        .queryFactory(this.employeesearch)
        .subscribe(
          (res: HttpResponse<IEmployeeView[]>) => this.paginateEmployeeViews(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    this.loadAll();
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/employee-view',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.employeesearch = new EmployeeSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEmployeeViews();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEmployeeView) {
    return item.cardNo;
  }

  registerChangeInEmployeeViews() {
    this.eventSubscriber = this.eventManager.subscribe('employeeViewListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'cardNo') {
      result.push('cardNo');
    }
    return result;
  }

  protected paginateEmployeeViews(data: IEmployeeView[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.employeeViews = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  addData(employeeView) {
    this.eventManager.broadcast({ name: 'selectedUserLinkCreation', content: employeeView });
    this.activeModal.dismiss('cancel');
  }
}
