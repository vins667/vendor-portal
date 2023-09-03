import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IKnitProcessMaster, KnitProcessMaster } from 'app/shared/model/knit-process-master.model';
import { KnitProcessMasterService } from './knit-process-master.service';

@Component({
  selector: 'jhi-knit-process-master-update',
  templateUrl: './knit-process-master-update.component.html'
})
export class KnitProcessMasterUpdateComponent implements OnInit {
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
    protected knitProcessMasterService: KnitProcessMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ knitProcessMaster }) => {
      this.updateForm(knitProcessMaster);
    });
  }

  updateForm(knitProcessMaster: IKnitProcessMaster) {
    this.editForm.patchValue({
      id: knitProcessMaster.id,
      code: knitProcessMaster.code,
      description: knitProcessMaster.description,
      flag: knitProcessMaster.flag,
      createdBy: knitProcessMaster.createdBy,
      createdDate: knitProcessMaster.createdDate != null ? knitProcessMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: knitProcessMaster.lastUpdatedBy,
      lastUpdatedDate: knitProcessMaster.lastUpdatedDate != null ? knitProcessMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const knitProcessMaster = this.createFromForm();
    if (knitProcessMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.knitProcessMasterService.update(knitProcessMaster));
    } else {
      this.subscribeToSaveResponse(this.knitProcessMasterService.create(knitProcessMaster));
    }
  }

  private createFromForm(): IKnitProcessMaster {
    return {
      ...new KnitProcessMaster(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKnitProcessMaster>>) {
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
