import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IReportTypeMaster, ReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { ReportTypeMasterService } from './report-type-master.service';

@Component({
  selector: 'jhi-report-type-master-update',
  templateUrl: './report-type-master-update.component.html'
})
export class ReportTypeMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(50)]],
    desc: [null, [Validators.required, Validators.maxLength(200)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected reportTypeMasterService: ReportTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ reportTypeMaster }) => {
      this.updateForm(reportTypeMaster);
    });
  }

  updateForm(reportTypeMaster: IReportTypeMaster) {
    this.editForm.patchValue({
      id: reportTypeMaster.id,
      code: reportTypeMaster.code,
      desc: reportTypeMaster.desc,
      createdBy: reportTypeMaster.createdBy,
      createdDate: reportTypeMaster.createdDate != null ? reportTypeMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: reportTypeMaster.lastUpdatedBy,
      lastUpdatedDate: reportTypeMaster.lastUpdatedDate != null ? reportTypeMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const reportTypeMaster = this.createFromForm();
    if (reportTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.reportTypeMasterService.update(reportTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.reportTypeMasterService.create(reportTypeMaster));
    }
  }

  private createFromForm(): IReportTypeMaster {
    return {
      ...new ReportTypeMaster(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      desc: this.editForm.get(['desc']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportTypeMaster>>) {
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
