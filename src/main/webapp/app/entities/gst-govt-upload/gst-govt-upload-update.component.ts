import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGstGovtUpload, GstGovtUpload } from 'app/shared/model/gst-govt-upload.model';
import { GstGovtUploadService } from './gst-govt-upload.service';

@Component({
  selector: 'jhi-gst-govt-upload-update',
  templateUrl: './gst-govt-upload-update.component.html'
})
export class GstGovtUploadUpdateComponent implements OnInit {
  isSaving: boolean;
  gMonthDp: any;

  editForm = this.fb.group({
    id: [],
    gGstin: [null, [Validators.required, Validators.maxLength(20)]],
    gSupplier: [null, [Validators.required, Validators.maxLength(200)]],
    gInvno: [null, [Validators.required, Validators.maxLength(20)]],
    gInvdate: [],
    gInvtype: [null, [Validators.required, Validators.maxLength(5)]],
    gState: [null, [Validators.required, Validators.maxLength(40)]],
    gReverse: [null, [Validators.required, Validators.maxLength(5)]],
    gInvamt: [],
    gInvnet: [],
    gRate: [],
    gCgst: [],
    gSgst: [],
    gIgst: [],
    gCess: [],
    gMonth: [],
    gLocation: [null, [Validators.required, Validators.maxLength(40)]],
    gSrlno: [null, [Validators.required, Validators.maxLength(20)]],
    gStatus: [null, [Validators.maxLength(5)]],
    gConfirmdate: []
  });

  constructor(protected gstGovtUploadService: GstGovtUploadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gstGovtUpload }) => {
      this.updateForm(gstGovtUpload);
    });
  }

  updateForm(gstGovtUpload: IGstGovtUpload) {
    this.editForm.patchValue({
      id: gstGovtUpload.id,
      gGstin: gstGovtUpload.gGstin,
      gSupplier: gstGovtUpload.gSupplier,
      gInvno: gstGovtUpload.gInvno,
      gInvdate: gstGovtUpload.gInvdate != null ? gstGovtUpload.gInvdate.format(DATE_TIME_FORMAT) : null,
      gInvtype: gstGovtUpload.gInvtype,
      gState: gstGovtUpload.gState,
      gReverse: gstGovtUpload.gReverse,
      gInvamt: gstGovtUpload.gInvamt,
      gInvnet: gstGovtUpload.gInvnet,
      gRate: gstGovtUpload.gRate,
      gCgst: gstGovtUpload.gCgst,
      gSgst: gstGovtUpload.gSgst,
      gIgst: gstGovtUpload.gIgst,
      gCess: gstGovtUpload.gCess,
      gMonth: gstGovtUpload.gMonth,
      gLocation: gstGovtUpload.gLocation,
      gSrlno: gstGovtUpload.gSrlno,
      gStatus: gstGovtUpload.gStatus,
      gConfirmdate: gstGovtUpload.gConfirmdate != null ? gstGovtUpload.gConfirmdate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gstGovtUpload = this.createFromForm();
    if (gstGovtUpload.id !== undefined) {
      this.subscribeToSaveResponse(this.gstGovtUploadService.update(gstGovtUpload));
    } else {
      this.subscribeToSaveResponse(this.gstGovtUploadService.create(gstGovtUpload));
    }
  }

  private createFromForm(): IGstGovtUpload {
    return {
      ...new GstGovtUpload(),
      id: this.editForm.get(['id']).value,
      gGstin: this.editForm.get(['gGstin']).value,
      gSupplier: this.editForm.get(['gSupplier']).value,
      gInvno: this.editForm.get(['gInvno']).value,
      gInvdate: this.editForm.get(['gInvdate']).value != null ? moment(this.editForm.get(['gInvdate']).value, DATE_TIME_FORMAT) : undefined,
      gInvtype: this.editForm.get(['gInvtype']).value,
      gState: this.editForm.get(['gState']).value,
      gReverse: this.editForm.get(['gReverse']).value,
      gInvamt: this.editForm.get(['gInvamt']).value,
      gInvnet: this.editForm.get(['gInvnet']).value,
      gRate: this.editForm.get(['gRate']).value,
      gCgst: this.editForm.get(['gCgst']).value,
      gSgst: this.editForm.get(['gSgst']).value,
      gIgst: this.editForm.get(['gIgst']).value,
      gCess: this.editForm.get(['gCess']).value,
      gMonth: this.editForm.get(['gMonth']).value,
      gLocation: this.editForm.get(['gLocation']).value,
      gSrlno: this.editForm.get(['gSrlno']).value,
      gStatus: this.editForm.get(['gStatus']).value,
      gConfirmdate:
        this.editForm.get(['gConfirmdate']).value != null ? moment(this.editForm.get(['gConfirmdate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGstGovtUpload>>) {
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
