import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IFabricSubstractDetails, FabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';
import { FabricSubstractDetailsService } from './fabric-substract-details.service';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';
import { FabricSubstractMasterService } from '../fabric-substract-master/fabric-substract-master.service';

@Component({
  selector: 'jhi-fabric-substract-details-update',
  templateUrl: './fabric-substract-details-update.component.html'
})
export class FabricSubstractDetailsUpdateComponent implements OnInit {
  isSaving: boolean;

  fabricsubstractmasters: IFabricSubstractMaster[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(15)]],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    flag: [null, [Validators.maxLength(1)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: [],
    fabricSubstractMaster: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected fabricSubstractDetailsService: FabricSubstractDetailsService,
    protected fabricSubstractMasterService: FabricSubstractMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ fabricSubstractDetails }) => {
      this.updateForm(fabricSubstractDetails);
    });
    this.fabricSubstractMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IFabricSubstractMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IFabricSubstractMaster[]>) => response.body)
      )
      .subscribe(
        (res: IFabricSubstractMaster[]) => (this.fabricsubstractmasters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(fabricSubstractDetails: IFabricSubstractDetails) {
    this.editForm.patchValue({
      id: fabricSubstractDetails.id,
      code: fabricSubstractDetails.code,
      description: fabricSubstractDetails.description,
      flag: fabricSubstractDetails.flag,
      createdBy: fabricSubstractDetails.createdBy,
      createdDate: fabricSubstractDetails.createdDate != null ? fabricSubstractDetails.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: fabricSubstractDetails.lastUpdatedBy,
      lastUpdatedDate:
        fabricSubstractDetails.lastUpdatedDate != null ? fabricSubstractDetails.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      fabricSubstractMaster: fabricSubstractDetails.fabricSubstractMaster
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const fabricSubstractDetails = this.createFromForm();
    if (fabricSubstractDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.fabricSubstractDetailsService.update(fabricSubstractDetails));
    } else {
      this.subscribeToSaveResponse(this.fabricSubstractDetailsService.create(fabricSubstractDetails));
    }
  }

  private createFromForm(): IFabricSubstractDetails {
    return {
      ...new FabricSubstractDetails(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      description: this.editForm.get(['description']).value,
      flag: this.editForm.get(['flag']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      fabricSubstractMaster: this.editForm.get(['fabricSubstractMaster']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricSubstractDetails>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
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

  trackFabricSubstractMasterById(index: number, item: IFabricSubstractMaster) {
    return item.id;
  }
}
