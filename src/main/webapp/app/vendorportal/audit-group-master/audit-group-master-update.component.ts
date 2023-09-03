import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IAuditGroupMaster, AuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { AuditGroupMasterService } from './audit-group-master.service';

@Component({
  selector: 'jhi-audit-group-master-update',
  templateUrl: './audit-group-master-update.component.html'
})
export class AuditGroupMasterUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    flag: [null, [Validators.maxLength(1)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected auditGroupMasterService: AuditGroupMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ auditGroupMaster }) => {
      this.updateForm(auditGroupMaster);
    });
  }

  updateForm(auditGroupMaster: IAuditGroupMaster) {
    this.editForm.patchValue({
      id: auditGroupMaster.id,
      description: auditGroupMaster.description,
      flag: auditGroupMaster.flag,
      createdBy: auditGroupMaster.createdBy,
      createdDate: auditGroupMaster.createdDate != null ? auditGroupMaster.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: auditGroupMaster.lastUpdatedBy,
      lastUpdatedDate: auditGroupMaster.lastUpdatedDate != null ? auditGroupMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const auditGroupMaster = this.createFromForm();
    if (auditGroupMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.auditGroupMasterService.update(auditGroupMaster));
    } else {
      this.subscribeToSaveResponse(this.auditGroupMasterService.create(auditGroupMaster));
    }
  }

  private createFromForm(): IAuditGroupMaster {
    return {
      ...new AuditGroupMaster(),
      id: this.editForm.get(['id']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuditGroupMaster>>) {
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
