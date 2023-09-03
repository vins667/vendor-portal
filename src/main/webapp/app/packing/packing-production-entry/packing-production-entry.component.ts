import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPackingProductionEntry } from 'app/shared/model/packing-production-entry.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PackingProductionEntryService } from './packing-production-entry.service';
import { IPackingLineIssue } from 'app/shared/model/packing-line-issue.model';
import { CutPlanSearch, ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

@Component({
  selector: 'jhi-packing-production-entry',
  templateUrl: './packing-production-entry.component.html'
})
export class PackingProductionEntryComponent implements OnInit, OnDestroy {
  sewingProductionEntries?: IPackingProductionEntry[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  search?: ICutPlanSearch;
  isProcess = false;

  constructor(
    protected sewingProductionEntryService: PackingProductionEntryService,
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

    this.sewingProductionEntryService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IPackingProductionEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInSewingProductionEntries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPackingLineIssue): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSewingProductionEntries(): void {
    this.eventSubscriber = this.eventManager.subscribe('sewingProductionEntryListModification', () => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPackingProductionEntry[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.isProcess = false;
    this.sewingProductionEntries = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
