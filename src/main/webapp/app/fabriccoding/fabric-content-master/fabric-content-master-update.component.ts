import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFabricContentMaster, FabricContentMaster } from 'app/shared/model/fabric-content-master.model';
import { FabricContentMasterService } from './fabric-content-master.service';

@Component({
  selector: 'jhi-fabric-content-master-update',
  templateUrl: './fabric-content-master-update.component.html'
})
export class FabricContentMasterUpdateComponent implements OnInit {
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
    protected fabricContentMasterService: FabricContentMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fabricContentMaster }) => {
      this.updateForm(fabricContentMaster);
    });
  }

  updateForm(fabricContentMaster: IFabricContentMaster) {
    this.editForm.patchValue({
      id: fabricContentMaster.id,
      code: fabricContentMaster.code,
      description: fabricContentMaster.description,
      flag: fabricContentMaster.flag,
      createdBy: fabricContentMaster.createdBy,
      createdDate: fabricContentMaster.createdDate != null ? fabricContentMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: fabricContentMaster.lastUpdatedBy,
      lastUpdatedDate: fabricContentMaster.lastUpdatedDate != null ? fabricContentMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fabricContentMaster = this.createFromForm();
    if (fabricContentMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.fabricContentMasterService.update(fabricContentMaster));
    } else {
      this.subscribeToSaveResponse(this.fabricContentMasterService.create(fabricContentMaster));
    }
  }

  private createFromForm(): IFabricContentMaster {
    return {
      ...new FabricContentMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricContentMaster>>) {
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
