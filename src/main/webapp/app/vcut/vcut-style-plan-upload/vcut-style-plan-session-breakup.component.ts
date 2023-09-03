import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { VcutSessionMasterService } from 'app/vcut/vcut-session-master';
import { IVcutSessionDetails, VcutSessionDetails } from 'app/shared/model/vcut-session-details.model';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { IVcutStylePlanSessionBreakup } from 'app/shared/model/vcut-style-plan-session-breakup.model';

@Component({
  selector: 'jhi-vcut-style-plan-session-breakup',
  templateUrl: './vcut-style-plan-session-breakup.component.html'
})
export class VcutStylePlanSessionBreakupComponent implements OnInit {
  vcutSessionMasters: IVcutSessionMaster[];
  vcutStylePlanUpload: IVcutStylePlanUpload;
  saveSession: boolean;
  isSaving: boolean;
  selectVcutSessionMaster: IVcutSessionMaster;
  mask = [/[0-2]/, /[0-9]/, ':', /[0-5]/, /[0-9]/];

  constructor(
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected snotifyService: SnotifyService,
    public vcutSessionMasterService: VcutSessionMasterService
  ) {}

  ngOnInit() {
    this.vcutSessionMasterService.query().subscribe(vcutSessionMasters => {
      this.vcutSessionMasters = vcutSessionMasters.body;
      if (this.vcutStylePlanUpload && this.vcutStylePlanUpload.vcutSessionMasterId && this.vcutSessionMasters) {
        this.saveSession = true;
        let ctr = 0;
        this.vcutSessionMasters.forEach(vcutSessionMaster => {
          ++ctr;
          if (vcutSessionMaster.id === this.vcutStylePlanUpload.vcutSessionMasterId) {
            this.selectVcutSessionMaster = vcutSessionMaster;
          }
        });
        if (ctr === this.vcutSessionMasters.length) {
          this.selectVcutSessionMaster.vcutSessionDetails = [];
          this.vcutSessionMasterService
            .findBreakup(this.vcutStylePlanUpload.id)
            .subscribe((res: HttpResponse<IVcutStylePlanSessionBreakup[]>) => {
              let vcutSessionDtls: IVcutStylePlanSessionBreakup[] = [];
              vcutSessionDtls = res.body;
              vcutSessionDtls.forEach(vcutStylePlanSessionBreakup => {
                const vcutSessionDetails = new VcutSessionDetails();
                vcutSessionDetails.startTime = vcutStylePlanSessionBreakup.id.startTime;
                vcutSessionDetails.endTime = vcutStylePlanSessionBreakup.endTime;
                vcutSessionDetails.type = vcutStylePlanSessionBreakup.type;
                vcutSessionDetails.duration = vcutStylePlanSessionBreakup.duration;
                vcutSessionDetails.order = vcutStylePlanSessionBreakup.order;
                vcutSessionDetails.cumulativeMins = vcutStylePlanSessionBreakup.cumulativeMins;
                vcutSessionDetails.planQuantity = vcutStylePlanSessionBreakup.planQuantity;
                vcutSessionDetails.sessionId = this.vcutStylePlanUpload.vcutSessionMasterId;
                vcutSessionDetails.vcutStylePlanUploadId = vcutStylePlanSessionBreakup.id.vcutStylePlanUploadId;
                this.selectVcutSessionMaster.vcutSessionDetails.push(vcutSessionDetails);
              });
            });
        }
      }
    });
  }

  save() {
    this.isSaving = true;
    let index = 0;
    let planQuantity = 0;
    this.selectVcutSessionMaster.vcutSessionDetails.forEach(vcutSessionDetail => {
      ++index;
      planQuantity += vcutSessionDetail.planQuantity;
    });
    if (planQuantity === this.vcutStylePlanUpload.quantity && index === this.selectVcutSessionMaster.vcutSessionDetails.length) {
      this.subscribeToSaveResponse(this.vcutSessionMasterService.saveBreakup(this.selectVcutSessionMaster.vcutSessionDetails));
    } else {
      this.snotifyService.error('Planned Quantity must not be greator/ less than ' + this.vcutStylePlanUpload.quantity, '', toastConfig);
      this.isSaving = false;
    }
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }

