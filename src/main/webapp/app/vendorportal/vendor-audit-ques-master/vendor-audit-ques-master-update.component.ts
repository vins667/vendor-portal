import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';
import { VendorAuditQuesMasterService } from './vendor-audit-ques-master.service';
import { VendorAuditQuesDetails } from 'app/shared/model/vendor-audit-ques-details.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { IAuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { AuditGroupMasterService } from 'app/vendorportal/audit-group-master/audit-group-master.service';
import {JhiAlertService} from 'ng-jhipster';

@Component({
    selector: 'jhi-vendor-audit-ques-master-update',
    templateUrl: './vendor-audit-ques-master-update.component.html'
})
export class VendorAuditQuesMasterUpdateComponent implements OnInit {
    vendorAuditQuesMaster: IVendorAuditQuesMaster;
    isSaving: boolean;
    createdDate: string;
    lastUpdatedDate: string;

    auditgroupmasters: IAuditGroupMaster[];

    constructor(protected vendorAuditQuesMasterService: VendorAuditQuesMasterService,
                protected activatedRoute: ActivatedRoute,
                protected snotifyService: SnotifyService,
                protected auditGroupMasterService: AuditGroupMasterService,
                protected jhiAlertService: JhiAlertService) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vendorAuditQuesMaster }) => {
            this.vendorAuditQuesMaster = vendorAuditQuesMaster;
            this.createdDate =
                this.vendorAuditQuesMaster.createdDate != null ? this.vendorAuditQuesMaster.createdDate.format(DATE_TIME_FORMAT) : null;
            this.lastUpdatedDate =
                this.vendorAuditQuesMaster.lastUpdatedDate != null
                    ? this.vendorAuditQuesMaster.lastUpdatedDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.auditGroupMasterService.query().subscribe((res: HttpResponse<IAuditGroupMaster[]>) => {
            this.auditgroupmasters = res.body;
          }, (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.vendorAuditQuesMaster && this.vendorAuditQuesMaster.vendorAuditQuesDetails === undefined || this.vendorAuditQuesMaster.vendorAuditQuesDetails === null) {
          this.vendorAuditQuesMaster.vendorAuditQuesDetails = [];
          for (let i = 0; i < 10; i++) {
            this.vendorAuditQuesMaster.vendorAuditQuesDetails.push(new VendorAuditQuesDetails());
          }
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.vendorAuditQuesMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        this.vendorAuditQuesMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
        if (this.vendorAuditQuesMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.vendorAuditQuesMasterService.update(this.vendorAuditQuesMaster));
        } else {
            this.subscribeToSaveResponse(this.vendorAuditQuesMasterService.create(this.vendorAuditQuesMaster));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorAuditQuesMaster>>) {
        result.subscribe(
            (res: HttpResponse<IVendorAuditQuesMaster>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
      }

    clearBox(index: number) {
      if (this.vendorAuditQuesMaster.vendorAuditQuesDetails[index].id !== undefined && this.vendorAuditQuesMaster.vendorAuditQuesDetails[index] !== null) {
        this.deleteRow(this.vendorAuditQuesMaster.vendorAuditQuesDetails[index].id, index);
      } else {
        this.vendorAuditQuesMaster.vendorAuditQuesDetails.splice(index, 1);
      }
    }

    addRow() {
      if (this.vendorAuditQuesMaster.vendorAuditQuesDetails) {
        this.vendorAuditQuesMaster.vendorAuditQuesDetails.push(new VendorAuditQuesDetails());
      } else {
        this.vendorAuditQuesMaster.vendorAuditQuesDetails = [];
        this.vendorAuditQuesMaster.vendorAuditQuesDetails.push(new VendorAuditQuesDetails());
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
          {text: 'Yes', action: toast => this.delete(toast, id, index), bold: false},
          {text: 'No', action: toast => this.snotifyService.remove(toast.id)}
        ]
      });
    }

    delete(toast, id, index) {
      this.vendorAuditQuesMasterService.deleteDetail(id).subscribe(any => {
        this.snotifyService.remove(toast.id);
        this.vendorAuditQuesMaster.vendorAuditQuesDetails.splice(index, 1);
      });
    }

    protected onError(errorMessage: string) {
      this.jhiAlertService.error(errorMessage, null, null);
    }
}
