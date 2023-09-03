import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISalesOrderClosing, SalesOrderClosing } from './sales-order-closing.model';
import { SalesOrderClosingService } from './sales-order-closing.service';
import {
  ISalesOrderClosingHeaderBean,
  SalesOrderClosingHeaderBean
} from 'app/finance/sales-order-closing/sales-order-closing-header-bean.model';
import { ICutPlanEntryDetails } from 'app/shared/model/cut-plan-entry-details.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-sales-order-closing-update',
  templateUrl: './sales-order-closing-update.component.html'
})
export class SalesOrderClosingUpdateComponent implements OnInit {
  isSaving: boolean;
  projectCode: string;
  salesOrderClosing?: ISalesOrderClosing;

  constructor(
    protected salesOrderClosingService: SalesOrderClosingService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.projectCode = this.salesOrderClosingService.projectcode;
    this.salesOrderClosingService.find(this.projectCode).subscribe(salesOrderClosing => {
      this.salesOrderClosing = salesOrderClosing.body;
    });
  }

  previousState() {
    window.history.back();
  }

  saveConfirm(): void {
    this.snotifyService.confirm('Are you sure to post?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.save(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  save(toast: any) {
    this.snotifyService.remove(toast.id);
    this.isSaving = true;
    this.subscribeToSaveResponse(this.salesOrderClosingService.update(this.salesOrderClosing));
  }

  checked(value: boolean, salesOrderClosingHeaderBean?: ISalesOrderClosingHeaderBean): void {
    if (salesOrderClosingHeaderBean != null) {
      salesOrderClosingHeaderBean.isChecked = value;
    } else {
      this.salesOrderClosing.isChecked = value;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesOrderClosing>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
