import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { SnotifyService } from 'ng-snotify';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';
import { WorkerWorkFlowMasterService } from './worker-work-flow-master.service';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from 'app/entities/worker-forward-type-master';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { WorkerWorkFlowSearchComponent } from './worker-work-flow-search.component';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-worker-work-flow-master-update',
  templateUrl: './worker-work-flow-master-update.component.html'
})
export class WorkerWorkFlowMasterUpdateComponent implements OnInit, OnDestroy {
  workerWorkFlowMaster: IWorkerWorkFlowMaster;
  isSaving: boolean;
  protected ngbModalRef: NgbModalRef;

  workerforwardtypemasters: IWorkerForwardTypeMaster[];
  createdDate: string;
  lastUpdatedDate: string;
  userType: string;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected workerWorkFlowMasterService: WorkerWorkFlowMasterService,
    protected workerForwardTypeMasterService: WorkerForwardTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ workerWorkFlowMaster }) => {
      this.workerWorkFlowMaster = workerWorkFlowMaster;
      if (this.workerWorkFlowMaster && workerWorkFlowMaster.empName && workerWorkFlowMaster.forwardName) {
        this.workerWorkFlowMaster.empCodeName = workerWorkFlowMaster.empName + '(' + workerWorkFlowMaster.empCode + ')';
        this.workerWorkFlowMaster.forwardCodeName = workerWorkFlowMaster.forwardName + '(' + workerWorkFlowMaster.forwardCode + ')';
      }
      this.createdDate =
        this.workerWorkFlowMaster.createdDate != null ? this.workerWorkFlowMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.workerWorkFlowMaster.lastUpdatedDate != null ? this.workerWorkFlowMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.workerForwardTypeMasterService.query().subscribe(
      (res: HttpResponse<IWorkerForwardTypeMaster[]>) => {
        this.workerforwardtypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.registerChangeInSearchUser();
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }

  searchEmployee(type) {
    this.userType = type;
    this.ngbModalRef = this.modalService.open(WorkerWorkFlowSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('selectedUserLinkCreation', message => {
      const userDetails = message.content;
      if (this.userType === 'F') {
        this.workerWorkFlowMaster.empCode = userDetails.cardNo;
        this.workerWorkFlowMaster.empName = userDetails.name;
        this.workerWorkFlowMaster.empCodeName = userDetails.name + '(' + userDetails.cardNo + ')';
      } else {
        this.workerWorkFlowMaster.forwardCode = userDetails.cardNo;
        this.workerWorkFlowMaster.forwardName = userDetails.name;
        this.workerWorkFlowMaster.forwardCodeName = userDetails.name + '(' + userDetails.cardNo + ')';
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.workerWorkFlowMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.workerWorkFlowMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.workerWorkFlowMaster.workerForwardTypeMaster && this.workerWorkFlowMaster.workerForwardTypeMaster.code === 'R') {
      if (this.workerWorkFlowMaster.empCode !== this.workerWorkFlowMaster.forwardCode) {
        this.snotifyService.error('Emp Name and Forward Name must be same', '', toastConfig);
        this.isSaving = false;
      } else {
        if (this.workerWorkFlowMaster.id !== undefined) {
          this.subscribeToSaveResponse(this.workerWorkFlowMasterService.update(this.workerWorkFlowMaster));
        } else {
          this.subscribeToSaveResponse(this.workerWorkFlowMasterService.create(this.workerWorkFlowMaster));
        }
      }
    } else {
      if (this.workerWorkFlowMaster.empCode === this.workerWorkFlowMaster.forwardCode) {
        this.snotifyService.error('Emp Name and Forward Name must not be same', '', toastConfig);
        this.isSaving = false;
      } else {
        if (this.workerWorkFlowMaster.id !== undefined) {
          this.subscribeToSaveResponse(this.workerWorkFlowMasterService.update(this.workerWorkFlowMaster));
        } else {
          this.subscribeToSaveResponse(this.workerWorkFlowMasterService.create(this.workerWorkFlowMaster));
        }
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerWorkFlowMaster>>) {
    result.subscribe((res: HttpResponse<IWorkerWorkFlowMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    if (this.workerWorkFlowMaster.workerForwardTypeMaster && this.workerWorkFlowMaster.workerForwardTypeMaster.code === 'R') {
      if (this.workerWorkFlowMaster.empCode) {
        this.workerWorkFlowMaster.forwardCode = this.workerWorkFlowMaster.empCode;
        this.workerWorkFlowMaster.forwardName = this.workerWorkFlowMaster.empName;
        this.workerWorkFlowMaster.forwardCodeName = this.workerWorkFlowMaster.empCodeName;
      }
    } else {
      if (this.workerWorkFlowMaster.empCode === this.workerWorkFlowMaster.forwardCode) {
        this.workerWorkFlowMaster.forwardCode = undefined;
        this.workerWorkFlowMaster.forwardName = undefined;
        this.workerWorkFlowMaster.forwardCodeName = undefined;
      }
    }
  }
}
