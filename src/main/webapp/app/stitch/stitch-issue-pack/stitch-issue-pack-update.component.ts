import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStitchIssuePack, StitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';
import { StitchIssuePackService } from './stitch-issue-pack.service';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { CutPlanEntryService } from 'app/cutting/cut-plan-entry/cut-plan-entry.service';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { TermsofshippingService } from 'app/shared/db2/service/termsofshipping.service';
import { TermsofdeliveryService } from 'app/shared/db2/service/termsofdelivery.service';
import { ITermsofshipping } from 'app/shared/db2/model/termsofshipping.model';
import { ITermsofdelivery } from 'app/shared/db2/model/termsofdelivery.model';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { ColorPalleteService } from 'app/core/util/color-pallete.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { StitchIssuePackSelectionComponent } from './stitch-issue-pack-selection.component';
import { JhiEventManager } from 'ng-jhipster';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-stitch-issue-pack-update',
  templateUrl: './stitch-issue-pack-update.component.html'
})
export class StitchIssuePackUpdateComponent implements OnInit {
  isSaving = false;
  userPlants?: IUserPlant[] = [];
  userDestinationPlants?: IUserPlant[] = [];
  stitchIssuePackDetails?: IStitchIssuePackDetails[] = [];
  stitchIssuePackDetailsTemp?: IStitchIssuePackDetails[] = [];
  termsofshippings?: ITermsofshipping[] = [];
  termsofdeliveries?: ITermsofdelivery[] = [];
  public dataRemoteProject: RemoteData;
  colorCounter = 0;
  colors?: IMaster[] = [];
  destinations?: IMaster[];
  eventSubscriber?: Subscription;

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
    scannedBy: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  @ViewChildren('bundleCode') enteredbundleCodes;

