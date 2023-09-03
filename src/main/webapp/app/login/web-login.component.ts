import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';
import { LoginService } from 'app/core/login/login.service';
import { SnotifyService } from 'ng-snotify';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { StateStorageService } from 'app/core/auth/state-storage.service';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-web-login',
  templateUrl: './web-login.component.html',
  styleUrls: ['web-login.scss'],
  encapsulation: ViewEncapsulation.None
})
export class WebLoginComponent implements OnInit {
  account: Account;
  modalRef: NgbModalRef;
  authenticationError: boolean;
  password: string;
  mobile: string;
  rememberMe: boolean;
  username: string;
  credentials: any;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private loginService: LoginService,
    private stateStorageService: StateStorageService,
    private router: Router,
    private snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.account = account;
      if (this.account !== null) {
        this.router.navigate(['dashboard']);
      }
    });
    this.registerAuthenticationSuccess();
  }

  login() {
    this.loginService
      .login({
        username: this.username,
        password: this.password,
        mobile: this.mobile,
        rememberMe: this.rememberMe
      })
      .then(() => {
        this.authenticationError = false;

        this.eventManager.broadcast({
          name: 'authenticationSuccess',
          content: 'Sending Authentication Success'
        });

        // previousState was set in the authExpiredInterceptor before being redirected to login modal.
        // since login is successful, go to stored previousState and clear previousState
        this.stateStorageService.storeUrl('dashboard');
        const redirect = this.stateStorageService.getUrl();
        if (redirect) {
          this.router.navigate(['dashboard']);
        }
      })
      .catch(() => {
        this.authenticationError = true;
        this.snotifyService.error('Please check your credentials and try again.', '', toastConfig);
      });
  }

  registerAuthenticationSuccess() {
    this.eventManager.subscribe('authenticationSuccess', message => {
      this.accountService.identity().then(account => {
        this.account = account;
      });
    });
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }
}
