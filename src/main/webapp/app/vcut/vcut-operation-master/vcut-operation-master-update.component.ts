import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutOperationMaster, VcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { VcutOperationMasterService } from './vcut-operation-master.service';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';

@Component({
  selector: 'jhi-vcut-operation-master-update',
  templateUrl: './vcut-operation-master-update.component.html'
})
export class VcutOperationMasterUpdateComponent implements OnInit {
  vcutOperationMaster: IVcutOperationMaster;
  isSaving: boolean;
  constructor(
    protected vcutOperationMasterService: VcutOperationMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.loadAll();
  }

  loadAll() {
    this.activatedRoute.data.subscribe(({ vcutOperationMaster }) => {
      this.vcutOperationMaster = vcutOperationMaster;
      if (
        this.vcutOperationMaster &&
        this.vcutOperationMaster.vcutOperationMasters &&
        this.vcutOperationMaster.vcutOperationMasters.length > 0
      ) {
      } else {
        this.vcutOperationMaster.vcutOperationMasters = [];
        for (let i = 0; i < 10; i++) {
          this.vcutOperationMaster.vcutOperationMasters.push(new VcutOperationMaster());
        }
      }
    });
  }

  addRow() {
    if (this.vcutOperationMaster && this.vcutOperationMaster.vcutOperationMasters) {
      const vcutOperationMaster = new VcutOperationMaster();
      this.vcutOperationMaster.vcutOperationMasters.push(vcutOperationMaster);
    } else {
      this.vcutOperationMaster.vcutOperationMasters = [];
      const vcutOperationMaster = new VcutOperationMaster();
      this.vcutOperationMaster.vcutOperationMasters.push(vcutOperationMaster);
    }
  }

  removeRow(index: any) {
    if (this.vcutOperationMaster.vcutOperationMasters[index].id !== undefined) {
      this.deleteRow(this.vcutOperationMaster.vcutOperationMasters[index].id, index);
    } else {
      this.vcutOperationMaster.vcutOperationMasters.splice(index, 1);
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
    this.vcutOperationMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.vcutOperationMaster.vcutOperationMasters.splice(index, 1);
    });
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.vcutOperationMasterService.create(this.vcutOperationMaster));
  }

  previousState() {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutOperationMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
