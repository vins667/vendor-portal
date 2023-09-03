import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGstVoplUpload, GstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';
import { GstVoplUploadService } from './gst-vopl-upload.service';

@Component({
  selector: 'jhi-gst-vopl-upload-update',
  templateUrl: './gst-vopl-upload-update.component.html'
})
export class GstVoplUploadUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    vVchno: [null, [Validators.required, Validators.maxLength(15)]],
    vVchdate: [],
    vGstin: [null, [Validators.required, Validators.maxLength(20)]],
    vSupplierCode: [null, [Validators.required, Validators.maxLength(20)]],
    vSupplierName: [null, [Validators.required, Validators.maxLength(200)]],
    vInvoiceno: [null, [Validators.required, Validators.maxLength(25)]],
    vInvoicedate: [],
    vInvamt: [],
    vInvnet: [],
    vCgst: [],
    vSgst: [],
    vIgst: [],
    uploadDate: [],
    confirmDate: [],
    status: [null, [Validators.maxLength(2)]]
  });

  constructor(protected gstVoplUploadService: GstVoplUploadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gstVoplUpload }) => {
      this.updateForm(gstVoplUpload);
    });
  }

  updateForm(gstVoplUpload: IGstVoplUpload) {
    this.editForm.patchValue({
      id: gstVoplUpload.id,
      vVchno: gstVoplUpload.vVchno,
      vVchdate: gstVoplUpload.vVchdate != null ? gstVoplUpload.vVchdate.format(DATE_TIME_FORMAT) : null,
      vGstin: gstVoplUpload.vGstin,
      vSupplierCode: gstVoplUpload.vSupplierCode,
      vSupplierName: gstVoplUpload.vSupplierName,
      vInvoiceno: gstVoplUpload.vInvoiceno,
      vInvoicedate: gstVoplUpload.vInvoicedate != null ? gstVoplUpload.vInvoicedate.format(DATE_TIME_FORMAT) : null,
      vInvamt: gstVoplUpload.vInvamt,
      vInvnet: gstVoplUpload.vInvnet,
      vCgst: gstVoplUpload.vCgst,
      vSgst: gstVoplUpload.vSgst,
      vIgst: gstVoplUpload.vIgst,
      uploadDate: gstVoplUpload.uploadDate != null ? gstVoplUpload.uploadDate.format(DATE_TIME_FORMAT) : null,
      confirmDate: gstVoplUpload.confirmDate != null ? gstVoplUpload.confirmDate.format(DATE_TIME_FORMAT) : null,
      status: gstVoplUpload.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gstVoplUpload = this.createFromForm();
    if (gstVoplUpload.id !== undefined) {
      this.subscribeToSaveResponse(this.gstVoplUploadService.update(gstVoplUpload));
    } else {
      this.subscribeToSaveResponse(this.gstVoplUploadService.create(gstVoplUpload));
    }
  }

  private createFromForm(): IGstVoplUpload {
    return {
      ...new GstVoplUpload(),
      id: this.editForm.get(['id']).value,
      vVchno: this.editForm.get(['vVchno']).value,
      vVchdate: this.editForm.get(['vVchdate']).value != null ? moment(this.editForm.get(['vVchdate']).value, DATE_TIME_FORMAT) : undefined,
      vGstin: this.editForm.get(['vGstin']).value,
      vSupplierCode: this.editForm.get(['vSupplierCode']).value,
      vSupplierName: this.editForm.get(['vSupplierName']).value,
      vInvoiceno: this.editForm.get(['vInvoiceno']).value,
      vInvoicedate:
        this.editForm.get(['vInvoicedate']).value != null ? moment(this.editForm.get(['vInvoicedate']).value, DATE_TIME_FORMAT) : undefined,
      vInvamt: this.editForm.get(['vInvamt']).value,
      vInvnet: this.editForm.get(['vInvnet']).value,
      vCgst: this.editForm.get(['vCgst']).value,
      vSgst: this.editForm.get(['vSgst']).value,
      vIgst: this.editForm.get(['vIgst']).value,
      uploadDate:
        this.editForm.get(['uploadDate']).value != null ? moment(this.editForm.get(['uploadDate']).value, DATE_TIME_FORMAT) : undefined,
      confirmDate:
        this.editForm.get(['confirmDate']).value != null ? moment(this.editForm.get(['confirmDate']).value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGstVoplUpload>>) {
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
