import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';

@Component({
  selector: 'jhi-payment-request-form-detail',
  templateUrl: './payment-request-form-detail.component.html'
})
export class PaymentRequestFormDetailsComponent implements OnInit {
  paymentRequestForms: IPaymentRequestForm;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentRequestForm }) => (this.paymentRequestForms = paymentRequestForm));
  }

  previousState(): void {
    window.history.back();
  }
}
