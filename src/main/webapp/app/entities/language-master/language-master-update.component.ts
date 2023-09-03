import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ILanguageMaster } from 'app/shared/model/language-master.model';
import { LanguageMasterService } from './language-master.service';

@Component({
  selector: 'jhi-language-master-update',
  templateUrl: './language-master-update.component.html'
})
export class LanguageMasterUpdateComponent implements OnInit {
  languageMaster: ILanguageMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected languageMasterService: LanguageMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ languageMaster }) => {
      this.languageMaster = languageMaster;
      this.createdDate = this.languageMaster.createdDate != null ? this.languageMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.languageMaster.lastUpdatedDate != null ? this.languageMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.languageMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.languageMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.languageMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.languageMasterService.update(this.languageMaster));
    } else {
      this.subscribeToSaveResponse(this.languageMasterService.create(this.languageMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILanguageMaster>>) {
    result.subscribe((res: HttpResponse<ILanguageMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