  changeSession() {
    if (this.vcutStylePlanUpload.vcutSessionMasterId) {
      this.vcutSessionMasterService.find(this.vcutStylePlanUpload.vcutSessionMasterId).subscribe(vcutSessionMaster => {
        this.selectVcutSessionMaster = vcutSessionMaster.body;
        let minutes = 0;
        let ctr = 0;
        this.selectVcutSessionMaster.vcutSessionDetails.forEach(vcutsessionDetail => {
          ++ctr;
          vcutsessionDetail.sessionId = this.vcutStylePlanUpload.vcutSessionMasterId;
          vcutsessionDetail.vcutStylePlanUploadId = this.vcutStylePlanUpload.id;
          if (vcutsessionDetail.displayFlag === '1') {
            minutes += vcutsessionDetail.duration;
          }
        });
        if (ctr === this.selectVcutSessionMaster.vcutSessionDetails.length) {
          const percentage = this.vcutStylePlanUpload.quantity / minutes;
          ctr = 0;
          let totPlanValue = 0;
          this.selectVcutSessionMaster.vcutSessionDetails.forEach(vcutsessionDetail => {
            ++ctr;
            if (ctr === this.selectVcutSessionMaster.vcutSessionDetails.length) {
              if (vcutsessionDetail.displayFlag === '1') {
                vcutsessionDetail.planQuantity = this.vcutStylePlanUpload.quantity - totPlanValue;
              } else {
                vcutsessionDetail.planQuantity = 0;
              }
            } else {
              if (vcutsessionDetail.displayFlag === '1') {
                const planValue = Math.floor(vcutsessionDetail.duration * percentage);
                totPlanValue += planValue;
                vcutsessionDetail.planQuantity = planValue;
              } else {
                vcutsessionDetail.planQuantity = 0;
              }
            }
          });
        }
      });
    }
  }

  addRow(index: number) {
    const vcutSessionDetailsList: IVcutSessionDetails[] = this.selectVcutSessionMaster.vcutSessionDetails.map(x => Object.assign({}, x));
    const vcutSessionDetail = Object.assign({}, vcutSessionDetailsList[index]);
    const vcutSessionDetailNext = Object.assign({}, vcutSessionDetailsList[index + 1]);
    vcutSessionDetailsList.splice(index, 0, Object.assign({}, new VcutSessionDetails()));
    // vcutSessionDetailsList.splice(index + 1, 1, vcutSessionDetail, vcutSessionDetailNext);
    let i = 0;
    this.selectVcutSessionMaster.vcutSessionDetails = [];
    vcutSessionDetailsList.forEach(vcutSessionDetailT => {
      i = i + 1;
      vcutSessionDetailT.order = i;
      vcutSessionDetailT.sessionId = this.vcutStylePlanUpload.vcutSessionMasterId;
      vcutSessionDetailT.vcutStylePlanUploadId = this.vcutStylePlanUpload.id;
      this.selectVcutSessionMaster.vcutSessionDetails.push(vcutSessionDetailT);
    });
  }

  removeRow(index: any) {
    this.selectVcutSessionMaster.vcutSessionDetails.splice(index, 1);
    let i = 0;
    this.selectVcutSessionMaster.vcutSessionDetails.forEach(vcutSessionDetailT => {
      i = i + 1;
      vcutSessionDetailT.order = i;
    });
  }

