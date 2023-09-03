import { Component, Input, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { CutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { CutPlanBundleService } from './cut-plan-bundle.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CutSchBundleProductionorderSelectionComponent } from './cut-sch-bundle-productionorder-selection.component';
import { JhiEventManager } from 'ng-jhipster';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { MarkerMasterEntryService } from 'app/cutting/marker-master-entry/marker-master-entry.service';
import { BalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { ICutQuantity } from 'app/shared/model/cut-quantity.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { ICutPlanBundleBean } from 'app/shared/model/cut-plan-bundle-bean.model';
import { ICutPlanBundleDetailsBean } from 'app/shared/model/cut-plan-bundle-details-bean.model';
import { ICutPlanBundleMatrixBean } from 'app/shared/model/cut-plan-bundle-matrix-bean.model';
import { CutPlanBundleMatrixBreakup } from 'app/shared/model/cut-plan-bundle-matrix-breakup.model';
import { CutPlanBundlePrintComponent } from 'app/cutting/cut-plan-bundle/cut-plan-bundle-print.component';

@Component({
  selector: 'jhi-cut-plan-bundle-update',
  templateUrl: './cut-plan-bundle-update.component.html'
})
export class CutPlanBundleUpdateComponent implements OnInit {
  isProcess: boolean;
  isSaving: boolean;
  search?: IMaster;
  @Input() cutPlanEntry = new CutPlanEntry();
  colors?: IMaster[];
  eventSubscriber?: Subscription;
  protected ngbModalRef: NgbModalRef;
  userPlants?: IUserPlant[] = [];
  cutPlanBundleBean?: ICutPlanBundleBean;
  breakType?: string;
  destinations?: IMaster[] = [];
  disabledPost = false;

  constructor(
    protected cutPlanEntryService: CutPlanBundleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected markerMasterEntryService: MarkerMasterEntryService
  ) {
    this.search = new Master();
  }

  ngOnInit(): void {
    this.isSaving = false;
    this.isProcess = false;
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.cutPlanEntry = Object.assign({}, this.cutPlanEntryService.cutPlanEntry);
    this.cutPlanEntryService.cutPlanEntry = undefined;
    if (this.cutPlanEntry && this.cutPlanEntry.color) {
      this.fetchColors();
      this.fetchDestinations();
      this.fetchReservationItem();
    }

    this.registerChangeInOrderFilter();
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('cuttingSchBundleEntryFilter', data => {
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

  save(): void {
    if (this.validate() === true) {
      this.isSaving = true;
      this.subscribeToSaveResponse(this.cutPlanEntryService.create(this.cutPlanBundleBean.cutPlanBundleMatrixBeans));
    }
  }

  validate(): any {
    if (this.cutPlanBundleBean && this.cutPlanBundleBean.cutPlanBundleMatrixBeans) {
      let ctr = 0;
      let ctrVal = 0;
      this.cutPlanBundleBean.cutPlanBundleMatrixBeans.forEach(cutPlanBundleMatrix => {
        if (cutPlanBundleMatrix.bundlePcs < 0) {
          this.snotifyService.error('Bundle size must be greator than zero!', '', toastConfig);
          return false;
        } else if (cutPlanBundleMatrix.bundlePcs > 0) {
          ++ctrVal;
        }
        ++ctr;
      });
      if (ctr === this.cutPlanBundleBean.cutPlanBundleMatrixBeans.length && ctrVal > 0) {
        return true;
      } else if (ctr === this.cutPlanBundleBean.cutPlanBundleMatrixBeans.length) {
        this.snotifyService.error('Atleast 1 bundle is required!', '', toastConfig);
        return false;
      }
    } else {
      return false;
    }
  }

  postSave(): void {
    this.snotifyService.confirm('Are you sure to Post Bundles?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.post(toast, true), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  post(toast: any, isPost: boolean): void {
    if (toast) {
      this.snotifyService.remove(toast.id);
    }
    this.cutPlanEntryService.lock(this.cutPlanBundleBean.cutPlanBundleMatrixBeans).subscribe(
      value => {
        this.isSaving = true;
        this.isProcess = true;
        this.subscribeToSaveResponse(this.cutPlanEntryService.post(this.cutPlanBundleBean.cutPlanBundleMatrixBeans), isPost);
      },
      (error: HttpErrorResponse) => this.onSaveError(error.headers)
    );
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICutPlanBundleMatrixBean[]>>, isPost?: boolean) {
    result.subscribe(data => this.onSaveSuccess(data, isPost), (error: HttpErrorResponse) => this.onSaveError(error.headers));
  }

  protected onSaveSuccess(result: HttpResponse<ICutPlanBundleMatrixBean[]>, isPost?: boolean) {
    this.isSaving = false;
    this.isProcess = false;
    if (isPost) {
      this.snotifyService.success('Post Successfully', '', toastConfig);
      this.previousState();
    } else {
      this.snotifyService.success('Save Successfully', '', toastConfig);
      this.cutPlanBundleBean.cutPlanBundleMatrixBeans = result.body;
      this.previousState();
      this.createBreakupLoop();
    }
  }

  protected onSaveError(res: HttpHeaders) {
    this.isProcess = false;
    this.isSaving = false;
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
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

  callBundlePrint(btnType?: string): void {
    if (
      this.cutPlanEntry.style &&
      this.cutPlanEntry.style !== undefined &&
      this.cutPlanEntry.color &&
      this.cutPlanEntry.color !== undefined &&
      this.cutPlanEntry.plantCode &&
      this.cutPlanEntry.plantCode !== undefined
    ) {
      this.search.id = this.cutPlanEntry.style;
      this.search.desc = this.cutPlanEntry.color;
      this.search.code = this.cutPlanEntry.productionCode;
      this.search.plantCode = this.cutPlanEntry.plantCode;
      const modelRef = this.modalService.open(CutPlanBundlePrintComponent, { size: 'lg', backdrop: 'static' });
      modelRef.componentInstance.search = this.search;
      modelRef.componentInstance.btnType = btnType;
    }
  }

  fetchDestinations(): void {
    if (
      this.cutPlanEntry.style &&
      this.cutPlanEntry.style !== undefined &&
      this.cutPlanEntry.color &&
      this.cutPlanEntry.color !== undefined
    ) {
      this.search.id = this.cutPlanEntry.style;
      this.search.desc = this.cutPlanEntry.color;
      this.search.code = this.cutPlanEntry.productionCode;
      this.search.plantCode = this.cutPlanEntry.plantCode;
      this.isProcess = true;
      this.cutPlanEntryService.queryColorByDestination(this.search).subscribe(
        destinations => {
          this.destinations = destinations.body;
          this.isProcess = false;
        },
        () => {
          this.isProcess = true;
        }
      );
    }
  }

  fetchReservationItem(): void {
    if (
      this.cutPlanEntry.style &&
      this.cutPlanEntry.style !== undefined &&
      this.cutPlanEntry.color &&
      this.cutPlanEntry.color !== undefined &&
      this.cutPlanEntry.plantCode &&
      this.cutPlanEntry.plantCode !== undefined &&
      this.cutPlanEntry.destination &&
      this.cutPlanEntry.destination !== undefined
    ) {
      let index = 0;
      this.destinations.forEach(destination => {
        if (destination.name === this.cutPlanEntry.destination) {
          this.cutPlanEntry.destinationDesc = destination.desc;
        }
        index++;
      });
      if (index === this.destinations.length) {
        this.search.id = this.cutPlanEntry.style;
        this.search.desc = this.cutPlanEntry.color;
        this.search.code = this.cutPlanEntry.productionCode;
        this.search.plantCode = this.cutPlanEntry.plantCode;
        this.search.destination = this.cutPlanEntry.destination;
        this.search.destinationDesc = this.cutPlanEntry.destinationDesc;
        this.isProcess = true;
        this.cutPlanEntryService.getAllReservationItemByPo(this.search).subscribe(
          (item: HttpResponse<ICutPlanBundleBean>) => {
            this.cutPlanBundleBean = item.body;
            this.createBreakupLoop();
            this.isProcess = false;
          },
          () => {
            this.isProcess = false;
          }
        );
      }
    } else {
      this.cutPlanBundleBean = undefined;
    }
  }

  callDetails(): void {
    this.modalService.open(CutSchBundleProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
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

  calculateTotal(cutPlanBundleDetailsBean?: ICutPlanBundleDetailsBean): number {
    let value = 0;
    cutPlanBundleDetailsBean.cutPlanBundleSizesBeans.forEach(cutPlanBundleSizesBean => {
      if (cutPlanBundleSizesBean.quantity) {
        value += cutPlanBundleSizesBean.quantity;
      }
    });
    return value;
  }

  changeExpend(cutPlanBundleMatrix?: ICutPlanBundleMatrixBean, value?: boolean, breakType?: string): void {
    cutPlanBundleMatrix.expend = value;
    this.breakType = breakType;
  }

  createBreakup(cutPlanBundleMatrix?: ICutPlanBundleMatrixBean): void {
    cutPlanBundleMatrix.cutPlanBundleMatrixBreakups = [];
    if (cutPlanBundleMatrix.bundleSize) {
      cutPlanBundleMatrix.bundlePcs = Math.ceil(Number(cutPlanBundleMatrix.balanceQty) / Number(cutPlanBundleMatrix.bundleSize));
      let balqty = 0;
      const x = cutPlanBundleMatrix.cutPlanBundleMatrixExistBreakups.length;
      for (let i = 0; i < Number(cutPlanBundleMatrix.bundlePcs); i++) {
        if (i === Number(cutPlanBundleMatrix.bundlePcs) - 1) {
          const cutPlanBundleMatrixBreakup = new CutPlanBundleMatrixBreakup();
          cutPlanBundleMatrixBreakup.bundle = 'B' + (Number(x) + Number(i) + 1);
          cutPlanBundleMatrixBreakup.bundlePcs = cutPlanBundleMatrix.balanceQty - balqty;
          cutPlanBundleMatrix.cutPlanBundleMatrixBreakups.push(cutPlanBundleMatrixBreakup);
        } else {
          const cutPlanBundleMatrixBreakup = new CutPlanBundleMatrixBreakup();
          cutPlanBundleMatrixBreakup.bundle = 'B' + (Number(x) + Number(i) + 1);
          cutPlanBundleMatrixBreakup.bundlePcs = cutPlanBundleMatrix.bundleSize;
          balqty += cutPlanBundleMatrix.bundleSize;
          cutPlanBundleMatrix.cutPlanBundleMatrixBreakups.push(cutPlanBundleMatrixBreakup);
        }
      }
    }
  }

  createBreakupLoop(): void {
    if (
      this.cutPlanBundleBean &&
      this.cutPlanBundleBean.cutPlanBundleMatrixBeans &&
      this.cutPlanBundleBean.cutPlanBundleMatrixBeans.length > 0
    ) {
      this.cutPlanBundleBean.cutPlanBundleMatrixBeans.forEach(cutPlanBundleMatrixBean => {
        this.createBreakupByBundlePcs(cutPlanBundleMatrixBean);
      });
    }
  }

  keyUpChange(): void {
    this.disabledPost = true;
  }

  createBreakupByBundlePcs(cutPlanBundleMatrix?: ICutPlanBundleMatrixBean): void {
    cutPlanBundleMatrix.cutPlanBundleMatrixBreakups = [];
    if (cutPlanBundleMatrix.bundleSize && cutPlanBundleMatrix.bundlePcs) {
      const bundlePcs = Math.ceil(Number(cutPlanBundleMatrix.balanceQty) / Number(cutPlanBundleMatrix.bundleSize));
      if (cutPlanBundleMatrix.bundlePcs > bundlePcs) {
        this.snotifyService.error("Bundle can't be greator than " + bundlePcs);
        this.createBreakup(cutPlanBundleMatrix);
      } else {
        let balqty = 0;
        const x = cutPlanBundleMatrix.cutPlanBundleMatrixExistBreakups.length;
        for (let i = 0; i < Number(cutPlanBundleMatrix.bundlePcs); i++) {
          if (i === Number(cutPlanBundleMatrix.bundlePcs) - 1) {
            const cutPlanBundleMatrixBreakup = new CutPlanBundleMatrixBreakup();
            cutPlanBundleMatrixBreakup.bundle = 'B' + (Number(x) + Number(i) + 1);
            const balPcs = cutPlanBundleMatrix.balanceQty - balqty;
            if (Number(balPcs) > Number(Number(cutPlanBundleMatrix.bundleSize))) {
              cutPlanBundleMatrixBreakup.bundlePcs = cutPlanBundleMatrix.bundleSize;
            } else {
              cutPlanBundleMatrixBreakup.bundlePcs = cutPlanBundleMatrix.balanceQty - balqty;
            }
            cutPlanBundleMatrix.cutPlanBundleMatrixBreakups.push(cutPlanBundleMatrixBreakup);
          } else {
            const cutPlanBundleMatrixBreakup = new CutPlanBundleMatrixBreakup();
            cutPlanBundleMatrixBreakup.bundle = 'B' + (Number(x) + Number(i) + 1);
            cutPlanBundleMatrixBreakup.bundlePcs = cutPlanBundleMatrix.bundleSize;
            balqty += cutPlanBundleMatrix.bundleSize;
            cutPlanBundleMatrix.cutPlanBundleMatrixBreakups.push(cutPlanBundleMatrixBreakup);
          }
        }
      }
    }
  }
}
