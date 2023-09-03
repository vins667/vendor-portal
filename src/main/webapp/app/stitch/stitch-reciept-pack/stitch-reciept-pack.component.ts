import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { StitchRecieptPackService } from './stitch-reciept-pack.service';
import { CutPlanSearch, ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

@Component({
  selector: 'jhi-stitch-reciept-pack',
  templateUrl: './stitch-reciept-pack.component.html'
})
export class StitchRecieptPackComponent implements OnInit, OnDestroy {
  search?: ICutPlanSearch;
  stitchIssuePacks?: IStitchIssuePack[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected stitchIssuePackService: StitchRecieptPackService,
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

    this.stitchIssuePackService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IStitchIssuePack[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInStitchIssuePackes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStitchIssuePack): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStitchIssuePackes(): void {
    this.eventSubscriber = this.eventManager.subscribe('stitchIssuePackListModification', () => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IStitchIssuePack[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.stitchIssuePacks = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
