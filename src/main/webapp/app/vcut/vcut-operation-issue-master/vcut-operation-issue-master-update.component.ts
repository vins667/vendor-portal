import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutOperationIssueMaster, VcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';
import { VcutOperationIssueMasterService } from './vcut-operation-issue-master.service';

@Component({
  selector: 'jhi-vcut-operation-issue-master-update',
  templateUrl: './vcut-operation-issue-master-update.component.html'
})
export class VcutOperationIssueMasterUpdateComponent implements OnInit {
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
    protected vcutOperationIssueMasterService: VcutOperationIssueMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vcutOperationIssueMaster }) => {
      this.updateForm(vcutOperationIssueMaster);
    });
  }

  updateForm(vcutOperationIssueMaster: IVcutOperationIssueMaster) {
    this.editForm.patchValue({
      id: vcutOperationIssueMaster.id,
      description: vcutOperationIssueMaster.description,
      descriptionLocal: vcutOperationIssueMaster.descriptionLocal,
      type: vcutOperationIssueMaster.type,
      createdBy: vcutOperationIssueMaster.createdBy,
      createdDate: vcutOperationIssueMaster.createdDate != null ? vcutOperationIssueMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: vcutOperationIssueMaster.lastUpdatedBy,
      lastUpdatedDate:
        vcutOperationIssueMaster.lastUpdatedDate != null ? vcutOperationIssueMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const vcutOperationIssueMaster = this.createFromForm();
    if (vcutOperationIssueMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutOperationIssueMasterService.update(vcutOperationIssueMaster));
    } else {
      this.subscribeToSaveResponse(this.vcutOperationIssueMasterService.create(vcutOperationIssueMaster));
    }
  }

  private createFromForm(): IVcutOperationIssueMaster {
    return {
      ...new VcutOperationIssueMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutOperationIssueMaster>>) {
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
