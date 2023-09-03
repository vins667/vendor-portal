import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPackingProductionEntry, PackingProductionEntry } from 'app/shared/model/packing-production-entry.model';
import { PackingProductionEntryService } from './packing-production-entry.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IGeneralBean } from 'app/shared/db2/model/general-bean.model';
import { IProductionOrderBean } from 'app/shared/db2/model/production-order-bean.model';
import { IProductionOrderBreakup } from 'app/shared/db2/model/production-order-breakup.model';
import { IQualitylevel } from 'app/shared/db2/model/qualitylevel.model';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { ProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { Resources } from 'app/shared/model/resources.model';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { CutPlanMrkrEntryService } from 'app/cutting/cut-plan-mrkr-entry/cut-plan-mrkr-entry.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { PackingProductionorderSelectionComponent } from './packing-productionorder-selection.component';
import { BalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { ICutQuantity } from 'app/shared/model/cut-quantity.model';
import { IPackingProgressEntry } from 'app/shared/model/packing-progress-entry.model';
import { PackingProductionSelectionComponent } from './packing-production-selection.component';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-packing-production-entry-update',
  templateUrl: './packing-production-entry-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class PackingProductionEntryUpdateComponent implements OnInit, OnDestroy {
  isSaving = false;
  isProcess = false;
  colors?: IMaster[];
  destinations?: IMaster[];
  productionOrderBean?: IProductionOrderBean;
  sizewisestemp?: IProductionOrderBreakup[];
  sizewises?: IProductionOrderBreakup[];
  qualityList?: IQualitylevel[];
  workcenterList?: IGeneralBean[];
  operationList?: IGeneralBean[];
  eventSubscriber?: Subscription;
  productionOrderBreakups?: IProductionOrderBreakup[];
  resources?: IGeneralBean[];
  userPlants?: IUserPlant[] = [];
  packingProgressEntries?: IPackingProgressEntry[] = [];
  selectedPackingProgressEntry?: IPackingProgressEntry;
  activeTab?: string;

  editForm = this.fb.group({
    id: [],
    companycode: [null, [Validators.required, Validators.maxLength(3)]],
    countercode: [null, [Validators.required, Validators.maxLength(8)]],
    productionordercode: [null, [Validators.required, Validators.maxLength(15)]],
    plantCode: [null, [Validators.required, Validators.maxLength(20)]],
    plantDesc: [null, [Validators.maxLength(100)]],
    projectcode: [null, [Validators.required, Validators.maxLength(20)]],
    style: [null, [Validators.required, Validators.maxLength(20)]],
    color: [null, [Validators.required, Validators.maxLength(20)]],
    colorDesc: [null, [Validators.maxLength(100)]],
    destination: [null, [Validators.required, Validators.maxLength(20)]],
    destinationDesc: [null, [Validators.maxLength(100)]],
    orderQty: [],
    tolerance: [],
    netOrderQty: [],
    createdby: [null, [Validators.maxLength(50)]],
    createddate: [],
    updatedby: [null, [Validators.maxLength(50)]],
    updateddate: [],
    progressPostedBy: [null, [Validators.maxLength(50)]],
    progressPostedDate: []
  });

  constructor(
    protected packingProductionEntryService: PackingProductionEntryService,
    protected cutPlanEntryService: CutPlanMrkrEntryService,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.registerChangeInOrderFilter();
    this.registerChangeInOperatorFilter();
    this.registerChangeInPackFilter();
    this.activatedRoute.data.subscribe(({ packingProductionEntry }) => {
      this.updateForm(packingProductionEntry);
      if (packingProductionEntry && packingProductionEntry.packingProgressEntries) {
        this.packingProgressEntries = packingProductionEntry.packingProgressEntries;
        this.activeTab = this.packingProgressEntries[0].operationCode;
      }
    });
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('packingProductionEntryFilter', data => {
      this.isProcess = true;
      const productionorder: IProductionorder = data.content;
      const productionorderSearch = new ProductionorderSearch();
      this.editForm.controls['companycode'].setValue(productionorder.id.companycode);
      this.editForm.controls['countercode'].setValue(productionorder.productionordercountercode);
      this.editForm.controls['productionordercode'].setValue(productionorder.id.code);
      if (this.editForm.controls['productionordercode'].value && this.editForm.controls['productionordercode'].value !== undefined) {
        this.cutPlanEntryService
          .getAllDetailByPo(this.editForm.controls['productionordercode'].value)
          .subscribe((master: HttpResponse<IMaster>) => {
            const styleMaster = master.body;
            this.editForm.controls['projectcode'].setValue(styleMaster.name);
            this.editForm.controls['style'].setValue(styleMaster.desc);
            this.fetchColors();
            this.isProcess = false;
          });
      }
    });
  }

  fetchColors(): void {
    if (this.editForm.controls['projectcode'].value && this.editForm.controls['projectcode'].value !== undefined) {
      const search = new Master();
      search.id = this.editForm.controls['productionordercode'].value;
      search.name = this.editForm.controls['projectcode'].value;
      this.cutPlanEntryService.queryColorByCountry(search).subscribe((country: HttpResponse<IMaster[]>) => {
        this.colors = country.body;
      });
    } else {
      this.colors = [];
    }
  }

  fetchReservationItem(): void {
    if (this.editForm.controls['color'].value) {
      this.colors.forEach(color => {
        if (color.name === this.editForm.controls['color'].value) {
          this.editForm.controls['colorDesc'].setValue(color.desc);
        }
      });
    }
    if (
      this.editForm.controls['style'].value &&
      this.editForm.controls['style'].value !== undefined &&
      this.editForm.controls['color'].value &&
      this.editForm.controls['color'].value !== undefined
    ) {
      const search = new Master();
      search.id = this.editForm.controls['style'].value;
      search.desc = this.editForm.controls['color'].value;
      search.code = this.editForm.controls['productionordercode'].value;

      this.cutPlanEntryService.queryColorByDestination(search).subscribe(destinations => {
        this.destinations = destinations.body;
      });
      this.fetchOrderQuantities();
    }
  }

  fetchOrderQuantities() {
    const search = new BalanceSuggestionSearch();
    search.productionorder = this.editForm.controls['productionordercode'].value;
    search.style = this.editForm.controls['style'].value;
    search.color = this.editForm.controls['color'].value;
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
          this.editForm.controls['orderQty'].setValue(quantity);
          this.editForm.controls['netOrderQty'].setValue(netquantity);
          this.editForm.controls['tolerance'].setValue(tolerance);
        }
      }
    });
  }

  fetchBreakup(): void {
    if (this.editForm.get(['subcode08']).value && this.editForm.get(['subcode09']).value) {
      this.sizewises = [];
      let markerQuantity = 0;
      if (this.sizewisestemp.length > 0) {
        this.sizewisestemp.forEach(sizes => {
          if (sizes.color === this.editForm.get(['subcode09']).value && sizes.destination === this.editForm.get(['subcode08']).value) {
            this.sizewises.push(sizes);
            markerQuantity += sizes.qty;
            this.editForm.get(['sewingquantity']).setValue(markerQuantity);
            const balanceQuantity = this.editForm.get(['sewingquantity']).value - this.editForm.get(['sewingquantitytill']).value;
            this.editForm.get(['sewingquantitybalance']).setValue(balanceQuantity);
          }
        });
      }
    } else {
      this.sizewises = [];
      this.editForm.get(['sewingquantity']).setValue(0);
      this.editForm.get(['sewingquantitybalance']).setValue(0);
    }
  }

  registerChangeInOperatorFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('SewingProductionOperatorFilter', data => {
      if (data.content) {
        const operator: Resources = data.content;
        this.editForm.get(['operatorcode']).setValue(operator.id.code);
        this.editForm.get(['operatordescription']).setValue(operator.longdescription);
      }
    });
  }

  updateForm(packingProductionEntry: IPackingProductionEntry): void {
    this.editForm.patchValue({
      id: packingProductionEntry.id,
      companycode: packingProductionEntry.companycode,
      countercode: packingProductionEntry.countercode,
      productionordercode: packingProductionEntry.productionordercode,
      plantCode: packingProductionEntry.plantCode,
      plantDesc: packingProductionEntry.plantDesc,
      projectcode: packingProductionEntry.projectcode,
      style: packingProductionEntry.style,
      color: packingProductionEntry.color,
      colorDesc: packingProductionEntry.colorDesc,
      destination: packingProductionEntry.destination,
      destinationDesc: packingProductionEntry.destinationDesc,
      orderQty: packingProductionEntry.orderQty,
      tolerance: packingProductionEntry.tolerance,
      netOrderQty: packingProductionEntry.netOrderQty,
      createdby: packingProductionEntry.createdby,
      createddate: packingProductionEntry.createddate != null ? packingProductionEntry.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: packingProductionEntry.updatedby,
      updateddate: packingProductionEntry.updateddate != null ? packingProductionEntry.updateddate.format(DATE_TIME_FORMAT) : null,
      progressPostedBy: packingProductionEntry.progressPostedBy,
      progressPostedDate: packingProductionEntry.progressPostedDate
    });
    this.fetchResources();
  }

  previousState(): void {
    window.history.back();
  }

  save(packingProgressEntry: IPackingProgressEntry): void {
    this.isSaving = true;
    this.activeTab = packingProgressEntry.operationCode;
    const packingProductionEntry = this.createFromForm();
    packingProductionEntry.packingProgressEntry = packingProgressEntry;
    if (packingProductionEntry.id !== undefined) {
      this.subscribeToSaveResponse(this.packingProductionEntryService.update(packingProductionEntry));
    } else {
      this.subscribeToSaveResponse(this.packingProductionEntryService.create(packingProductionEntry));
    }
  }

  postCall(packingProgressEntry: IPackingProgressEntry): void {
    if (packingProgressEntry.id && packingProgressEntry.id !== undefined) {
      this.snotifyService.confirm('Are you sure to post?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.post(toast, packingProgressEntry), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    }
  }

  post(toast, packingProgressEntry: IPackingProgressEntry): void {
    this.activeTab = packingProgressEntry.operationCode;
    this.packingProductionEntryService.post(packingProgressEntry.id).subscribe(response => {
      this.snotifyService.success('Post successfully!', '', toastConfig);
      this.snotifyService.remove(toast.id);
      this.updateForm(response.body);
      if (response.body && response.body.packingProgressEntries) {
        this.packingProgressEntries = response.body.packingProgressEntries;
      }
    });
  }

  private createFromForm(): IPackingProductionEntry {
    return {
      ...new PackingProductionEntry(),
      id: this.editForm.get(['id'])!.value,
      companycode: this.editForm.get(['companycode'])!.value,
      countercode: this.editForm.get(['countercode'])!.value,
      productionordercode: this.editForm.get(['productionordercode'])!.value,
      plantCode: this.editForm.get(['plantCode'])!.value,
      plantDesc: this.editForm.get(['plantDesc'])!.value,
      projectcode: this.editForm.get(['projectcode'])!.value,
      style: this.editForm.get(['style'])!.value,
      color: this.editForm.get(['color'])!.value,
      colorDesc: this.editForm.get(['colorDesc'])!.value,
      destination: this.editForm.get(['destination'])!.value,
      destinationDesc: this.editForm.get(['destinationDesc'])!.value,
      orderQty: this.editForm.get(['orderQty'])!.value,
      tolerance: this.editForm.get(['tolerance'])!.value,
      netOrderQty: this.editForm.get(['netOrderQty'])!.value,
      createdby: this.editForm.get(['createdby'])!.value,
      createddate:
        this.editForm.get(['createddate'])!.value != null ? moment(this.editForm.get(['createddate'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby'])!.value,
      updateddate:
        this.editForm.get(['updateddate'])!.value != null ? moment(this.editForm.get(['updateddate'])!.value, DATE_TIME_FORMAT) : undefined,
      progressPostedBy: this.editForm.get(['progressPostedBy'])!.value,
      progressPostedDate:
        this.editForm.get(['progressPostedDate'])!.value != null
          ? moment(this.editForm.get(['progressPostedDate'])!.value, DATE_TIME_FORMAT)
          : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPackingProductionEntry>>): void {
    result.subscribe(data => this.onSaveSuccess(data), () => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IPackingProductionEntry>): void {
    this.isSaving = false;
    this.updateForm(result.body);
    this.snotifyService.success('Save Successfully!', '', toastConfig);
    if (result.body && result.body.packingProgressEntries) {
      this.packingProgressEntries = result.body.packingProgressEntries;
    }
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  callDetails(): void {
    this.modalService.open(PackingProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  callOperators(): void {
    // this.modalService.open(SewPrdOperatorSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  evaluateDestination(): void {
    if (this.editForm.get(['subcode09']) && this.editForm.get(['subcode09']).value !== 'undefined' && this.destinations.length === 1) {
      this.editForm.get(['subcode08']).setValue(this.destinations[0].code);
      this.editForm.get(['destlongdescription']).setValue(this.destinations[0].destinationDesc);
      this.fetchBreakup();
    } else if (
      this.editForm.get(['subcode09']) &&
      this.editForm.get(['subcode09']).value !== 'undefined' &&
      this.editForm.get(['subcode08']) &&
      this.editForm.get(['subcode08']).value !== 'undefined'
    ) {
      this.fetchBreakup();
    } else {
      this.editForm.get(['subcode09']).setValue(undefined);
      this.editForm.get(['subcode08']).setValue(undefined);
      this.editForm.get(['destlongdescription']).setValue(undefined);
      this.fetchBreakup();
    }
  }

  addDestDesc(): void {
    if (this.editForm.get(['subcode08']).value) {
      this.destinations.forEach((value: IGeneralBean) => {
        if (this.editForm.get(['subcode08']).value === value.code) {
          this.editForm.get(['destlongdescription']).setValue(value.description);
        }
      });
    } else {
      this.editForm.get(['destlongdescription']).setValue(undefined);
    }
    this.fetchBreakup();
  }

  addWorkcenter(): void {
    if (this.editForm.get(['workcentercode']).value) {
      this.workcenterList.forEach((value: IGeneralBean) => {
        if (this.editForm.get(['workcentercode']).value === value.code) {
          this.editForm.get(['workcenterdescription']).setValue(value.description);
        }
      });
      this.fetchResources();
    } else {
      this.editForm.get(['workcenterdescription']).setValue(undefined);
      this.fetchResources();
    }
  }

  addResources(): void {
    if (this.editForm.get(['resourcescode']).value) {
      this.resources.forEach((value: IGeneralBean) => {
        if (this.editForm.get(['resourcescode']).value === value.code) {
          this.editForm.get(['resourcesdescription']).setValue(value.description);
        }
      });
    } else {
      this.editForm.get(['resourcesdescription']).setValue(undefined);
    }
  }

  addOperationDesc(): void {
    if (this.editForm.get(['operationcode']).value) {
      this.operationList.forEach((value: IGeneralBean) => {
        if (this.editForm.get(['operationcode']).value === value.code) {
          this.editForm.get(['operationdescription']).setValue(value.description);
        }
      });
    } else {
      this.editForm.get(['operationdescription']).setValue(undefined);
    }
  }

  fetchDetails(): void {
    if (this.editForm.get(['productionordercode']).value && this.editForm.get(['productionordercode']).value) {
      const search = new Master();
      search.code = this.editForm.get(['productionordercode']).value;
      this.packingProductionEntryService.fetchDetails(search).subscribe(packingProgressEntries => {
        this.packingProgressEntries = packingProgressEntries.body;
        this.packingProgressEntries.forEach(packingProgressEntry => {
          packingProgressEntry.scannedBy = 'PCS';
        });
      });
    }
  }

  addQualityDesc(): void {
    if (this.editForm.get(['qualitylevelcode']).value) {
      this.qualityList.forEach((value: IQualitylevel) => {
        if (Number(this.editForm.get(['qualitylevelcode']).value) === Number(value.id.code)) {
          this.editForm.get(['qualitydescription']).setValue(value.longdescription);
        }
      });
    } else {
      this.editForm.get(['qualitydescription']).setValue(undefined);
    }
  }

  scanData(packingProgressEntry?: IPackingProgressEntry): void {
    const packingProductionEntry = this.createFromForm();
    packingProductionEntry.scannedBy = packingProgressEntry.scannedBy;
    this.selectedPackingProgressEntry = packingProgressEntry;

    const modelRef = this.modalService.open(PackingProductionSelectionComponent, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
    modelRef.componentInstance.packingProductionEntry = packingProductionEntry;
  }

  registerChangeInPackFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('packingProductionEntryDetailFilter', data => {
      const stitchIssuePackDetails: IStitchIssuePackDetails[] = data.content;
      if (stitchIssuePackDetails && stitchIssuePackDetails.length > 0) {
        stitchIssuePackDetails.forEach(stitchIssuePackDetail => {
          let i = 0;
          let exist = false;
          if (this.selectedPackingProgressEntry && this.selectedPackingProgressEntry.stitchIssuePackDetails) {
            this.selectedPackingProgressEntry.stitchIssuePackDetails.forEach(stitchIssuePackDetailMain => {
              if (stitchIssuePackDetail.cutPlanBundleDetailsId === stitchIssuePackDetailMain.cutPlanBundleDetailsId) {
                exist = true;
              }
              ++i;
            });
            if (i === this.selectedPackingProgressEntry.stitchIssuePackDetails.length && exist === false) {
              if (stitchIssuePackDetail.checked && stitchIssuePackDetail.checked === true) {
                this.selectedPackingProgressEntry.stitchIssuePackDetails.push(stitchIssuePackDetail);
              }
            }
          } else if (this.selectedPackingProgressEntry) {
            this.selectedPackingProgressEntry.stitchIssuePackDetails = [];
            this.selectedPackingProgressEntry.stitchIssuePackDetails.forEach(stitchIssuePackDetailMain => {
              if (stitchIssuePackDetail.cutPlanBundleDetailsId === stitchIssuePackDetailMain.cutPlanBundleDetailsId) {
                exist = true;
              }
              ++i;
            });
            if (i === this.selectedPackingProgressEntry.stitchIssuePackDetails.length && exist === false) {
              if (stitchIssuePackDetail.checked && stitchIssuePackDetail.checked === true) {
                this.selectedPackingProgressEntry.stitchIssuePackDetails.push(stitchIssuePackDetail);
              }
            }
          }
        });
      }
    });
  }

  deleteRow(index, stitchIssuePackDetails: IStitchIssuePackDetails[], stitchIssuePackDetail: IStitchIssuePackDetails): any {
    if (stitchIssuePackDetail.id && stitchIssuePackDetail.id !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.delete(toast, stitchIssuePackDetails, stitchIssuePackDetail, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      stitchIssuePackDetails.splice(index, 1);
    }
  }

  delete(toast, stitchIssuePackDetails: IStitchIssuePackDetails[], stitchIssuePackDetail: IStitchIssuePackDetails, index?: number): void {
    this.packingProductionEntryService.deleteDetails(stitchIssuePackDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      stitchIssuePackDetails.splice(index, 1);
    });
  }

  changeDate(packingProgressEntry: IPackingProgressEntry): void {
    if (packingProgressEntry.startDate && packingProgressEntry.endDate && packingProgressEntry.noCutters) {
      const startDate = moment(packingProgressEntry.startDate)
        .seconds(0)
        .milliseconds(0);
      const endDate = moment(packingProgressEntry.endDate)
        .seconds(0)
        .milliseconds(0);
      const minutes = endDate.diff(startDate, 'minutes');
      packingProgressEntry.totalHour = (Number((Number(minutes) / 60).toFixed(2)) * Number(packingProgressEntry.noCutters)).toFixed(2);
      this.packingProgressEntries.forEach((packingProgressEntryTemp, index) => {
        if (
          packingProgressEntry.operationCode === packingProgressEntryTemp.operationCode &&
          index === this.packingProgressEntries.length - 1
        ) {
          packingProgressEntry.lastProgress = 'Y';
        }
      });
    }
  }

  fetchLines(): void {
    if (this.editForm.controls['plantCode'].value) {
      this.userPlants.forEach(plant => {
        if (this.editForm.controls['plantCode'].value === plant.id.plantCode) {
          this.editForm.controls['plantDesc'].setValue(plant.plantDescription);
        }
      });
    }
  }

  selectDestination(): void {
    if (this.editForm.controls['destination'].value) {
      this.destinations.forEach(destination => {
        if (destination.name === this.editForm.controls['destination'].value) {
          this.editForm.controls['destinationDesc'].setValue(destination.desc);
        }
      });
    } else {
      this.editForm.controls['destinationDesc'].setValue(undefined);
    }
  }

  fetchResources(): void {
    /* if (this.editForm.get(['workcentercode']).value) {
      const search = new GeneralBean();
      search.code = this.editForm.get(['workcentercode']).value;
      this.packingProductionEntryService.queryResources(search).subscribe(resources => {
        this.resources = resources.body;
      });
    } else {
      this.resources = [];
      this.editForm.get(['resourcescode']).setValue(undefined);
      this.editForm.get(['resourcesdescription']).setValue(undefined);

    } */
  }
}
