import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';
import { RecruitmentCountryMasterService } from './recruitment-country-master.service';

@Component({
  selector: 'jhi-recruitment-country-master-update',
  templateUrl: './recruitment-country-master-update.component.html'
})
export class RecruitmentCountryMasterUpdateComponent implements OnInit {
  recruitmentCountryMaster: IRecruitmentCountryMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected recruitmentCountryMasterService: RecruitmentCountryMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recruitmentCountryMaster }) => {
      this.recruitmentCountryMaster = recruitmentCountryMaster;
      this.createdDate =
        this.recruitmentCountryMaster.createdDate != null ? this.recruitmentCountryMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.recruitmentCountryMaster.lastUpdatedDate != null
          ? this.recruitmentCountryMaster.lastUpdatedDate.format(DATE_TIME_FORMAT)
          : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.recruitmentCountryMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.recruitmentCountryMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.recruitmentCountryMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.recruitmentCountryMasterService.update(this.recruitmentCountryMaster));
    } else {
      this.subscribeToSaveResponse(this.recruitmentCountryMasterService.create(this.recruitmentCountryMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecruitmentCountryMaster>>) {
    result.subscribe(
      (res: HttpResponse<IRecruitmentCountryMaster>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
