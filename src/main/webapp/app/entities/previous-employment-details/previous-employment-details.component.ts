import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IPreviousEmploymentDetails } from 'app/shared/model/previous-employment-details.model';
import { AccountService } from 'app/core/auth/account.service';
import { PreviousEmploymentDetailsService } from './previous-employment-details.service';

@Component({
  selector: 'jhi-previous-employment-details',
  templateUrl: './previous-employment-details.component.html'
})
export class PreviousEmploymentDetailsComponent implements OnInit, OnDestroy {
  currentAccount: any;
  previousEmploymentDetails: IPreviousEmploymentDetails[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;

  constructor(
    protected previousEmploymentDetailsService: PreviousEmploymentDetailsService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {}

  loadAll() {
    this.previousEmploymentDetailsService
      .query()
      .subscribe(
        (res: HttpResponse<IPreviousEmploymentDetails[]>) => this.paginatePreviousEmploymentDetails(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  clear() {
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPreviousEmploymentDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPreviousEmploymentDetails) {
    return item.id;
  }

  registerChangeInPreviousEmploymentDetails() {
    this.eventSubscriber = this.eventManager.subscribe('previousEmploymentDetailsListModification', response => this.loadAll());
  }

  protected paginatePreviousEmploymentDetails(data: IPreviousEmploymentDetails[], headers: HttpHeaders) {
    this.previousEmploymentDetails = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
