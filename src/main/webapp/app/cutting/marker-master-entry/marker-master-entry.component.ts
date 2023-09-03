import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IMarkerMasterEntry, MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MarkerMasterEntryService } from './marker-master-entry.service';
import { IMarkerMasterSearch, MarkerMasterSearch } from 'app/shared/model/marker-master-search.model';
import { Fullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';

@Component({
  selector: 'jhi-marker-master-entry',
  templateUrl: './marker-master-entry.component.html'
})
export class MarkerMasterEntryComponent implements OnInit, OnDestroy {
  search?: IMarkerMasterSearch;
  markerMasterEntries: IMarkerMasterEntry[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected markerMasterEntryService: MarkerMasterEntryService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.search = new MarkerMasterSearch();
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
      this.search.sort = 'style';
      this.search.sortType = 'asc';
    }

    this.markerMasterEntryService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IMarkerMasterEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInMarkerMasterEntries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMarkerMasterEntry): any {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.style!;
  }

  registerChangeInMarkerMasterEntries() {
    this.eventSubscriber = this.eventManager.subscribe('markerMasterEntryListModification', response => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'style') {
      result.push('style');
    }
    return result;
  }

  protected onSuccess(data: IMarkerMasterEntry[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.markerMasterEntries = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  viewEntry(markerMasterEntry: IMarkerMasterEntry): void {
    const tempMarkerMaster = Object.assign({}, markerMasterEntry);
    tempMarkerMaster.id = undefined;
    tempMarkerMaster.markerCode = undefined;
    const fullitemkeydecoder = new Fullitemkeydecoder();
    fullitemkeydecoder.ordersubcode01 = tempMarkerMaster.subcode01;
    fullitemkeydecoder.ordersubcode02 = tempMarkerMaster.subcode02;
    fullitemkeydecoder.ordersubcode03 = tempMarkerMaster.subcode03;
    fullitemkeydecoder.ordersubcode04 = tempMarkerMaster.subcode04;
    fullitemkeydecoder.ordersubcode05 = tempMarkerMaster.subcode05;
    fullitemkeydecoder.ordersubcode06 = tempMarkerMaster.subcode06;
    fullitemkeydecoder.ordersubcode07 = tempMarkerMaster.subcode07;
    fullitemkeydecoder.ordersubcode08 = tempMarkerMaster.subcode08;
    fullitemkeydecoder.ordersubcode09 = tempMarkerMaster.subcode09;
    fullitemkeydecoder.ordersubcode10 = tempMarkerMaster.subcode10;
    fullitemkeydecoder.itemtypecompanycode = tempMarkerMaster.itemType;
    fullitemkeydecoder.summarizeddescription = tempMarkerMaster.itemCode as string;
    tempMarkerMaster.itemCode = fullitemkeydecoder;
    this.markerMasterEntryService.markerMasterEntry = tempMarkerMaster;
    this.router.navigate(['marker-master-entry/edit']);
  }
}
