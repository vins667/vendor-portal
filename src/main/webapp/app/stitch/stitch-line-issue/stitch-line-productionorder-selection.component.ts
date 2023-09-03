import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IProductionorderSearch, ProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { JhiEventManager } from 'ng-jhipster';
import { StitchLineIssueService } from './stitch-line-issue.service';

@Component({
  selector: 'jhi-stitch-line-productionorder-selection',
  templateUrl: './stitch-line-productionorder-selection.component.html'
})
export class StitchLineProductionorderSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  productionorders: IProductionorder[];
  allowedFilter: string;
  productionorderSearch: IProductionorderSearch;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    public stitchLineIssueService: StitchLineIssueService
  ) {
    this.productionorderSearch = new ProductionorderSearch();
  }

  search(): void {
    const pageToLoad = 1;
    this.productionorderSearch.size = ITEMS_PER_PAGE;
    this.productionorderSearch.pageNo = 0;
    this.page = 1;
    this.isProcess = true;
    this.stitchLineIssueService
      .queryCustom(this.productionorderSearch)
      .subscribe((res: HttpResponse<IProductionorder[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;
    this.productionorderSearch.size = this.itemsPerPage;
    this.productionorderSearch.pageNo = pageToLoad - 1;
    this.stitchLineIssueService
      .queryCustom(this.productionorderSearch)
      .subscribe((res: HttpResponse<IProductionorder[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.search();
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
  protected onSuccess(data: IProductionorder[] | null, headers: HttpHeaders, page: number): void {
    this.isProcess = false;
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.productionorders = data || [];
  }

  protected onError(): void {
    this.isProcess = false;
    this.ngbPaginationPage = this.page;
  }

  loadData(productionorder?: IProductionorder): void {
    this.eventManager.broadcast({ name: 'stitchLineEntryFilter', content: productionorder });
    this.activeModal.dismiss();
  }
}
