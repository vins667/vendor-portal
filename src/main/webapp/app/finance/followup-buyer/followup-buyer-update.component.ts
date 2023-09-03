import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFollowupBuyer, FollowupBuyer } from './followup-buyer.model';
import { FollowupBuyerService } from './followup-buyer.service';

@Component({
  selector: 'jhi-followup-buyer-update',
  templateUrl: './followup-buyer-update.component.html'
})
export class FollowupBuyerUpdateComponent implements OnInit {
  isSaving: boolean;
  followupBuyer: IFollowupBuyer;
  createdDate: string;

  editForm = this.fb.group({
    id: [],
    buyercode: [null, [Validators.required, Validators.maxLength(20)]],
    buyername: [null, [Validators.required, Validators.maxLength(200)]],
    flag: [null, [Validators.required, Validators.maxLength(1)]],
    createdby: [null, [Validators.maxLength(50)]],
    createddate: [],
    updatedby: [null, [Validators.maxLength(50)]],
    updateddate: []
  });

  constructor(protected followupBuyerService: FollowupBuyerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ followupBuyer }) => {
      this.followupBuyer = followupBuyer;
      if (followupBuyer.id === undefined) {
        followupBuyer.flag = 'Y';
      }
      this.updateForm(followupBuyer);
    });
  }

  updateForm(followupBuyer: IFollowupBuyer) {
    this.editForm.patchValue({
      id: followupBuyer.id,
      buyercode: followupBuyer.buyercode,
      buyername: followupBuyer.buyername,
      flag: followupBuyer.flag,
      createdby: followupBuyer.createdby,
      createddate: followupBuyer.createddate != null ? followupBuyer.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: followupBuyer.updatedby,
      updateddate: followupBuyer.updateddate != null ? followupBuyer.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const followupBuyer = this.createFromForm();
    if (followupBuyer.id !== undefined) {
      this.followupBuyer.createddate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
      this.subscribeToSaveResponse(this.followupBuyerService.update(followupBuyer));
    } else {
      this.subscribeToSaveResponse(this.followupBuyerService.create(followupBuyer));
    }
  }

  private createFromForm(): IFollowupBuyer {
    return {
      ...new FollowupBuyer(),
      id: this.editForm.get(['id']).value,
      buyercode: this.editForm.get(['buyercode']).value,
      buyername: this.editForm.get(['buyername']).value,
      flag: this.editForm.get(['flag']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFollowupBuyer>>) {
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
