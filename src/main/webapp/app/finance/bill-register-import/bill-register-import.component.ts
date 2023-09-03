import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { BillRegisterImport, IBillRegisterImport } from './bill-register-import.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BillRegisterImportService } from './bill-register-import.service';
import { BillRegisterImportDeleteDialogComponent } from './bill-register-import-delete-dialog.component';
import { BillRegisterImportSearch, IBillRegisterImportSearch } from 'app/finance/bill-register-import/bill-register-import-search.model';
import { IBillRegisterImportMaster } from 'app/finance/bill-register-import/bill-register-import-master.model';
import { BillRegisterImportMasterService } from 'app/finance/bill-register-import/bill-register-import-master.service';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-bill-register-import',
  templateUrl: './bill-register-import.component.html'
})
export class BillRegisterImportComponent implements OnInit, OnDestroy {
  isDownload?: boolean;
  billRegisters?: IBillRegisterImportMaster[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  checked = false;
  submit = false;
  isSaving = false;
  search?: IBillRegisterImportSearch;
  dropdownList = [];
  dropdownSettings = {};
  constructor(
    protected billRegisterService: BillRegisterImportMasterService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.search = new BillRegisterImportSearch();
    this.search.dateType = 'INV';
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
      this.search.sort = 'code';
      this.search.sortType = 'asc';
    }

    this.billRegisterService
      .queryFilter(this.search)
      .subscribe(
        (res: HttpResponse<IBillRegisterImportMaster[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  excelExport(): void {
    const pageToLoad = 0;

    this.search.size = this.itemsPerPage;
    this.search.pageNo = pageToLoad - 1;
    const sort = this.sort();
    if (sort && sort.length > 0) {
      this.search.sort = sort[0].split(',')[0];
      this.search.sortType = sort[0].split(',')[1];
    } else {
      this.search.sort = 'code';
      this.search.sortType = 'asc';
    }

    this.isDownload = true;
    this.billRegisterService.downloadXls(this.search).subscribe(
      res => {
        FileSaver.saveAs(res, 'billRegister.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }

  ngOnInit(): void {
    this.dropdownList = [
      { id: 'SUPPLIER', itemName: 'SUPPLIER' },
      { id: 'CHA', itemName: 'CHA' },
      { id: 'FORWARDER', itemName: 'FORWARDER' },
      { id: 'TRANSPORTER', itemName: 'TRANSPORTER' },
      { id: 'OTHER', itemName: 'OTHER' }
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text: 'Select Bill Type',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      classes: ''
    };
    this.registerChangeInBillRegisterImportEntries();
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

  trackId(index: number, item: IBillRegisterImport): number {
    return item.id!;
  }

  registerChangeInBillRegisterImportEntries(): void {
    this.eventSubscriber = this.eventManager.subscribe('billRegisterEntryListModification', () => this.loadPage());
  }

  delete(billRegister: IBillRegisterImport): void {
    const modalRef = this.modalService.open(BillRegisterImportDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.billRegister = billRegister;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  checkAll(): void {
    if (this.checked) {
      this.billRegisters.forEach(billRegister => {
        if (!billRegister.receiveDate) {
          billRegister.received = true;
        }
      });
    } else {
      this.billRegisters.forEach(billRegister => {
        if (!billRegister.receiveDate) {
          billRegister.received = false;
        }
      });
    }
  }

  submittedCheckAll(): void {
    if (this.submit) {
      this.billRegisters.forEach(billRegister => {
        if (!billRegister.submitDate) {
          billRegister.submitted = true;
        }
      });
    } else {
      this.billRegisters.forEach(billRegister => {
        if (!billRegister.submitDate) {
          billRegister.submitted = false;
        }
      });
    }
  }

  save() {
    this.isDownload = false;
    this.subscribeToSaveResponse(this.billRegisterService.submit(this.billRegisters));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillRegisterImportMaster[]>>) {
    result.subscribe(res => this.onSaveSuccess(res.body), () => this.onSaveError());
  }

  protected onSaveSuccess(billRegisters: IBillRegisterImportMaster[]) {
    this.isDownload = false;
    this.billRegisters = billRegisters;
    this.snotifyService.success('Save successfully', '', toastConfig);
  }

  protected onSaveError(): void {
    this.isDownload = false;
  }

  onItemSelect(item: any): void {
    console.log(item);
    console.log(this.search.selectedItems);
  }

  OnItemDeSelect(item: any): void {
    console.log(item);
    console.log(this.search.selectedItems);
  }

  onSelectAll(items: any): void {
    console.log(items);
  }

  onDeSelectAll(items: any): void {
    console.log(items);
  }

  protected onSuccess(data: IBillRegisterImport[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/bill-register-import'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.billRegisters = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
