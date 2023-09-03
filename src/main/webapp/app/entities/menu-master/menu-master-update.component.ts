import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMenuMaster } from 'app/shared/model/menu-master.model';
import { MenuMasterService } from './menu-master.service';

@Component({
  selector: 'jhi-menu-master-update',
  templateUrl: './menu-master-update.component.html'
})
export class MenuMasterUpdateComponent implements OnInit {
  menuMaster: IMenuMaster;
  isSaving: boolean;
  createdDate: string;

  constructor(protected menuMasterService: MenuMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ menuMaster }) => {
      this.menuMaster = menuMaster;
      this.createdDate = this.menuMaster.createdDate != null ? this.menuMaster.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.menuMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.menuMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.menuMasterService.update(this.menuMaster));
    } else {
      this.subscribeToSaveResponse(this.menuMasterService.create(this.menuMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMenuMaster>>) {
    result.subscribe((res: HttpResponse<IMenuMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
