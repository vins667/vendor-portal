import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPoDatatexApproval, PoDatatexApproval } from 'app/shared/model/po-datatex-approval.model';
import { PoDatatexApprovalService } from './po-datatex-approval.service';

@Component({
  selector: 'jhi-po-datatex-approval-update',
  templateUrl: './po-datatex-approval-update.component.html'
})
export class PoDatatexApprovalUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected poDatatexApprovalService: PoDatatexApprovalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ poDatatexApproval }) => {
      this.updateForm(poDatatexApproval);
    });
  }

  updateForm(poDatatexApproval: IPoDatatexApproval) {
    this.editForm.patchValue({
      id: poDatatexApproval.id
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const poDatatexApproval = this.createFromForm();
    if (poDatatexApproval.id !== undefined) {
      this.subscribeToSaveResponse(this.poDatatexApprovalService.update(poDatatexApproval));
    } else {
      this.subscribeToSaveResponse(this.poDatatexApprovalService.create(poDatatexApproval));
    }
  }

  private createFromForm(): IPoDatatexApproval {
    return {
      ...new PoDatatexApproval(),
      id: this.editForm.get(['id']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPoDatatexApproval>>) {
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
