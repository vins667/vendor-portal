import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager,JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { StitchCostHeadMasterService } from './stitch-cost-Head-master.service';
import { IStitchCostHeadMaster } from 'app/shared/model/stitch-cost-head-master.model';

@Component({
  selector: 'jhi-stitch-cost-head-master',
  templateUrl: './stitch-cost-head-master.component.html'
})
export class StitchCostHeadMasterComponent implements OnInit {
  currentAccount: any;
  stitchCostHeadMasters: IStitchCostHeadMaster[];
  routeData: any;

  constructor(
    protected service: StitchCostHeadMasterService,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) { }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
  }

  loadAll() {
    this.service.queryCostHeadMasters().subscribe(res => {
      this.stitchCostHeadMasters = res.body;
    });
  }

  changeExpend(mmrDepartment) {
    if (mmrDepartment.expend === true) {
      mmrDepartment.expend = false;
    } else {
      mmrDepartment.expend = true;
    }
  }
}
