import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';
import { InstituteMasterService } from './institute-master.service';

@Component({
  selector: 'jhi-institute-master-update',
  templateUrl: './institute-master-update.component.html'
})
export class InstituteMasterUpdateComponent implements OnInit {
  instituteMaster: IInstituteMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected instituteMasterService: InstituteMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ instituteMaster }) => {
      this.instituteMaster = instituteMaster;
      this.createdDate = this.instituteMaster.createdDate != null ? this.instituteMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.instituteMaster.lastUpdatedDate != null ? this.instituteMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.instituteMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.instituteMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.instituteMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.instituteMasterService.update(this.instituteMaster));
    } else {
      this.subscribeToSaveResponse(this.instituteMasterService.create(this.instituteMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstituteMaster>>) {
    result.subscribe((res: HttpResponse<IInstituteMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
