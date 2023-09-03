import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDocumentMaster } from 'app/shared/model/document-master.model';
import { DocumentMasterService } from './document-master.service';

@Component({
  selector: 'jhi-document-master-update',
  templateUrl: './document-master-update.component.html'
})
export class DocumentMasterUpdateComponent implements OnInit {
  documentMaster: IDocumentMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected documentMasterService: DocumentMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ documentMaster }) => {
      this.documentMaster = documentMaster;
      this.createdDate = this.documentMaster.createdDate != null ? this.documentMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.documentMaster.lastUpdatedDate != null ? this.documentMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.documentMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.documentMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.documentMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.documentMasterService.update(this.documentMaster));
    } else {
      this.subscribeToSaveResponse(this.documentMasterService.create(this.documentMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocumentMaster>>) {
    result.subscribe((res: HttpResponse<IDocumentMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
