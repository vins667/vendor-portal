import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IVcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { VcutStyleImageService } from './vcut-style-image.service';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';

@Component({
  selector: 'jhi-vcut-style-image',
  templateUrl: './vcut-style-image.component.html'
})
export class VcutStyleImageComponent implements OnInit, OnDestroy {
  currentAccount: any;
  vcutStyleImages: IVcutStyleImage[];
  vcutStylePlanUploadSearch: VcutStylePlanUploadSearch;
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

  constructor(
    protected vcutStyleImageService: VcutStyleImageService,
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
      this.page = 1;
    });
  }

  search() {
    this.vcutStylePlanUploadSearch.size = ITEMS_PER_PAGE;
    this.vcutStylePlanUploadSearch.pageNo = 0;
    this.page = 1;
    this.vcutStyleImageService
      .queryCustom(this.vcutStylePlanUploadSearch)
      .subscribe(
        (res: HttpResponse<IVcutStyleImage[]>) => this.paginateVcutStyleImages(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.vcutStylePlanUploadSearch.size = this.itemsPerPage;
    this.vcutStylePlanUploadSearch.pageNo = this.page - 1;
    this.vcutStyleImageService
      .queryCustom(this.vcutStylePlanUploadSearch)
      .subscribe(
        (res: HttpResponse<IVcutStyleImage[]>) => this.paginateVcutStyleImages(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
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

  transition() {
    this.router.navigate(['/vcut-style-image'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/vcut-style-image',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.vcutStylePlanUploadSearch = new VcutStylePlanUploadSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInVcutStyleImages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVcutStyleImage) {
    return item.id;
  }

  registerChangeInVcutStyleImages() {
    this.eventSubscriber = this.eventManager.subscribe('vcutStyleImageListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVcutStyleImages(data: IVcutStyleImage[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.vcutStyleImages = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
