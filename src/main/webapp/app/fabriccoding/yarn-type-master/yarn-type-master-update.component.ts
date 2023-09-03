import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IYarnTypeMaster, YarnTypeMaster } from 'app/shared/model/yarn-type-master.model';
import { YarnTypeMasterService } from './yarn-type-master.service';

@Component({
  selector: 'jhi-yarn-type-master-update',
  templateUrl: './yarn-type-master-update.component.html'
})
export class YarnTypeMasterUpdateComponent implements OnInit {
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

  constructor(protected yarnTypeMasterService: YarnTypeMasterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ yarnTypeMaster }) => {
      this.updateForm(yarnTypeMaster);
    });
  }

  updateForm(yarnTypeMaster: IYarnTypeMaster) {
    this.editForm.patchValue({
      id: yarnTypeMaster.id,
      code: yarnTypeMaster.code,
      description: yarnTypeMaster.description,
      flag: yarnTypeMaster.flag,
      createdBy: yarnTypeMaster.createdBy,
      createdDate: yarnTypeMaster.createdDate != null ? yarnTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: yarnTypeMaster.lastUpdatedBy,
      lastUpdatedDate: yarnTypeMaster.lastUpdatedDate != null ? yarnTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const yarnTypeMaster = this.createFromForm();
    if (yarnTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.yarnTypeMasterService.update(yarnTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.yarnTypeMasterService.create(yarnTypeMaster));
    }
  }

  private createFromForm(): IYarnTypeMaster {
    return {
      ...new YarnTypeMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IYarnTypeMaster>>) {
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
