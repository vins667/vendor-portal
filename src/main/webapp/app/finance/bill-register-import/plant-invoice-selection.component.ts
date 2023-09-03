import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IPlantinvoiceSearch, PlantinvoiceSearch } from './plantinvoice-search.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JhiEventManager } from 'ng-jhipster';
import { BillRegisterImportMasterService } from './bill-register-import-master.service';
import { IBuyerRegisterPurchaseLineBeanModel } from './buyer-register-purchase-line-bean.model';

@Component({
  selector: 'jhi-plant-invoice-selection',
  templateUrl: './plant-invoice-selection.component.html'
})
export class PlantInvoiceSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  supplierCode?: string;
  plantinvoices: IBuyerRegisterPurchaseLineBeanModel[];
  allowedFilter: string;
  plantinvoiceSearch: IPlantinvoiceSearch;
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
    public billRegisterService: BillRegisterImportMasterService
  ) {
    this.plantinvoiceSearch = new PlantinvoiceSearch();
  }

  search(): void {
    const pageToLoad = 1;
    this.plantinvoiceSearch.suppliercode = this.supplierCode;
    this.plantinvoiceSearch.size = ITEMS_PER_PAGE;
    this.plantinvoiceSearch.pageNo = 0;
    this.page = 1;
    this.isProcess = true;
    this.billRegisterService
      .findBySupplierCode(this.plantinvoiceSearch)
      .subscribe(
        (res: HttpResponse<IBuyerRegisterPurchaseLineBeanModel[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;
    this.plantinvoiceSearch.suppliercode = this.supplierCode;
    this.plantinvoiceSearch.size = this.itemsPerPage;
    this.plantinvoiceSearch.pageNo = pageToLoad - 1;
    this.billRegisterService
      .findBySupplierCode(this.plantinvoiceSearch)
      .subscribe(
        (res: HttpResponse<IBuyerRegisterPurchaseLineBeanModel[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
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
  protected onSuccess(data: IBuyerRegisterPurchaseLineBeanModel[] | null, headers: HttpHeaders, page: number): void {
    this.isProcess = false;
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.plantinvoices = data || [];
  }

  protected onError(): void {
    this.isProcess = false;
    this.ngbPaginationPage = this.page;
  }

  loadData(plantinvoice?: IBuyerRegisterPurchaseLineBeanModel): void {
    this.eventManager.broadcast({ name: 'billRegisterPurchaseorderFilter', content: plantinvoice });
    this.activeModal.dismiss();
  }
}
