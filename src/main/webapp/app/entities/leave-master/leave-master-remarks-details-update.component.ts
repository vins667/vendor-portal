import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ILeaveMasterRemarksDetails, LeaveMasterRemarksDetails } from 'app/shared/model/leave-master-remarks-details.model';
import { ILeaveMasterRemarksDetailsBean } from 'app/shared/model/leave-master-remarks-details-bean.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { LeaveMasterService } from './leave-master.service';
import { IMaster, Master } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-leave-remarks-details-update',
  templateUrl: './leave-master-remarks-details-update.component.html'
})
export class LeaveRemarksDetailsUpdateComponent implements OnInit {
  leaveMasterRemarksDetails: ILeaveMasterRemarksDetailsBean;
  isSaving: boolean;
  masters: IMaster[];
  currentLeaveMasterRemarksDetail: ILeaveMasterRemarksDetails;
  constructor(
    public activeModal: NgbActiveModal,
    protected jhiAlertService: JhiAlertService,
    protected leaveMasterService: LeaveMasterService,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.currentLeaveMasterRemarksDetail = new LeaveMasterRemarksDetails();
    this.currentLeaveMasterRemarksDetail.empCode = this.leaveMasterRemarksDetails.empCode;
    this.currentLeaveMasterRemarksDetail.empName = this.leaveMasterRemarksDetails.empName;
    this.currentLeaveMasterRemarksDetail.status = this.leaveMasterRemarksDetails.status;
    this.currentLeaveMasterRemarksDetail.id = this.leaveMasterRemarksDetails.id;
    this.currentLeaveMasterRemarksDetail.leaveMaster = this.leaveMasterRemarksDetails.leaveMaster;

    if (
      this.leaveMasterRemarksDetails &&
      this.leaveMasterRemarksDetails.statusList &&
      this.leaveMasterRemarksDetails.statusList.length > 0
    ) {
      this.currentLeaveMasterRemarksDetail.status = this.leaveMasterRemarksDetails.statusList[0].id;
      this.onChangeAuthType(this.currentLeaveMasterRemarksDetail.status);
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.leaveMasterService.updateRemarks(this.currentLeaveMasterRemarksDetail));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeaveMasterRemarksDetailsBean>>) {
    result.subscribe(
      (res: HttpResponse<ILeaveMasterRemarksDetailsBean>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess(result: HttpResponse<ILeaveMasterRemarksDetailsBean>) {
    this.isSaving = false;
    this.eventManager.broadcast({ name: 'leaveMasterStatusModification' });
    this.leaveMasterRemarksDetails = result.body;
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }

  isValidForm() {
    if (this.leaveMasterRemarksDetails && this.leaveMasterRemarksDetails.allowEntry === true) {
      return false;
    } else {
      return true;
    }
  }

  public onChangeAuthType(val) {
    const newVal = val;
    if (newVal !== null && newVal === 'Q') {
      this.currentLeaveMasterRemarksDetail.forwardCode = this.leaveMasterRemarksDetails.createdBy;
      this.currentLeaveMasterRemarksDetail.forwardName = this.leaveMasterRemarksDetails.createdName;
      this.masters = [];
      const master = new Master();
      master.id = this.leaveMasterRemarksDetails.createdBy;
      master.desc = this.leaveMasterRemarksDetails.createdName;
      this.masters.push(master);
    }
    if (newVal !== null && newVal === 'E') {
      this.currentLeaveMasterRemarksDetail.forwardCode = this.leaveMasterRemarksDetails.hodApprovedBy;
      this.currentLeaveMasterRemarksDetail.forwardName = this.leaveMasterRemarksDetails.hodApprovedName;
      this.masters = [];
      const master = new Master();
      master.id = this.leaveMasterRemarksDetails.hodApprovedBy;
      master.desc = this.leaveMasterRemarksDetails.hodApprovedName;
      this.masters.push(master);
    } else {
      this.currentLeaveMasterRemarksDetail.forwardCode = this.leaveMasterRemarksDetails.empCode;
      this.currentLeaveMasterRemarksDetail.forwardName = this.leaveMasterRemarksDetails.empName;
      this.masters = [];
      const master = new Master();
      master.id = this.leaveMasterRemarksDetails.empCode;
      master.desc = this.leaveMasterRemarksDetails.empName;
      this.masters.push(master);
    }
  }
}
