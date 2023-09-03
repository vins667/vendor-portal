import { Component, OnInit } from '@angular/core';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subscription } from 'rxjs';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { BankRealisationCertificateUploadService } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.service';
import {
  BankRealisationCertificateUpload,
  IBankRealisationCertificateUpload
} from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HttpResponse } from '@angular/common/http';
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
  selector: 'jhi-bank-realisation-certificate-upload-update',
  templateUrl: './bank-realisation-certificate-upload-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class BankRealisationCertificateUploadUpdateComponent implements OnInit {
  isSaving: boolean;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;
  editForm = this.fb.group({
    id: [],
    sbNo: [null, [Validators.maxLength(100)]],
    sbDate: [null],
    brcNo: [null, [Validators.maxLength(50)]],
    brcDate: [null],
    portCode: [null, [Validators.maxLength(50)]],
    fob: [null, Validators.maxLength(50)],
    currency: [null, [Validators.maxLength(50)]],
    realisationDate: [null],
    status: [null, [Validators.maxLength(50)]],
    createddate: [],
    createdby: []
  });
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected bankRealisationCertificateUploadService: BankRealisationCertificateUploadService,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}
  ngOnInit() {
    this.isSaving = false;
    const brcDateFrom = new Date();
    brcDateFrom.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(({ bankRealisationCertificateUpload }) => {
      console.log(bankRealisationCertificateUpload);
      this.updateForm(bankRealisationCertificateUpload);
    });
  }
  updateForm(bankRealisationCertificateUpload: IBankRealisationCertificateUpload) {
    this.editForm.patchValue({
      id: bankRealisationCertificateUpload.id,
      sbNo: bankRealisationCertificateUpload.sbNo,
      sbDate: bankRealisationCertificateUpload.sbDate,
      brcNo: bankRealisationCertificateUpload.brcNo,
      brcDate: bankRealisationCertificateUpload.brcDate,
      portCode: bankRealisationCertificateUpload.portCode,
      fob: bankRealisationCertificateUpload.fob,
      currency: bankRealisationCertificateUpload.currency,
      realisationDate: bankRealisationCertificateUpload.realisationDate,
      status: bankRealisationCertificateUpload.status,
      createddate: bankRealisationCertificateUpload.createddate,
      createdby: bankRealisationCertificateUpload.createdby
    });
  }
  previousState() {
    window.history.back();
  }
  save() {
    this.isSaving = true;
    const bankRealisationCertificateUpload = this.createFromForm();
    if (bankRealisationCertificateUpload.id != null) {
      this.subscribeToSaveResponse(this.bankRealisationCertificateUploadService.update(bankRealisationCertificateUpload));
    } else {
      this.subscribeToSaveResponse(this.bankRealisationCertificateUploadService.create(bankRealisationCertificateUpload));
    }
  }
  private createFromForm(): IBankRealisationCertificateUpload {
    return {
      ...new BankRealisationCertificateUpload(),
      id: this.editForm.get(['id']).value,
      sbNo: this.editForm.get(['sbNo']).value,
      sbDate: this.editForm.get(['sbDate']).value != null ? moment(this.editForm.get(['sbDate']).value, DATE_TIME_FORMAT) : undefined,
      brcNo: this.editForm.get(['brcNo']).value,
      brcDate: this.editForm.get(['brcDate']).value != null ? moment(this.editForm.get(['brcDate']).value, DATE_TIME_FORMAT) : undefined,
      portCode: this.editForm.get(['portCode']).value,
      fob: this.editForm.get(['fob']).value,
      currency: this.editForm.get(['currency']).value,
      realisationDate:
        this.editForm.get(['realisationDate']).value != null
          ? moment(this.editForm.get(['realisationDate']).value, DATE_TIME_FORMAT)
          : undefined,
      status: this.editForm.get(['status']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined
    };
  }
  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankRealisationCertificateUpload>>) {
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
  trackBankRealisationCertificateUploadById(index: number, item: IBankRealisationCertificateUpload) {
    return item.id;
  }
}
