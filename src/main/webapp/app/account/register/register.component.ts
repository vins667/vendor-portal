import { Component, OnInit, Renderer, ElementRef } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Register } from './register.service';
import { ISmsRegistration, SmsRegistration } from 'app/shared/model/sms-registration.model';
import { Observable } from 'rxjs';
import { IValidateOtp, ValidateOtp } from 'app/shared/model/validate-otp.model';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { IUser } from 'app/core/user/user.model';
import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared/constants/error.constants';

@Component({
  selector: 'jhi-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  confirmPassword: string;
  doNotMatch: string;
  error: string;
  errorEmailExists: string;
  errorUserExists: string;
  registerAccount: any;
  success: boolean;
  modalRef: NgbModalRef;
  smsRegistration: ISmsRegistration;
  isOtp: boolean;
  leftTime: any;
  btnLabel: string;
  isSubmitBtn: boolean;
  otpValidate: IValidateOtp;

  isSaving: boolean;
  cardNo: string;
  name: string;
  mobileNumber: string;
  mobileNumber1: string;
  mobileNumber2: string;
  otp: string;
  public mask = [/[1-9]/, ' ', /\d/, ' ', /\d/, ' ', /\d/, ' ', /\d/, ' ', /\d/];
  public maskMobile = [/[0-9]/, ' ', /\d/, ' ', /\d/];

  constructor(
    private loginModalService: LoginModalService,
    private registerService: Register,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private router: Router,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.success = false;
    this.isOtp = false;
    this.registerAccount = {};
    this.leftTime = { leftTime: 0 };
    this.btnLabel = 'Generate OTP';
    this.isSubmitBtn = false;
  }

  callDetails() {
    if (this.cardNo !== undefined) {
      this.name = undefined;
      this.mobileNumber1 = undefined;
      this.mobileNumber2 = undefined;
      this.registerService.findByLogin(this.cardNo).subscribe(employeeView => {
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

  register() {
    if (this.registerAccount.password !== this.confirmPassword) {
      this.doNotMatch = 'ERROR';
    } else {
      this.doNotMatch = null;
      this.error = null;
      this.errorUserExists = null;
      this.errorEmailExists = null;
      this.registerAccount.langKey = 'en';
      this.registerService.save(this.registerAccount).subscribe(
        () => {
          this.success = true;
        },
        response => this.processError(response)
      );
    }
  }

  generateOtp() {
    const validateNo = this.mobileNumber2.replace(/ /g, '');
    this.smsRegistration = new SmsRegistration();
    this.smsRegistration.cardNo = this.cardNo;
    this.smsRegistration.mobileNumber = this.mobileNumber1 + validateNo;
    this.smsRegistration.createdBy = 'anonymousUser';
    this.smsRegistration.otp = 'temp';
    this.isSaving = true;
    this.subscribeToSaveResponse(this.registerService.create(this.smsRegistration));
  }

  validateOtp() {
    const validateNo = this.mobileNumber2.replace(/ /g, '');
    this.otpValidate = new ValidateOtp();
    this.otpValidate.cardNo = this.cardNo;
    this.otpValidate.mobileNumber = this.mobileNumber1 + validateNo;
    this.otpValidate.otp = this.otp.replace(/\s/g, '');
    this.isSaving = true;
    this.subscribeToSaveResponseOtp(this.registerService.otpValidate(this.otpValidate));
  }

  onFinished() {
    this.isSubmitBtn = false;
    this.isSaving = false;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISmsRegistration>>) {
    result.subscribe((res: HttpResponse<ISmsRegistration>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<ISmsRegistration>) {
    if (res.body && res.body.id !== undefined && res.body.exist === true) {
      this.isOtp = true;
      this.leftTime = { leftTime: 120 };
      this.btnLabel = 'Re-Generate OTP';
      this.isSubmitBtn = true;
      this.isSaving = false;
    } else if (res.body.exist === false) {
      this.isSaving = false;
      this.snotifyService.error(res.body.errorMessage, '', toastConfig);
    }
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  openLogin() {
    this.modalRef = this.loginModalService.open();
  }

  protected subscribeToSaveResponseOtp(result: Observable<HttpResponse<IUser>>) {
    result.subscribe((res: HttpResponse<IUser>) => this.onSaveSuccessOtp(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccessOtp(res: HttpResponse<IUser>) {
    if (res.body && res.body.id !== undefined && res.body.exist === true) {
      this.snotifyService.success(
        'You have successfully registered on vamani portal. You will receive password through sms.',
        '',
        toastConfig
      );
      this.router.navigate(['']);
    } else if (res.body.exist === false) {
      this.isSaving = false;
      this.snotifyService.error(res.body.errorMessage, '', toastConfig);
    }
  }

  private processError(response: HttpErrorResponse) {
    this.success = null;
    if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
      this.errorUserExists = 'ERROR';
    } else if (response.status === 400 && response.error.type === EMAIL_ALREADY_USED_TYPE) {
      this.errorEmailExists = 'ERROR';
    } else {
      this.error = 'ERROR';
    }
  }
}
