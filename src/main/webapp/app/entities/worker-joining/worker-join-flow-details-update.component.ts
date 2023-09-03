import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IWorkerJoinFlowDetails } from 'app/shared/model/worker-join-flow-details.model';
import { IWorkerJoinFlowBean } from 'app/shared/model/worker-join-flow-bean.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from 'app/entities/worker-forward-type-master';
import { IWorkerJoinFlowMaster } from 'app/shared/model/worker-join-flow-master.model';
import { WorkerJoiningService } from 'app/entities/worker-joining/worker-joining.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-worker-join-flow-details-update',
  templateUrl: './worker-join-flow-details-update.component.html'
})
export class WorkerJoinFlowDetailsUpdateComponent implements OnInit {
  workerJoinFlow: IWorkerJoinFlowBean;
  workerForwardTypeMasters: IWorkerForwardTypeMaster[];
  workerJoinFlowMasters: IWorkerJoinFlowMaster[];
  isSaving: boolean;
  authDate: string;

  constructor(
    public workerForwardTypeMasterService: WorkerForwardTypeMasterService,
    public workerJoiningService: WorkerJoiningService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.workerForwardTypeMasterService.forwardJoin().subscribe(workerForwardTypeMaster => {
      this.workerForwardTypeMasters = workerForwardTypeMaster.body;
    });
  }

  public onChangeAuthType(val) {
    const newVal = val;
    this.workerForwardTypeMasterService.empListJoin(newVal).subscribe(workerJoinFlowMasters => {
      this.workerJoinFlowMasters = workerJoinFlowMasters.body;
    });
  }

  public userTypeChange() {
    if (this.workerJoinFlow.forwardCode != null) {
      this.workerJoinFlowMasters.forEach(workerJoinFlowMaster => {
        if (this.workerJoinFlow.forwardCode === workerJoinFlowMaster.forwardCode) {
          this.workerJoinFlow.forwardName = workerJoinFlowMaster.forwardName;
        }
      });
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.workerJoiningService.updateFlow(this.workerJoinFlow));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerJoinFlowDetails>>) {
    result.subscribe(
      (res: HttpResponse<IWorkerJoinFlowDetails>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess(result: HttpResponse<IWorkerJoinFlowDetails>) {
    this.isSaving = false;
    this.eventManager.broadcast({ name: 'joinFlowForwardStatusModification' });
    this.snotifyService.success('Save Successfully!', '', toastConfig);
    this.workerJoiningService.workFlow(result.body.joiningId).subscribe(workerWorkFlow => {
      this.workerJoinFlow = workerWorkFlow.body;
    });
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  isValidForm() {
    if (this.workerJoinFlow && this.workerJoinFlow.allowEntry === true) {
      return false;
    } else {
      return true;
    }
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }
}
