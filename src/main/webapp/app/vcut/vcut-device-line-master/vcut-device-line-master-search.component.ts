import { Component, OnDestroy, OnInit } from '@angular/core';
import { VcutDeviceLineMasterService } from 'app/vcut/vcut-device-line-master/vcut-device-line-master.service';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { EmployeeSearch, IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IVcutUserDeviceMaster } from 'app/shared/model/vcut-user-device-master.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IMaster } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-vcut-device-line-master-search',
  templateUrl: './vcut-device-line-master-search.component.html'
})
export class VcutDeviceLineMasterSearchComponent implements OnInit, OnDestroy {
  vcutUserDeviceMasters: IVcutUserDeviceMaster[];
  employeeSearch: IEmployeeSearch;
  myList: IVcutUserDeviceMaster[];
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
  masterSearch: IMaster;

  constructor(
    protected vcutDeviceLineMasterService: VcutDeviceLineMasterService,
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

  ngOnDestroy(): void {}

  ngOnInit(): void {
    this.employeeSearch = new EmployeeSearch();
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  search() {
    this.employeeSearch.size = ITEMS_PER_PAGE;
    this.employeeSearch.pageNo = 0;
    this.page = 1;
    this.vcutDeviceLineMasterService
      .querySearchEmployee(this.employeeSearch)
      .subscribe(
        (res: HttpResponse<IVcutUserDeviceMaster[]>) => this.paginateEmployeeSearch(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  manageList() {
    this.myList = [];
    this.vcutUserDeviceMasters.forEach(VcutUserDeviceMaster => {
      if (VcutUserDeviceMaster.checked === true) {
        this.myList.push(VcutUserDeviceMaster);
      }
    });
    this.eventManager.broadcast({ name: 'selectedEmployeeList', content: this.myList });
    this.activeModal.dismiss('cancel');
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  loadAll() {
    this.employeeSearch.size = ITEMS_PER_PAGE;
    this.employeeSearch.pageNo = 0;
    this.page = 1;
    this.vcutDeviceLineMasterService
      .querySearchEmployee(this.employeeSearch)
      .subscribe(
        (res: HttpResponse<IVcutUserDeviceMaster[]>) => this.paginateEmployeeSearch(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.employeeSearch.size = ITEMS_PER_PAGE;
    this.employeeSearch.pageNo = this.page - 1;
    // this.page = 1;
    this.vcutDeviceLineMasterService
      .querySearchEmployee(this.employeeSearch)
      .subscribe(
        (res: HttpResponse<IVcutUserDeviceMaster[]>) => this.paginateEmployeeSearch(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
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

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateEmployeeSearch(data: IVcutUserDeviceMaster[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.vcutUserDeviceMasters = data;
  }
}
