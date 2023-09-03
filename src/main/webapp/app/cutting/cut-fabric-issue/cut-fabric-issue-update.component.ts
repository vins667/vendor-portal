import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { CutFabricIssueService } from './cut-fabric-issue.service';
import { ICutFabricIssue } from 'app/shared/model/cut-fabric-issue.model';
import { ICutPlanEntryDetails } from 'app/shared/model/cut-plan-entry-details.model';
import { CutPlanEntryService } from './cut-plan-entry.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { IResources } from 'app/shared/model/resources.model';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-cut-fabric-issue-update',
  templateUrl: './cut-fabric-issue-update.component.html'
})
export class CutFabricIssueUpdateComponent implements OnInit {
  isProcess = false;
  isSaving: boolean;
  iCheckAll: boolean[];
  cutplanEntryDetails: ICutPlanEntryDetails[];
  cutPlanEntry: ICutPlanEntry;
  allowPliesAll = true;
  resources: IResources[] = [];
  resourceCode: string;
  resourceDescription: string;
  isDisabled = false;

  constructor(
    protected cutFabricIssueService: CutFabricIssueService,
    protected cutPlanEntryService: CutPlanEntryService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {
    this.cutplanEntryDetails = [];
  }

  ngOnInit() {
    this.isSaving = false;
    this.iCheckAll = [];
    this.activatedRoute.data.subscribe(({ cutPlanEntry }) => {
      this.cutPlanEntry = cutPlanEntry;
      if (this.cutPlanEntry.plantCode) {
        this.cutPlanEntryService.resourcesByPlantCode(this.cutPlanEntry.plantCode).subscribe(resources => {
          this.resources = resources.body;
        });
      }
      this.cutplanEntryDetails = cutPlanEntry.cutPlanEntryDetailsBeans;
      if (this.cutplanEntryDetails && this.cutplanEntryDetails.length > 0) {
        this.cutplanEntryDetails.forEach(cutplanEntryDetail => {
          if (cutplanEntryDetail.resourceCode && !this.isDisabled) {
            this.isDisabled = true;
            this.resourceCode = cutplanEntryDetail.resourceCode;
            this.resourceDescription = cutplanEntryDetail.resourceDescription;
          }
        });
      }
    });
  }

  generatePdf() {
    // this.spinner.show();
    this.cutFabricIssueService.downloadPdf(this.cutPlanEntry.id).subscribe(
      res => {
        FileSaver.saveAs(res, 'CutPlanIssueStich.pdf');
        // this.spinner.hide();
      },
      () => {
        // this.spinner.hide();
      }
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    if (this.allowPliesCheck() === true) {
      this.isSaving = true;
      this.subscribeToSaveResponse(this.cutFabricIssueService.create(this.cutplanEntryDetails));
    }
  }

  allowPliesCheck(): any {
    let ctr = 0;
    let checked = 0;
    this.cutplanEntryDetails.forEach(cutplanEntryDetail => {
      if (cutplanEntryDetail.allowPlies && cutplanEntryDetail.allowPlies === true) {
        ++checked;
      }
      ++ctr;
    });
    if (ctr === this.cutplanEntryDetails.length && checked > 0) {
      return true;
    } else {
      this.snotifyService.error('Choose at-least 1 line.', '', toastConfig);
      return false;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICutFabricIssue>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
    this.isSaving = false;
  }

  allowPliesChange(): void {
    if (this.allowPliesAll && this.allowPliesAll === true) {
      this.cutplanEntryDetails.forEach(cutplanEntryDetail => {
        if (cutplanEntryDetail.splitFlag === 'Y' && cutplanEntryDetail.splitNoPlies && !cutplanEntryDetail.issuedBy) {
          cutplanEntryDetail.allowPlies = true;
        } else if (!cutplanEntryDetail.splitNoPlies && !cutplanEntryDetail.issuedBy) {
          cutplanEntryDetail.allowPlies = true;
        }
      });
    } else {
      this.cutplanEntryDetails.forEach(cutplanEntryDetail => {
        if (!cutplanEntryDetail.issuedBy) {
          cutplanEntryDetail.allowPlies = false;
        }
      });
    }
  }

  callResources(): void {
    if (this.resourceCode) {
      this.resources.forEach(resource => {
        if (resource.id.code === this.resourceCode) {
          this.resourceDescription = resource.longdescription;
          this.cutplanEntryDetails.forEach(cutPlanEntryDetail => {
            cutPlanEntryDetail.resourceCode = this.resourceCode;
            cutPlanEntryDetail.resourceDescription = this.resourceDescription;
          });
        }
      });
    } else {
      this.resourceDescription = undefined;
    }
  }

  splitConfirm(cutPlanEntryDetail: ICutPlanEntryDetails): void {
    this.snotifyService.confirm('Are you sure to split?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.split(toast, cutPlanEntryDetail), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  split(toast, cutPlanEntryDetail: ICutPlanEntryDetails): void {
    this.snotifyService.remove(toast.id);
    this.isProcess = true;
    this.cutFabricIssueService.pushSplitted(cutPlanEntryDetail).subscribe(any => {
      this.isProcess = false;
      this.previousState();
    });
  }
}
