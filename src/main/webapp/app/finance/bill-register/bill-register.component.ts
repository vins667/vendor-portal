import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { BillRegister, IBillRegister } from './bill-register.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BillRegisterService } from './bill-register.service';
import { BillRegisterDeleteDialogComponent } from './bill-register-delete-dialog.component';
import { BillRegisterSearch, IBillRegisterSearch } from 'app/finance/bill-register/bill-register-search.model';
import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingEntryDeleteDialogComponent } from 'app/directbooking/direct-booking-entry/direct-booking-entry-delete-dialog.component';
import { IBillRegisterMaster } from 'app/finance/bill-register/bill-register-master.model';
import { BillRegisterMasterService } from 'app/finance/bill-register/bill-register-master.service';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-bill-register',
  templateUrl: './bill-register.component.html'
})
export class BillRegisterComponent implements OnInit, OnDestroy {
  isDownload?: boolean;
  billRegisters?: IBillRegisterMaster[];
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
  search?: IBillRegisterSearch;

  constructor(
    protected billRegisterService: BillRegisterMasterService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {
    this.search = new BillRegisterSearch();
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
      .subscribe((res: HttpResponse<IBillRegisterMaster[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
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
    this.registerChangeInBillRegisterEntries();
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

  trackId(index: number, item: IBillRegister): number {
    return item.id!;
  }

  registerChangeInBillRegisterEntries(): void {
    this.eventSubscriber = this.eventManager.subscribe('billRegisterEntryListModification', () => this.loadPage());
  }

  delete(billRegister: IBillRegister): void {
    const modalRef = this.modalService.open(BillRegisterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillRegisterMaster[]>>) {
    result.subscribe(res => this.onSaveSuccess(res.body), () => this.onSaveError());
  }

  protected onSaveSuccess(billRegisters: IBillRegisterMaster[]) {
    this.isDownload = false;
    this.billRegisters = billRegisters;
    this.snotifyService.success('Save successfully', '', toastConfig);
  }

  protected onSaveError() {
    this.isDownload = false;
  }

  protected onSuccess(data: IBillRegister[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/bill-register'], {
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