  constructor(
    protected stitchIssuePackService: StitchIssuePackService,
    protected cutPlanEntryService: CutPlanEntryService,
    protected termsofshippingService: TermsofshippingService,
    protected termsofdeliveryService: TermsofdeliveryService,
    protected snotifyService: SnotifyService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    public completerService: CompleterService,
    public colorPalleteService: ColorPalleteService,
    private elementRef: ElementRef,
    private cdRef: ChangeDetectorRef,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {
    this.dataRemoteProject = this.completerService.remote(this.stitchIssuePackService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit(): void {
    this.registerChangeInPackFilter();
    this.editForm.controls['scannedBy'].setValue('PCS');
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.termsofshippingService.query().subscribe(termsofshippings => {
      this.termsofshippings = termsofshippings.body;
    });
    this.termsofdeliveryService.query().subscribe(termsofdeliveries => {
      this.termsofdeliveries = termsofdeliveries.body;
    });
    this.activatedRoute.data.subscribe(({ stitchIssuePack }) => {
      if (!stitchIssuePack.id) {
      } else {
        this.stitchIssuePackDetails = stitchIssuePack.stitchIssuePackDetails;
        const lengthDetails = this.stitchIssuePackDetails.length;
      }

      this.updateForm(stitchIssuePack);
    });
  }

  updateForm(stitchIssuePack: IStitchIssuePack): void {
    this.editForm.patchValue({
      id: stitchIssuePack.id,
      transactionType: stitchIssuePack.transactionType,
      plantCode: stitchIssuePack.plantCode,
      plantDescription: stitchIssuePack.plantDescription,
      destinationPlantCode: stitchIssuePack.destinationPlantCode,
      destinationPlantDescription: stitchIssuePack.destinationPlantDescription,
      projectcode: stitchIssuePack.projectcode,
      style: stitchIssuePack.style,
      color: stitchIssuePack.color,
      colordescription: stitchIssuePack.colordescription,
      destination: stitchIssuePack.destination,
      destinationDesc: stitchIssuePack.destinationDesc,
      termsofdeliverycode: stitchIssuePack.termsofdeliverycode,
      termsofdeliverydescription: stitchIssuePack.termsofdeliverydescription,
      termsofshippingcode: stitchIssuePack.termsofshippingcode,
      termsofshippingdescription: stitchIssuePack.termsofshippingdescription,
      eway: stitchIssuePack.eway,
      createdby: stitchIssuePack.createdby,
      createddate: stitchIssuePack.createddate ? stitchIssuePack.createddate.format(DATE_TIME_FORMAT) : null,
      lastupdatedby: stitchIssuePack.lastupdatedby,
      lastupdateddate: stitchIssuePack.lastupdateddate ? stitchIssuePack.lastupdateddate.format(DATE_TIME_FORMAT) : null,
      postedBy: stitchIssuePack.postedBy,
      postedDate: stitchIssuePack.postedDate ? stitchIssuePack.postedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stitchIssuePack = this.createFromForm();
    if (stitchIssuePack.id !== undefined) {
      this.subscribeToSaveResponse(this.stitchIssuePackService.update(stitchIssuePack));
    } else {
      this.subscribeToSaveResponse(this.stitchIssuePackService.create(stitchIssuePack));
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
      this.stitchIssuePackService.style(selected.originalObject.id.code).subscribe(style => {
        this.editForm.controls['style'].setValue(style.body.style);
        this.colors = style.body.colors;
      });
    } else {
      this.editForm.controls['projectcode'].setValue(undefined);
      this.editForm.controls['style'].setValue(undefined);
      this.colors = [];
    }
  }

  private createFromForm(): IStitchIssuePack {
    return {
      ...new StitchIssuePack(),
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
      scannedBy: this.editForm.get(['scannedBy'])!.value,
      stitchIssuePackDetails: this.stitchIssuePackDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStitchIssuePack>>, toast?: any): void {
    result.subscribe(() => this.onSaveSuccess(toast), () => this.onSaveError(toast));
  }

  protected onSaveSuccess(toast?: any): void {
    this.isSaving = false;
    if (toast) {
      this.snotifyService.remove(toast.id);
      this.snotifyService.success('Post Successfully', '', toastConfig);
      this.previousState();
    } else {
      this.snotifyService.success('Save Successfully', '', toastConfig);
      this.previousState();
    }
  }

  protected onSaveError(toast?: any): void {
    this.isSaving = false;

    if (toast) {
      this.snotifyService.remove(toast.id);
    }
  }

  protected onError(index: number, res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  generatePdf() {
    // this.spinner.show();
    this.stitchIssuePackService.downloadPdf(this.editForm.get(['id']).value).subscribe(
      res => {
        FileSaver.saveAs(res, 'CutPlanIssueStich.pdf');
        // this.spinner.hide();
      },
      () => {
        // this.spinner.hide();
      }
    );
  }

  deleteRow(index, stitchIssuePackDetails: IStitchIssuePackDetails): any {
    if (stitchIssuePackDetails.id && stitchIssuePackDetails.id !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.delete(toast, stitchIssuePackDetails, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      this.stitchIssuePackDetails.splice(index, 1);
    }
  }

  delete(toast, stitchIssuePackDetails: IStitchIssuePackDetails, index?: number): void {
    this.stitchIssuePackService.deleteDetails(stitchIssuePackDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.stitchIssuePackDetails.splice(index, 1);
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
    this.snotifyService.confirm('Are you sure to Post Issue Entry?', 'Confirm', {
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
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.stitchIssuePackService.post(this.editForm.controls['id'].value), toast);
  }

  scanData(): void {
    const stitchIssuePack = this.createFromForm();
    const modelRef = this.modalService.open(StitchIssuePackSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
    modelRef.componentInstance.stitchIssuePack = stitchIssuePack;
  }

  registerChangeInPackFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('stitchIssuePackEntryFilter', data => {
      const stitchIssuePackDetails: IStitchIssuePackDetails[] = data.content;
      if (stitchIssuePackDetails && stitchIssuePackDetails.length > 0) {
        stitchIssuePackDetails.forEach(stitchIssuePackDetail => {
          let i = 0;
          let exist = false;
          this.stitchIssuePackDetails.forEach(stitchIssuePackDetailMain => {
            if (stitchIssuePackDetail.cutPlanBundleDetailsId === stitchIssuePackDetailMain.cutPlanBundleDetailsId) {
              exist = true;
            }
            ++i;
          });
          if (i === this.stitchIssuePackDetails.length && exist === false) {
            if (stitchIssuePackDetail.checked && stitchIssuePackDetail.checked === true) {
              this.stitchIssuePackDetails.push(stitchIssuePackDetail);
            }
          }
        });
      }
    });
  }
}
