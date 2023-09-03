import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IYarnCountMaster, YarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { YarnCountMasterService } from './yarn-count-master.service';

@Component({
  selector: 'jhi-yarn-count-master-update',
  templateUrl: './yarn-count-master-update.component.html'
})
export class YarnCountMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(15)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    flag: [null, [Validators.maxLength(1)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastCreatedBy: [null, [Validators.maxLength(50)]],
    lastCreatedDate: []
  });

  constructor(
    protected yarnCountMasterService: YarnCountMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ yarnCountMaster }) => {
      this.updateForm(yarnCountMaster);
    });
  }

  updateForm(yarnCountMaster: IYarnCountMaster) {
    this.editForm.patchValue({
      id: yarnCountMaster.id,
      code: yarnCountMaster.code,
      description: yarnCountMaster.description,
      flag: yarnCountMaster.flag,
      createdBy: yarnCountMaster.createdBy,
      createdDate: yarnCountMaster.createdDate != null ? yarnCountMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastCreatedBy: yarnCountMaster.lastCreatedBy,
      lastCreatedDate: yarnCountMaster.lastCreatedDate != null ? yarnCountMaster.lastCreatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const yarnCountMaster = this.createFromForm();
    if (yarnCountMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.yarnCountMasterService.update(yarnCountMaster));
    } else {
      this.subscribeToSaveResponse(this.yarnCountMasterService.create(yarnCountMaster));
    }
  }

  private createFromForm(): IYarnCountMaster {
    return {
      ...new YarnCountMaster(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      description: this.editForm.get(['description']).value,
      flag: this.editForm.get(['flag']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastCreatedBy: this.editForm.get(['lastCreatedBy']).value,
      lastCreatedDate:
        this.editForm.get(['lastCreatedDate']).value != null
          ? moment(this.editForm.get(['lastCreatedDate']).value, DATE_TIME_FORMAT)
          : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IYarnCountMaster>>) {
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
