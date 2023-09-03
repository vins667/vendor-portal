import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutOperationRejectMaster, VcutOperationRejectMaster } from 'app/shared/model/vcut-operation-reject-master.model';
import { VcutOperationRejectMasterService } from './vcut-operation-reject-master.service';

@Component({
  selector: 'jhi-vcut-operation-reject-master-update',
  templateUrl: './vcut-operation-reject-master-update.component.html'
})
export class VcutOperationRejectMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required, Validators.maxLength(200)]],
    descriptionLocal: [null, [Validators.maxLength(200)]],
    type: [null, [Validators.maxLength(2)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected vcutOperationRejectMasterService: VcutOperationRejectMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vcutOperationRejectMaster }) => {
      this.updateForm(vcutOperationRejectMaster);
    });
  }

  updateForm(vcutOperationRejectMaster: IVcutOperationRejectMaster) {
    this.editForm.patchValue({
      id: vcutOperationRejectMaster.id,
      description: vcutOperationRejectMaster.description,
      descriptionLocal: vcutOperationRejectMaster.descriptionLocal,
      type: vcutOperationRejectMaster.type,
      createdBy: vcutOperationRejectMaster.createdBy,
      createdDate: vcutOperationRejectMaster.createdDate != null ? vcutOperationRejectMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: vcutOperationRejectMaster.lastUpdatedBy,
      lastUpdatedDate:
        vcutOperationRejectMaster.lastUpdatedDate != null ? vcutOperationRejectMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const vcutOperationRejectMaster = this.createFromForm();
    if (vcutOperationRejectMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutOperationRejectMasterService.update(vcutOperationRejectMaster));
    } else {
      this.subscribeToSaveResponse(this.vcutOperationRejectMasterService.create(vcutOperationRejectMaster));
    }
  }

  private createFromForm(): IVcutOperationRejectMaster {
    return {
      ...new VcutOperationRejectMaster(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      descriptionLocal: this.editForm.get(['descriptionLocal']).value,
      type: this.editForm.get(['type']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutOperationRejectMaster>>) {
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
