import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IEmailInvitation } from 'app/shared/model/email-invitation.model';
import { AccountService } from 'app/core/auth/account.service';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { EmailInvitationService } from './email-invitation.service';

@Component({
  selector: 'jhi-email-invitation',
  templateUrl: './email-invitation.component.html'
})
export class EmailInvitationComponent implements OnInit, OnDestroy {
  currentAccount: any;
  emailInvitations: IEmailInvitation[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected emailInvitationService: EmailInvitationService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.emailInvitationService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IEmailInvitation[]>) => this.paginateEmailInvitations(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/email-invitation'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/email-invitation',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEmailInvitations();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEmailInvitation) {
    return item.id;
  }

  registerChangeInEmailInvitations() {
    this.eventSubscriber = this.eventManager.subscribe('emailInvitationListModification', response => this.loadAll());
  }

  emailResend(emailInvitation) {
    this.subscribeToSaveResponse(this.emailInvitationService.emailResend(emailInvitation.id));
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateEmailInvitations(data: IEmailInvitation[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.emailInvitations = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailInvitation>>) {
    result.subscribe((res: HttpResponse<IEmailInvitation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.snotifyService.success('Email Resend Successfully', '', toastConfig);
  }

  protected onSaveError() {
    this.snotifyService.success('Email not send', '', toastConfig);
  }
}
