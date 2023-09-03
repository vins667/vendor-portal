import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IKnitTypeMaster, KnitTypeMaster } from 'app/shared/model/knit-type-master.model';
import { KnitTypeMasterService } from './knit-type-master.service';

@Component({
  selector: 'jhi-knit-type-master-update',
  templateUrl: './knit-type-master-update.component.html'
})
export class KnitTypeMasterUpdateComponent implements OnInit {
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

  constructor(protected knitTypeMasterService: KnitTypeMasterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ knitTypeMaster }) => {
      this.updateForm(knitTypeMaster);
    });
  }

  updateForm(knitTypeMaster: IKnitTypeMaster) {
    this.editForm.patchValue({
      id: knitTypeMaster.id,
      code: knitTypeMaster.code,
      description: knitTypeMaster.description,
      flag: knitTypeMaster.flag,
      createdBy: knitTypeMaster.createdBy,
      createdDate: knitTypeMaster.createdDate != null ? knitTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: knitTypeMaster.lastUpdatedBy,
      lastUpdatedDate: knitTypeMaster.lastUpdatedDate != null ? knitTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const knitTypeMaster = this.createFromForm();
    if (knitTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.knitTypeMasterService.update(knitTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.knitTypeMasterService.create(knitTypeMaster));
    }
  }

  private createFromForm(): IKnitTypeMaster {
    return {
      ...new KnitTypeMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKnitTypeMaster>>) {
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
