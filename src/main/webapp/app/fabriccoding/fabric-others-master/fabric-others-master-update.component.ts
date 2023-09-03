import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFabricOthersMaster, FabricOthersMaster } from 'app/shared/model/fabric-others-master.model';
import { FabricOthersMasterService } from './fabric-others-master.service';

@Component({
  selector: 'jhi-fabric-others-master-update',
  templateUrl: './fabric-others-master-update.component.html'
})
export class FabricOthersMasterUpdateComponent implements OnInit {
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
    protected fabricOthersMasterService: FabricOthersMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fabricOthersMaster }) => {
      this.updateForm(fabricOthersMaster);
    });
  }

  updateForm(fabricOthersMaster: IFabricOthersMaster) {
    this.editForm.patchValue({
      id: fabricOthersMaster.id,
      code: fabricOthersMaster.code,
      description: fabricOthersMaster.description,
      flag: fabricOthersMaster.flag,
      createdBy: fabricOthersMaster.createdBy,
      createdDate: fabricOthersMaster.createdDate != null ? fabricOthersMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: fabricOthersMaster.lastUpdatedBy,
      lastUpdatedDate: fabricOthersMaster.lastUpdatedDate != null ? fabricOthersMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fabricOthersMaster = this.createFromForm();
    if (fabricOthersMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.fabricOthersMasterService.update(fabricOthersMaster));
    } else {
      this.subscribeToSaveResponse(this.fabricOthersMasterService.create(fabricOthersMaster));
    }
  }

  private createFromForm(): IFabricOthersMaster {
    return {
      ...new FabricOthersMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricOthersMaster>>) {
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
