import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMonthlyNewsData, MonthlyNewsData } from 'app/shared/model/monthly-news-data.model';
import { MonthlyNewsDataService } from './monthly-news-data.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-monthly-news-data-update',
  templateUrl: './monthly-news-data-update.component.html'
})
export class MonthlyNewsDataUpdateComponent implements OnInit {
  isSaving: boolean;
  selectedFile: FileList;
  currentFileUpload: File;
  extn: string;
  editForm = this.fb.group({
    id: [],
    fileName: [null],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    closedDate: []
  });

  constructor(
    protected monthlyNewsDataService: MonthlyNewsDataService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ monthlyNewsData }) => {
      this.updateForm(monthlyNewsData);
    });
  }

  updateForm(monthlyNewsData: IMonthlyNewsData) {
    this.editForm.patchValue({
      id: monthlyNewsData.id,
      fileName: monthlyNewsData.fileName,
      createdBy: monthlyNewsData.createdBy,
      createdDate: monthlyNewsData.createdDate != null ? monthlyNewsData.createdDate.format(DATE_TIME_FORMAT) : null,
      closedDate: monthlyNewsData.closedDate != null ? monthlyNewsData.closedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  selectFile(event) {
    this.selectedFile = event.target.files;
    const file = event.target.files[0];
    const fileName = file.name;
    this.extn = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
    if (this.extn !== null && this.extn !== 'pdf') {
      this.selectedFile = null;
      this.isSaving = false;
      this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
    }
  }

  save() {
    this.currentFileUpload = this.selectedFile.item(0);
    this.isSaving = true;
    if (this.currentFileUpload && this.currentFileUpload !== undefined) {
      this.subscribeToSaveResponse(this.monthlyNewsDataService.create(this.currentFileUpload));
    }
  }

  private createFromForm(): IMonthlyNewsData {
    return {
      ...new MonthlyNewsData(),
      id: this.editForm.get(['id']).value,
      fileName: this.editForm.get(['fileName']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      closedDate:
        this.editForm.get(['closedDate']).value != null ? moment(this.editForm.get(['closedDate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonthlyNewsData>>) {
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
