import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutPlanChangeMaster, VcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';
import { VcutPlanChangeMasterService } from './vcut-plan-change-master.service';

@Component({
  selector: 'jhi-vcut-plan-change-master-update',
  templateUrl: './vcut-plan-change-master-update.component.html'
})
export class VcutPlanChangeMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.maxLength(200)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected vcutPlanChangeMasterService: VcutPlanChangeMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vcutPlanChangeMaster }) => {
      this.updateForm(vcutPlanChangeMaster);
    });
  }

  updateForm(vcutPlanChangeMaster: IVcutPlanChangeMaster) {
    this.editForm.patchValue({
      id: vcutPlanChangeMaster.id,
      description: vcutPlanChangeMaster.description,
      createdBy: vcutPlanChangeMaster.createdBy,
      createdDate: vcutPlanChangeMaster.createdDate != null ? vcutPlanChangeMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: vcutPlanChangeMaster.lastUpdatedBy,
      lastUpdatedDate: vcutPlanChangeMaster.lastUpdatedDate != null ? vcutPlanChangeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const vcutPlanChangeMaster = this.createFromForm();
    if (vcutPlanChangeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutPlanChangeMasterService.update(vcutPlanChangeMaster));
    } else {
      this.subscribeToSaveResponse(this.vcutPlanChangeMasterService.create(vcutPlanChangeMaster));
    }
  }

  private createFromForm(): IVcutPlanChangeMaster {
    return {
      ...new VcutPlanChangeMaster(),
      id: this.editForm.get(['id']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutPlanChangeMaster>>) {
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
