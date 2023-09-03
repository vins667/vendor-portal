import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IMenuAccessMaster } from 'app/shared/model/menu-access-master.model';
import { MenuAccessMasterService } from './menu-access-master.service';
import { IMenuMaster } from 'app/shared/model/menu-master.model';
import { MenuMasterService } from 'app/entities/menu-master';

@Component({
  selector: 'jhi-menu-access-master-update',
  templateUrl: './menu-access-master-update.component.html'
})
export class MenuAccessMasterUpdateComponent implements OnInit {
  menuAccessMaster: IMenuAccessMaster;
  isSaving: boolean;

  menumasters: IMenuMaster[];
  createdDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected menuAccessMasterService: MenuAccessMasterService,
    protected menuMasterService: MenuMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ menuAccessMaster }) => {
      this.menuAccessMaster = menuAccessMaster;
      this.createdDate = this.menuAccessMaster.createdDate != null ? this.menuAccessMaster.createdDate.format(DATE_TIME_FORMAT) : null;
    });
    this.menuMasterService.query().subscribe(
      (res: HttpResponse<IMenuMaster[]>) => {
        this.menumasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.menuAccessMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.menuAccessMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.menuAccessMasterService.update(this.menuAccessMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMenuAccessMaster>>) {
    result.subscribe((res: HttpResponse<IMenuAccessMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMenuMasterById(index: number, item: IMenuMaster) {
    return item.id;
  }
}
