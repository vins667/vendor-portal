import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IQuotationMaster } from 'app/shared/model/quotation-master.model';
import { QuotationMasterService } from './quotation-master.service';

@Component({
  selector: 'jhi-quotation-master-update',
  templateUrl: './quotation-master-update.component.html'
})
export class QuotationMasterUpdateComponent implements OnInit {
  quotationMaster: IQuotationMaster;
  isSaving: boolean;

  constructor(protected quotationMasterService: QuotationMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ quotationMaster }) => {
      this.quotationMaster = quotationMaster;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.quotationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.quotationMasterService.update(this.quotationMaster));
    } else {
      this.subscribeToSaveResponse(this.quotationMasterService.create(this.quotationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuotationMaster>>) {
    result.subscribe((res: HttpResponse<IQuotationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
