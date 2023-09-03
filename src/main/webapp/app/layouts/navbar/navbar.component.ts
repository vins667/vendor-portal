import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { VERSION } from 'app/app.constants';
import { ProfileService } from '../profiles/profile.service';
import { CommonService } from 'app/common.service';
import { IUser } from 'app/core/user/user.model';
import { LoginService } from 'app/core/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { UserService } from 'app/core/user/user.service';
import { HttpResponse } from '@angular/common/http';
import { IMaster } from 'app/shared/model/master.modal';

declare const $: any;
declare const jquery: any;
declare const screenfull: any;

@Component({
  selector: 'jhi-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['navbar.scss']
})
export class NavbarComponent implements OnInit {
  inProduction: boolean;
  isNavbarCollapsed: boolean;
  languages: any[];
  swaggerEnabled: boolean;
  modalRef: NgbModalRef;
  version: string;
  sidebarActive: string;
  user: IUser;

  constructor(
    private loginService: LoginService,
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private profileService: ProfileService,
    private router: Router,
    public commonService: CommonService,
    public userService: UserService
  ) {
    this.version = VERSION ? 'v' + VERSION : '';
    this.isNavbarCollapsed = true;
  }

  ngOnInit() {
    this.sidebarActive = 'menu';
  }

  hideNavbar = () => {
    setTimeout(() => {
      this.commonService.navbarToggleValue = true;
    }, 100);
  };

  showNavbar = () => {
    setTimeout(() => {
      this.commonService.navbarToggleValue = false;
    }, 100);
  };

  collapseNavbar() {
    this.isNavbarCollapsed = true;
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  changeSidebar(type) {
    this.sidebarActive = type;
  }

  login() {
    this.modalRef = this.loginModalService.open();
  }

  logout() {
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
  }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  getImageUrl() {
    return this.isAuthenticated() ? this.accountService.getImageUrl() : null;
  }

  externalLink(url) {
    this.router.navigate(['external-url', url]);
  }

  changeTheme(theme: any) {
    this.accountService.update(theme).subscribe((data: HttpResponse<IMaster>) => {
      this.accountService.setTheme(data.body.id);
    });
  }

  activeTheme() {
    return this.accountService.fetchTheme();
  }
}
