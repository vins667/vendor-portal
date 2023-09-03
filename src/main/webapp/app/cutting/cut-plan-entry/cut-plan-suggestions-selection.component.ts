import { Component, Input, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { CutPlanEntryService } from 'app/cutting/cut-plan-entry/cut-plan-entry.service';
import { BalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { HttpResponse } from '@angular/common/http';
import { ILotBean } from 'app/shared/db2/model/lot-bean.model';
import { IMarkerBean } from 'app/shared/db2/model/marker-bean.model';
import { ICutPlanEntryDetails } from 'app/shared/model/cut-plan-entry-details.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-cut-plan-suggestions-selection',
  templateUrl: './cut-plan-suggestions-selection.component.html'
})
export class CutPlanSuggestionsSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  @Input() search = new BalanceSuggestionSearch();
  lotBeans?: ILotBean[];
  markerBeans?: IMarkerBean[];
  cutPlanEntryDetails: ICutPlanEntryDetails[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    public cuttingEntryService: CutPlanEntryService
  ) {}

  ngOnInit(): void {
    this.cuttingEntryService.suggestion(this.search).subscribe((markerBeans: HttpResponse<ILotBean[]>) => {
      this.markerBeans = markerBeans.body;
    });
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  markerCollapse(val?: boolean, markerBeans?: IMarkerBean): void {
    markerBeans.collapse = val;
  }

  lotCollapse(val?: boolean, lotBeans?: ILotBean): void {
    lotBeans.collapse = val;
  }

  suggestionUpdate(markerBean: IMarkerBean): void {
    if (markerBean && markerBean.balances) {
      let noPlies = 0;
      let endBits = 0;
      let index = 0;
      markerBean.balances.forEach(balance => {
        if (balance.allowPlies && balance.allowPlies === true) {
          noPlies += balance.noPlies;
          endBits += balance.endBits;
        }
        ++index;
      });
      if (index === markerBean.balances.length) {
        markerBean.noPlies = noPlies;
        markerBean.endBits = Number(endBits.toFixed(3));
      }
    }
  }

  addRow(markerBean?: IMarkerBean): void {
    if (markerBean.noPlies === this.search.noPlies) {
      const balanceRolls = [];
      let index = 0;
      markerBean.balances.forEach(balance => {
        if (balance.allowPlies && balance.allowPlies === true) {
          balanceRolls.push(balance);
        }
        ++index;
      });
      if (index === markerBean.balances.length) {
        markerBean.noRolls = balanceRolls.length;
        markerBean.balances = balanceRolls;
        this.eventManager.broadcast({ name: 'cutPlanLotBalanceDtls', content: markerBean });
        this.activeModal.dismiss();
      }
    } else {
      if (markerBean.highlight && markerBean.highlight === true) {
        this.snotifyService.error('For best consumption, No. of plies should be ' + markerBean.noPlies, '', toastConfig);
      } else {
        this.snotifyService.error('No of plies must be equals to ' + this.search.noPlies, '', toastConfig);
      }
    }
  }
}
