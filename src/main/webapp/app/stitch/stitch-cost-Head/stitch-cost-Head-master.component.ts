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
  constructor(
    protected service: StitchCostHeadMasterService,
    protected factoryMasterService: FactoryMasterService,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.masterBean = new StitchCostHeadMasterBean();
    this.masterBean.factory = "";
  }

  ngOnInit() {
    this.factoryMasterService.now().subscribe(factories => {
      this.factories = factories.body;
    });
  }

  loadAll() {
    this.service.query(this.masterBean.factory).subscribe(res => {
      this.masterBean = res.body;
    });
  }

  changeExpend(mmrDepartment) {
    if (mmrDepartment.expend === true) {
      mmrDepartment.expend = false;
    } else {
      mmrDepartment.expend = true;
    }
  }

  save() {
    this.isSaving = true;
    if (this.masterBean !== undefined) {
      this.masterBean.stitchCostHeadMasters = this.masterBean.stitchCostHeadMasters.filter(data => undefined != data.expend && data.expend);
      this.subscribeToSaveResponse(this.service.update(this.masterBean));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStitchCostHeadMasterBean>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
  }

}
