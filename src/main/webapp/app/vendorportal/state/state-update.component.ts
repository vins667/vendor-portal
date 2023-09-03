import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IState } from 'app/shared/model/state.model';
import { StateService } from './state.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/vendorportal/country';

@Component({
  selector: 'jhi-state-update',
  templateUrl: './state-update.component.html'
})
export class StateUpdateComponent implements OnInit {
  state: IState;
  isSaving: boolean;

  countries: ICountry[];
  createdDate: string;
  lastUpdatedDate: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected stateService: StateService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ state }) => {
      this.state = state;
      this.createdDate = this.state.createdDate != null ? this.state.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate = this.state.lastUpdatedDate != null ? this.state.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.countryService.query().subscribe(
      (res: HttpResponse<ICountry[]>) => {
        this.countries = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.state.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.state.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.state.id !== undefined) {
      this.subscribeToSaveResponse(this.stateService.update(this.state));
    } else {
      this.subscribeToSaveResponse(this.stateService.create(this.state));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IState>>) {
    result.subscribe((res: HttpResponse<IState>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackCountryById(index: number, item: ICountry) {
    return item.id;
  }
}
