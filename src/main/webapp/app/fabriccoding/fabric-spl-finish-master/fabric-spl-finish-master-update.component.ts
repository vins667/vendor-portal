import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFabricSplFinishMaster, FabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';
import { FabricSplFinishMasterService } from './fabric-spl-finish-master.service';

@Component({
  selector: 'jhi-fabric-spl-finish-master-update',
  templateUrl: './fabric-spl-finish-master-update.component.html'
})
export class FabricSplFinishMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(15)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    flag: [null, [Validators.maxLength(1)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected fabricSplFinishMasterService: FabricSplFinishMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fabricSplFinishMaster }) => {
      this.updateForm(fabricSplFinishMaster);
    });
  }

  updateForm(fabricSplFinishMaster: IFabricSplFinishMaster) {
    this.editForm.patchValue({
      id: fabricSplFinishMaster.id,
      code: fabricSplFinishMaster.code,
      description: fabricSplFinishMaster.description,
      flag: fabricSplFinishMaster.flag,
      createdBy: fabricSplFinishMaster.createdBy,
      createdDate: fabricSplFinishMaster.createdDate != null ? fabricSplFinishMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: fabricSplFinishMaster.lastUpdatedBy,
      lastUpdatedDate: fabricSplFinishMaster.lastUpdatedDate != null ? fabricSplFinishMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fabricSplFinishMaster = this.createFromForm();
    if (fabricSplFinishMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.fabricSplFinishMasterService.update(fabricSplFinishMaster));
    } else {
      this.subscribeToSaveResponse(this.fabricSplFinishMasterService.create(fabricSplFinishMaster));
    }
  }

  private createFromForm(): IFabricSplFinishMaster {
    return {
      ...new FabricSplFinishMaster(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      description: this.editForm.get(['description']).value,
      flag: this.editForm.get(['flag']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricSplFinishMaster>>) {
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
