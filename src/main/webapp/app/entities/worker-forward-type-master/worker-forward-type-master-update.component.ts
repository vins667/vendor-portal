import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IWorkerForwardTypeMaster } from 'app/shared/model/worker-forward-type-master.model';
import { WorkerForwardTypeMasterService } from './worker-forward-type-master.service';

@Component({
  selector: 'jhi-worker-forward-type-master-update',
  templateUrl: './worker-forward-type-master-update.component.html'
})
export class WorkerForwardTypeMasterUpdateComponent implements OnInit {
  workerForwardTypeMaster: IWorkerForwardTypeMaster;
  isSaving: boolean;

  constructor(protected workerForwardTypeMasterService: WorkerForwardTypeMasterService, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ workerForwardTypeMaster }) => {
      this.workerForwardTypeMaster = workerForwardTypeMaster;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.workerForwardTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.workerForwardTypeMasterService.update(this.workerForwardTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.workerForwardTypeMasterService.create(this.workerForwardTypeMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerForwardTypeMaster>>) {
    result.subscribe((res: HttpResponse<IWorkerForwardTypeMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
