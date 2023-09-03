import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IEmailInvitation } from 'app/shared/model/email-invitation.model';
import { EmailInvitationService } from './email-invitation.service';

@Component({
  selector: 'jhi-email-invitation-update',
  templateUrl: './email-invitation-update.component.html'
})
export class EmailInvitationUpdateComponent implements OnInit {
  emailInvitation: IEmailInvitation;
  isSaving: boolean;
  createdDate: string;

  constructor(protected emailInvitationService: EmailInvitationService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ emailInvitation }) => {
      this.emailInvitation = emailInvitation;
      this.createdDate = this.emailInvitation.createdDate != null ? this.emailInvitation.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.emailInvitation.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.emailInvitation.id !== undefined) {
      this.subscribeToSaveResponse(this.emailInvitationService.update(this.emailInvitation));
    } else {
      this.subscribeToSaveResponse(this.emailInvitationService.create(this.emailInvitation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailInvitation>>) {
    result.subscribe((res: HttpResponse<IEmailInvitation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
