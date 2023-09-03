import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICutPlanIssueStitch, CutPlanIssueStitch } from 'app/shared/model/cut-plan-issue-stitch.model';
import { CutPlanRecieptStitchService } from './cut-plan-reciept-stitch.service';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { CutPlanEntryService } from 'app/cutting/cut-plan-entry/cut-plan-entry.service';
import { ICutIssueStitchDetails } from 'app/shared/model/cut-issue-stitch-details.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { TermsofshippingService } from 'app/shared/db2/service/termsofshipping.service';
import { TermsofdeliveryService } from 'app/shared/db2/service/termsofdelivery.service';
import { ITermsofshipping } from 'app/shared/db2/model/termsofshipping.model';
import { ITermsofdelivery } from 'app/shared/db2/model/termsofdelivery.model';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { ColorPalleteService } from 'app/core/util/color-pallete.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-cut-plan-reciept-stitch-update',
  templateUrl: './cut-plan-reciept-stitch-update.component.html'
})
export class CutPlanRecieptStitchUpdateComponent implements OnInit {
  isSaving = false;
  isProcess: boolean;
  userPlants?: IUserPlant[] = [];
  userDestinationPlants?: IUserPlant[] = [];
  cutIssueStitchDetails?: ICutIssueStitchDetails[] = [];
  cutIssueStitchDetailsTemp?: ICutIssueStitchDetails[] = [];
  termsofshippings?: ITermsofshipping[] = [];
  termsofdeliveries?: ITermsofdelivery[] = [];
  public dataRemoteProject: RemoteData;
  colorCounter = 0;
  colors?: IMaster[] = [];
  destinations?: IMaster[];

