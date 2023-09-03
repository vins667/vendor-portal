import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';
import { TdsComputationEntryService } from './tds-computation-entry.service';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';

@Component({
  selector: 'jhi-tds-computation-update',
  templateUrl: './tds-computation-entry-update.component.html'
})
export class TdsComputationEntryUpdateComponent implements OnInit {
  tdsComputation: ITdsComputation;
  isSaving: boolean;
  tdsYearMaster: ITdsYearMaster;

  constructor(
    protected tdsComputationService: TdsComputationEntryService,
    protected activatedRoute: ActivatedRoute,
    protected tdsYearMasterService: TdsYearMasterService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tdsComputation }) => {
      this.tdsComputation = tdsComputation;

      this.tdsYearMasterService.findByYear(Number(this.tdsComputation.financialYear)).subscribe(tdsYearMaster => {
        this.tdsYearMaster = tdsYearMaster.body;
      });
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.tdsComputation.id !== undefined) {
      this.subscribeToSaveResponse(this.tdsComputationService.update(this.tdsComputation));
    } else {
      this.subscribeToSaveResponse(this.tdsComputationService.create(this.tdsComputation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsComputation>>) {
    result.subscribe((res: HttpResponse<ITdsComputation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  changeExpend(groupMasterBean) {
    if (groupMasterBean.expend === true) {
      groupMasterBean.expend = false;
    } else {
      groupMasterBean.expend = true;
    }
  }
}
