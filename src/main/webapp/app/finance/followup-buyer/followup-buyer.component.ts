import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IFollowupBuyer } from './followup-buyer.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FollowupBuyerService } from './followup-buyer.service';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FollowupBuyerDeletePopupComponent } from 'app/finance/followup-buyer/followup-buyer-delete-dialog.component';

@Component({
  selector: 'jhi-followup-buyer',
  templateUrl: './followup-buyer.component.html'
})
export class FollowupBuyerComponent implements OnInit, OnDestroy {
  currentAccount: any;
  followupBuyers: IFollowupBuyer[];
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
    protected followupBuyerService: FollowupBuyerService,
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

    this.followupBuyerService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IFollowupBuyer[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInFollowupBuyers();
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFollowupBuyer) {
    return item.id;
  }

  registerChangeInFollowupBuyers() {
    this.eventSubscriber = this.eventManager.subscribe('  ListModification', response => this.loadPage());
  }

  delete(followupBuyer: IFollowupBuyer): void {
    const modalRef = this.modalService.open(FollowupBuyerDeletePopupComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.followupBuyer = followupBuyer;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IFollowupBuyer[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/followup-buyers'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.followupBuyers = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
