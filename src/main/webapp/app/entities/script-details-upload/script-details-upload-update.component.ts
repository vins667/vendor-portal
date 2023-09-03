import { Component, OnInit } from '@angular/core';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subscription } from 'rxjs';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HttpResponse } from '@angular/common/http';
import { ScriptDetailsUploadService } from 'app/entities/script-details-upload/script-details-upload.service';
import { IScriptDetailsUpload, ScriptDetailsUpload } from 'app/entities/script-details-upload/script-details-upload.model';

export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};
@Component({
  selector: 'jhi-script-details-upload-update',
  templateUrl: './script-details-upload-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ScriptDetailsUploadUpdateComponent implements OnInit {
  isSaving: boolean;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;
  editForm = this.fb.group({
    id: [],
    sNo: [null, [Validators.maxLength(30)]],
    shippingBillNo: [null, [Validators.maxLength(30)]],
    invoiceNo: [null, [Validators.maxLength(30)]],
    shippingBillDate: [],
    scrollNo: [null, Validators.maxLength(50)],
    portCode: [null, [Validators.maxLength(50)]],
    sanctionedValue: [],
    fobInFc: [],
    fobInInr: [],
    brcNumber: [],
    customerName: [],
    entryDate: [],
    brcRealisedValue: [],
    ifscCode: [],
    scriptNo: [],
    scriptAmount: [],
    createdby: [],
    createddate: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected scriptDetailsUploadService: ScriptDetailsUploadService,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    const brcDateFrom = new Date();
    brcDateFrom.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(({ scriptDetailsUpload }) => {
      console.log(scriptDetailsUpload);
      this.updateForm(scriptDetailsUpload);
    });
  }

  updateForm(scriptDetailsUpload: IScriptDetailsUpload) {
    this.editForm.patchValue({
      id: scriptDetailsUpload.id,
      sNo: scriptDetailsUpload.sNo,
      shippingBillNo: scriptDetailsUpload.shippingBillNo,
      invoiceNo: scriptDetailsUpload.invoiceNo,
      shippingBillDate: scriptDetailsUpload.shippingBillDate,
      scrollNo: scriptDetailsUpload.scrollNo,
      portCode: scriptDetailsUpload.portCode,
      sanctionedValue: scriptDetailsUpload.sanctionedValue,
      fobInFc: scriptDetailsUpload.fobInFc,
      fobInInr: scriptDetailsUpload.fobInInr,
      brcNumber: scriptDetailsUpload.brcNumber,
      customerName: scriptDetailsUpload.customerName,
      entryDate: scriptDetailsUpload.entryDate,
      brcRealisedValue: scriptDetailsUpload.brcRealisedValue,
      ifscCode: scriptDetailsUpload.ifscCode,
      scriptNo: scriptDetailsUpload.scriptNo,
      scriptAmount: scriptDetailsUpload.scriptAmount,
      createdby: scriptDetailsUpload.createdby,
      createddate: scriptDetailsUpload.createddate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const scriptDetailsUpload = this.createFromForm();
    if (scriptDetailsUpload.id != null) {
      this.subscribeToSaveResponse(this.scriptDetailsUploadService.update(scriptDetailsUpload));
    } else {
      this.subscribeToSaveResponse(this.scriptDetailsUploadService.create(scriptDetailsUpload));
    }
  }

  private createFromForm(): IScriptDetailsUpload {
    return {
      ...new ScriptDetailsUpload(),
      id: this.editForm.get(['id']).value,
      sNo: this.editForm.get(['sNo']).value,
      shippingBillNo: this.editForm.get(['shippingBillNo']).value,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      shippingBillDate:
        this.editForm.get(['shippingBillDate']).value != null
          ? moment(this.editForm.get(['shippingBillDate']).value, DATE_TIME_FORMAT)
          : undefined,
      portCode: this.editForm.get(['portCode']).value,
      sanctionedValue: this.editForm.get(['sanctionedValue']).value,
      fobInFc: this.editForm.get(['fobInFc']).value,
      fobInInr: this.editForm.get(['fobInInr']).value,
      brcNumber: this.editForm.get(['brcNumber']).value,
      customerName: this.editForm.get(['customerName']).value,
      entryDate:
        this.editForm.get(['entryDate']).value != null ? moment(this.editForm.get(['entryDate']).value, DATE_TIME_FORMAT) : undefined,
      brcRealisedValue: this.editForm.get(['brcRealisedValue']).value,
      ifscCode: this.editForm.get(['ifscCode']).value,
      scriptNo: this.editForm.get(['scriptNo']).value,
      scriptAmount: this.editForm.get(['scriptAmount']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScriptDetailsUpload>>) {
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
}
