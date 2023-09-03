import { Component, OnInit } from '@angular/core';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';
import { AssetAuditSoftwareCompareService } from './asset-audit-software-compare.service';
import { AssetCompareBean, IAssetCompareBean } from 'app/shared/model/asset-compare-bean.model';
import { AssetAuditRunTimes, IAssetAuditRunTimes } from 'app/shared/model/asset-audit-run-times.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';

@Component({
  selector: 'jhi-asset-audit-software-compare',
  templateUrl: './asset-audit-software-compare.component.html'
})
export class AssetAuditSoftwareCompareComponent implements OnInit {
  assetCompareBean: IAssetCompareBean;
  assetAuditRunTimes: IAssetAuditRunTimes[];
  assetAuditRunTimesEnd: IAssetAuditRunTimes[];
  currentAccount: any;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  queryCount: any;
  reverse: any;
  totalItems: number;
  startTime: any;
  endTime: any;

  constructor(
    protected assetAuditSoftwareCompareService: AssetAuditSoftwareCompareService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected accountService: AccountService
  ) {}

  ngOnInit() {
    this.assetCompareBean = new AssetCompareBean();
    this.assetAuditSoftwareCompareService.queryRunTime().subscribe(assetAuditRunTimes => {
      this.assetAuditRunTimes = assetAuditRunTimes.body;
    });
  }

  fetchEndTime() {
    const assetAuditRunTime = new AssetAuditRunTimes();
    assetAuditRunTime.runTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
    this.assetAuditSoftwareCompareService.fetchEndTime(assetAuditRunTime).subscribe(assetAuditRunTimes => {
      this.assetAuditRunTimesEnd = assetAuditRunTimes.body;
    });
  }

  search() {
    if (this.startTime && this.endTime) {
      this.assetCompareBean.startTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
      this.assetCompareBean.endTime = this.endTime != null ? moment(this.endTime, DATE_TIME_FORMAT) : null;
      this.assetAuditSoftwareCompareService.search(this.assetCompareBean).subscribe(assetCompareBean => {
        this.assetCompareBean = assetCompareBean.body;
      });
    }
  }
}
