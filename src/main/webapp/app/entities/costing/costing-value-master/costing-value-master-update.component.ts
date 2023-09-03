import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICostingValueMaster, CostingValueMaster } from 'app/shared/model/costing-value-master.model';
import { CostingValueMasterService } from './costing-value-master.service';
import { CostingProcessMasterService } from '../costing-process-master/costing-process-master.service';
import { ICostingProcessMaster } from 'app/shared/model/costing-process-master.model';

@Component({
  selector: 'jhi-costing-value-master-update',
  templateUrl: './costing-value-master-update.component.html'
})
export class CostingValueMasterUpdateComponent implements OnInit {
  isSaving: boolean;
  costingProcessMasters: ICostingProcessMaster[];
  editForm = this.fb.group({
    id: [],
    processname: [null, [Validators.required, Validators.maxLength(20)]],
    valuetype: [null, [Validators.required, Validators.maxLength(10)]],
    value: [null, [Validators.required, Validators.maxLength(100)]],
    createdby: [null, [Validators.maxLength(60)]],
    createddate: [],
    updatedby: [null, [Validators.maxLength(60)]],
    updateddate: []
  });

  constructor(
    protected costingValueMasterService: CostingValueMasterService,
    protected costingProcessMasterService: CostingProcessMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.costingProcessMasterService.findCostingProcessList().subscribe(res => {
      this.costingProcessMasters = res.body;
    });
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingValueMaster }) => {
      this.updateForm(costingValueMaster);
    });
  }

  updateForm(costingValueMaster: ICostingValueMaster) {
    this.editForm.patchValue({
      id: costingValueMaster.id,
      processname: costingValueMaster.processname,
      valuetype: costingValueMaster.valuetype,
      value: costingValueMaster.value,
      createdby: costingValueMaster.createdby,
      createddate: costingValueMaster.createddate != null ? costingValueMaster.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: costingValueMaster.updatedby,
      updateddate: costingValueMaster.updateddate != null ? costingValueMaster.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingValueMaster = this.createFromForm();
    if (costingValueMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.costingValueMasterService.update(costingValueMaster));
    } else {
      this.subscribeToSaveResponse(this.costingValueMasterService.create(costingValueMaster));
    }
  }

  private createFromForm(): ICostingValueMaster {
    return {
      ...new CostingValueMaster(),
      id: this.editForm.get(['id']).value,
      processname: this.editForm.get(['processname']).value,
      valuetype: this.editForm.get(['valuetype']).value,
      value: this.editForm.get(['value']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingValueMaster>>) {
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
