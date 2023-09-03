import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';
import { WorkerJoinFlowMasterService } from './worker-join-flow-master.service';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from 'app/entities/worker-forward-type-master';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { WorkerJoinFlowSearchComponent } from './worker-join-flow-search.component';

@Component({
  selector: 'jhi-worker-join-flow-master-update',
  templateUrl: './worker-join-flow-master-update.component.html'
})
export class WorkerJoinFlowMasterUpdateComponent implements OnInit {
  workerJoinFlowMaster: IWorkerJoinFlowMaster;
  isSaving: boolean;

  workerforwardtypemasters: IWorkerForwardTypeMaster[];
  createdDate: string;
  lastUpdatedDate: string;
  userType: string;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected workerJoinFlowMasterService: WorkerJoinFlowMasterService,
    protected workerForwardTypeMasterService: WorkerForwardTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInSearchUser();
    this.activatedRoute.data.subscribe(({ workerJoinFlowMaster }) => {
      this.workerJoinFlowMaster = workerJoinFlowMaster;
      if (this.workerJoinFlowMaster && workerJoinFlowMaster.empName && workerJoinFlowMaster.forwardName) {
        this.workerJoinFlowMaster.empCodeName = workerJoinFlowMaster.empName + '(' + workerJoinFlowMaster.empCode + ')';
        this.workerJoinFlowMaster.forwardCodeName = workerJoinFlowMaster.forwardName + '(' + workerJoinFlowMaster.forwardCode + ')';
      }
      this.createdDate =
        this.workerJoinFlowMaster.createdDate != null ? this.workerJoinFlowMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.workerJoinFlowMaster.lastUpdatedDate != null ? this.workerJoinFlowMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.workerForwardTypeMasterService.query().subscribe(
      (res: HttpResponse<IWorkerForwardTypeMaster[]>) => {
        this.workerforwardtypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  searchEmployee(type) {
    this.userType = type;
    this.ngbModalRef = this.modalService.open(WorkerJoinFlowSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('selectedUserLinkJoinCreation', message => {
      const userDetails = message.content;
      if (this.userType === 'F') {
        this.workerJoinFlowMaster.empCode = userDetails.cardNo;
        this.workerJoinFlowMaster.empName = userDetails.name;
        this.workerJoinFlowMaster.empCodeName = userDetails.name + '(' + userDetails.cardNo + ')';
      } else {
        this.workerJoinFlowMaster.forwardCode = userDetails.cardNo;
        this.workerJoinFlowMaster.forwardName = userDetails.name;
        this.workerJoinFlowMaster.forwardCodeName = userDetails.name + '(' + userDetails.cardNo + ')';
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.workerJoinFlowMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.workerJoinFlowMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (
      this.workerJoinFlowMaster.workerForwardTypeMaster &&
      (this.workerJoinFlowMaster.workerForwardTypeMaster.code === 'R' || this.workerJoinFlowMaster.workerForwardTypeMaster.code === 'A')
    ) {
      if (this.workerJoinFlowMaster.empCode !== this.workerJoinFlowMaster.forwardCode) {
        this.snotifyService.error('Emp Name and Forward Name must be same', '', toastConfig);
        this.isSaving = false;
      } else {
        if (this.workerJoinFlowMaster.id !== undefined) {
          this.subscribeToSaveResponse(this.workerJoinFlowMasterService.update(this.workerJoinFlowMaster));
        } else {
          this.subscribeToSaveResponse(this.workerJoinFlowMasterService.create(this.workerJoinFlowMaster));
        }
      }
    } else {
      if (this.workerJoinFlowMaster.empCode === this.workerJoinFlowMaster.forwardCode) {
        this.snotifyService.error('Emp Name and Forward Name must not be same', '', toastConfig);
        this.isSaving = false;
      } else {
        if (this.workerJoinFlowMaster.id !== undefined) {
          this.subscribeToSaveResponse(this.workerJoinFlowMasterService.update(this.workerJoinFlowMaster));
        } else {
          this.subscribeToSaveResponse(this.workerJoinFlowMasterService.create(this.workerJoinFlowMaster));
        }
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerJoinFlowMaster>>) {
    result.subscribe((res: HttpResponse<IWorkerJoinFlowMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWorkerForwardTypeMasterById(index: number, item: IWorkerForwardTypeMaster) {
    return item.id;
  }

  checkWorkflow() {
    if (
      this.workerJoinFlowMaster.workerForwardTypeMaster &&
      (this.workerJoinFlowMaster.workerForwardTypeMaster.code === 'R' || this.workerJoinFlowMaster.workerForwardTypeMaster.code === 'A')
    ) {
      if (this.workerJoinFlowMaster.empCode) {
        this.workerJoinFlowMaster.forwardCode = this.workerJoinFlowMaster.empCode;
        this.workerJoinFlowMaster.forwardName = this.workerJoinFlowMaster.empName;
        this.workerJoinFlowMaster.forwardCodeName = this.workerJoinFlowMaster.empCodeName;
      }
    } else {
      if (this.workerJoinFlowMaster.empCode === this.workerJoinFlowMaster.forwardCode) {
        this.workerJoinFlowMaster.forwardCode = undefined;
        this.workerJoinFlowMaster.forwardName = undefined;
        this.workerJoinFlowMaster.forwardCodeName = undefined;
      }
    }
  }
}
