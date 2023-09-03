import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITemplateMaster } from 'app/shared/model/template-master.model';
import { TemplateMasterService } from './template-master.service';
import { JhiEventManager } from 'ng-jhipster';
import { ICategoryMaster } from 'app/shared/model/category-master.model';
import { TemplateDetails } from 'app/shared/model/template-details.model';
import { CategoryMasterService } from 'app/vendorportal/category-master';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-template-master-update',
  templateUrl: './template-master-update.component.html'
})
export class TemplateMasterUpdateComponent implements OnInit {
  templateMaster: ITemplateMaster;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  modalService: any;
  router: any;
  categorymasters: ICategoryMaster[];

  constructor(
    protected templateMasterService: TemplateMasterService,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected categoryMasterService: CategoryMasterService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ templateMaster }) => {
      this.templateMaster = templateMaster;
      if (this.templateMaster.templateDetails && this.templateMaster.templateDetails.length > 0) {
        this.templateMaster.templateDetails.forEach(templateDetail => {
          if (templateDetail.templateDetailsBreakups) {
            let values = '';
            templateDetail.templateDetailsBreakups.forEach((breakUp, i) => {
              if (i === 0) {
                values = breakUp.description;
              } else {
                values += ', ' + breakUp.description;
              }
            });
            templateDetail.fieldValue = values;
          }
        });
      } else {
        this.loadAll();
      }
    });
    this.categoryMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategoryMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategoryMaster[]>) => response.body)
      )
      .subscribe((res: ICategoryMaster[]) => (this.categorymasters = res));
  }

  loadAll() {
    this.templateMaster.templateDetails = [];
    for (let i = 0; i < 5; i++) {
      this.templateMaster.templateDetails.push(new TemplateDetails());
    }
  }
  addRow() {
    if (this.templateMaster.templateDetails) {
      this.templateMaster.templateDetails.push(new TemplateDetails());
    } else {
      this.templateMaster.templateDetails = [];
      this.templateMaster.templateDetails.push(new TemplateDetails());
    }
  }

  removeRow(index: any) {
    if (this.templateMaster.templateDetails[index].id !== undefined) {
      this.deleteRow(this.templateMaster.templateDetails[index].id, index);
    } else {
      this.templateMaster.templateDetails.splice(index, 1);
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
    this.templateMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.templateMaster.templateDetails.splice(index, 1);
    });
  }

  /* viewRow(index: any) {
    const fieldValue = this.templateMaster.templateDetails[index].fieldValue;
    let arr = fieldValue.split(',');
  } */

  previousState() {
    window.history.back();
  }

  selected(index: any) {
    /* if (this.templateMaster.templateDetails[index].fieldType === 'D') {
    }
    if (this.templateMaster.templateDetails[index].fieldType === 'T') {
    } */
  }

  save() {
    this.isSaving = true;
    // this.templateMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    // this.templateMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.templateMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.templateMasterService.update(this.templateMaster));
    } else {
      this.subscribeToSaveResponse(this.templateMasterService.create(this.templateMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITemplateMaster>>) {
    result.subscribe((res: HttpResponse<ITemplateMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
