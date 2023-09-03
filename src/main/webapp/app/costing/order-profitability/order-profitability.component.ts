import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { OrderProfitabilityService } from './order-profitability.service';
import { SnotifyService } from 'ng-snotify';
import { IDiCostananlysis } from 'app/shared/db2/model/di-costanalysis.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';

@Component({
  selector: 'jhi-order-profitability',
  templateUrl: './order-profitability.component.html'
})
export class OrderProfitabilityComponent implements OnInit {
  projectcode: string;
  isSaving: boolean;
  diCostanalysises: IDiCostananlysis[] = [];
  public dataRemoteProject: RemoteData;

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected orderProfitabilityService: OrderProfitabilityService,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDiCostananlysis[]>>): void {
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
