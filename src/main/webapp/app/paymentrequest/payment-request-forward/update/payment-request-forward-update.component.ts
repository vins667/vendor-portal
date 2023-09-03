import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { IPaymentRequestForward, PaymentRequestForward } from '../payment-request-forward.model';
import { PaymentRequestForwardService } from '../service/payment-request-forward.service';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PayReqEmployeeSearchComponent } from '../update/employee-search.component';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-payment-request-forward-update',
  templateUrl: './payment-request-forward-update.component.html'
})
export class PaymentRequestForwardUpdateComponent implements OnInit {
  isSaving = false;
  type: string;
  protected ngbModalRef: NgbModalRef;

  editForm = this.fb.group({
    id: [],
    empCode: [null, [Validators.maxLength(50)]],
    empName: [null, [Validators.maxLength(200)]],
    flag: [null, [Validators.maxLength(1)]],
    forwardCode: [null, [Validators.maxLength(50)]],
    forwardName: [null, [Validators.maxLength(200)]],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: []
  });

  constructor(
    protected paymentRequestForwardService: PaymentRequestForwardService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.registerChangeInSearchUser();
    this.activatedRoute.data.subscribe(({ paymentRequestForward }) => {
      if (paymentRequestForward.id === undefined) {
        const today = new Date();
        paymentRequestForward.createdDate = today;
      }

      this.updateForm(paymentRequestForward);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentRequestForward = this.createFromForm();
    if (paymentRequestForward.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentRequestForwardService.update(paymentRequestForward));
    } else {
      this.subscribeToSaveResponse(this.paymentRequestForwardService.create(paymentRequestForward));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentRequestForward>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError()
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(paymentRequestForward: IPaymentRequestForward): void {
    this.editForm.patchValue({
      id: paymentRequestForward.id,
      empCode: paymentRequestForward.empCode,
      empName: paymentRequestForward.empName,
      flag: paymentRequestForward.flag,
      forwardCode: paymentRequestForward.forwardCode,
      forwardName: paymentRequestForward.forwardName
    });
  }

  protected createFromForm(): IPaymentRequestForward {
    return {
      ...new PaymentRequestForward(),
      id: this.editForm.get(['id'])!.value,
      empCode: this.editForm.get(['empCode'])!.value,
      empName: this.editForm.get(['empName'])!.value,
      flag: this.editForm.get(['flag'])!.value,
      forwardCode: this.editForm.get(['forwardCode'])!.value,
      forwardName: this.editForm.get(['forwardName'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
    };
  }

  searchEmployee(typea: string) {
    this.type = typea;
    this.ngbModalRef = this.modalService.open(PayReqEmployeeSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  changeValue(): void {
    if (this.editForm.controls['flag'].value === 'A' || this.editForm.controls['flag'].value === 'R') {
      this.editForm.controls['forwardCode'].setValue(this.editForm.controls['empCode'].value);
      this.editForm.controls['forwardName'].setValue(this.editForm.controls['empName'].value);
    }
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('selectedPayReqUserLinkCreation', message => {
      const userDetails = message.content;
      if (this.type === 'empCode') {
        this.editForm.controls['empCode'].setValue(userDetails.cardNo);
        this.editForm.controls['empName'].setValue(userDetails.name);
      } else {
        this.editForm.controls['forwardCode'].setValue(userDetails.cardNo);
        this.editForm.controls['forwardName'].setValue(userDetails.name);
      }
    });
  }
}
