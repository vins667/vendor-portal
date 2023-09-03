import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPackingLineIssue } from 'app/shared/model/packing-line-issue.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PackingLineIssueService } from './packing-line-issue.service';
import { PackingLineIssueDeleteDialogComponent } from './packing-line-issue-delete-dialog.component';
import { CutPlanSearch, ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';

@Component({
  selector: 'jhi-packing-line-issue',
  templateUrl: './packing-line-issue.component.html'
})
export class PackingLineIssueComponent implements OnInit, OnDestroy {
  search?: ICutPlanSearch;
  stitchLineIssues?: IPackingLineIssue[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected cutPlanIssueStitchService: PackingLineIssueService,
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
      .subscribe((res: HttpResponse<IPackingLineIssue[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
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

  trackId(index: number, item: IPackingLineIssue): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCutPlanIssueStitches(): void {
    this.eventSubscriber = this.eventManager.subscribe('cutPlanIssueStitchListModification', () => this.loadPage());
  }

  delete(cutPlanIssueStitch: IPackingLineIssue): void {
    const modalRef = this.modalService.open(PackingLineIssueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cutPlanIssueStitch = cutPlanIssueStitch;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IPackingLineIssue[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.stitchLineIssues = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
