import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';
import { RecruitmentDistrictService } from './recruitment-district.service';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';
import { RecruitmentStateMasterService } from 'app/entities/recruitment-state-master';

@Component({
  selector: 'jhi-recruitment-district-update',
  templateUrl: './recruitment-district-update.component.html'
})
export class RecruitmentDistrictUpdateComponent implements OnInit {
  recruitmentDistrict: IRecruitmentDistrict;
  isSaving: boolean;

  recruitmentstatemasters: IRecruitmentStateMaster[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected recruitmentDistrictService: RecruitmentDistrictService,
    protected recruitmentStateMasterService: RecruitmentStateMasterService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recruitmentDistrict }) => {
      this.recruitmentDistrict = recruitmentDistrict;
      this.createdDate =
        this.recruitmentDistrict.createdDate != null ? this.recruitmentDistrict.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.recruitmentDistrict.lastUpdatedDate != null ? this.recruitmentDistrict.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.recruitmentStateMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRecruitmentStateMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRecruitmentStateMaster[]>) => response.body)
      )
      .subscribe(
        (res: IRecruitmentStateMaster[]) => (this.recruitmentstatemasters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.recruitmentDistrict.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.recruitmentDistrict.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.recruitmentDistrict.id !== undefined) {
      this.subscribeToSaveResponse(this.recruitmentDistrictService.update(this.recruitmentDistrict));
    } else {
      this.subscribeToSaveResponse(this.recruitmentDistrictService.create(this.recruitmentDistrict));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecruitmentDistrict>>) {
    result.subscribe((res: HttpResponse<IRecruitmentDistrict>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackRecruitmentStateMasterById(index: number, item: IRecruitmentStateMaster) {
    return item.id;
  }
}
