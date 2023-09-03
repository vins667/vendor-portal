import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { TdsQueryService } from './tds-query.service';
import { TdsGroupDetailsService } from '../tds-group-details';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { TdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';
import { TdsDeclarationUploadSearch } from 'app/shared/model/tds-declaration-upload-search.model';
import { TdsDeclarationUploadQryUpdateComponent } from 'app/entities/tds-query/tds-declaration-upload-qry-update.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsDeclarationService } from 'app/entities/tds-declaration';

@Component({
  selector: 'jhi-tds-query-update',
  templateUrl: './tds-query-update.component.html'
})
export class TdsQueryUpdateComponent implements OnInit {
  tdsDeclaration: ITdsDeclaration;
  isSaving: boolean;
  isRequired: boolean;
  protected ngbModalRef: NgbModalRef;
  tdsYearMaster: ITdsYearMaster;
  constructor(
    protected tdsDeclarationService: TdsDeclarationService,
    protected tdsD: TdsGroupDetailsService,
    protected route: ActivatedRoute,
    private snotifyService: SnotifyService,
    private service: TdsQueryService,
    protected tdsYearMasterService: TdsYearMasterService,
    protected modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.route.params.subscribe(params => {
      const year = params['year'] ? params['year'] : null;
      const cardNo = params['cardNo'] ? params['cardNo'] : null;
      if (cardNo && year) {
        const tdsDeclarationSearch = new TdsDeclarationSearch();
        tdsDeclarationSearch.year = year;
        tdsDeclarationSearch.cardNo = cardNo;
        this.service.edit(tdsDeclarationSearch).subscribe(tdsDeclaration => {
          this.tdsDeclaration = tdsDeclaration.body;
          if (this.tdsDeclaration && this.tdsDeclaration.monthRent > 8300) {
            this.isRequired = true;
          }
          if (this.tdsDeclaration && this.tdsDeclaration.groupMasterBeans) {
            this.amountCalculate();
          }
        });
        this.tdsYearMasterService.findByYear(year).subscribe(tdsYearMaster => {
          this.tdsYearMaster = tdsYearMaster.body;
        });
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

  tdsDocumentUpload(year?: string, cardNo?: string) {
    const tdsDeclarationUploadSearch = new TdsDeclarationUploadSearch();
    tdsDeclarationUploadSearch.cardNo = cardNo;
    tdsDeclarationUploadSearch.financialYear = year;
    this.service.document(tdsDeclarationUploadSearch).subscribe(tdsDeclarationUploads => {
      this.ngbModalRef = this.modalService.open(TdsDeclarationUploadQryUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
      this.ngbModalRef.componentInstance.tdsDeclarationUpload = tdsDeclarationUploads.body;
      this.ngbModalRef.componentInstance.tdsYearMaster = this.tdsYearMaster;
    });
  }
}
