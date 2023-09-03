import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { IAssetMaster } from 'app/shared/model/asset-master.model';
import { AssetMasterService } from './asset-master.service';
import { IAssetOwnershipMaster } from 'app/shared/model/asset-ownership-master.model';
import { AssetOwnershipMasterService } from 'app/entities/asset-ownership-master';
import { IAssetTypeMaster } from 'app/shared/model/asset-type-master.model';
import { AssetTypeMasterService } from 'app/entities/asset-type-master';
import { IAssetSubTypeMaster } from 'app/shared/model/asset-sub-type-master.model';
import { AssetSubTypeMasterService } from 'app/entities/asset-sub-type-master';
import { IAssetSubTypeDetailMaster } from 'app/shared/model/asset-sub-type-detail-master.model';
import { AssetSubTypeDetailMasterService } from 'app/entities/asset-sub-type-detail-master';
import { IAssetSoftTypeMaster } from 'app/shared/model/asset-soft-type-master.model';
import { AssetSoftTypeMasterService } from 'app/entities/asset-soft-type-master';
import { IAssetCompanyMaster } from 'app/shared/model/asset-company-master.model';
import { AssetCompanyMasterService } from 'app/entities/asset-company-master';
import { IAssetWarrantyTypeMaster } from 'app/shared/model/asset-warranty-type-master.model';
import { AssetWarrantyTypeMasterService } from 'app/entities/asset-warranty-type-master';
import { IAssetSupplierMaster } from 'app/shared/model/asset-supplier-master.model';
import { AssetSupplierMasterService } from 'app/entities/asset-supplier-master';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IAssetLocationMaster } from 'app/shared/model/asset-location-master.model';
import { AssetLocationMasterService } from 'app/entities/asset-location-master';
import { AssetAuditDetails, IAssetAuditDetails } from 'app/shared/model/asset-audit-details.model';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AssetAuditSearchComponent } from './asset-audit-search.component';
import { AssetFileUploadDetailsComponent } from './asset-file-upload-details.component';

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
  selector: 'jhi-asset-master-update',
  templateUrl: './asset-master-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class AssetMasterUpdateComponent implements OnInit, OnDestroy {
  assetMaster: IAssetMaster;
  isSaving: boolean;

  assetownershipmasters: IAssetOwnershipMaster[];

  assettypemasters: IAssetTypeMaster[];

  assetsubtypemasters: IAssetSubTypeMaster[];

  assetsubtypedetailmasters: IAssetSubTypeDetailMaster[];

  assetsofttypemasters: IAssetSoftTypeMaster[];

  assetcompanymasters: IAssetCompanyMaster[];

  assetwarrantytypemasters: IAssetWarrantyTypeMaster[];

  assetsuppliermasters: IAssetSupplierMaster[];

  assetlocationmasters: IAssetLocationMaster[];
  warrantyDateDp: any;
  invoiceDateDp: any;
  createdDate: string;
  lastUpdatedDate: string;

  currentAssetAuditDetails: IAssetAuditDetails;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected assetMasterService: AssetMasterService,
    protected assetOwnershipMasterService: AssetOwnershipMasterService,
    protected assetTypeMasterService: AssetTypeMasterService,
    protected assetSubTypeMasterService: AssetSubTypeMasterService,
    protected assetSubTypeDetailMasterService: AssetSubTypeDetailMasterService,
    protected assetSoftTypeMasterService: AssetSoftTypeMasterService,
    protected assetCompanyMasterService: AssetCompanyMasterService,
    protected assetWarrantyTypeMasterService: AssetWarrantyTypeMasterService,
    protected assetSupplierMasterService: AssetSupplierMasterService,
    protected assetLocationMasterService: AssetLocationMasterService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager
  ) {}

  changeAudit() {
    if (this.assetMaster && this.assetMaster.quantity > 0) {
      this.assetMaster.assetAuditDetails = [];
      for (let i = 0; i < this.assetMaster.quantity; i++) {
        const assetAuditDetails = new AssetAuditDetails();
        assetAuditDetails.runNumber = i;
        this.assetMaster.assetAuditDetails.push(assetAuditDetails);
      }
    } else {
      this.assetMaster.assetAuditDetails = [];
    }
  }

  searchUUID(assetAuditDetail: IAssetAuditDetails) {
    this.currentAssetAuditDetails = assetAuditDetail;
    this.ngbModalRef = this.modalService.open(AssetAuditSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInAssetAuditDetails();
    this.activatedRoute.data.subscribe(({ assetMaster }) => {
      this.assetMaster = assetMaster;
      this.createdDate = this.assetMaster.createdDate != null ? this.assetMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate = this.assetMaster.lastUpdatedDate != null ? this.assetMaster.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
      this.fetchTangibility();
      this.fetchAssetTypes();
    });
    if (this.assetMaster && !this.assetMaster.quantity) {
      this.assetMaster.quantity = 1;
      this.assetMaster.assetAuditDetails = [];
      const assetAuditDetails = new AssetAuditDetails();
      assetAuditDetails.runNumber = 0;
      this.assetMaster.assetAuditDetails.push(assetAuditDetails);
    } else {
      let ctr = 0;
      if (this.assetMaster.assetAuditDetails) {
        this.assetMaster.assetAuditDetails.forEach(assetAuditDetails => {
          assetAuditDetails.runNumber = ctr;
          ++ctr;
        });
      }
    }
    this.assetOwnershipMasterService.query().subscribe(
      (res: HttpResponse<IAssetOwnershipMaster[]>) => {
        this.assetownershipmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetTypeMasterService.query().subscribe(
      (res: HttpResponse<IAssetTypeMaster[]>) => {
        this.assettypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetSoftTypeMasterService.query().subscribe(
      (res: HttpResponse<IAssetSoftTypeMaster[]>) => {
        this.assetsofttypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetCompanyMasterService.query().subscribe(
      (res: HttpResponse<IAssetCompanyMaster[]>) => {
        this.assetcompanymasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetWarrantyTypeMasterService.query().subscribe(
      (res: HttpResponse<IAssetWarrantyTypeMaster[]>) => {
        this.assetwarrantytypemasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetSupplierMasterService.query().subscribe(
      (res: HttpResponse<IAssetSupplierMaster[]>) => {
        this.assetsuppliermasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.assetLocationMasterService.query().subscribe(
      (res: HttpResponse<IAssetLocationMaster[]>) => {
        this.assetlocationmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  fetchTangibility() {
    this.assetsubtypemasters = [];
    if (this.assetMaster && this.assetMaster.assetTypeMaster) {
      this.assetSubTypeMasterService.findByTangibility(this.assetMaster.assetTypeMaster.id).subscribe(
        (res: HttpResponse<IAssetSubTypeMaster[]>) => {
          this.assetsubtypemasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchAssetTypes() {
    this.assetsubtypedetailmasters = [];
    if (this.assetMaster && this.assetMaster.assetSubTypeMaster) {
      this.assetSubTypeDetailMasterService.findByAssetTypes(this.assetMaster.assetSubTypeMaster.id).subscribe(
        (res: HttpResponse<IAssetSubTypeDetailMaster[]>) => {
          this.assetsubtypedetailmasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.assetMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.assetMaster.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.assetMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.assetMasterService.update(this.assetMaster));
    } else {
      this.subscribeToSaveResponse(this.assetMasterService.create(this.assetMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssetMaster>>) {
    result.subscribe((res: HttpResponse<IAssetMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackAssetOwnershipMasterById(index: number, item: IAssetOwnershipMaster) {
    return item.id;
  }

  trackAssetTypeMasterById(index: number, item: IAssetTypeMaster) {
    return item.id;
  }

  trackAssetSubTypeMasterById(index: number, item: IAssetSubTypeMaster) {
    return item.id;
  }

  trackAssetSubTypeDetailMasterById(index: number, item: IAssetSubTypeDetailMaster) {
    return item.id;
  }

  trackAssetSoftTypeMasterById(index: number, item: IAssetSoftTypeMaster) {
    return item.id;
  }

  trackAssetCompanyMasterById(index: number, item: IAssetCompanyMaster) {
    return item.id;
  }

  trackAssetWarrantyTypeMasterById(index: number, item: IAssetWarrantyTypeMaster) {
    return item.id;
  }

  trackAssetSupplierMasterById(index: number, item: IAssetSupplierMaster) {
    return item.id;
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }

  registerChangeInAssetAuditDetails() {
    this.eventManager.subscribe('selectedAssetAuditDetails', message => {
      const assetAuditDetails = message.content;
      let ctr = 0;
      this.assetMaster.assetAuditDetails.forEach(assetAuditDetail => {
        if (assetAuditDetail.runNumber === this.currentAssetAuditDetails.runNumber) {
          assetAuditDetails.runNumber = this.currentAssetAuditDetails.runNumber;
          this.assetMaster.assetAuditDetails.splice(ctr, 1, assetAuditDetails);
        }
        ++ctr;
      });
    });
  }

  attachment() {
    this.assetMasterService.attachment(this.assetMaster.id).subscribe(assetFileUploadBean => {
      this.ngbModalRef = this.modalService.open(AssetFileUploadDetailsComponent as Component, {
        size: 'lg',
        backdrop: 'static'
      });
      this.ngbModalRef.componentInstance.assetFileUploadBean = assetFileUploadBean.body;
    });
  }
}
