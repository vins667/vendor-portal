import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { KnitCreationMasterService } from './knit-creation-master.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IKnitCreationSearchMaster, KnitCreationSearchMaster } from 'app/shared/model/knit-creation-search-master.model';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';

@Component({
  selector: 'jhi-knit-creation-search-master',
  templateUrl: './knit-creation-search-master.component.html'
})
export class KnitCreationSearchMasterComponent implements OnInit, OnDestroy {
  currentAccount: any;
  knitCreationMasters: IYarnCountMaster[];
  knitCreationSearchMaster: IKnitCreationSearchMaster;
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  queryCount: any;
  previousPage: any;
  reverse: any;
  data: string;
  constructor(
    protected knitCreationMasterService: KnitCreationMasterService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    public activeModal: NgbActiveModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  loadAll() {
    this.knitCreationSearchMaster.size = ITEMS_PER_PAGE;
    this.knitCreationSearchMaster.pageNo = 0;
    this.page = 1;
    if (this.data && this.data === 'A') {
      this.knitCreationMasterService
        .queryYarnCount(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'B') {
      this.knitCreationMasterService
        .queryYarnType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'C') {
      this.knitCreationMasterService
        .queryKnitType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'D') {
      this.knitCreationMasterService
        .queryProcess(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
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

  loadAllNew() {
    this.knitCreationSearchMaster.size = this.itemsPerPage;
    this.knitCreationSearchMaster.pageNo = this.page - 1;
    if (this.data && this.data === 'A') {
      this.knitCreationMasterService
        .queryYarnCount(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'B') {
      this.knitCreationMasterService
        .queryYarnType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'C') {
      this.knitCreationMasterService
        .queryKnitType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'D') {
      this.knitCreationMasterService
        .queryProcess(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  clear() {
    this.loadAll();
  }

  search() {
    this.knitCreationSearchMaster.size = ITEMS_PER_PAGE;
    this.knitCreationSearchMaster.pageNo = 0;
    this.page = 0;
    if (this.data && this.data === 'A') {
      this.knitCreationMasterService
        .queryYarnCount(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'B') {
      this.knitCreationMasterService
        .queryYarnType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'C') {
      this.knitCreationMasterService
        .queryKnitType(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    if (this.data && this.data === 'D') {
      this.knitCreationMasterService
        .queryProcess(this.knitCreationSearchMaster)
        .subscribe(
          (res: HttpResponse<IYarnCountMaster[]>) => this.paginateKnitCreationMasters(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
  }

  ngOnInit() {
    this.knitCreationSearchMaster = new KnitCreationSearchMaster();
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInKnitCreationMasters();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IKnitCreationMaster) {
    return item.id;
  }

  registerChangeInKnitCreationMasters() {
    this.eventSubscriber = this.eventManager.subscribe('knitCreationMasterListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateKnitCreationMasters(data: IKnitCreationMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.knitCreationMasters = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  addData(knitCreationMasters) {
    this.eventManager.broadcast({ name: 'selectedknitCreationMaster', content: knitCreationMasters });
    this.activeModal.dismiss('cancel');
  }
}
