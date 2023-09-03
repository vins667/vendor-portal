import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPackingLineIssue, PackingLineIssue } from 'app/shared/model/packing-line-issue.model';
import { PackingLineIssueService } from './packing-line-issue.service';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { CutPlanEntryService } from 'app/cutting/cut-plan-entry/cut-plan-entry.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { CompleterCmp, CompleterService, RemoteData } from 'ng2-completer';
import { ColorPalleteService } from 'app/core/util/color-pallete.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IResources } from 'app/shared/model/resources.model';
import { IPackingLineIssueDetails } from 'app/shared/model/packing-line-issue-details.model';
import { PackingLineProductionorderSelectionComponent } from './packing-line-productionorder-selection.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { JhiEventManager } from 'ng-jhipster';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { PackingLineTransferSelectionComponent } from './packing-line-transfer-selection.component';
import { PackIssueLineSelectionComponent } from 'app/packing/packing-line-issue/pack-issue-line-selection.component';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import * as FileSaver from 'file-saver';
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
  selector: 'jhi-packing-line-issue-update',
  templateUrl: './packing-line-issue-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class PackingLineIssueUpdateComponent implements OnInit {
  isSaving = false;
  isProcess: boolean;
  userPlants?: IUserPlant[] = [];
  public dataRemoteProject: RemoteData;
  colorCounter = 0;
  colors?: IMaster[] = [];
  destinations?: IMaster[];
  resources: IResources[] = [];
  eventSubscriber?: Subscription;
  protected ngbModalRef: NgbModalRef;
  stitchIssuePackDetails?: IStitchIssuePackDetails[] = [];
  stitchIssuePackDetailsTemp?: IStitchIssuePackDetails[] = [];

  editForm = this.fb.group({
    id: [],
    porductionCounterCode: [null, [Validators.required, Validators.maxLength(8)]],
    productionCode: [null, [Validators.required, Validators.maxLength(15)]],
    plantCode: [null, [Validators.required, Validators.maxLength(20)]],
    plantDescription: [null, [Validators.maxLength(100)]],
    projectcode: [null, [Validators.required, Validators.maxLength(50)]],
    projectcodetemp: [null, [Validators.maxLength(50)]],
    style: [null, [Validators.required, Validators.maxLength(20)]],
    color: [null, [Validators.required, Validators.maxLength(20)]],
    colordescription: [null, [Validators.maxLength(100)]],
    destination: [null, [Validators.required, Validators.maxLength(20)]],
    destinationDesc: [null, [Validators.maxLength(100)]],
    line: [null, [Validators.required, Validators.maxLength(20)]],
    lineDesc: [null, [Validators.maxLength(100)]],
    issuedate: [null, [Validators.required]],
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
    protected packingLineIssueService: PackingLineIssueService,
    protected cutPlanEntryService: CutPlanEntryService,
    protected activatedRoute: ActivatedRoute,
    public completerService: CompleterService,
    public colorPalleteService: ColorPalleteService,
    protected eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    private elementRef: ElementRef,
    private cdRef: ChangeDetectorRef,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {
    this.dataRemoteProject = this.completerService.remote(this.packingLineIssueService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit(): void {
    this.registerChangeInPackFilter();
    this.editForm.controls['scannedBy'].setValue('PCS');
    this.isProcess = false;
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.activatedRoute.data.subscribe(({ cutPlanIssueStitch }) => {
      if (!cutPlanIssueStitch.id) {
      } else {
        this.stitchIssuePackDetails = cutPlanIssueStitch.stitchIssuePackDetails;
        const lengthDetails = this.stitchIssuePackDetails.length;
      }
      this.updateForm(cutPlanIssueStitch);
    });
    this.registerChangeInOrderFilter();
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('stitchLineEntryFilter', data => {
      const productionorder: IProductionorder = data.content;
      this.editForm.controls['porductionCounterCode'].setValue(productionorder.productionordercountercode);
      this.editForm.controls['productionCode'].setValue(productionorder.id.code);
      if (this.editForm.controls['productionCode'].value && this.editForm.controls['productionCode'].value !== undefined) {
        this.packingLineIssueService
          .getAllDetailByPo(this.editForm.controls['productionCode'].value)
          .subscribe((master: HttpResponse<IMaster>) => {
            const styleMaster = master.body;
            this.editForm.controls['projectcode'].setValue(styleMaster.name);
            this.editForm.controls['style'].setValue(styleMaster.desc);
            this.fetchColors();
          });
      }
    });
  }

  updateForm(stitchLineIssue: IPackingLineIssue): void {
    this.editForm.patchValue({
      id: stitchLineIssue.id,
      porductionCounterCode: stitchLineIssue.porductionCounterCode,
      productionCode: stitchLineIssue.productionCode,
      plantCode: stitchLineIssue.plantCode,
      plantDescription: stitchLineIssue.plantDescription,
      projectcode: stitchLineIssue.projectcode,
      style: stitchLineIssue.style,
      color: stitchLineIssue.color,
      colordescription: stitchLineIssue.colordescription,
      destination: stitchLineIssue.destination,
      destinationDesc: stitchLineIssue.destinationDesc,
      line: stitchLineIssue.line,
      lineDesc: stitchLineIssue.lineDesc,
      issuedate: stitchLineIssue.issuedate != null ? stitchLineIssue.issuedate.format(DATE_TIME_FORMAT) : null,
      createdby: stitchLineIssue.createdby,
      createddate: stitchLineIssue.createddate ? stitchLineIssue.createddate.format(DATE_TIME_FORMAT) : null,
      lastupdatedby: stitchLineIssue.lastupdatedby,
      lastupdateddate: stitchLineIssue.lastupdateddate ? stitchLineIssue.lastupdateddate.format(DATE_TIME_FORMAT) : null,
      postedBy: stitchLineIssue.postedBy,
      postedDate: stitchLineIssue.postedDate ? stitchLineIssue.postedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cutPlanIssueStitch = this.createFromForm();
    if (cutPlanIssueStitch.id !== undefined) {
      this.subscribeToSaveResponse(this.packingLineIssueService.update(cutPlanIssueStitch));
    } else {
      this.subscribeToSaveResponse(this.packingLineIssueService.create(cutPlanIssueStitch));
    }
  }

  fetchColors(): void {
    if (this.editForm.controls['projectcode'].value) {
      this.packingLineIssueService.style(this.editForm.controls['projectcode'].value).subscribe(style => {
        this.colors = style.body.colors;
      });
    } else {
      this.colors = [];
    }
  }

  generatePdf() {
    this.packingLineIssueService.downloadPdf(this.editForm.get(['id']).value).subscribe(
      res => {
        FileSaver.saveAs(res, 'PackingLineIssue.pdf');
      },
      () => {}
    );
  }

  private createFromForm(): IPackingLineIssue {
    return {
      ...new PackingLineIssue(),
      id: this.editForm.get(['id'])!.value,
      porductionCounterCode: this.editForm.get(['porductionCounterCode'])!.value,
      productionCode: this.editForm.get(['productionCode'])!.value,
      plantCode: this.editForm.get(['plantCode'])!.value,
      plantDescription: this.editForm.get(['plantDescription'])!.value,
      projectcode: this.editForm.get(['projectcode'])!.value,
      style: this.editForm.get(['style'])!.value,
      color: this.editForm.get(['color'])!.value,
      colordescription: this.editForm.get(['colordescription'])!.value,
      destination: this.editForm.get(['destination'])!.value,
      destinationDesc: this.editForm.get(['destinationDesc'])!.value,
      line: this.editForm.get(['line'])!.value,
      lineDesc: this.editForm.get(['lineDesc'])!.value,
      createdby: this.editForm.get(['createdby'])!.value,
      createddate: this.editForm.get(['createddate'])!.value
        ? moment(this.editForm.get(['createddate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      issuedate:
        this.editForm.get(['issuedate'])!.value != null ? moment(this.editForm.get(['issuedate'])!.value, DATE_TIME_FORMAT) : undefined,
      lastupdatedby: this.editForm.get(['lastupdatedby'])!.value,
      lastupdateddate: this.editForm.get(['lastupdateddate'])!.value
        ? moment(this.editForm.get(['lastupdateddate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      scannedBy: this.editForm.get(['scannedBy'])!.value,
      postedBy: this.editForm.get(['postedBy'])!.value,
      postedDate: this.editForm.get(['postedDate'])!.value ? moment(this.editForm.get(['postedDate'])!.value, DATE_TIME_FORMAT) : undefined,
      stitchIssuePackDetails: this.stitchIssuePackDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPackingLineIssue>>, toast?: any): void {
    result.subscribe(() => this.onSaveSuccess(toast), () => this.onSaveError(toast));
  }

  protected onSaveSuccess(toast?: any): void {
    this.isSaving = false;
    this.isProcess = false;
    if (toast) {
      this.snotifyService.success('Post Successfully', '', toastConfig);
      this.previousState();
    } else {
      this.snotifyService.success('Save Successfully', '', toastConfig);
      this.previousState();
    }
  }

  protected onSaveError(toast?: any): void {
    this.isSaving = false;
    this.isProcess = false;
  }

  protected onError(index: number, res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
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
    this.packingLineIssueService.deleteDetails(stitchIssuePackDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.stitchIssuePackDetails.splice(index, 1);
    });
  }

  callDetails(): void {
    this.modalService.open(PackingLineProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  callTransfer(stitchLineIssueDetail: IPackingLineIssueDetails): void {
    this.ngbModalRef = this.modalService.open(PackingLineTransferSelectionComponent, { size: 'lg', backdrop: 'static' });
    this.ngbModalRef.componentInstance.plantCode = this.editForm.controls['plantCode'].value;
    this.ngbModalRef.componentInstance.stitchLineIssueDetail = stitchLineIssueDetail;
  }

  fetchLines(): void {
    if (this.editForm.controls['plantCode'].value) {
      this.userPlants.forEach(plant => {
        if (this.editForm.controls['plantCode'].value === plant.id.plantCode) {
          this.editForm.controls['plantDescription'].setValue(plant.plantDescription);
        }
      });
      this.packingLineIssueService.resourcesByPlantCode(this.editForm.controls['plantCode'].value).subscribe(resources => {
        this.resources = resources.body;
      });
    }
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

  selectLine(): void {
    if (this.editForm.controls['line'].value) {
      this.resources.forEach(resource => {
        if (resource.id.code === this.editForm.controls['line'].value) {
          this.editForm.controls['lineDesc'].setValue(resource.longdescription);
        }
      });
    } else {
      this.editForm.controls['lineDesc'].setValue(undefined);
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
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.packingLineIssueService.post(this.editForm.controls['id'].value), this.isProcess);
  }

  scanData(): void {
    const packingLineIssue = this.createFromForm();
    const modelRef = this.modalService.open(PackIssueLineSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
    modelRef.componentInstance.packLineIssue = packingLineIssue;
  }

  registerChangeInPackFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('packLineIssueEntryFilter', data => {
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
