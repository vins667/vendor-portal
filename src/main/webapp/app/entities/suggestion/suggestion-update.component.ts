import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ISuggestion } from 'app/shared/model/suggestion.model';
import { SuggestionService } from './suggestion.service';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-suggestion-update',
  templateUrl: './suggestion-update.component.html'
})
export class SuggestionUpdateComponent implements OnInit {
  suggestion: ISuggestion;
  isSaving: boolean;
  createdDate: string;

  constructor(
    protected suggestionService: SuggestionService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ suggestion }) => {
      this.suggestion = suggestion;
      this.createdDate = this.suggestion.createdDate != null ? this.suggestion.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.suggestion.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.suggestion.id !== undefined) {
      this.subscribeToSaveResponse(this.suggestionService.update(this.suggestion));
    } else {
      this.subscribeToSaveResponse(this.suggestionService.create(this.suggestion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISuggestion>>) {
    result.subscribe((res: HttpResponse<ISuggestion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Send Successfully', '', toastConfig);
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
