import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { TdsDeclarationService } from './tds-declaration.service';
import { TdsGroupDetailsService } from '../tds-group-details';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-tds-declaration-update',
  templateUrl: './tds-declaration-update.component.html'
})
export class TdsDeclarationUpdateComponent implements OnInit {
  tdsDeclaration: ITdsDeclaration;
  isSaving: boolean;
  isRequired: boolean;
  constructor(
    protected tdsDeclarationService: TdsDeclarationService,
    protected tdsGroupDetailsService: TdsGroupDetailsService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.tdsDeclarationService.customQuery().subscribe((res: HttpResponse<ITdsDeclaration>) => {
      this.tdsDeclaration = res.body;
      if (this.tdsDeclaration.monthRent > 8300) {
        this.isRequired = true;
      }
      if (this.tdsDeclaration && this.tdsDeclaration.groupMasterBeans) {
        this.amountCalculate();
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.tdsDeclarationService.create(this.tdsDeclaration));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITdsDeclaration>>) {
    result.subscribe((res: HttpResponse<ITdsDeclaration>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', '', toastConfig);
    //  this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Record not save', '', toastConfig);
  }
  isMonthlyRent() {
    this.isRequired = false;
    if (this.tdsDeclaration.monthRent > 8300) {
      this.isRequired = true;
    }
  }

  amountCalculate() {
    if (this.tdsDeclaration && this.tdsDeclaration.groupMasterBeans) {
      this.tdsDeclaration.groupMasterBeans.forEach(groupMaster => {
        let totalVal = 0;
        let ctr = 0;
        const map = new Map<number, number>();
        groupMaster.tdsGroupDetailsBean.forEach(tdsGroupDetail => {
          ++ctr;
          let detailTotalVal = 0;
          tdsGroupDetail.tdsDeclarationBreakupBeans.forEach(tdsBreakUp => {
            if (tdsBreakUp.amount) {
              totalVal += tdsBreakUp.amount;

              detailTotalVal += tdsBreakUp.amount;
              if (map.has(tdsBreakUp.employerId)) {
                map.set(tdsBreakUp.employerId, tdsBreakUp.amount + map.get(tdsBreakUp.employerId));
              } else {
                map.set(tdsBreakUp.employerId, tdsBreakUp.amount);
              }
            }
          });
          tdsGroupDetail.amount = detailTotalVal;
        });
        if (ctr === groupMaster.tdsGroupDetailsBean.length) {
          groupMaster.previousEmploymentDetailBeans.forEach(prevEmployer => {
            if (map.has(prevEmployer.id)) {
              prevEmployer.amount = map.get(prevEmployer.id);
            } else {
              prevEmployer.amount = 0;
            }
          });
          groupMaster.totalAmount = totalVal;
        }
      });
    }
  }

  regimeCalculation(): void {
    if (!this.tdsDeclaration.regime) {
      this.tdsDeclaration.monthRent = undefined;
      this.tdsDeclaration.landLoardName = undefined;
      this.tdsDeclaration.landLoardAddress = undefined;
      this.tdsDeclaration.landLoardPanNo = undefined;
      this.isRequired = false;
    }
  }
}
