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
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IBalance } from 'app/shared/db2/model/balance.model';

@Component({
  selector: 'jhi-cut-plan-mrkr-suggestions-selection',
  templateUrl: './cut-plan-mrkr-suggestions-selection.component.html'
})
export class CutPlanMrkrSuggestionsSelectionComponent implements OnInit {
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
    this.cuttingEntryService.suggestionMarker(this.search).subscribe((markerBeans: HttpResponse<ILotBean[]>) => {
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

  suggestionUpdate(markerBean: IMarkerBean, balanceTemp: IBalance): void {
    if (balanceTemp.allowPlies && balanceTemp.allowPlies === true) {
      balanceTemp.splitPlies = false;
      balanceTemp.splitNoPlies = undefined;
      balanceTemp.splitEndBits = undefined;
    }
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

  splitUpdate(markerBean: IMarkerBean, balanceTemp?: IBalance): void {
    if (balanceTemp && balanceTemp.splitPlies && balanceTemp.splitPlies === true) {
      balanceTemp.allowPlies = false;
    }
    if (markerBean && markerBean.balances) {
      let noPlies = 0;
      let endBits = 0;
      let index = 0;
      markerBean.balances.forEach(balance => {
        if (balance.allowPlies && balance.allowPlies === true) {
          noPlies += balance.noPlies;
          endBits += balance.endBits;
        }
        if (balance.splitPlies && balance.splitPlies === true) {
          const balanceValue = balance.baseprimaryquantityunit - this.round(balance.splitNoPlies * markerBean.markerLength, 3);
          if (balanceValue > 0) {
            balance.splitEndBits = balanceValue;
            noPlies += balance.splitNoPlies;
          } else {
            balance.splitNoPlies = 0;
          }
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
        if ((balance.allowPlies && balance.allowPlies === true) || (balance.splitPlies && balance.splitPlies === true)) {
          balanceRolls.push(balance);
        }
        ++index;
      });
      if (index === markerBean.balances.length) {
        markerBean.noRolls = balanceRolls.length;
        markerBean.balances = balanceRolls;
        this.eventManager.broadcast({ name: 'cutPlanLotBalanceDtlsMarker', content: markerBean });
        this.activeModal.dismiss();
      }
    } else {
      if (markerBean.highlight && markerBean.highlight === true) {
        this.snotifyService.confirm(
          'For best consumption, No. of plies should be ' + markerBean.noPlies + ', Are you sure to choose rolls?',
          'Confirm',
          {
            timeout: 25000,
            showProgressBar: false,
            closeOnClick: false,
            pauseOnHover: true,
            position: SnotifyPosition.centerTop,
            buttons: [
              { text: 'Yes', action: toast => this.pushRoll(toast, markerBean), bold: false },
              { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
            ]
          }
        );
      } else {
        this.snotifyService.confirm(
          'No. of plies should be ' +
            this.search.noPlies +
            ' But suggested plies are ' +
            markerBean.noPlies +
            ', Are you sure to choose rolls?',
          'Confirm',
          {
            timeout: 25000,
            showProgressBar: false,
            closeOnClick: false,
            pauseOnHover: true,
            position: SnotifyPosition.centerTop,
            buttons: [
              { text: 'Yes', action: toast => this.pushRoll(toast, markerBean), bold: false },
              { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
            ]
          }
        );
        this.snotifyService.error('No of plies must be equals to ' + this.search.noPlies, '', toastConfig);
      }
    }
  }

  pushRoll(toast?: any, markerBean?: IMarkerBean): void {
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
      this.snotifyService.remove(toast.id);
      this.eventManager.broadcast({ name: 'cutPlanLotBalanceDtlsMarker', content: markerBean });
      this.activeModal.dismiss();
    }
  }

  round(number, decimalPlaces): any {
    return Math.ceil(number * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
  }
}
