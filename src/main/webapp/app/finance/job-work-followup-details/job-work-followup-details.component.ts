import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IJobWorkFollowupDetails } from './job-work-followup-details.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JobWorkFollowupDetailsService } from './job-work-followup-details.service';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-job-work-followup-details',
  templateUrl: './job-work-followup-details.component.html'
})
export class JobWorkFollowupDetailsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  jobWorkFollowupDetails: IJobWorkFollowupDetails[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage = ITEMS_PER_PAGE;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any = true;
  ngbPaginationPage = 1;
  isDownload = false;
  ascending!: boolean;
  search: IMasterSearch;

  constructor(
    protected jobWorkFollowupDetailsService: JobWorkFollowupDetailsService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {
    this.search = new MasterSearch();
    this.search.code = 'P';
  }

  loadPage(page?: number) {
    const pageToLoad: number = page || this.page;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'jobWorkDate';
      this.search.sortType = 'asc';
    }

    this.jobWorkFollowupDetailsService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IJobWorkFollowupDetails[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit() {
    this.registerChangeInJobWorkFollowupDetailss();
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IJobWorkFollowupDetails) {
    return item.id;
  }

  registerChangeInJobWorkFollowupDetailss() {
    this.eventSubscriber = this.eventManager.subscribe('  ListModification', response => this.loadPage());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'jobWorkDate') {
      result.push('jobWorkDate');
    }
    return result;
  }

  protected onSuccess(data: IJobWorkFollowupDetails[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/job-work-followup-details'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.jobWorkFollowupDetails = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
