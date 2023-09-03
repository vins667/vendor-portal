import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { UserPlantService } from './user-plant.service';
import { IUserPlantNew, UserPlantNew } from 'app/shared/model/user-plant-new.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';

@Component({
  selector: 'jhi-user-plant',
  templateUrl: './user-plant.component.html'
})
export class UserPlantComponent implements OnInit, OnDestroy {
  currentAccount: any;
  search?: IMasterSearch;
  userPlantNews: IUserPlantNew[];
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
  ascending!: boolean;
  reverse: any;
  ngbPaginationPage = 1;

  constructor(
    protected userPlantService: UserPlantService,
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
      this.search.sortType = 'asc';
    }

    this.userPlantService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<UserPlantNew[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  transition() {
    this.router.navigate(['/user-plants-new'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadPage();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/user-plants-new',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadPage();
  }

  ngOnInit() {
    this.loadPage();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });

    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });

    this.registerChangeInUserPlants();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUserPlant) {
    return item.id;
  }

  registerChangeInUserPlants() {
    this.eventSubscriber = this.eventManager.subscribe('userPlantListModification', response => this.loadPage());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateUserPlants(data: UserPlantNew[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.userPlantNews = data;
  }

  protected onSuccess(data: UserPlantNew[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    setTimeout(() => {
      /** spinner ends after 5 seconds */
    }, 1000);
    this.userPlantNews = data || [];
  }

  protected onError(): void {
    setTimeout(() => {
      /** spinner ends after 5 seconds */
    }, 1000);
    this.ngbPaginationPage = this.page;
  }
}
