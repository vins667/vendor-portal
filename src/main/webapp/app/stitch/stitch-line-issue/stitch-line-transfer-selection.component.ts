import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IProductionorderSearch, ProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { JhiEventManager } from 'ng-jhipster';
import { StitchLineIssueService } from './stitch-line-issue.service';
import { IStitchLineIssueDetails } from 'app/shared/model/stitch-line-issue-details.model';
import { IResources } from 'app/shared/model/resources.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { Observable } from 'rxjs';
import { IStitchLineIssue } from 'app/shared/model/stitch-line-issue.model';

@Component({
  selector: 'jhi-stitch-line-transfer-selection',
  templateUrl: './stitch-line-transfer-selection.component.html'
})
export class StitchLineTransferSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  plantCode?: string;
  resources: IResources[] = [];
  stitchLineIssueDetail?: IStitchLineIssueDetails;

  constructor(
    protected cutPlanIssueStitchService: StitchLineIssueService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    public stitchLineIssueService: StitchLineIssueService
  ) {}

  ngOnInit(): void {
    this.fetchLines();
  }

  save(): void {
    if (this.stitchLineIssueDetail.line) {
      this.isSaving = true;
      this.subscribeToSaveResponse(this.cutPlanIssueStitchService.transfer(this.stitchLineIssueDetail));
    } else {
      this.snotifyService.error('Line is required', '', toastConfig);
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStitchLineIssueDetails>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  onSaveSuccess(): void {
    this.isSaving = false;
    this.snotifyService.success('Line updated', '', toastConfig);
    this.activeModal.dismiss('CLOSE');
  }

  onSaveError(): void {
    this.isSaving = false;
    this.snotifyService.error('Line not updated', '', toastConfig);
  }

  selectLine(): void {
    if (this.stitchLineIssueDetail.line) {
      this.resources.forEach(resource => {
        if (resource.id.code === this.stitchLineIssueDetail.line) {
          this.stitchLineIssueDetail.lineDesc = resource.longdescription;
        }
      });
    } else {
      this.stitchLineIssueDetail.lineDesc = undefined;
    }
  }

  fetchLines(): void {
    if (this.plantCode) {
      this.cutPlanIssueStitchService.resourcesByPlantCode(this.plantCode).subscribe(resources => {
        this.resources = resources.body;
      });
    }
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }
}
