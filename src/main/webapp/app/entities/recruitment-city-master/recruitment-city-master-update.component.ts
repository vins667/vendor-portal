import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';
import { RecruitmentCityMasterService } from './recruitment-city-master.service';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';
import { RecruitmentDistrictService } from 'app/entities/recruitment-district';

@Component({
  selector: 'jhi-recruitment-city-master-update',
  templateUrl: './recruitment-city-master-update.component.html'
})
export class RecruitmentCityMasterUpdateComponent implements OnInit {
  recruitmentCityMaster: IRecruitmentCityMaster;
  isSaving: boolean;

  recruitmentdistricts: IRecruitmentDistrict[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected recruitmentCityMasterService: RecruitmentCityMasterService,
    protected recruitmentDistrictService: RecruitmentDistrictService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ recruitmentCityMaster }) => {
      this.recruitmentCityMaster = recruitmentCityMaster;
      this.createdDate =
        this.recruitmentCityMaster.createdDate != null ? this.recruitmentCityMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.recruitmentCityMaster.lastUpdatedDate != null ? this.recruitmentCityMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.recruitmentDistrictService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRecruitmentDistrict[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRecruitmentDistrict[]>) => response.body)
      )
      .subscribe((res: IRecruitmentDistrict[]) => (this.recruitmentdistricts = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.recruitmentCityMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.recruitmentCityMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.recruitmentCityMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.recruitmentCityMasterService.update(this.recruitmentCityMaster));
    } else {
      this.subscribeToSaveResponse(this.recruitmentCityMasterService.create(this.recruitmentCityMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecruitmentCityMaster>>) {
    result.subscribe((res: HttpResponse<IRecruitmentCityMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackRecruitmentDistrictById(index: number, item: IRecruitmentDistrict) {
    return item.id;
  }
}
