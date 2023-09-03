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
import { IMarkerMasterEntry, MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IMarkerDestinationBean, MarkerDestinationBean } from 'app/shared/model/marker-destination-bean.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { MarkerMasterEntryService } from 'app/cutting/marker-master-entry/marker-master-entry.service';

@Component({
  selector: 'jhi-cut-plan-mrkr-master-selection',
  templateUrl: './cut-plan-mrkr-master-selection.component.html'
})
export class CutPlanMrkrMasterSelectionComponent implements OnInit {
  isProcess = false;
  @Input() markerMasterEntry = new MarkerMasterEntry();
  markerDestinationBean?: MarkerDestinationBean;
  markerMasterEntries?: IMarkerMasterEntry[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    public markerMasterEntryService: MarkerMasterEntryService
  ) {}

  ngOnInit(): void {
    if (this.markerMasterEntry) {
      this.isProcess = true;
      const search = new Master();
      search.code = this.markerMasterEntry.style;
      search.desc = this.markerMasterEntry.color;
      this.markerMasterEntryService.querySizeByStyle(search).subscribe((response: HttpResponse<IMarkerDestinationBean>) => {
        this.markerDestinationBean = response.body;
        this.isProcess = true;
        this.markerMasterEntryService.viewApp(this.markerMasterEntry).subscribe(
          markerMasterEntries => {
            this.isProcess = false;
            this.markerMasterEntries = markerMasterEntries.body;
            if (this.markerMasterEntries && this.markerMasterEntries.length > 0) {
              this.markerMasterEntry.bodyFabric = this.markerMasterEntries[0].bodyFabric;
            }
          },
          () => {
            this.isProcess = false;
          }
        );

        this.isProcess = false;
      });
    }
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  pushMarker(markerMasterEntry?: IMarkerMasterEntry): void {
    this.eventManager.broadcast({ name: 'cutPlanMarkerEntryDetail', content: markerMasterEntry });
    this.activeModal.dismiss();
  }
}
