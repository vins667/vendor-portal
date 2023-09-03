import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ICostingGroupDetails, CostingGroupDetails } from 'app/shared/model/costing-group-details.model';
import { CostingGroupDetailsService } from './costing-group-details.service';
import { ICostingGroupMaster } from 'app/shared/model/costing-group-master.model';
import { CostingGroupMasterService } from '../costing-group-master/costing-group-master.service';

@Component({
  selector: 'jhi-costing-group-details-update',
  templateUrl: './costing-group-details-update.component.html'
})
export class CostingGroupDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  costinggroupmasters: ICostingGroupMaster[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(10)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    valueType: [null, [Validators.required, Validators.maxLength(4)]],
    masterType: [null, [Validators.required]],
    ugcType: [null, [Validators.required]],
    createdBy: [null, [Validators.maxLength(60)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(60)]],
    lastUpdatedDate: [],
    costingGroupMaster: [null, [Validators.required]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected costingGroupDetailsService: CostingGroupDetailsService,
    protected costingGroupMasterService: CostingGroupMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ costingGroupDetails }) => {
      this.updateForm(costingGroupDetails);
    });
    this.costingGroupMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICostingGroupMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICostingGroupMaster[]>) => response.body)
      )
      .subscribe((res: ICostingGroupMaster[]) => (this.costinggroupmasters = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(costingGroupDetails: ICostingGroupDetails) {
    this.editForm.patchValue({
      id: costingGroupDetails.id,
      code: costingGroupDetails.code,
      description: costingGroupDetails.description,
      valueType: costingGroupDetails.valueType,
      masterType: costingGroupDetails.masterType,
      ugcType: costingGroupDetails.ugcType,
      createdBy: costingGroupDetails.createdBy,
      createdDate: costingGroupDetails.createdDate != null ? costingGroupDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: costingGroupDetails.lastUpdatedBy,
      lastUpdatedDate: costingGroupDetails.lastUpdatedDate != null ? costingGroupDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      costingGroupMaster: costingGroupDetails.costingGroupMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const costingGroupDetails = this.createFromForm();
    if (costingGroupDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.costingGroupDetailsService.update(costingGroupDetails));
    } else {
      this.subscribeToSaveResponse(this.costingGroupDetailsService.create(costingGroupDetails));
    }
  }

  private createFromForm(): ICostingGroupDetails {
    return {
      ...new CostingGroupDetails(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      description: this.editForm.get(['description']).value,
      valueType: this.editForm.get(['valueType']).value,
      masterType: this.editForm.get(['masterType']).value,
      ugcType: this.editForm.get(['ugcType']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      costingGroupMaster: this.editForm.get(['costingGroupMaster']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICostingGroupDetails>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCostingGroupMasterById(index: number, item: ICostingGroupMaster) {
    return item.id;
  }
}
