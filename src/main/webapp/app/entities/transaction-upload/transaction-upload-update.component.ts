import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subscription } from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute } from '@angular/router';
import { TransactionUploadService } from 'app/entities/transaction-upload/transaction-upload.service';
import { ITransactionUpload, TransactionUpload } from 'app/entities/transaction-upload/transaction-upload.model';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HttpResponse } from '@angular/common/http';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-update.component';

@Component({
  selector: 'jhi-transaction-upload-update',
  templateUrl: './transaction-upload-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class TransactionUploadUpdateComponent implements OnInit {
  isSaving: boolean;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;
  editForm = this.fb.group({
    id: [],
    chequeNo: [null, [Validators.maxLength(30)]],
    transactionPostedDate: [],
    description: [null, [Validators.maxLength(200)]],
    mode: [null, [Validators.maxLength(50)]],
    transactionAmount: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected transactionUploadService: TransactionUploadService,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    const brcDateFrom = new Date();
    brcDateFrom.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(({ transactionUpload }) => {
      console.log(transactionUpload);
      this.updateForm(transactionUpload);
    });
  }

  updateForm(transactionUpload: ITransactionUpload) {
    this.editForm.patchValue({
      id: transactionUpload.id,
      transactionPostedDate:
        transactionUpload.transactionPostedDate != null ? transactionUpload.transactionPostedDate.format(DATE_TIME_FORMAT) : null,
      chequeNo: transactionUpload.chequeNo,
      description: transactionUpload.description,
      mode: transactionUpload.mode,
      transactionAmount: transactionUpload.transactionAmount
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const transactionUpload = this.createFromForm();
    if (transactionUpload.id != null) {
      this.subscribeToSaveResponse(this.transactionUploadService.update(transactionUpload));
    } else {
      this.subscribeToSaveResponse(this.transactionUploadService.create(transactionUpload));
    }
  }

  private createFromForm(): ITransactionUpload {
    return {
      ...new TransactionUpload(),
      id: this.editForm.get(['id']).value,
      transactionPostedDate:
        this.editForm.get(['transactionPostedDate']).value != null
          ? moment(this.editForm.get(['transactionPostedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      chequeNo: this.editForm.get(['chequeNo']).value,
      description: this.editForm.get(['description']).value,
      mode: this.editForm.get(['mode']).value,
      transactionAmount: this.editForm.get(['transactionAmount']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionUpload>>) {
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
