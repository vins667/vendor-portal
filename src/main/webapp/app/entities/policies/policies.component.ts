import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { IPolicies } from 'app/shared/model/policies.model';
import { AccountService } from 'app/core/auth/account.service';
import { PoliciesService } from './policies.service';
import { IPolicyGroup, PolicyGroup } from 'app/shared/model/policy-group.model';

@Component({
  selector: 'jhi-policies',
  templateUrl: './policies.component.html'
})
export class PoliciesComponent implements OnInit, OnDestroy {
  policies: IPolicies[];
  currentAccount: any;
  eventSubscriber: Subscription;
  page = 1;
  totalPages: number;
  isLoaded = false;
  policyGroup: IPolicyGroup[];

  constructor(
    protected policiesService: PoliciesService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
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

  loadAll() {
    this.policiesService.queryOrdered().subscribe(
      (res: HttpResponse<IPolicies[]>) => {
        this.policies = res.body;
        const policiesGroup = new Set();
        this.policies.forEach(policy => {
          policiesGroup.add(policy.policiesGroup.description);
        });
        policiesGroup.forEach(value => {
          const policyGp = new PolicyGroup();
          policyGp.policyGroup = value;
          const pol = [];
          this.policies.forEach(policy => {
            if (policy.policiesGroup.description === value) {
              pol.push(policy);
            }
          });
          policyGp.policies = [];
          policyGp.policies = pol;
          this.policyGroup.push(policyGp);
        });
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  ngOnInit() {
    this.policyGroup = [];
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPolicies();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPolicies) {
    return item.id;
  }

  registerChangeInPolicies() {
    this.eventSubscriber = this.eventManager.subscribe('policiesListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
