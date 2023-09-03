import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICostingProcessMaster, CostingProcessMaster } from 'app/shared/model/costing-process-master.model';
import { CostingProcessMasterService } from './costing-process-master.service';

@Component({
  selector: 'jhi-costing-process-master-update',
  templateUrl: './costing-process-master-update.component.html'
})
export class CostingProcessMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    processcode: [null, [Validators.required, Validators.maxLength(20)]],
    processdesc: [null, [Validators.required, Validators.maxLength(100)]],
    createdby: [null, [Validators.maxLength(60)]],
    createddate: [],
    updatedby: [],
    updateddate: []
  });

  constructor(
    protected costingProcessMasterService: CostingProcessMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingProcessMaster }) => {
      this.updateForm(costingProcessMaster);
    });
  }

  updateForm(costingProcessMaster: ICostingProcessMaster) {
    this.editForm.patchValue({
      id: costingProcessMaster.id,
      processcode: costingProcessMaster.processcode,
      processdesc: costingProcessMaster.processdesc,
      createdby: costingProcessMaster.createdby,
      createddate: costingProcessMaster.createddate != null ? costingProcessMaster.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: costingProcessMaster.updatedby,
      updateddate: costingProcessMaster.updateddate != null ? costingProcessMaster.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingProcessMaster = this.createFromForm();
    if (costingProcessMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.costingProcessMasterService.update(costingProcessMaster));
    } else {
      this.subscribeToSaveResponse(this.costingProcessMasterService.create(costingProcessMaster));
    }
  }

  private createFromForm(): ICostingProcessMaster {
    return {
      ...new CostingProcessMaster(),
      id: this.editForm.get(['id']).value,
      processcode: this.editForm.get(['processcode']).value,
      processdesc: this.editForm.get(['processdesc']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingProcessMaster>>) {
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
