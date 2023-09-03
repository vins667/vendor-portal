import { OnInit, Component, OnDestroy } from '@angular/core';
import { IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { Subscription } from 'rxjs';
import { NgbModalRef, NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DeliveryChallanService } from '.';
import { JhiParseLinks, JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IDeliveryChallanSearch, DeliveryChallanSearch } from 'app/shared/model/delivery-challan-search.model';
import { IFactory } from 'app/shared/model/factory.model';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { IDeliveryChallanDetails } from 'app/shared/model/delivery-challan-details.model';
@Component({
  selector: 'jhi-delivery-challan-tariff-popup',
  templateUrl: './delivery-challan-tariff-popup.component.html'
})
export class DeliveryChallanTariffPopupComponent implements OnInit, OnDestroy {
  deliveryChallanSearch: IDeliveryChallanSearch;
  deliveryChallanDetail: IDeliveryChallanDetails;
  deliveryChallan: IDeliveryChallan;
  currentAccount: any;
  factories: IFactory[];
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  protected ngbModalRef: NgbModalRef;
  isState: string;
  index: number;
  constructor(
    protected deliveryChallanService: DeliveryChallanService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    public activeModal: NgbActiveModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      /* this.page = data.pagingParams.page;
             this.previousPage = data.pagingParams.page;
             this.reverse = data.pagingParams.ascending;
             this.predicate = data.pagingParams.predicate; */
      this.page = 1;
    });
  }

  search() {
    this.deliveryChallanSearch.size = ITEMS_PER_PAGE;
    this.deliveryChallanSearch.pageNo = 0;
    this.page = 0;
    this.deliveryChallanService
      .queryTariff(this.deliveryChallanSearch)
      .subscribe(
        (res: HttpResponse<IFactory[]>) => this.paginateDeliveryChallans(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.deliveryChallanSearch.size = this.itemsPerPage;
    this.deliveryChallanSearch.pageNo = this.page - 1;
    this.deliveryChallanService
      .queryTariff(this.deliveryChallanSearch)
      .subscribe(
        (res: HttpResponse<IFactory[]>) => this.paginateDeliveryChallans(res.body, res.headers),
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
    this.loadAllNew();
  }

  clear() {
    this.loadAll();
  }

  ngOnInit() {
    this.deliveryChallanSearch = new DeliveryChallanSearch();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInDeliveryChallans();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDeliveryChallan) {
    return item.id;
  }

  registerChangeInDeliveryChallans() {
    this.eventSubscriber = this.eventManager.subscribe('deliveryChallanListModification', response => this.loadAll());
  }

  protected paginateDeliveryChallans(data: IFactory[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.factories = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  addData(tarrifcode: string) {
    if (this.isState !== undefined && this.isState !== null) {
      this.deliveryChallanService.findByTaxPers(tarrifcode, this.isState).subscribe(res => {
        this.deliveryChallan = res.body;
        this.deliveryChallan.index = this.index;
        this.eventManager.broadcast({ name: 'taxper', content: this.deliveryChallan });
        this.activeModal.dismiss('cancel');
      });
    }
  }
}
