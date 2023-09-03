import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICostingEfficiencyMaste, CostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';
import { CostingEfficiencyMasteService } from './costing-efficiency-maste.service';

@Component({
  selector: 'jhi-costing-efficiency-maste-update',
  templateUrl: './costing-efficiency-maste-update.component.html'
})
export class CostingEfficiencyMasteUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    fromQuantity: [null, [Validators.required]],
    toQuantity: [null, [Validators.required]],
    efficiencyPerc: [null, [Validators.required]],
    createdby: [null, [Validators.maxLength(60)]],
    createddate: [],
    updatedby: [null, [Validators.maxLength(60)]],
    updateddate: []
  });

  constructor(
    protected costingEfficiencyMasteService: CostingEfficiencyMasteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingEfficiencyMaste }) => {
      this.updateForm(costingEfficiencyMaste);
    });
  }

  updateForm(costingEfficiencyMaste: ICostingEfficiencyMaste) {
    this.editForm.patchValue({
      id: costingEfficiencyMaste.id,
      fromQuantity: costingEfficiencyMaste.fromQuantity,
      toQuantity: costingEfficiencyMaste.toQuantity,
      efficiencyPerc: costingEfficiencyMaste.efficiencyPerc,
      createdby: costingEfficiencyMaste.createdby,
      createddate: costingEfficiencyMaste.createddate != null ? costingEfficiencyMaste.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: costingEfficiencyMaste.updatedby,
      updateddate: costingEfficiencyMaste.updateddate != null ? costingEfficiencyMaste.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingEfficiencyMaste = this.createFromForm();
    if (costingEfficiencyMaste.id !== undefined) {
      this.subscribeToSaveResponse(this.costingEfficiencyMasteService.update(costingEfficiencyMaste));
    } else {
      this.subscribeToSaveResponse(this.costingEfficiencyMasteService.create(costingEfficiencyMaste));
    }
  }

  private createFromForm(): ICostingEfficiencyMaste {
    return {
      ...new CostingEfficiencyMaste(),
      id: this.editForm.get(['id']).value,
      fromQuantity: this.editForm.get(['fromQuantity']).value,
      toQuantity: this.editForm.get(['toQuantity']).value,
      efficiencyPerc: this.editForm.get(['efficiencyPerc']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingEfficiencyMaste>>) {
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
