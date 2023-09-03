import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IVendorUsers, VendorUsers } from 'app/shared/model/vendor-users.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-vendor-users-update',
  templateUrl: './vendor-users-update.component.html'
})
export class VendorUsersUpdateComponent implements OnInit {
  @Input() vendorUsers: IVendorUsers[];
  @Input() approvalStatus: string;
  vendorFinanceUsers: IVendorUsers[];
  vendorTaxUsers: IVendorUsers[];
  vendorPaymentUsers: IVendorUsers[];
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected activatedRoute: ActivatedRoute, private snotifyService: SnotifyService, protected eventManager: JhiEventManager) {}

  ngOnInit() {
    this.isSaving = false;
    this.vendorFinanceUsers = [];
    this.vendorTaxUsers = [];
    this.vendorPaymentUsers = [];
    if (this.vendorUsers && this.vendorUsers.length > 0) {
      this.vendorFinanceUsers = [];
      this.vendorTaxUsers = [];
      this.vendorPaymentUsers = [];
      this.vendorUsers.forEach(item => {
        if (item.userType === 'FINANCE') {
          this.vendorFinanceUsers.push({
            id: item.id,
            userType: item.userType,
            userName: item.userName,
            designation: item.designation,
            emailId: item.emailId,
            mobileNumber: item.mobileNumber,
            vendorId: item.vendorId,
            dataFlag: item.dataFlag
          });
        }
        if (item.userType === 'TAX') {
          this.vendorTaxUsers.push({
            id: item.id,
            userType: item.userType,
            userName: item.userName,
            designation: item.designation,
            emailId: item.emailId,
            mobileNumber: item.mobileNumber,
            vendorId: item.vendorId,
            dataFlag: item.dataFlag
          });
        }
        if (item.userType === 'PAYMENT') {
          this.vendorPaymentUsers.push({
            id: item.id,
            userType: item.userType,
            userName: item.userName,
            designation: item.designation,
            emailId: item.emailId,
            mobileNumber: item.mobileNumber,
            vendorId: item.vendorId,
            dataFlag: item.dataFlag
          });
        }
      });
    }
    if (this.vendorFinanceUsers === undefined || this.vendorFinanceUsers === null) {
      const vendorFinanceUser = new VendorUsers();
      vendorFinanceUser.userType = 'FINANCE';
      vendorFinanceUser.index = 0;
      this.vendorFinanceUsers.push(vendorFinanceUser);
    }
    if (this.vendorTaxUsers === undefined || this.vendorTaxUsers === null) {
      const vendorTaxUser = new VendorUsers();
      vendorTaxUser.userType = 'TAX';
      vendorTaxUser.index = 0;
      this.vendorTaxUsers.push(vendorTaxUser);
    }
    if (this.vendorPaymentUsers === undefined || this.vendorPaymentUsers === null) {
      const vendorPaymentUser = new VendorUsers();
      vendorPaymentUser.userType = 'PAYMENT';
      vendorPaymentUser.index = 0;
      this.vendorPaymentUsers.push(vendorPaymentUser);
    }
  }

  previousState() {
    window.history.back();
  }

  removeUser(index, type) {
    if (type === 'FINANCE') {
      this.vendorFinanceUsers.splice(index, 1);
      let ctr = 0;
      this.vendorFinanceUsers.forEach(vendors => {
        vendors.index = ctr;
        ++ctr;
      });
    } else if (type === 'TAX') {
      this.vendorTaxUsers.splice(index, 1);
      let ctr = 0;
      this.vendorTaxUsers.forEach(vendors => {
        vendors.index = ctr;
        ++ctr;
      });
    } else if (type === 'PAYMENT') {
      this.vendorPaymentUsers.splice(index, 1);
      let ctr = 0;
      this.vendorPaymentUsers.forEach(vendors => {
        vendors.index = ctr;
        ++ctr;
      });
    }
  }

  addUser(type) {
    const vendorUser = new VendorUsers();
    vendorUser.userType = type;
    if (type === 'FINANCE') {
      vendorUser.index = this.vendorFinanceUsers.length;
      this.vendorFinanceUsers.push(vendorUser);
    } else if (type === 'TAX') {
      vendorUser.index = this.vendorTaxUsers.length;
      this.vendorTaxUsers.push(vendorUser);
    } else if (type === 'PAYMENT') {
      vendorUser.index = this.vendorPaymentUsers.length;
      this.vendorPaymentUsers.push(vendorUser);
    }
  }

  save() {}

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorUsers>>) {
    result.subscribe((res: HttpResponse<IVendorUsers>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('People Details save successfully', '', toastConfig);
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
    //  this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
