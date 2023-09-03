import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SnotifyService } from 'ng-snotify';
import { ICutBundleLock } from 'app/shared/model/cut-bundle-lock.model';
import { CutBundleLockService } from 'app/cutting/cut-bundle-lock/cut-bundle-lock.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CutBundleLockDeleteDialogComponent } from 'app/cutting/cut-bundle-lock/cut-bundle-lock-delete-dialog.component';

@Component({
  selector: 'jhi-cut-bundle-lock',
  templateUrl: './cut-bundle-lock.component.html'
})
export class CutBundleLockComponent implements OnInit, OnDestroy {
  currentAccount: any;
  cutBundleLocks: ICutBundleLock[] = [];
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
  ngbPaginationPage = 1;

  constructor(
    protected cutBundleLockService: CutBundleLockService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
    });
  }

  loadAll() {
    this.cutBundleLockService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICutBundleLock[]>) => this.paginateCutBundleLockMasters(res.body, res.headers),
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
    this.router.navigate(['/cut-bundle-lock'], {
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
      '/education-master',
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
    this.registerChangeInCutBundleLockMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICutBundleLock) {
    return item.id;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  delete(cutBundleLock: ICutBundleLock): void {
    const modalRef = this.modalService.open(CutBundleLockDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cutBundleLock = cutBundleLock;
  }

  protected paginateCutBundleLockMasters(data: ICutBundleLock[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.cutBundleLocks = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
    this.ngbPaginationPage = this.page;
  }

  registerChangeInCutBundleLockMasters() {
    this.eventSubscriber = this.eventManager.subscribe('cutBundleLockListModification', response => this.loadAll());
  }
}
