import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';
import { TrimsCreationMasterService } from './trims-creation-master.service';
import { TrimsTemplateMasterService } from 'app/fabriccoding/trims-template-master';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-trims-creation-master-update',
  templateUrl: './trims-creation-master-update.component.html'
})
export class TrimsCreationMasterUpdateComponent implements OnInit {
  trimsCreationMaster: ITrimsCreationMaster;
  isSaving: boolean;

  trimstemplatemasters: ITrimsTemplateMaster[];

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trimsCreationMasterService: TrimsCreationMasterService,
    protected trimsTemplateMasterService: TrimsTemplateMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ trimsCreationMaster }) => {
      this.trimsCreationMaster = trimsCreationMaster;
    });
    this.trimsTemplateMasterService.query().subscribe(
      (res: HttpResponse<ITrimsTemplateMaster[]>) => {
        this.trimstemplatemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.trimsCreationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.trimsCreationMasterService.update(this.trimsCreationMaster));
    } else {
      this.subscribeToSaveResponse(this.trimsCreationMasterService.create(this.trimsCreationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrimsCreationMaster>>) {
    result.subscribe((res: HttpResponse<ITrimsCreationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackTrimsTemplateMasterById(index: number, item: ITrimsTemplateMaster) {
    return item.id;
  }

  trimsCoding() {
    let description = '';
    if (this.trimsCreationMaster.trimsTemplateMaster !== undefined && this.trimsCreationMaster.trimsTemplateMaster !== undefined) {
      description += this.trimsCreationMaster.trimsTemplateMaster.description;
      if (
        this.trimsCreationMaster.trimsTemplateMaster.trimsTemplateDetails !== undefined &&
        this.trimsCreationMaster.trimsTemplateMaster.trimsTemplateDetails !== null
      ) {
        this.trimsCreationMaster.trimsTemplateMaster.trimsTemplateDetails.forEach(trimsTemplateDetail => {
          if (
            (trimsTemplateDetail.fieldValue !== undefined &&
              trimsTemplateDetail.fieldValue != null &&
              trimsTemplateDetail.fieldValue.length > 0) ||
            (trimsTemplateDetail.fieldValueDropDown !== undefined &&
              trimsTemplateDetail.fieldValueDropDown != null &&
              trimsTemplateDetail.fieldValueDropDown.length > 0)
          ) {
            if (trimsTemplateDetail.fieldType !== undefined && trimsTemplateDetail.fieldType === 'T') {
              if (trimsTemplateDetail.display && trimsTemplateDetail.display === true) {
                description += '..' + trimsTemplateDetail.fieldValue;
              } else {
                description += '..' + trimsTemplateDetail.specification + '=' + trimsTemplateDetail.fieldValue;
              }
            } else {
              trimsTemplateDetail.trimTemplateDetailsBreakup.forEach(trimTemplateDetailsBreakup => {
                if (trimTemplateDetailsBreakup.id.id === Number(trimsTemplateDetail.fieldValueDropDown)) {
                  if (trimsTemplateDetail.display && trimsTemplateDetail.display === true) {
                    description += '..' + trimTemplateDetailsBreakup.description;
                  } else {
                    description += '..' + trimsTemplateDetail.specification + '=' + trimTemplateDetailsBreakup.description;
                  }
                }
              });
            }
          }
        });
      }
    }
    this.trimsCreationMaster.description = description;
  }
}
