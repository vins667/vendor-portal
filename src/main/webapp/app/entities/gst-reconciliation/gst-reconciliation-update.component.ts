import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGstReconciliation, GstReconciliation } from 'app/shared/model/gst-reconciliation.model';
import { GstReconciliationService } from './gst-reconciliation.service';

@Component({
  selector: 'jhi-gst-reconciliation-update',
  templateUrl: './gst-reconciliation-update.component.html'
})
export class GstReconciliationUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    unitCode: [null, [Validators.required, Validators.maxLength(5)]],
    gstin: [null, [Validators.required, Validators.maxLength(20)]],
    invoiceType: [null, [Validators.required, Validators.maxLength(5)]],
    invoiceNo: [null, [Validators.required, Validators.maxLength(20)]],
    invoiceDate: [],
    supplierName: [null, [Validators.required, Validators.maxLength(200)]],
    state: [null, [Validators.maxLength(40)]],
    invoiceAmount: [],
    reverseAmount: [],
    cgstAmount: [],
    sgstAmount: [],
    igstAmount: [],
    cessAmount: [],
    location: [null, [Validators.maxLength(20)]],
    srlNumber: [],
    status: [null, [Validators.maxLength(5)]],
    creationDate: []
  });

  constructor(
    protected gstReconciliationService: GstReconciliationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gstReconciliation }) => {
      this.updateForm(gstReconciliation);
    });
  }

  updateForm(gstReconciliation: IGstReconciliation) {
    this.editForm.patchValue({
      id: gstReconciliation.id,
      unitCode: gstReconciliation.unitCode,
      gstin: gstReconciliation.gstin,
      invoiceType: gstReconciliation.invoiceType,
      invoiceNo: gstReconciliation.invoiceNo,
      invoiceDate: gstReconciliation.invoiceDate != null ? gstReconciliation.invoiceDate.format(DATE_TIME_FORMAT) : null,
      supplierName: gstReconciliation.supplierName,
      state: gstReconciliation.state,
      invoiceAmount: gstReconciliation.invoiceAmount,
      reverseAmount: gstReconciliation.reverseAmount,
      cgstAmount: gstReconciliation.cgstAmount,
      sgstAmount: gstReconciliation.sgstAmount,
      igstAmount: gstReconciliation.igstAmount,
      cessAmount: gstReconciliation.cessAmount,
      location: gstReconciliation.location,
      srlNumber: gstReconciliation.srlNumber,
      status: gstReconciliation.status,
      creationDate: gstReconciliation.creationDate != null ? gstReconciliation.creationDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gstReconciliation = this.createFromForm();
    if (gstReconciliation.id !== undefined) {
      this.subscribeToSaveResponse(this.gstReconciliationService.update(gstReconciliation));
    } else {
      this.subscribeToSaveResponse(this.gstReconciliationService.create(gstReconciliation));
    }
  }

  private createFromForm(): IGstReconciliation {
    return {
      ...new GstReconciliation(),
      id: this.editForm.get(['id']).value,
      unitCode: this.editForm.get(['unitCode']).value,
      gstin: this.editForm.get(['gstin']).value,
      invoiceType: this.editForm.get(['invoiceType']).value,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      invoiceDate:
        this.editForm.get(['invoiceDate']).value != null ? moment(this.editForm.get(['invoiceDate']).value, DATE_TIME_FORMAT) : undefined,
      supplierName: this.editForm.get(['supplierName']).value,
      state: this.editForm.get(['state']).value,
      invoiceAmount: this.editForm.get(['invoiceAmount']).value,
      reverseAmount: this.editForm.get(['reverseAmount']).value,
      cgstAmount: this.editForm.get(['cgstAmount']).value,
      sgstAmount: this.editForm.get(['sgstAmount']).value,
      igstAmount: this.editForm.get(['igstAmount']).value,
      cessAmount: this.editForm.get(['cessAmount']).value,
      location: this.editForm.get(['location']).value,
      srlNumber: this.editForm.get(['srlNumber']).value,
      status: this.editForm.get(['status']).value,
      creationDate:
        this.editForm.get(['creationDate']).value != null ? moment(this.editForm.get(['creationDate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGstReconciliation>>) {
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
