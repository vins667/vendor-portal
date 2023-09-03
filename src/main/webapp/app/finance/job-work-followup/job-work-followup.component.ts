import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IJobWorkFollowup } from './job-work-followup.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JobWorkFollowupService } from './job-work-followup.service';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { JobWorkFollowupDeleteDialogComponent } from 'app/finance/job-work-followup/job-work-followup-delete-dialog.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IFollowupBuyer } from 'app/finance/followup-buyer/followup-buyer.model';
import { JobWorkFollowupScheduleComponent } from 'app/finance/job-work-followup/job-work-followup-schedule.component';

@Component({
  selector: 'jhi-job-work-followup',
  templateUrl: './job-work-followup.component.html'
})
export class JobWorkFollowupComponent implements OnInit, OnDestroy {
  currentAccount: any;
  jobWorkFollowups: IJobWorkFollowup[];
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
  reverse: any;
  ngbPaginationPage = 1;
  isDownload = false;
  ascending!: boolean;
  search: IMasterSearch;

  constructor(
    protected jobWorkFollowupService: JobWorkFollowupService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager
  ) {
    this.search = new MasterSearch();
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
      this.search.sort = 'slCode';
      this.search.sortType = 'asc';
    }

    this.jobWorkFollowupService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IJobWorkFollowup[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInJobWorkFollowups();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IJobWorkFollowup) {
    return item.id;
  }

  registerChangeInJobWorkFollowups() {
    this.eventSubscriber = this.eventManager.subscribe('jobWorkFollowupListModification', response => this.loadPage());
  }

  delete(jobWorkFollowup: IJobWorkFollowup): void {
    const modalRef = this.modalService.open(JobWorkFollowupDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.jobWorkFollowup = jobWorkFollowup;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  selectTimeline(jobWorkFollowup: IJobWorkFollowup) {
    const modalRef = this.modalService.open(JobWorkFollowupScheduleComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
    modalRef.componentInstance.jobWorkFollowup = jobWorkFollowup;
  }

  protected onSuccess(data: IJobWorkFollowup[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/job-work-followups'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.jobWorkFollowups = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
