import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanEntryService } from './cut-plan-entry.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CutSchProductionorderSelectionComponent } from './cut-sch-productionorder-selection.component';
import { JhiEventManager } from 'ng-jhipster';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { MarkerMasterEntryService } from 'app/cutting/marker-master-entry/marker-master-entry.service';
import { IMarkerMasterEntry, MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { CutPlanSuggestionsSelectionComponent } from 'app/cutting/cut-plan-entry/cut-plan-suggestions-selection.component';
import { BalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { ICutQuantity } from 'app/shared/model/cut-quantity.model';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
import { IMarkerBean } from 'app/shared/db2/model/marker-bean.model';
import { CutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IUserPlant } from 'app/shared/model/user-plant.model';

@Component({
  selector: 'jhi-cut-plan-entry-update',
  templateUrl: './cut-plan-entry-update.component.html'
})
export class CutPlanEntryUpdateComponent implements OnInit {
  isSaving: boolean;
  search?: IMaster;
  cutPlanEntry: ICutPlanEntry;
  destinations?: IMaster[];
  colors?: IMaster[];
  fullitemkeydecoders: IFullitemkeydecoder[];
  eventSubscriber?: Subscription;
  markerMasterEntity: IMarkerMasterEntry;
  protected ngbModalRef: NgbModalRef;
  markerBean?: IMarkerBean;
  userPlants?: IUserPlant[] = [];

  constructor(
    protected cutPlanEntryService: CutPlanEntryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected markerMasterEntryService: MarkerMasterEntryService
  ) {
    this.search = new Master();
    this.fullitemkeydecoders = [];
  }

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cutPlanEntry }) => {
      this.cutPlanEntry = cutPlanEntry;
      if (cutPlanEntry.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.markerMasterEntry;
        this.markerBean = cutPlanEntry.markerBean;
      } else {
        this.cutPlanEntryService.plants().subscribe(userPlants => {
          this.userPlants = userPlants.body;
        });
        this.markerMasterEntity = new MarkerMasterEntry();
      }
    });
    this.registerChangeInOrderFilter();
    this.registerChangeInRolls();
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('cuttingScrEntryFilter', data => {
      const productionorder: IProductionorder = data.content;
      this.cutPlanEntry.porductionCounterCode = productionorder.productionordercountercode;
      this.cutPlanEntry.productionCode = productionorder.id.code;
      if (this.cutPlanEntry.productionCode && this.cutPlanEntry.productionCode !== undefined) {
        this.cutPlanEntryService.getAllDetailByPo(this.cutPlanEntry.productionCode).subscribe((master: HttpResponse<IMaster>) => {
          const styleMaster = master.body;
          this.cutPlanEntry.style = styleMaster.name;
          this.fetchColors();
        });
      }
    });
  }

  previousState() {
    window.history.back();
  }

  callRelease(): void {
    this.snotifyService.confirm('Are you sure to Release Cut Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.release(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  release(toast): void {
    this.cutPlanEntryService.release(this.cutPlanEntry.id).subscribe(cutPlanEntry => {
      this.cutPlanEntry = cutPlanEntry.body;
      if (cutPlanEntry.body.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.body.markerMasterEntry;
        this.markerBean = cutPlanEntry.body.markerBean;
      }
      this.snotifyService.error('Released successfully!', '', toastConfig);
      this.snotifyService.remove(toast.id);
    });
  }

  save() {
    if (this.validate() === true) {
      this.isSaving = true;
      this.cutPlanEntry.markerMasterEntry = this.markerMasterEntity;
      this.cutPlanEntry.markerBean = this.markerBean;
      if (this.cutPlanEntry.id !== undefined) {
        this.subscribeToSaveResponse(this.cutPlanEntryService.update(this.cutPlanEntry));
      } else {
        this.subscribeToSaveResponse(this.cutPlanEntryService.create(this.cutPlanEntry));
      }
    }
  }

  validate(): any {
    if (!this.markerBean || !this.markerMasterEntity) {
      this.snotifyService.error('Detail entry not exist!', '', toastConfig);
      return false;
    } else if (this.cutPlanEntry.noPlies <= 0) {
      this.snotifyService.error('No of plies must be greator than 0!', '', toastConfig);
      return false;
    } else {
      return true;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICutPlanEntry>>) {
    result.subscribe(data => this.onSaveSuccess(data), () => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<ICutPlanEntry>) {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully', '', toastConfig);
    this.cutPlanEntryService.find(result.body.id).subscribe(cutPlanEntry => {
      this.cutPlanEntry = cutPlanEntry.body;
      if (cutPlanEntry.body.id !== undefined) {
        this.markerMasterEntity = cutPlanEntry.body.markerMasterEntry;
        this.markerBean = cutPlanEntry.body.markerBean;
      }
    });
    // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  fetchColors(): void {
    if (this.cutPlanEntry.style && this.cutPlanEntry.style !== undefined) {
      this.search.id = this.cutPlanEntry.productionCode;
      this.search.name = this.cutPlanEntry.style;
      this.cutPlanEntryService.queryColorByCountry(this.search).subscribe((country: HttpResponse<IMaster[]>) => {
        this.colors = country.body;
      });
    } else {
      this.colors = [];
    }
  }

  fetchReservationItem(): void {
    if (this.cutPlanEntry.color) {
      this.colors.forEach(color => {
        if (color.name === this.cutPlanEntry.color) {
          this.cutPlanEntry.colorDesc = color.desc;
        }
      });
    }
    if (
      this.cutPlanEntry.style &&
      this.cutPlanEntry.style !== undefined &&
      this.cutPlanEntry.color &&
      this.cutPlanEntry.color !== undefined
    ) {
      this.search.id = this.cutPlanEntry.style;
      this.search.desc = this.cutPlanEntry.color;
      this.search.code = this.cutPlanEntry.productionCode;
      this.cutPlanEntryService.getAllReservationItemByPo(this.search).subscribe((item: HttpResponse<IFullitemkeydecoder[]>) => {
        this.fullitemkeydecoders = item.body;
      });
    } else {
      this.fullitemkeydecoders = [];
    }
  }

  callDetails(): void {
    this.modalService.open(CutSchProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  callSuggestions(): void {
    const search = new BalanceSuggestionSearch();
    search.id = this.cutPlanEntry.id;
    search.plantCode = this.cutPlanEntry.plantCode;
    search.productionorder = this.cutPlanEntry.productionCode;
    search.color = this.cutPlanEntry.color;
    search.style = this.cutPlanEntry.style;
    search.subcode01 = this.cutPlanEntry.itemcode.ordersubcode01;
    search.subcode02 = this.cutPlanEntry.itemcode.ordersubcode02;
    search.subcode03 = this.cutPlanEntry.itemcode.ordersubcode03;
    search.subcode04 = this.cutPlanEntry.itemcode.ordersubcode04;
    search.subcode05 = this.cutPlanEntry.itemcode.ordersubcode05;
    search.subcode06 = this.cutPlanEntry.itemcode.ordersubcode06;
    search.subcode07 = this.cutPlanEntry.itemcode.ordersubcode07;
    search.subcode08 = this.cutPlanEntry.itemcode.ordersubcode08;
    search.subcode09 = this.cutPlanEntry.itemcode.ordersubcode09;
    search.subcode10 = this.cutPlanEntry.itemcode.ordersubcode10;
    search.pliesLength = this.markerMasterEntity.length;
    search.noPlies = this.cutPlanEntry.noPlies;

    this.ngbModalRef = this.modalService.open(CutPlanSuggestionsSelectionComponent, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
    this.ngbModalRef.componentInstance.search = search;
  }

  fetchMarkers() {
    if (
      this.cutPlanEntry.style &&
      this.cutPlanEntry.style !== undefined &&
      this.cutPlanEntry.color &&
      this.cutPlanEntry.color !== undefined
    ) {
      const master = new Master();
      master.id = this.cutPlanEntry.style;
      master.desc = this.cutPlanEntry.color;
      this.markerMasterEntryService.fetch(master).subscribe(markersMasters => {
        this.cutPlanEntry.noMarkers = Number(markersMasters.body.markerCode);
        this.fetchOrderQuantities();
      });
    }
  }

  fetchOrderQuantities() {
    const search = new BalanceSuggestionSearch();
    search.productionorder = this.cutPlanEntry.productionCode;
    search.style = this.cutPlanEntry.style;
    search.color = this.cutPlanEntry.color;
    this.cutPlanEntryService.fetchQuantity(search).subscribe((cutQuantities: HttpResponse<ICutQuantity[]>) => {
      if (cutQuantities.body && cutQuantities.body.length > 0) {
        let quantity = 0;
        let netquantity = 0;
        let tolerance = 0;
        let count = 0;
        cutQuantities.body.forEach(cutQuantity => {
          quantity += cutQuantity.orderQuantity;
          netquantity += cutQuantity.netQuantity;
          tolerance = cutQuantity.toleranceQuantity;
          ++count;
        });
        if (count === cutQuantities.body.length) {
          this.cutPlanEntry.orderQty = quantity;
          this.cutPlanEntry.netOrderQty = netquantity;
          this.cutPlanEntry.tolerance = tolerance;
        }
      }
    });
  }

  callMarkerDetails(markerMasterId?: number, noOfPlies?: number) {
    const search = new CutPlanSearch();
    search.id = markerMasterId;
    search.pono = this.cutPlanEntry.productionCode;
    search.color = this.cutPlanEntry.color;
    search.tolPer = this.cutPlanEntry.tolerance;
    this.markerMasterEntryService.cutting(search).subscribe(markerMasterEntry => {
      this.markerMasterEntity = markerMasterEntry.body;
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        if (markerEntryDetail.sizeQty && markerEntryDetail.sizeQty > 0) {
          markerEntryDetail.pliesQty = markerEntryDetail.sizeQty * noOfPlies;
        }
      });
    });
  }

  calculateValue(): void {
    if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && this.cutPlanEntry.noPlies) {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        if (markerEntryDetail.sizeQty) {
          markerEntryDetail.pliesQty = markerEntryDetail.sizeQty * this.cutPlanEntry.noPlies;
        }
      });
    }
  }

  registerChangeInRolls() {
    this.eventSubscriber = this.eventManager.subscribe('cutPlanLotBalanceDtls', response => {
      this.markerBean = response.content;
      this.callMarkerDetails(this.markerBean.markerId, this.cutPlanEntry.noPlies);
    });
  }

  markerCollapse(val?: boolean, markerBeans?: IMarkerBean): void {
    markerBeans.collapse = val;
  }

  calculateTotal(variable: string): number {
    let value = 0;
    if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'orderQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.orderQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'plannedQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.plannedQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'sizeQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        value += markerEntryDetail.sizeQty;
      });
    } else if (this.markerMasterEntity && this.markerMasterEntity.markerEntryDetails && variable === 'pliesQty') {
      this.markerMasterEntity.markerEntryDetails.forEach(markerEntryDetail => {
        if (markerEntryDetail.pliesQty) {
          value += markerEntryDetail.pliesQty;
        }
      });
    }
    return value;
  }

  selectPlant(): void {
    if (this.cutPlanEntry.plantCode) {
      this.userPlants.forEach(userPlant => {
        if (userPlant.id.plantCode === this.cutPlanEntry.plantCode) {
          this.cutPlanEntry.plantDescription = userPlant.plantDescription;
        }
      });
    } else {
      this.cutPlanEntry.plantDescription = undefined;
    }
  }
}
