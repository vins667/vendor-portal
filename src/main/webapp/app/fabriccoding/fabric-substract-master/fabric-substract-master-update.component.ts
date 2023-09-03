import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFabricSubstractMaster, FabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';
import { FabricSubstractMasterService } from './fabric-substract-master.service';

@Component({
  selector: 'jhi-fabric-substract-master-update',
  templateUrl: './fabric-substract-master-update.component.html'
})
export class FabricSubstractMasterUpdateComponent implements OnInit {
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
    protected fabricSubstractMasterService: FabricSubstractMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fabricSubstractMaster }) => {
      this.updateForm(fabricSubstractMaster);
    });
  }

  updateForm(fabricSubstractMaster: IFabricSubstractMaster) {
    this.editForm.patchValue({
      id: fabricSubstractMaster.id,
      code: fabricSubstractMaster.code,
      description: fabricSubstractMaster.description,
      flag: fabricSubstractMaster.flag,
      createdBy: fabricSubstractMaster.createdBy,
      createdDate: fabricSubstractMaster.createdDate != null ? fabricSubstractMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: fabricSubstractMaster.lastUpdatedBy,
      lastUpdatedDate: fabricSubstractMaster.lastUpdatedDate != null ? fabricSubstractMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fabricSubstractMaster = this.createFromForm();
    if (fabricSubstractMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.fabricSubstractMasterService.update(fabricSubstractMaster));
    } else {
      this.subscribeToSaveResponse(this.fabricSubstractMasterService.create(fabricSubstractMaster));
    }
  }

  private createFromForm(): IFabricSubstractMaster {
    return {
      ...new FabricSubstractMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricSubstractMaster>>) {
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
