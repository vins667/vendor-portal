import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IMarkerMasterSearch, MarkerMasterSearch } from 'app/shared/model/marker-master-search.model';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { MarkerEntryApprovalService } from './marker-entry-approval.service';
import { BalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';

@Component({
  selector: 'jhi-marker-entry-approval',
  templateUrl: './marker-entry-approval.component.html'
})
export class MarkerEntryApprovalComponent implements OnInit, OnDestroy {
  search?: IMarkerMasterSearch;
  markerMasterEntries: IMarkerMasterEntry[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  isProcess = false;
  isSaving: boolean;

  constructor(
    protected markerEntryApprovalService: MarkerEntryApprovalService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
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
    this.markerEntryApprovalService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IMarkerMasterEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.search.status = 'P';
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

  /*approveEntry(markerMasterEntry: IMarkerMasterEntry): void {
    const tempMarkerMaster = Object.assign({}, markerMasterEntry);
    tempMarkerMaster.id = undefined;
    this.markerEntryApprovalService.markerMasterEntry = tempMarkerMaster;
    this.router.navigate(['marker-master-entry/approve']);
  }*/

  approveEntry(markerMasterEntry: IMarkerMasterEntry): void {
    this.snotifyService.confirm('Are you sure to approve this Marker Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.forwardApproval(markerMasterEntry, toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  forwardApproval(markerMasterEntry: IMarkerMasterEntry, toast: any): void {
    this.isSaving = true;
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.markerEntryApprovalService.forwardApproval(markerMasterEntry.id));
  }

  RejectionEntry(markerMasterEntry: IMarkerMasterEntry): void {
    this.snotifyService.confirm('Are you sure to Reject this Marker Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.forwardRejection(markerMasterEntry, toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  forwardRejection(markerMasterEntry: IMarkerMasterEntry, toast: any): void {
    this.isSaving = true;
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.markerEntryApprovalService.forwardRejection(markerMasterEntry.id));
  }

  ReturnEntry(markerMasterEntry: IMarkerMasterEntry): void {
    this.snotifyService.confirm('Are you sure to Return this Marker Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.forwardReturn(markerMasterEntry, toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  forwardReturn(markerMasterEntry: IMarkerMasterEntry, toast: any): void {
    this.isSaving = true;
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.markerEntryApprovalService.forwardReturn(markerMasterEntry.id));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMarkerMasterEntry>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.isProcess = false;
    this.loadPage();
  }

  protected onSaveError() {
    this.isSaving = false;
    this.isProcess = false;
  }

  previousState() {
    window.history.back();
  }

  showHide(markerMasterEntry: IMarkerMasterEntry, value: boolean): void {
    markerMasterEntry.exist = value;
  }
}
