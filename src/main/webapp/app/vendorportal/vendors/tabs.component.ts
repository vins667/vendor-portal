import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { VendorsService } from './vendors.service';
import { IVendorsBean } from 'app/shared/model/vendors-bean.model';
import { JhiEventManager } from 'ng-jhipster';
import { Observable, Subscription } from 'rxjs';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import * as moment from 'moment';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

@Component({
  selector: 'jhi-profile-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./vendors.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TabsComponent implements OnInit {
  account: Account;
  vendorsBean: IVendorsBean;
  eventSubscriber: Subscription;
  isSaving: boolean;
  vendorId: number;
  constructor(
    private accountService: AccountService,
    private profileService: VendorsService,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager,
    protected route: ActivatedRoute,
    protected vendorsService: VendorsService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.accountService.identity().then(account => {
      this.account = account;
    });
    this.route.params.subscribe(params => {
      if (params['id']) {
        const id = params['id'];
        this.vendorId = id;
        this.profileService.profile(id).subscribe(vendorsBean => {
          this.vendorsBean = vendorsBean.body;
        });
      }
    });
    this.registerChangeInTabModification();
  }

  loadAll(id) {
    this.profileService.profile(id).subscribe(vendorsBean => {
      this.vendorsBean = vendorsBean.body;
    });
  }

  registerChangeInTabModification() {
    this.eventSubscriber = this.eventManager.subscribe('tabModification', response => this.loadAll(this.vendorId));
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  save() {
    this.isSaving = true;
    this.vendorsBean.requestedDate = this.vendorsBean.requestedDate != null ? moment(this.vendorsBean.requestedDate) : null;
    this.vendorsBean.approvedDate = this.vendorsBean.approvedDate != null ? moment(this.vendorsBean.approvedDate) : null;
    this.subscribeToSaveResponse(this.vendorsService.update(this.vendorsBean));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorsBean>>) {
    result.subscribe((res: HttpResponse<IVendorsBean>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<IVendorsBean>) {
    this.isSaving = false;
    this.snotifyService.success('Vendors Details save successfully', '', toastConfig);
    this.vendorsBean = res.body;
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
