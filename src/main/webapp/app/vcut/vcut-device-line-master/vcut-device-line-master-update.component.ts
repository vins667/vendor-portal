import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IVcutDeviceLineMaster, VcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { VcutDeviceLineMasterService } from './vcut-device-line-master.service';
import { VcutDeviceLineMasterSearchComponent } from './vcut-device-line-master-search.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { IVcutUserDeviceMaster } from 'app/shared/model/vcut-user-device-master.model';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-vcut-device-line-master-update',
  templateUrl: './vcut-device-line-master-update.component.html'
})
export class VcutDeviceLineMasterUpdateComponent implements OnInit {
  vcutDeviceLineMaster: IVcutDeviceLineMaster;
  protected ngbModalRef: NgbModalRef;
  data: string;
  isSaving: boolean;
  assetMaster: any;
  currentAssetAuditDetails: any;

  constructor(
    protected vcutDeviceLineMasterService: VcutDeviceLineMasterService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder,
    private eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.vcutDeviceLineMaster = new VcutDeviceLineMaster();
    this.registerChangeVcutDeviceLineMaster();
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vcutDeviceLineMaster }) => {
      this.vcutDeviceLineMaster = vcutDeviceLineMaster;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.vcutDeviceLineMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutDeviceLineMasterService.update(this.vcutDeviceLineMaster));
    } else {
      this.subscribeToSaveResponse(this.vcutDeviceLineMasterService.create(this.vcutDeviceLineMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutDeviceLineMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res.headers));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  searchEmp() {
    this.ngbModalRef = this.modalService.open(VcutDeviceLineMasterSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'sxlModal'
    });
  }

  registerChangeVcutDeviceLineMaster() {
    this.eventManager.subscribe('selectedEmployeeList', message => {
      const vcutUserDeviceMaster: IVcutUserDeviceMaster[] = message.content;
      if (this.vcutDeviceLineMaster.vcutUserDeviceMaster) {
        vcutUserDeviceMaster.forEach(cutUserDeviceMaster => {
          let exist = false;
          let ctr = 0;
          this.vcutDeviceLineMaster.vcutUserDeviceMaster.forEach(deviceMaster => {
            ++ctr;
            if (deviceMaster.user.id === cutUserDeviceMaster.user.id) {
              exist = true;
            }
          });
          if (this.vcutDeviceLineMaster.vcutUserDeviceMaster.length === ctr && exist === false) {
            this.vcutDeviceLineMaster.vcutUserDeviceMaster.push(cutUserDeviceMaster);
          }
        });
      } else {
        this.vcutDeviceLineMaster.vcutUserDeviceMaster = [];
        this.vcutDeviceLineMaster.vcutUserDeviceMaster = vcutUserDeviceMaster;
      }
    });
  }

  removeRow(index: any) {
    if (
      this.vcutDeviceLineMaster.vcutUserDeviceMaster[index].id !== null &&
      this.vcutDeviceLineMaster.vcutUserDeviceMaster[index].id !== undefined &&
      this.vcutDeviceLineMaster.id !== undefined
    ) {
      this.deleteRow(this.vcutDeviceLineMaster.vcutUserDeviceMaster[index].id, index);
    } else {
      this.vcutDeviceLineMaster.vcutUserDeviceMaster.splice(index, 1);
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
    this.vcutDeviceLineMasterService.deleteDetail(id).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.vcutDeviceLineMaster.vcutUserDeviceMaster.splice(index, 1);
    });
  }
}
