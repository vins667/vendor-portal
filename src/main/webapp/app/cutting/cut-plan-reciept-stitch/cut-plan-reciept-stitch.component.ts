import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CutPlanRecieptStitchService } from './cut-plan-reciept-stitch.service';
import { CutPlanSearch, ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

@Component({
  selector: 'jhi-cut-plan-reciept-stitch',
  templateUrl: './cut-plan-reciept-stitch.component.html'
})
export class CutPlanRecieptStitchComponent implements OnInit, OnDestroy {
  search?: ICutPlanSearch;
  cutPlanIssueStitches?: ICutPlanIssueStitch[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected cutPlanIssueStitchService: CutPlanRecieptStitchService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.search = new CutPlanSearch();
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

    this.cutPlanIssueStitchService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<ICutPlanIssueStitch[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInCutPlanIssueStitches();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICutPlanIssueStitch): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCutPlanIssueStitches(): void {
    this.eventSubscriber = this.eventManager.subscribe('cutPlanIssueStitchListModification', () => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICutPlanIssueStitch[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.cutPlanIssueStitches = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
