import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { PasswordResetInitService } from './password-reset-init.service';
import { IValidateOtp, ValidateOtp } from 'app/shared/model/validate-otp.model';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { Router } from '@angular/router';
import { toastConfig } from 'app/core/toast/toast-config';
import { IUser } from 'app/core/user/user.model';

@Component({
  selector: 'jhi-password-reset-init',
  templateUrl: './password-reset-init.component.html'
})
export class PasswordResetInitComponent implements OnInit, AfterViewInit {
  error: string;
  errorEmailNotExists: string;
  resetAccount: any;
  success: string;

  isSaving: boolean;
  cardNo: string;
  name: string;
  mobileNumber: string;
  mobileNumber1: string;
  mobileNumber2: string;
  otpValidate: IValidateOtp;
  public maskMobile = [/[0-9]/, ' ', /\d/, ' ', /\d/];

  constructor(
    private passwordResetInitService: PasswordResetInitService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private router: Router,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.resetAccount = {};
  }

  ngAfterViewInit() {
    this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#email'), 'focus', []);
  }

  callDetails() {
    if (this.cardNo !== undefined) {
      this.name = undefined;
      this.mobileNumber1 = undefined;
      this.mobileNumber2 = undefined;
      this.passwordResetInitService.findByLogin(this.cardNo).subscribe(employeeView => {
        this.name = employeeView.body.name;
        if (employeeView.body.phone && employeeView.body.phone.length === 10) {
          this.mobileNumber1 = employeeView.body.phone.substring(0, employeeView.body.phone.length - 3);
        } else {
          this.snotifyService.error('In-valid card No!', '', toastConfig);
        }
      });
    } else {
      this.name = undefined;
      this.mobileNumber1 = undefined;
      this.mobileNumber2 = undefined;
    }
  }

  requestReset() {
    const validateNo = this.mobileNumber2.replace(/ /g, '');
    this.otpValidate = new ValidateOtp();
    this.otpValidate.cardNo = this.cardNo;
    this.otpValidate.mobileNumber = this.mobileNumber1 + validateNo;
    this.isSaving = true;
    this.subscribeToSaveResponse(this.passwordResetInitService.reset(this.otpValidate));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUser>>) {
    result.subscribe((res: HttpResponse<IUser>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<IUser>) {
    if (res.body && res.body.id !== undefined && res.body.exist === true) {
      this.snotifyService.success(
        'You have successfully reset your password on vamani portal. You will receive password through sms.',
        '',
        toastConfig
      );
      this.router.navigate(['']);
    } else if (res.body.exist === false) {
      this.isSaving = false;
      this.snotifyService.error(res.body.errorMessage, '', toastConfig);
    }
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
