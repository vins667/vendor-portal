import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';
import { RecruitmentDocumentMasterService } from './recruitment-document-master.service';

@Component({
  selector: 'jhi-recruitment-document-master-update',
  templateUrl: './recruitment-document-master-update.component.html'
})
export class RecruitmentDocumentMasterUpdateComponent implements OnInit {
  recruitmentDocumentMaster: IRecruitmentDocumentMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected recruitmentDocumentMasterService: RecruitmentDocumentMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recruitmentDocumentMaster }) => {
      this.recruitmentDocumentMaster = recruitmentDocumentMaster;
      this.createdDate =
        this.recruitmentDocumentMaster.createdDate != null ? this.recruitmentDocumentMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.recruitmentDocumentMaster.lastUpdatedDate != null
          ? this.recruitmentDocumentMaster.lastUpdatedDate.format(DATE_TIME_FORMAT)
          : null;
    });
    if (this.recruitmentDocumentMaster.id === undefined || this.recruitmentDocumentMaster.id === null) {
      this.recruitmentDocumentMaster.attachType = 'U';
      this.recruitmentDocumentMaster.forceDocumentType = 'A';
      this.recruitmentDocumentMaster.documentType = 'A';
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.recruitmentDocumentMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.recruitmentDocumentMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.recruitmentDocumentMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.recruitmentDocumentMasterService.update(this.recruitmentDocumentMaster));
    } else {
      this.subscribeToSaveResponse(this.recruitmentDocumentMasterService.create(this.recruitmentDocumentMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecruitmentDocumentMaster>>) {
    result.subscribe(
      (res: HttpResponse<IRecruitmentDocumentMaster>) => this.onSaveSuccess(),
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
