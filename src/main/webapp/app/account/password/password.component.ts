import { Component, OnInit } from '@angular/core';
import { PasswordService } from './password.service';
import { SnotifyService } from 'ng-snotify';
import { HttpResponse } from '@angular/common/http';
import { IMessageBean } from 'app/shared/model/message-bean.model';
import { AccountService } from 'app/core/auth/account.service';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-password',
  templateUrl: './password.component.html'
})
export class PasswordComponent implements OnInit {
  doNotMatch: string;
  error: string;
  success: string;
  account: any;
  currentPassword: string;
  newPassword: string;
  confirmPassword: string;

  constructor(private passwordService: PasswordService, private accountService: AccountService, private snotifyService: SnotifyService) {}

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.account = account;
    });
  }

  changePassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.error = null;
      this.success = null;
      this.snotifyService.error('The password and its confirmation do not match!', '', toastConfig);
      this.doNotMatch = 'ERROR';
    } else {
      this.doNotMatch = null;
      this.passwordService.save(this.newPassword, this.currentPassword).subscribe(
        (messageBean: HttpResponse<IMessageBean>) => {
          if (messageBean.body.exist === true) {
            this.error = null;
            this.success = 'OK';
            this.snotifyService.success('Password changed!', '', toastConfig);
          } else {
            this.success = null;
            this.snotifyService.error('An error has occurred! The password could not be changed.', '', toastConfig);
          }
        },
        () => {
          this.success = null;
          this.snotifyService.error('An error has occurred! The password could not be changed.', '', toastConfig);
        }
      );
    }
  }
}
