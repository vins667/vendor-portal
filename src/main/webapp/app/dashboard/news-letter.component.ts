import { Component, Input } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { LoginService } from 'app/core/login/login.service';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-ngb-modal-content',
  templateUrl: './news-letter.component.html'
})
export class NewsLetterComponent {
  page = 1;
  totalPages: number;
  isLoaded = false;
  isNavbarCollapsed: boolean;
  @Input() fileUrl: any;

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

  close() {
    this.modalService.dismissAll();
  }
}
