import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';
import { TrimsTemplateMasterService } from './trims-template-master.service';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { ITrimsTemplateDetails, TrimsTemplateDetails } from 'app/shared/model/trims-template-details.model';
import { TrimsTemplateDetailsBreakup } from 'app/shared/model/trims-template-details-breakup.model';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-trims-template-master-update',
  templateUrl: './trims-template-master-update.component.html'
})
export class TrimsTemplateMasterUpdateComponent implements OnInit {
  trimsTemplateMaster: ITrimsTemplateMaster;
  isSaving: boolean;
  candidateMaster: any;
  totalSal: string;

  constructor(
    protected trimsTemplateMasterService: TrimsTemplateMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    // this.trimsTemplateMaster = new TrimsTemplateMaster();
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ trimsTemplateMaster }) => {
      this.trimsTemplateMaster = trimsTemplateMaster;
      if (
        this.trimsTemplateMaster !== null &&
        this.trimsTemplateMaster.id !== undefined &&
        this.trimsTemplateMaster.trimsTemplateDetails &&
        this.trimsTemplateMaster.trimsTemplateDetails.length > 0
      ) {
        this.trimsTemplateMaster.trimsTemplateDetails.forEach(trimsTemplateDetail => {
          let breakupValue = '';
          if (trimsTemplateDetail.trimTemplateDetailsBreakup) {
            trimsTemplateDetail.trimTemplateDetailsBreakup.forEach(trimTemplateDetailsBreakup => {
              if (trimTemplateDetailsBreakup.description != null && trimTemplateDetailsBreakup.description !== undefined) {
                if (breakupValue.length > 0) {
                  breakupValue += ',' + trimTemplateDetailsBreakup.description;
                } else {
                  breakupValue += trimTemplateDetailsBreakup.description;
                }
              }
            });
          }
          trimsTemplateDetail.fieldValue = breakupValue;
        });
      } else {
        this.loadAll();
      }
    });
  }

  loadAll() {
    this.trimsTemplateMaster.trimsTemplateDetails = [];
    for (let i = 0; i < 5; i++) {
      const trimsTemplateMaster = new TrimsTemplateDetails();
      trimsTemplateMaster.display = true;
      trimsTemplateMaster.required = true;
      this.trimsTemplateMaster.trimsTemplateDetails.push(trimsTemplateMaster);
    }
  }

  addRow() {
    if (this.trimsTemplateMaster.trimsTemplateDetails) {
      const trimsTemplateMaster = new TrimsTemplateDetails();
      trimsTemplateMaster.display = true;
      trimsTemplateMaster.required = true;
      this.trimsTemplateMaster.trimsTemplateDetails.push(trimsTemplateMaster);
    } else {
      this.trimsTemplateMaster.trimsTemplateDetails = [];
      const trimsTemplateMaster = new TrimsTemplateDetails();
      trimsTemplateMaster.display = true;
      trimsTemplateMaster.required = true;
      this.trimsTemplateMaster.trimsTemplateDetails.push(trimsTemplateMaster);
    }
  }

  removeRow(index: any) {
    if (this.trimsTemplateMaster.trimsTemplateDetails[index].id !== undefined) {
      this.deleteRow(this.trimsTemplateMaster.trimsTemplateDetails[index].id, index);
    } else {
      this.trimsTemplateMaster.trimsTemplateDetails.splice(index, 1);
    }
  }

  deleteRow(id, index) {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.delete(toast, id, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  delete(toast, id, index) {
    this.trimsTemplateMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.trimsTemplateMaster.trimsTemplateDetails.splice(index, 1);
    });
  }

  previousState() {
    window.history.back();
  }

  selected(index: any) {
/*    if (this.trimsTemplateMaster.trimsTemplateDetails[index].fieldType === 'D') {
    }
    if (this.trimsTemplateMaster.trimsTemplateDetails[index].fieldType === 'T') {
    }*/
  }

  save() {
    this.isSaving = true;
    if (this.trimsTemplateMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.trimsTemplateMasterService.update(this.trimsTemplateMaster));
    } else {
      this.subscribeToSaveResponse(this.trimsTemplateMasterService.create(this.trimsTemplateMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrimsTemplateMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  addColumn(index: number) {
    this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup.push(new TrimsTemplateDetailsBreakup());
  }

  addText(index: number) {
    this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup = new Array<TrimsTemplateDetailsBreakup>();
    for (let j = 0; j < 5; j++) {
      this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup.push(new TrimsTemplateDetailsBreakup());
    }
  }

  removeText(index: number, index1: number) {
    if (
      this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup[index1].id !== undefined &&
      this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup[index1].id !== null
    ) {
      this.deleteBreakUpRow(
        this.trimsTemplateMaster.trimsTemplateDetails[index].id,
        this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup[index1].id,
        index,
        index1
      );
    } else {
      this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup.splice(index1, 1);
      this.getTotalDetails(this.trimsTemplateMaster.trimsTemplateDetails[index]);
    }
  }

  deleteBreakUpRow(did, id, index, index1) {
    this.snotifyService.confirm('Are you sure to delete breakup?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.deleteBreakUp(toast, did, id, index, index1), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  deleteBreakUp(toast, did, id, index, index1) {
    this.trimsTemplateMasterService.deleteBreakUp(id.id, did).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.trimsTemplateMaster.trimsTemplateDetails[index].trimTemplateDetailsBreakup.splice(index1, 1);
      this.getTotalDetails(this.trimsTemplateMaster.trimsTemplateDetails[index]);
    });
  }

  getTotalDetails(trimsTemplateDetail: ITrimsTemplateDetails) {
    let totalVal = '';
    if (trimsTemplateDetail.trimTemplateDetailsBreakup) {
      trimsTemplateDetail.trimTemplateDetailsBreakup.forEach(templateDetailsBreakup => {
        if (totalVal.length === 0) {
          if (templateDetailsBreakup.description) {
            totalVal += templateDetailsBreakup.description.toUpperCase();
          }
        } else {
          if (templateDetailsBreakup.description) {
            totalVal = totalVal + ',' + templateDetailsBreakup.description.toUpperCase();
          }
        }
      });
    }
    trimsTemplateDetail.fieldValue = totalVal;
  }

  expand(trimtemplateMasterDetail: ITrimsTemplateDetails, isExpanded, index: number) {
    trimtemplateMasterDetail.expend = isExpanded;
    if (trimtemplateMasterDetail.trimTemplateDetailsBreakup !== undefined || trimtemplateMasterDetail.trimTemplateDetailsBreakup !== null) {
      this.addText(index);
    }
  }
}
