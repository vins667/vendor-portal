import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';
import { RecruitmentStateMasterService } from './recruitment-state-master.service';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';
import { RecruitmentCountryMasterService } from 'app/entities/recruitment-country-master';

@Component({
  selector: 'jhi-recruitment-state-master-update',
  templateUrl: './recruitment-state-master-update.component.html'
})
export class RecruitmentStateMasterUpdateComponent implements OnInit {
  recruitmentStateMaster: IRecruitmentStateMaster;
  isSaving: boolean;

  recruitmentcountrymasters: IRecruitmentCountryMaster[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected recruitmentStateMasterService: RecruitmentStateMasterService,
    protected recruitmentCountryMasterService: RecruitmentCountryMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recruitmentStateMaster }) => {
      this.recruitmentStateMaster = recruitmentStateMaster;
      this.createdDate =
        this.recruitmentStateMaster.createdDate != null ? this.recruitmentStateMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.recruitmentStateMaster.lastUpdatedDate != null ? this.recruitmentStateMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.recruitmentCountryMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRecruitmentCountryMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRecruitmentCountryMaster[]>) => response.body)
      )
      .subscribe(
        (res: IRecruitmentCountryMaster[]) => (this.recruitmentcountrymasters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.recruitmentStateMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.recruitmentStateMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.recruitmentStateMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.recruitmentStateMasterService.update(this.recruitmentStateMaster));
    } else {
      this.subscribeToSaveResponse(this.recruitmentStateMasterService.create(this.recruitmentStateMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecruitmentStateMaster>>) {
    result.subscribe((res: HttpResponse<IRecruitmentStateMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackRecruitmentCountryMasterById(index: number, item: IRecruitmentCountryMaster) {
    return item.id;
  }
}
