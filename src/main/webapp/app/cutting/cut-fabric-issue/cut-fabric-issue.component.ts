import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CutPlanEntryService } from './cut-plan-entry.service';
import { CutPlanSearch, ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-cut-fabric-issue',
  templateUrl: './cut-fabric-issue.component.html'
})
export class CutFabricIssueComponent implements OnInit, OnDestroy {
  search?: ICutPlanSearch;
  cutPlanEntries: ICutPlanEntry[] = [];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected cutPlanEntryService: CutPlanEntryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {
    this.search = new CutPlanSearch();
    this.search.status = 'P';
  }

  loadPage(page?: number): void {
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

    this.cutPlanEntryService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<ICutPlanEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInCutPlanEntries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICutPlanEntry): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCutPlanEntries() {
    this.eventSubscriber = this.eventManager.subscribe('markerMasterEntryListModification', response => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICutPlanEntry[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.cutPlanEntries = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  callRelease(cutPlanEntry: ICutPlanEntry): void {
    this.snotifyService.confirm('Are you sure to Release Cut Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.release(toast, cutPlanEntry), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  release(toast, cutPlanEntry?: ICutPlanEntry): void {
    this.cutPlanEntryService.release(cutPlanEntry.id).subscribe(any => {
      this.snotifyService.error('Released successfully!', '', toastConfig);
      this.snotifyService.remove(toast.id);
      this.loadPage();
    });
  }
}
