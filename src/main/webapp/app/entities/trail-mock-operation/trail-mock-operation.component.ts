import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TrailMockOperationService } from './trail-mock-operation.service';
import { TrailMockSearchOperation, ITrailMockSearchOperation } from 'app/shared/model/trail-mock-search.model';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import * as FileSaver from 'file-saver';
@Component({
  selector: 'jhi-trail-mock-operation',
  templateUrl: './trail-mock-operation.component.html'
})
export class TrailMockOperationComponent implements OnInit, OnDestroy {
  currentAccount: any;
  trailMockOperations: IWorkerRecruitment[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  trailMockSearchOperation: ITrailMockSearchOperation;
  constructor(
    protected trailMockOperationService: TrailMockOperationService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.search();
  }
  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    if (this.trailMockSearchOperation.name !== null || this.trailMockSearchOperation.aadharNo !== null) {
      this.trailMockSearchOperation.size = ITEMS_PER_PAGE;
      this.trailMockSearchOperation.pageNo = 0;
      this.page = 0;
      this.trailMockOperationService
        .query(this.trailMockSearchOperation)
        .subscribe(
          (res: HttpResponse<IWorkerRecruitment[]>) => this.paginateTrailMockOperations(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  transition() {
    --this.page;
    if (this.trailMockSearchOperation.name !== null || this.trailMockSearchOperation.aadharNo !== null) {
      let size = this.totalItems - this.page * ITEMS_PER_PAGE;
      if (size < 0) {
        size = ITEMS_PER_PAGE + size;
      } else {
        size = ITEMS_PER_PAGE;
      }
      this.trailMockSearchOperation.size = size;
      this.trailMockSearchOperation.pageNo = this.page;
      this.trailMockOperationService
        .query(this.trailMockSearchOperation)
        .subscribe(
          (res: HttpResponse<IWorkerRecruitment[]>) => this.paginateTrailMockOperations(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/trail-mock-operation',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.trailMockSearchOperation = new TrailMockSearchOperation();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInTrailMockOperations();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWorkerRecruitment) {
    return item.id;
  }

  registerChangeInTrailMockOperations() {
    this.eventSubscriber = this.eventManager.subscribe('trailMockOperationListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  download(workerRecruitment: IWorkerRecruitment): any {
    this.trailMockOperationService.download(workerRecruitment.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${workerRecruitment.id}.pdf`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  protected paginateTrailMockOperations(data: IWorkerRecruitment[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.trailMockOperations = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