  editForm = this.fb.group({
    id: [],
    transactionType: [null, [Validators.required, Validators.maxLength(1)]],
    plantCode: [null, [Validators.required, Validators.maxLength(20)]],
    plantDescription: [null, [Validators.maxLength(100)]],
    destinationPlantCode: [null, [Validators.required, Validators.maxLength(20)]],
    destinationPlantDescription: [null, [Validators.maxLength(100)]],
    projectcode: [null, [Validators.required, Validators.maxLength(50)]],
    projectcodetemp: [null, [Validators.maxLength(50)]],
    style: [null, [Validators.required, Validators.maxLength(20)]],
    color: [null, [Validators.required, Validators.maxLength(20)]],
    colordescription: [null, [Validators.maxLength(100)]],
    destination: [null, [Validators.required, Validators.maxLength(20)]],
    destinationDesc: [null, [Validators.maxLength(100)]],
    termsofdeliverycode: [null, [Validators.required, Validators.maxLength(3)]],
    termsofdeliverydescription: [null, [Validators.maxLength(200)]],
    termsofshippingcode: [null, [Validators.required, Validators.maxLength(2)]],
    termsofshippingdescription: [null, [Validators.maxLength(200)]],
    eway: [null, [Validators.maxLength(20)]],
    createdby: [null, [Validators.maxLength(20)]],
    createddate: [],
    lastupdatedby: [null, [Validators.maxLength(20)]],
    lastupdateddate: [],
    postedBy: [null, [Validators.maxLength(20)]],
    postedDate: [],
    recieptPostedBy: [null, [Validators.maxLength(20)]],
    recieptPostedDate: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  @ViewChildren('bundleCode') enteredbundleCodes;

  constructor(
    protected cutPlanIssueStitchService: CutPlanRecieptStitchService,
    protected cutPlanEntryService: CutPlanEntryService,
    protected termsofshippingService: TermsofshippingService,
    protected termsofdeliveryService: TermsofdeliveryService,
    protected activatedRoute: ActivatedRoute,
    public completerService: CompleterService,
    public colorPalleteService: ColorPalleteService,
    protected snotifyService: SnotifyService,
    private elementRef: ElementRef,
    private cdRef: ChangeDetectorRef,
    private fb: FormBuilder
  ) {
    this.dataRemoteProject = this.completerService.remote(this.cutPlanIssueStitchService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit(): void {
    this.isProcess = false;
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.termsofshippingService.query().subscribe(termsofshippings => {
      this.termsofshippings = termsofshippings.body;
    });
    this.termsofdeliveryService.query().subscribe(termsofdeliveries => {
      this.termsofdeliveries = termsofdeliveries.body;
    });
    this.activatedRoute.data.subscribe(({ cutPlanIssueStitch }) => {
      if (!cutPlanIssueStitch.id) {
      } else {
        this.cutIssueStitchDetails = cutPlanIssueStitch.cutIssueStitchDetails;
        const lengthDetails = this.cutIssueStitchDetails.length;
        this.setColorcodes(lengthDetails - 1, lengthDetails);
      }

      this.updateForm(cutPlanIssueStitch);
    });
  }

  updateForm(cutPlanIssueStitch: ICutPlanIssueStitch): void {
    this.editForm.patchValue({
      id: cutPlanIssueStitch.id,
      transactionType: cutPlanIssueStitch.transactionType,
      plantCode: cutPlanIssueStitch.plantCode,
      plantDescription: cutPlanIssueStitch.plantDescription,
      destinationPlantCode: cutPlanIssueStitch.destinationPlantCode,
      destinationPlantDescription: cutPlanIssueStitch.destinationPlantDescription,
      projectcode: cutPlanIssueStitch.projectcode,
      style: cutPlanIssueStitch.style,
      color: cutPlanIssueStitch.color,
      colordescription: cutPlanIssueStitch.colordescription,
      destination: cutPlanIssueStitch.destination,
      destinationDesc: cutPlanIssueStitch.destinationDesc,
      termsofdeliverycode: cutPlanIssueStitch.termsofdeliverycode,
      termsofdeliverydescription: cutPlanIssueStitch.termsofdeliverydescription,
      termsofshippingcode: cutPlanIssueStitch.termsofshippingcode,
      termsofshippingdescription: cutPlanIssueStitch.termsofshippingdescription,
      eway: cutPlanIssueStitch.eway,
      createdby: cutPlanIssueStitch.createdby,
      createddate: cutPlanIssueStitch.createddate ? cutPlanIssueStitch.createddate.format(DATE_TIME_FORMAT) : null,
      lastupdatedby: cutPlanIssueStitch.lastupdatedby,
      lastupdateddate: cutPlanIssueStitch.lastupdateddate ? cutPlanIssueStitch.lastupdateddate.format(DATE_TIME_FORMAT) : null,
      postedBy: cutPlanIssueStitch.postedBy,
      postedDate: cutPlanIssueStitch.postedDate ? cutPlanIssueStitch.postedDate.format(DATE_TIME_FORMAT) : null,
      recieptPostedBy: cutPlanIssueStitch.recieptPostedBy,
      recieptPostedDate: cutPlanIssueStitch.recieptPostedDate ? cutPlanIssueStitch.recieptPostedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cutPlanIssueStitch = this.createFromForm();
    if (cutPlanIssueStitch.id !== undefined) {
      this.subscribeToSaveResponse(this.cutPlanIssueStitchService.update(cutPlanIssueStitch));
    } else {
      this.subscribeToSaveResponse(this.cutPlanIssueStitchService.create(cutPlanIssueStitch));
    }
  }

  destinationUnits(): void {
    if (this.editForm.controls['transactionType'].value && this.editForm.controls['plantCode'].value) {
      this.userPlants.forEach(userPlant => {
        if (userPlant.id.plantCode === this.editForm.controls['plantCode'].value) {
          this.editForm.controls['plantDescription'].setValue(userPlant.plantDescription);
        }
      });
      const search = new Master();
      search.btnType = this.editForm.controls['transactionType'].value;
      search.plantCode = this.editForm.controls['plantCode'].value;
      this.cutPlanEntryService.destPlants(search).subscribe(userPlants => {
        this.userDestinationPlants = userPlants.body;
        if (this.userDestinationPlants && this.userDestinationPlants.length === 1) {
          this.editForm.controls['destinationPlantCode'].setValue(this.userDestinationPlants[0].id.plantCode);
          this.editForm.controls['destinationPlantDescription'].setValue(this.userDestinationPlants[0].plantDescription);
        }
      });
    }
  }

  selectDestinationUnits(): void {
    if (this.editForm.controls['destinationPlantCode'].value) {
      this.userDestinationPlants.forEach(userPlant => {
        if (userPlant.id.plantCode === this.editForm.controls['destinationPlantCode'].value) {
          this.editForm.controls['destinationPlantDescription'].setValue(userPlant.plantDescription);
        }
      });
    } else {
      this.editForm.controls['destinationPlantDescription'].setValue(undefined);
    }
  }

  onProjectSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['projectcode'].setValue(selected.originalObject.id.code);
      this.cutPlanIssueStitchService.style(selected.originalObject.id.code).subscribe(style => {
        this.editForm.controls['style'].setValue(style.body.style);
        this.colors = style.body.colors;
      });
    } else {
      this.editForm.controls['projectcode'].setValue(undefined);
      this.editForm.controls['style'].setValue(undefined);
      this.colors = [];
    }
  }

  private createFromForm(): ICutPlanIssueStitch {
    return {
      ...new CutPlanIssueStitch(),
      id: this.editForm.get(['id'])!.value,
      transactionType: this.editForm.get(['transactionType'])!.value,
      plantCode: this.editForm.get(['plantCode'])!.value,
      plantDescription: this.editForm.get(['plantDescription'])!.value,
      destinationPlantCode: this.editForm.get(['destinationPlantCode'])!.value,
      destinationPlantDescription: this.editForm.get(['destinationPlantDescription'])!.value,
      projectcode: this.editForm.get(['projectcode'])!.value,
      style: this.editForm.get(['style'])!.value,
      color: this.editForm.get(['color'])!.value,
      colordescription: this.editForm.get(['colordescription'])!.value,
      destination: this.editForm.get(['destination'])!.value,
      destinationDesc: this.editForm.get(['destinationDesc'])!.value,
      termsofdeliverycode: this.editForm.get(['termsofdeliverycode'])!.value,
      termsofdeliverydescription: this.editForm.get(['termsofdeliverydescription'])!.value,
      termsofshippingcode: this.editForm.get(['termsofshippingcode'])!.value,
      termsofshippingdescription: this.editForm.get(['termsofshippingdescription'])!.value,
      eway: this.editForm.get(['eway'])!.value,
      createdby: this.editForm.get(['createdby'])!.value,
      createddate: this.editForm.get(['createddate'])!.value
        ? moment(this.editForm.get(['createddate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastupdatedby: this.editForm.get(['lastupdatedby'])!.value,
      lastupdateddate: this.editForm.get(['lastupdateddate'])!.value
        ? moment(this.editForm.get(['lastupdateddate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      postedBy: this.editForm.get(['postedBy'])!.value,
      postedDate: this.editForm.get(['postedDate'])!.value ? moment(this.editForm.get(['postedDate'])!.value, DATE_TIME_FORMAT) : undefined,
      cutIssueStitchDetails: this.cutIssueStitchDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICutPlanIssueStitch>>, toast?: any): void {
    result.subscribe(() => this.onSaveSuccess(toast), (error: HttpErrorResponse) => this.onSaveError(error.headers, toast));
  }

  protected onSaveSuccess(toast?: any): void {
    this.isSaving = false;
    if (toast) {
      this.isProcess = false;
      this.snotifyService.success('Post Successfully', '', toastConfig);
      this.previousState();
    } else {
      this.snotifyService.success('Save Successfully', '', toastConfig);
      this.previousState();
    }
  }

  protected onSaveError(res: HttpHeaders, toast?: any): void {
    this.isSaving = false;
    this.isProcess = false;
    this.snotifyService.error(res.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  fetchDetails(index: number) {
    if (this.cutIssueStitchDetails[index].bundleCode) {
      const lengthDetails = this.cutIssueStitchDetails.length;
      this.cutPlanIssueStitchService.bundle(Number(this.cutIssueStitchDetails[index].bundleCode)).subscribe(
        cutIssueStitchDetailTemp => {
          if (cutIssueStitchDetailTemp.body && cutIssueStitchDetailTemp.body.id) {
            let existIndex = 0;
            let exist = false;
            this.cutIssueStitchDetails.forEach(cutIssueStitchDetail => {
              if (cutIssueStitchDetailTemp.body.id === cutIssueStitchDetail.id) {
                exist = true;
              }
              ++existIndex;
            });
            if (existIndex === this.cutIssueStitchDetails.length && exist === false) {
              this.cutIssueStitchDetails[index] = cutIssueStitchDetailTemp.body;
              this.setColorcodes(index, lengthDetails);
            }
          } else {
            this.cdRef.detectChanges();
          }
        },
        (error: HttpErrorResponse) => this.onError(index, error.headers)
      );
    }
  }

  protected onError(index: number, res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  setColorcodes(headerIndex: number, headerLength: number): void {
    const sizeColorMap = new Map();
    this.colorCounter = 0;
    let index = 0;
    this.cutIssueStitchDetailsTemp = [];
    this.cutIssueStitchDetails.forEach(cutIssueStitchDetail => {
      if (cutIssueStitchDetail && cutIssueStitchDetail.productionCode && cutIssueStitchDetail.productionCode.startsWith('CP', 0)) {
        if (cutIssueStitchDetail && cutIssueStitchDetail.subcode08) {
          const copy = Object.assign({}, cutIssueStitchDetail);
          if (sizeColorMap.has(cutIssueStitchDetail.subcode08)) {
            copy.colorCode = this.colorPalleteService.colorCodes(sizeColorMap.get(cutIssueStitchDetail.subcode08));
            this.cutIssueStitchDetailsTemp.push(copy);
          } else {
            copy.colorCode = this.colorPalleteService.colorCodes(this.colorCounter);
            sizeColorMap.set(cutIssueStitchDetail.subcode08, this.colorCounter);
            this.cutIssueStitchDetailsTemp.push(copy);
            ++this.colorCounter;
          }
        }
      } else {
        if (cutIssueStitchDetail && cutIssueStitchDetail.subcode06) {
          const copy = Object.assign({}, cutIssueStitchDetail);
          if (sizeColorMap.has(cutIssueStitchDetail.subcode06)) {
            copy.colorCode = this.colorPalleteService.colorCodes(sizeColorMap.get(cutIssueStitchDetail.subcode06));
            this.cutIssueStitchDetailsTemp.push(copy);
          } else {
            copy.colorCode = this.colorPalleteService.colorCodes(this.colorCounter);
            sizeColorMap.set(cutIssueStitchDetail.subcode06, this.colorCounter);
            this.cutIssueStitchDetailsTemp.push(copy);
            ++this.colorCounter;
          }
        }
      }
      ++index;
    });
    if (index === this.cutIssueStitchDetails.length) {
      this.cutIssueStitchDetails = [];
      let tempIndex = 0;
      sizeColorMap.forEach((value: number, key: string) => {
        this.cutIssueStitchDetailsTemp.forEach(cutIssueStitchDetail => {
          ++tempIndex;
          if (cutIssueStitchDetail && cutIssueStitchDetail.productionCode && cutIssueStitchDetail.productionCode.startsWith('CP', 0)) {
            if (cutIssueStitchDetail && cutIssueStitchDetail.subcode08 && cutIssueStitchDetail.subcode08 === key) {
              const copy = Object.assign({}, cutIssueStitchDetail);
              this.cutIssueStitchDetails.push(copy);
            }
          } else {
            if (cutIssueStitchDetail && cutIssueStitchDetail.subcode06 && cutIssueStitchDetail.subcode06 === key) {
              const copy = Object.assign({}, cutIssueStitchDetail);
              this.cutIssueStitchDetails.push(copy);
            }
          }
        });
      });
      if (tempIndex === sizeColorMap.size * this.cutIssueStitchDetailsTemp.length) {
        if (headerLength === this.cutIssueStitchDetails.length) {
          this.cdRef.detectChanges();
        } else {
          let temp = 0;
          const loopLength = headerLength - this.cutIssueStitchDetails.length;
          for (let x = 0; x < loopLength; x++) {
            ++temp;
          }
          if (temp === loopLength) {
            this.cdRef.detectChanges();
          }
        }
      }
    }
  }

  deleteRow(index, cutIssueDetail: ICutIssueStitchDetails): any {
    if (cutIssueDetail.detailId && cutIssueDetail.detailId !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.delete(toast, cutIssueDetail, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      this.cutIssueStitchDetails.splice(index, 1);
      this.cdRef.detectChanges();
      let tempIndex = undefined;
      this.cutIssueStitchDetails.forEach((cutIssueStitchDetail, tIndex) => {
        if (!cutIssueStitchDetail.subcode08 && tempIndex === undefined) {
          tempIndex = tIndex;
        }
      });
    }
  }

  delete(toast, cutIssueDetail: ICutIssueStitchDetails, index?: number): void {
    this.cutPlanIssueStitchService.deleteDetails(cutIssueDetail.detailId).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.cutIssueStitchDetails.splice(index, 1);
      this.cdRef.detectChanges();
      let tempIndex = undefined;
      this.cutIssueStitchDetails.forEach((cutIssueStitchDetail, tIndex) => {
        if (!cutIssueStitchDetail.subcode08 && tempIndex === undefined) {
          tempIndex = tIndex;
        }
      });
    });
  }

  selectColor(): void {
    if (this.editForm.controls['color'].value) {
      const search = new Master();
      search.id = this.editForm.controls['projectcode'].value;
      search.desc = this.editForm.controls['color'].value;

      this.cutPlanEntryService.queryColorByDestination(search).subscribe(destinations => {
        this.destinations = destinations.body;
      });
      this.colors.forEach(color => {
        if (color.name === this.editForm.controls['color'].value) {
          this.editForm.controls['colordescription'].setValue(color.desc);
        }
      });
    } else {
      this.editForm.controls['colordescription'].setValue(undefined);
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

  selectDelivery(): void {
    if (this.editForm.controls['termsofdeliverycode'].value) {
      this.termsofdeliveries.forEach(termsofdelivery => {
        if (termsofdelivery.id.code === this.editForm.controls['termsofdeliverycode'].value) {
          this.editForm.controls['termsofdeliverydescription'].setValue(termsofdelivery.longdescription);
        }
      });
    } else {
      this.editForm.controls['termsofdeliverydescription'].setValue(undefined);
    }
  }

  selectShipping(): void {
    if (this.editForm.controls['termsofshippingcode'].value) {
      this.termsofshippings.forEach(termsofshipping => {
        if (termsofshipping.id.code === this.editForm.controls['termsofshippingcode'].value) {
          this.editForm.controls['termsofshippingdescription'].setValue(termsofshipping.longdescription);
        }
      });
    } else {
      this.editForm.controls['termsofshippingdescription'].setValue(undefined);
    }
  }

  postSave(): void {
    this.snotifyService.confirm('Are you sure to Post Reciept Entry?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.post(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  post(toast: any): void {
    this.isSaving = true;
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.cutPlanIssueStitchService.post(this.editForm.controls['id'].value), this.isProcess);
  }
}
