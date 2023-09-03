import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ItemAvgActualService } from './item-avg-actual.service';
import { SnotifyService } from 'ng-snotify';
import { IDiCostananlysisAverage } from 'app/shared/db2/model/di-costanalysis-average.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';

@Component({
  selector: 'jhi-item-avg-actual',
  templateUrl: './item-avg-actual.component.html'
})
export class ItemAvgActualComponent implements OnInit {
  projectcode: string;
  isSaving: boolean;
  diCostanalysises: IDiCostananlysisAverage[] = [];
  public dataRemoteProject: RemoteData;

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected orderProfitabilityService: ItemAvgActualService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    public completerService: CompleterService,
    private fb: FormBuilder
  ) {
    this.dataRemoteProject = this.completerService.remote(this.orderProfitabilityService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit() {
    this.isSaving = false;
  }

  previousState() {
    window.history.back();
  }

  save() {
    if (this.diCostanalysises !== undefined && this.diCostanalysises.length > 0) {
      this.isSaving = true;
      this.subscribeToSaveResponse(this.orderProfitabilityService.create(this.diCostanalysises));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDiCostananlysisAverage[]>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', '', toastConfig);
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  fetchDetails(): void {
    if (this.projectcode && this.projectcode !== undefined) {
      this.isSaving = true;
      this.orderProfitabilityService.find(this.projectcode.toUpperCase()).subscribe(diCostanalysises => {
        this.diCostanalysises = diCostanalysises.body;
        this.isSaving = false;
      });
    }
  }

  onProjectSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.projectcode = selected.originalObject.id.code;
      this.fetchDetails();
    } else {
      this.projectcode = undefined;
    }
  }
}
