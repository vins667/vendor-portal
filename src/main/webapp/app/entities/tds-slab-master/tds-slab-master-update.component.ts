import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITdsSlabMaster, TdsSlabMaster } from 'app/shared/model/tds-slab-master.model';
import { TdsSlabMasterService } from './tds-slab-master.service';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from '../tds-year-master/tds-year-master.service';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-tds-slab-master-update',
  templateUrl: './tds-slab-master-update.component.html'
})
export class TdsSlabMasterUpdateComponent implements OnInit {
  tdsSlabMasters: ITdsSlabMaster[];
  tdsyearmasters: ITdsYearMaster[];
  isSaving: boolean;
  createdDate: string;
  finYear: string;
  gender: string;
  constructor(
    protected tdsSlabMasterService: TdsSlabMasterService,
    protected tdsYearMasterService: TdsYearMasterService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.loadTdsYear();
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tdsSlabMaster }) => {
      this.tdsSlabMasters = tdsSlabMaster;
      if (this.tdsSlabMasters.length > 0) {
        this.finYear = this.tdsSlabMasters[1].finYear;
        this.gender = this.tdsSlabMasters[2].gender;
      } else {
        this.loadAll();
      }
    });
  }
  loadTdsYear() {
    this.tdsYearMasterService.query().subscribe((res: HttpResponse<ITdsYearMaster[]>) => {
      this.tdsyearmasters = res.body;
    });
  }
  loadAll() {
    this.tdsSlabMasters = new Array<TdsSlabMaster>();
    for (let i = 0; i < 5; i++) {
      this.tdsSlabMasters.push(new TdsSlabMaster());
    }
  }
  addRow() {
    this.tdsSlabMasters.push(new TdsSlabMaster());
  }
  removeRow(index: any) {
    this.tdsSlabMasters.splice(index, 1);
  }
  previousState() {
    window.history.back();
  }
  save() {
    this.isSaving = true;
    const tdsSlabMas = new Array<TdsSlabMaster>();
    this.tdsSlabMasters.forEach(item => {
      if (item.ageStart !== undefined && item.ageEnd !== undefined) {
        tdsSlabMas.push({
          id: item.id,
          finYear: this.finYear,
          gender: this.gender,
          ageStart: item.ageStart,
          ageEnd: item.ageEnd,
          amountStart: item.amountStart,
          amountEnd: item.amountEnd,
          taxPercentage: item.taxPercentage,
          taxSurcharge: item.taxSurcharge,
          exemptionLimit: item.exemptionLimit
        });
      }
    });
    if (tdsSlabMas !== undefined && tdsSlabMas.length > 0) {
      this.subscribeToSaveResponse(this.tdsSlabMasterService.create(tdsSlabMas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsSlabMaster>>) {
    result.subscribe((res: HttpResponse<ITdsSlabMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', '', toastConfig);
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Record not save', '', toastConfig);
  }
}
