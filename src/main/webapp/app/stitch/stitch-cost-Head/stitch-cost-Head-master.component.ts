import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { StitchCostHeadMasterService } from './stitch-cost-Head-master.service';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { IStitchCostHeadMasterBean, StitchCostHeadMasterBean } from 'app/shared/model/stitch-cost-head-master-bean.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IStitchCostHeadMaster } from 'app/shared/model/stitch-cost-head-master.model';
import { StitchCostSubHeadDetails } from 'app/shared/model/stitch-cost-sub-head-details.model';

@Component({
  selector: 'jhi-stitch-cost-head-master',
  templateUrl: './stitch-cost-head-master.component.html'
})
export class StitchCostHeadMasterComponent implements OnInit {
  currentAccount: any;
  masterBean: IStitchCostHeadMasterBean;
  factories?: IFactoryMaster[] = [];
  routeData: any;
  isSaving: boolean;
  headTotal:number = 0;
  countTotal=0;
  isProcess = false;
  constructor(
    protected service: StitchCostHeadMasterService,
    protected factoryMasterService: FactoryMasterService,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
  ) {
    this.masterBean = new StitchCostHeadMasterBean();

  }

  ngOnInit() {
    this.factoryMasterService.now().subscribe(factories => {
      this.factories = factories.body;
    });
  }

  loadAll() {
    this.isProcess = true;
    this.headTotal=0;
    this.service.query(this.masterBean.factory).subscribe(res => {
      this.isSaving = false;
      this.masterBean = res.body;
      if (undefined != this.masterBean) {
        this.masterBean.stitchCostHeadMasters.forEach(data => {
          this.totalCost(data);
         /* data.stitchCostSubHeadMaster.forEach(details => {
            if (null == details.stitchCostSubHeadDetails) {
              let stitchCostSubHeadDetails = new StitchCostSubHeadDetails();
              stitchCostSubHeadDetails.companyCost = 0;
              details.stitchCostSubHeadDetails = stitchCostSubHeadDetails;
            }
          })*/
        });
      }
      this.isProcess = false;
    },(res: HttpErrorResponse) => this.onError(res));
  }

  changeExpend(mmrDepartment) {
    if (mmrDepartment.expend === true) {
      mmrDepartment.expend = false;
    } else {
      mmrDepartment.expend = true;
    }
  }

  totalCost(master: IStitchCostHeadMaster): void {
    let total:number= 0;
    let head=0;
    master.stitchCostSubHeadMaster.forEach(data => {
      if (null != data.stitchCostSubHeadDetails && undefined != data.stitchCostSubHeadDetails.companyCost) {
        total = total + data.stitchCostSubHeadDetails.companyCost;
        master.totalCost = total;
      }
    });
    this.masterBean.stitchCostHeadMasters.forEach(data=>{
        if(undefined!=data.totalCost){
          head= head + data.totalCost;
          this.headTotal= head;
        }
    });
  }

  save() {
    this.isProcess = true;
    this.isSaving = true;
    let isId = false;
    if (this.masterBean !== undefined) {
      // this.masterBean.stitchCostHeadMasters = this.masterBean.stitchCostHeadMasters.filter(data => undefined != data.expend && data.expend);
      this.masterBean.stitchCostHeadMasters.forEach(data => {
        data.stitchCostSubHeadMaster.forEach(details => {
          isId = undefined != details.stitchCostSubHeadDetails && null != details.stitchCostSubHeadDetails.id;
        })
      })
      if (isId) {
        this.subscribeToUpdateResponse(this.service.update(this.masterBean));
      } else {
        this.subscribeToSaveResponse(this.service.create(this.masterBean));
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStitchCostHeadMasterBean>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onError(res));
  }

  protected subscribeToUpdateResponse(result: Observable<HttpResponse<IStitchCostHeadMasterBean>>) {
    result.subscribe(() => this.onUpdateSuccess(), (res: HttpErrorResponse) => this.onError(res));
  }

  protected onUpdateSuccess() {
    this.isProcess = false;
    this.isSaving = true;
    this.snotifyService.success("Stitch Manpower Cost Updated Successfully !!", toastConfig);
  }

  protected onSaveSuccess() {
    this.isProcess = false;
    this.isSaving = true;
    this.snotifyService.success("Stitch Manpower Cost saved Successfully !!", toastConfig);
  }

  protected onError(res: HttpErrorResponse) {
    this.isProcess = false;
    this.isSaving = false;
    this.snotifyService.error(res.statusText, '', toastConfig);
  }

}
