import { Component } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { LoginService } from 'app/core/login/login.service';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-ngb-modal-content',
  template: `
    <div class="modal-body">
      <pdf-viewer
        [src]="'content/profile/NDA.pdf'"
        [render-text]="true"
        style="display: block;"
        [show-all]="false"
        [page]="page"
        (after-load-complete)="afterLoadComplete($event)"
        [zoom]="0.9"
      ></pdf-viewer>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-sm btn-success btn-round" (click)="agree()">Agree</button>
      <button type="button" class="btn btn-sm btn-danger btn-round" (click)="logout()">Disagree</button>
    </div>
  `
})
export class NgbdModalComponent {
  page = 1;
  totalPages: number;
  isLoaded = false;
  isNavbarCollapsed: boolean;

  constructor(
    public activeModal: NgbActiveModal,
    private router: Router,
    private loginService: LoginService,
    private modalService: NgbModal,
    private userService: UserService
  ) {}

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }

  changePage() {
    this.page = 1;
  }

  collapseNavbar() {
    this.isNavbarCollapsed = true;
  }

  agree() {
    this.userService.agree().subscribe(user => {
      this.router.navigate(['dashboard']);
      this.modalService.dismissAll();
    });
  }

  logout() {
    this.modalService.dismissAll();
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
  }
}

@Component({
  selector: 'jhi-ngb-view-modal-content',
  template: `
    <div class="modal-body">
      <pdf-viewer
        [src]="'content/profile/NDA.pdf'"
        [render-text]="true"
        style="display: block;"
        [show-all]="false"
        [page]="page"
        (after-load-complete)="afterLoadComplete($event)"
        [zoom]="0.9"
      ></pdf-viewer>
    </div>
    <div class="modal-footer float-right">
      <button type="button" class="btn btn-sm btn-danger btn-round" (click)="close()">Close</button>
    </div>
  `
})
export class NgbdViewModalComponent {
  page = 1;
  totalPages: number;
  isLoaded = false;

  constructor(
    public activeModal: NgbActiveModal,
    private router: Router,
    private loginService: LoginService,
    private modalService: NgbModal
  ) {}

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }

  changePage() {
    this.page = 1;
  }

  close() {
    this.modalService.dismissAll();
  }
}
