import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICostingGroupMaster, CostingGroupMaster } from 'app/shared/model/costing-group-master.model';
import { CostingGroupMasterService } from './costing-group-master.service';

@Component({
  selector: 'jhi-costing-group-master-update',
  templateUrl: './costing-group-master-update.component.html'
})
export class CostingGroupMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(10)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    createdBy: [null, [Validators.maxLength(60)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(60)]],
    lastUpdatedDate: []
  });

  constructor(
    protected costingGroupMasterService: CostingGroupMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingGroupMaster }) => {
      this.updateForm(costingGroupMaster);
    });
  }

  updateForm(costingGroupMaster: ICostingGroupMaster) {
    this.editForm.patchValue({
      id: costingGroupMaster.id,
      code: costingGroupMaster.code,
      description: costingGroupMaster.description,
      createdBy: costingGroupMaster.createdBy,
      createdDate: costingGroupMaster.createdDate != null ? costingGroupMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: costingGroupMaster.lastUpdatedBy,
      lastUpdatedDate: costingGroupMaster.lastUpdatedDate != null ? costingGroupMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingGroupMaster = this.createFromForm();
    if (costingGroupMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.costingGroupMasterService.update(costingGroupMaster));
    } else {
      this.subscribeToSaveResponse(this.costingGroupMasterService.create(costingGroupMaster));
    }
  }

  private createFromForm(): ICostingGroupMaster {
    return {
      ...new CostingGroupMaster(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      description: this.editForm.get(['description']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingGroupMaster>>) {
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
