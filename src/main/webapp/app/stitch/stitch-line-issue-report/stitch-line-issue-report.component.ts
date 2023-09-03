import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IMarkerMasterEntry, MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { AccountService } from 'app/core/auth/account.service';
import { CompleterService, RemoteData } from 'ng2-completer';
import * as FileSaver from 'file-saver';
import { StitchLineIssueReportService } from 'app/stitch/stitch-line-issue-report/stitch-line-issue-report.service';
import { MasterSearch } from 'app/shared/model/master-search.model';

@Component({
  selector: 'jhi-stitch-line-issue-report',
  templateUrl: './stitch-line-issue-report.component.html'
})
export class StitchLineIssueReportComponent {
  isProcess?: boolean = false;
  project?: any;
  public dataRemoteProject: RemoteData;

  constructor(
    protected stitchLineIssueReportService: StitchLineIssueReportService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    public completerService: CompleterService,
    protected eventManager: JhiEventManager
  ) {
    this.dataRemoteProject = this.completerService.remote(this.stitchLineIssueReportService.resourceUrlProject, 'id.code', 'id.code');
  }

  generateXLS() {
    if (this.project) {
      this.isProcess = true;
      const search = new MasterSearch();
      search.code = this.project;

      this.stitchLineIssueReportService.downloadXlsx(search).subscribe(
        res => {
          FileSaver.saveAs(res, 'StitchLineIssue.xlsx');
          this.isProcess = false;
        },
        res => {
          this.isProcess = false;
        }
      );
    }
  }
}
