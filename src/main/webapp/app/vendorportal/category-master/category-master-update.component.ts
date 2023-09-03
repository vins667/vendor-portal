import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICategoryMaster } from 'app/shared/model/category-master.model';
import { CategoryMasterService } from './category-master.service';

@Component({
  selector: 'jhi-category-master-update',
  templateUrl: './category-master-update.component.html'
})
export class CategoryMasterUpdateComponent implements OnInit {
  categoryMaster: ICategoryMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  constructor(protected categoryMasterService: CategoryMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ categoryMaster }) => {
      this.categoryMaster = categoryMaster;
      this.createdDate = this.categoryMaster.createdDate != null ? this.categoryMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.categoryMaster.lastUpdatedDate != null ? this.categoryMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.categoryMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.categoryMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.categoryMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryMasterService.update(this.categoryMaster));
    } else {
      this.subscribeToSaveResponse(this.categoryMasterService.create(this.categoryMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryMaster>>) {
    result.subscribe((res: HttpResponse<ICategoryMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