  calTimeDifference(vcutSessionDetailTemp: VcutSessionDetails) {
    let value = 0;
    if (vcutSessionDetailTemp.startTime && vcutSessionDetailTemp.endTime) {
      const startTemp = vcutSessionDetailTemp.startTime.split(':');
      const endTemp = vcutSessionDetailTemp.endTime.split(':');
      const timeTemp1 = parseInt(startTemp[0], 10) * 60 + parseInt(startTemp[1], 10);
      const timeTemp2 = parseInt(endTemp[0], 10) * 60 + parseInt(endTemp[1], 10);
      const timeTemp3 = timeTemp2 - timeTemp1;
      vcutSessionDetailTemp.duration = timeTemp3;
    }

    const vcutSessionDetailsList: IVcutSessionDetails[] = this.selectVcutSessionMaster.vcutSessionDetails.map(x => Object.assign({}, x));

    let tempStart = undefined;
    let rootIndex = 0;
    vcutSessionDetailsList.forEach((vcutSessionDetail, index) => {
      ++rootIndex;
      if (index === 0) {
        if (vcutSessionDetail.startTime && vcutSessionDetail.endTime && vcutSessionDetail.duration) {
          const start = vcutSessionDetail.startTime.split(':');
          const end = vcutSessionDetail.endTime.split(':');
          const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
          const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
          const time3 = time2 - time1;
          vcutSessionDetail.duration = time3;
          value += time3;
          vcutSessionDetail.cumulativeMins = value;

          tempStart = vcutSessionDetail.endTime;
        }
      } else {
        if (vcutSessionDetail.startTime && vcutSessionDetail.endTime && vcutSessionDetail.duration) {
          if (tempStart === undefined) {
            const start = vcutSessionDetail.startTime.split(':');
            const end = vcutSessionDetail.endTime.split(':');
            const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
            const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
            const time3 = time2 - time1;
            vcutSessionDetail.duration = time3;
            value += time3;
            vcutSessionDetail.cumulativeMins = value;
          } else {
            if (!vcutSessionDetail.duration || vcutSessionDetail.duration === null) {
              const start = vcutSessionDetail.startTime.split(':');
              const end = vcutSessionDetail.endTime.split(':');
              const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
              const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
              const time3 = time2 - time1;
              vcutSessionDetail.duration = time3;
            }
            vcutSessionDetail.startTime = vcutSessionDetailsList[index - 1].endTime;
            const time = vcutSessionDetail.startTime.split(':');
            const valueOld = Number(time[1]) + (vcutSessionDetail.duration % 60);
            if (valueOld >= 60) {
              const valueNew = valueOld % 60;
              const pre = Number(time[0]) + Math.trunc(vcutSessionDetail.duration / 60);
              const preNew = pre + Math.trunc(valueNew / 60);
              vcutSessionDetail.endTime = preNew + ':' + (valueNew > 9 ? valueNew : '0' + valueNew);

              const start = tempStart.split(':');
              const end = vcutSessionDetail.endTime.split(':');
              const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
              const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
              const time3 = time2 - time1;
              vcutSessionDetail.duration = time3;
              value += time3;
              vcutSessionDetail.cumulativeMins = value;

              tempStart = vcutSessionDetail.endTime;
              console.log(vcutSessionDetail);
            } else {
              const pre = Number(time[0]) + Math.trunc(vcutSessionDetail.duration / 60);

              vcutSessionDetail.endTime = pre + ':' + (valueOld > 9 ? valueOld : '0' + valueOld);

              const start = tempStart.split(':');
              const end = vcutSessionDetail.endTime.split(':');
              const time1 = parseInt(start[0], 10) * 60 + parseInt(start[1], 10);
              const time2 = parseInt(end[0], 10) * 60 + parseInt(end[1], 10);
              const time3 = time2 - time1;
              vcutSessionDetail.duration = time3;
              value += time3;
              vcutSessionDetail.cumulativeMins = value;

              tempStart = vcutSessionDetail.endTime;
            }
          }
        }
      }
    });
    if (rootIndex === vcutSessionDetailsList.length) {
      this.selectVcutSessionMaster.vcutSessionDetails = [];
      vcutSessionDetailsList.forEach(vcutSessionDetailsTemp => {
        vcutSessionDetailsTemp.sessionId = this.vcutStylePlanUpload.vcutSessionMasterId;
        vcutSessionDetailsTemp.vcutStylePlanUploadId = this.vcutStylePlanUpload.id;
        this.selectVcutSessionMaster.vcutSessionDetails.push(vcutSessionDetailsTemp);
      });
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutStylePlanSessionBreakup[]>>) {
    result.subscribe(
      (res: HttpResponse<IVcutStylePlanSessionBreakup[]>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess(res: HttpResponse<IVcutStylePlanSessionBreakup[]>) {
    this.selectVcutSessionMaster.vcutSessionDetails = [];
    let vcutSessionDtls: IVcutStylePlanSessionBreakup[] = [];
    vcutSessionDtls = res.body;
    vcutSessionDtls.forEach(vcutStylePlanSessionBreakup => {
      const vcutSessionDetails = new VcutSessionDetails();
      vcutSessionDetails.startTime = vcutStylePlanSessionBreakup.id.startTime;
      vcutSessionDetails.endTime = vcutStylePlanSessionBreakup.endTime;
      vcutSessionDetails.type = vcutStylePlanSessionBreakup.type;
      vcutSessionDetails.duration = vcutStylePlanSessionBreakup.duration;
      vcutSessionDetails.order = vcutStylePlanSessionBreakup.order;
      vcutSessionDetails.cumulativeMins = vcutStylePlanSessionBreakup.cumulativeMins;
      vcutSessionDetails.planQuantity = vcutStylePlanSessionBreakup.planQuantity;
      vcutSessionDetails.sessionId = this.vcutStylePlanUpload.vcutSessionMasterId;
      vcutSessionDetails.vcutStylePlanUploadId = vcutStylePlanSessionBreakup.id.vcutStylePlanUploadId;
      this.selectVcutSessionMaster.vcutSessionDetails.push(vcutSessionDetails);
    });
    this.snotifyService.success('Save Successfully!', '', toastConfig);
    this.isSaving = false;
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
