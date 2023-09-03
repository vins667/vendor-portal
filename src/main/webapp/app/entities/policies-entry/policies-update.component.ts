import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { SnotifyService } from 'ng-snotify';
import { IPolicies } from 'app/shared/model/policies.model';
import { PoliciesEntryService } from './policies-entry.service';
import { IPoliciesGroup } from 'app/shared/model/policies-group.model';
import { PoliciesGroupService } from 'app/entities/policies-group';
import * as FileSaver from 'file-saver';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-policies-update',
  templateUrl: './policies-update.component.html'
})
export class PoliciesUpdateComponent implements OnInit {
  policies: IPolicies;
  isSaving: boolean;
  policyFile: any;
  selectedFile: FileList;
  currentFileUpload: File;

  policiesgroups: IPoliciesGroup[];
  createdDate: string;
  extn: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected policiesService: PoliciesEntryService,
    protected policiesGroupService: PoliciesGroupService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ policies }) => {
      this.policies = policies;
      this.createdDate = this.policies.createdDate != null ? this.policies.createdDate.format(DATE_TIME_FORMAT) : null;
    });
    this.policiesGroupService.query().subscribe(
      (res: HttpResponse<IPoliciesGroup[]>) => {
        this.policiesgroups = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.selectedFile && this.extn !== null) {
      this.currentFileUpload = this.selectedFile.item(0);
      if (this.extn !== null && this.extn === 'pdf') {
        if (this.policies.id !== undefined) {
          this.subscribeToSaveResponse(this.policiesService.update(this.currentFileUpload, this.policies));
        } else {
          this.subscribeToSaveResponse(this.policiesService.create(this.currentFileUpload, this.policies));
        }
      } else {
        this.isSaving = false;
        this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
      }
    } else {
      this.isSaving = false;
      this.snotifyService.error('Please choose file!!!', '', toastConfig);
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPolicies>>) {
    result.subscribe((res: HttpResponse<IPolicies>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPoliciesGroupById(index: number, item: IPoliciesGroup) {
    return item.id;
  }

  download(policies1: IPolicies): any {
    this.policiesService.download(policies1.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${policies1.policyFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  selectFile(event) {
    this.selectedFile = event.target.files;
    const file = event.target.files[0];
    const fileName = file.name;
    this.extn = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
    if (this.extn !== null && this.extn !== 'pdf') {
      this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
    }
  }
}
