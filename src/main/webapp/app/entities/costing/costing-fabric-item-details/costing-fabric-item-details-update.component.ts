import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICostingFabricItemDetails, CostingFabricItemDetails } from 'app/shared/model/costing-fabric-item-details.model';
import { CostingFabricItemDetailsService } from './costing-fabric-item-details.service';

@Component({
  selector: 'jhi-costing-fabric-item-details-update',
  templateUrl: './costing-fabric-item-details-update.component.html'
})
export class CostingFabricItemDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    itemType: [null, [Validators.required, Validators.maxLength(10)]],
    code: [null, [Validators.required, Validators.maxLength(10)]],
    descrption: [null, [Validators.required, Validators.maxLength(100)]],
    createdBy: [null, [Validators.maxLength(60)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(60)]],
    lastUpdatedDate: []
  });

  constructor(
    protected costingFabricItemDetailsService: CostingFabricItemDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingFabricItemDetails }) => {
      this.updateForm(costingFabricItemDetails);
    });
  }

  updateForm(costingFabricItemDetails: ICostingFabricItemDetails) {
    this.editForm.patchValue({
      id: costingFabricItemDetails.id,
      itemType: costingFabricItemDetails.itemType,
      code: costingFabricItemDetails.code,
      descrption: costingFabricItemDetails.descrption,
      createdBy: costingFabricItemDetails.createdBy,
      createdDate: costingFabricItemDetails.createdDate != null ? costingFabricItemDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: costingFabricItemDetails.lastUpdatedBy,
      lastUpdatedDate:
        costingFabricItemDetails.lastUpdatedDate != null ? costingFabricItemDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingFabricItemDetails = this.createFromForm();
    if (costingFabricItemDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.costingFabricItemDetailsService.update(costingFabricItemDetails));
    } else {
      this.subscribeToSaveResponse(this.costingFabricItemDetailsService.create(costingFabricItemDetails));
    }
  }

  private createFromForm(): ICostingFabricItemDetails {
    return {
      ...new CostingFabricItemDetails(),
      id: this.editForm.get(['id']).value,
      itemType: this.editForm.get(['itemType']).value,
      code: this.editForm.get(['code']).value,
      descrption: this.editForm.get(['descrption']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingFabricItemDetails>>) {
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
