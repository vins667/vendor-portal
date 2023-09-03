import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DirectBookingApprovalEntryService } from './direct-booking-approval-entry.service';
import { DirectBookingSearch, IDirectBookingSearch } from 'app/shared/model/direct-booking-search.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FactoryService } from 'app/shared/db2/service/factory.service';
import { ICompany } from 'app/shared/db2/model/company.model';
import { IDivision } from 'app/shared/db2/model/division.model';
import { IFactory } from 'app/shared/model/factory.model';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-direct-booking-approval-entry',
  templateUrl: './direct-booking-approval-entry.component.html'
})
export class DirectBookingApprovalEntryComponent implements OnInit, OnDestroy {
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  search?: IDirectBookingSearch;
  directBookingEntries?: IDirectBookingEntry[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  isDownload = false;

  constructor(
    protected directBookingEntryService: DirectBookingApprovalEntryService,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected factoryService: FactoryService,
    protected finbusinessunitService: FinbusinessunitService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.search = new DirectBookingSearch();
    this.search.flag = 'F';
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
      this.search.sort = 'slCode';
      this.search.sortType = 'asc';
    }

    this.directBookingEntryService
      .queryFilter(this.search)
      .subscribe((res: HttpResponse<IDirectBookingEntry[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  generateXLS(page?: number) {
    this.isDownload = true;
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
    this.directBookingEntryService.downloadXlsx(this.search).subscribe(
      res => {
        FileSaver.saveAs(res, 'DirectBooking.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }

  ngOnInit(): void {
    this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
      this.companies = companies.body;
    });
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInDirectBookingEntries();
  }

  fetchDivision(): void {
    if (this.search.company) {
      this.divisionService.query(this.search.company).subscribe((divisions: HttpResponse<IDivision[]>) => {
        this.divisions = divisions.body;
      });
    } else {
      this.divisions = [];
    }
  }

  fetchBusinessUnit(): void {
    if (this.search.company) {
      this.finbusinessunitService.query(this.search.company).subscribe((businessunits: HttpResponse<IFinbusinessunit[]>) => {
        this.finbusinessunits = businessunits.body;
      });
    } else {
      this.finbusinessunits = [];
    }
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDirectBookingEntry): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDirectBookingEntries(): void {
    this.eventSubscriber = this.eventManager.subscribe('directBookingEntryListModification', () => this.loadPage());
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IDirectBookingEntry[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/direct-booking-approval-entry'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.directBookingEntries = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
