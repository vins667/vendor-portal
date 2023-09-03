import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IMessage } from 'app/shared/model/message.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { PoliciesEntryService } from 'app/entities/policies-entry/policies-entry.service';
import { IPoliciesGroup } from 'app/shared/model/policies-group.model';
import { PoliciesGroupService } from 'app/entities/policies-group';
import { IPolicies } from 'app/shared/model/policies.model';

@Component({
  selector: 'jhi-policies-sort',
  templateUrl: './policies-sort.component.html'
})
export class PoliciesSortComponent implements OnInit {
  policiesgroups: IPoliciesGroup[];
  policiesGroup: any;

  policies: IPolicies[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected policiesService: PoliciesEntryService,
    public modal: NgbModal,
    protected snotifyService: SnotifyService,
    protected policiesGroupService: PoliciesGroupService
  ) {}

  ngOnInit() {
    this.policiesgroups = [];
    this.policiesGroupService.query().subscribe((res: HttpResponse<IPoliciesGroup[]>) => {
      this.policiesgroups = res.body;
    });
  }

  getPolicies() {
    if (this.policiesGroup !== undefined) {
      this.policiesService.queryByProductGroup(Number(this.policiesGroup)).subscribe(policies => {
        this.policies = policies.body;
        let ctr = -1;
        this.policies.forEach(policy => {
          policy.ordering = ++ctr;
        });
      });
    }
  }

  previousState() {
    window.history.back();
  }

  moveUp(value, index) {
    if (index > 0) {
      const tmp = this.policies[index - 1];
      this.policies[index - 1] = this.policies[index];
      this.policies[index] = tmp;

      let ctr = -1;
      this.policies.forEach(policies => {
        policies.ordering = ++ctr;
      });
    }
  }

  moveDown(value, index) {
    if (index < this.policies.length) {
      const tmp = this.policies[index + 1];
      this.policies[index + 1] = this.policies[index];
      this.policies[index] = tmp;

      let ctr = -1;
      this.policies.forEach(policies => {
        policies.ordering = ++ctr;
      });
    }
  }

  save() {
    this.subscribeToSaveResponse(this.policiesService.updateMultiple(this.policies));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IMessage>) {
    this.modal.dismissAll();
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
  }

  protected onSaveError() {}
}
