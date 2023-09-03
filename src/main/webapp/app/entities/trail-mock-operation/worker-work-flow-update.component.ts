import { Component, OnInit } from '@angular/core';
import { IWorkerWorkFlowBean } from 'app/shared/model/worker-work-flow-bean.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from 'app/entities/worker-forward-type-master';
import { IWorkerWorkFlowMaster } from 'app/shared/model/worker-work-flow-master.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IWorkerWorkFlow } from 'app/shared/model/worker-work-flow.model';
import { TrailMockOperationService } from 'app/entities/trail-mock-operation/trail-mock-operation.service';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-worker-work-flow-update',
  templateUrl: './worker-work-flow-update.component.html',
  styleUrls: ['./flow.scss']
})
export class WorkerWorkFlowUpdateComponent implements OnInit {
  workerWorkFlow: IWorkerWorkFlowBean;
  workerForwardTypeMasters: IWorkerForwardTypeMaster[];
  workerWorkFlowMasters: IWorkerWorkFlowMaster[];
  isSaving: boolean;
  authDate: string;

  constructor(
    public activeModal: NgbActiveModal,
    public workerWorkFlowService: TrailMockOperationService,
    public workerForwardTypeMasterService: WorkerForwardTypeMasterService,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.workerForwardTypeMasterService.forward().subscribe(workerForwardTypeMaster => {
      this.workerForwardTypeMasters = workerForwardTypeMaster.body;
    });
  }

  public onChangeAuthType(val) {
    const newVal = val;
    this.workerForwardTypeMasterService.empList(newVal).subscribe(workerWorkFlowMasters => {
      this.workerWorkFlowMasters = workerWorkFlowMasters.body;
    });
  }

  public userTypeChange() {
    if (this.workerWorkFlow.forwardCode != null) {
      this.workerWorkFlowMasters.forEach(workerWorkFlowMaster => {
        if (this.workerWorkFlow.forwardCode === workerWorkFlowMaster.forwardCode) {
          this.workerWorkFlow.forwardName = workerWorkFlowMaster.forwardName;
          this.workerWorkFlow.userType = workerWorkFlowMaster.forwardType;
        }
      });
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.workerWorkFlowService.updateFlow(this.workerWorkFlow));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerWorkFlow>>) {
    result.subscribe((res: HttpResponse<IWorkerWorkFlow>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IWorkerWorkFlow>) {
    this.isSaving = false;
    this.eventManager.broadcast({ name: 'workFlowForwardStatusModification' });
    this.snotifyService.success('Save Successfully!', '', toastConfig);
    this.workerWorkFlowService.workFlow(result.body.mockId).subscribe(workerWorkFlow => {
      this.workerWorkFlow = workerWorkFlow.body;
    });
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Record not save!', '', toastConfig);
  }

  isValidForm() {
    if (this.workerWorkFlow && this.workerWorkFlow.allowEntry === true) {
      return false;
    } else {
      return true;
    }
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }
}
