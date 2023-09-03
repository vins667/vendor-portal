import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStitchLineIssue, StitchLineIssue } from 'app/shared/model/stitch-line-issue.model';
import { StitchLineIssueService } from './stitch-line-issue.service';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { CutPlanEntryService } from 'app/cutting/cut-plan-entry/cut-plan-entry.service';
import { CutIssueStitchDetails, ICutIssueStitchDetails } from 'app/shared/model/cut-issue-stitch-details.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { ColorPalleteService } from 'app/core/util/color-pallete.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IResources } from 'app/shared/model/resources.model';
import { IStitchLineIssueDetails } from 'app/shared/model/stitch-line-issue-details.model';
import { StitchLineProductionorderSelectionComponent } from './stitch-line-productionorder-selection.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { JhiEventManager } from 'ng-jhipster';
import { CutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { StitchLineTransferSelectionComponent } from 'app/stitch/stitch-line-issue/stitch-line-transfer-selection.component';
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
  selector: 'jhi-stitch-line-issue-update',
  templateUrl: './stitch-line-issue-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class StitchLineIssueUpdateComponent implements OnInit {
  isSaving = false;
  isProcess: boolean;
  userPlants?: IUserPlant[] = [];
  stitchLineIssueDetails?: IStitchLineIssueDetails[] = [];
  stitchLineIssueDetailsTemp?: IStitchLineIssueDetails[] = [];
  public dataRemoteProject: RemoteData;
  colorCounter = 0;
  colors?: IMaster[] = [];
  destinations?: IMaster[];
  resources: IResources[] = [];
  eventSubscriber?: Subscription;
  protected ngbModalRef: NgbModalRef;

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
    postedDate: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  @ViewChildren('bundleCode') enteredbundleCodes;

  constructor(
    protected cutPlanIssueStitchService: StitchLineIssueService,
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
    this.dataRemoteProject = this.completerService.remote(this.cutPlanIssueStitchService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit(): void {
    this.isProcess = false;
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.activatedRoute.data.subscribe(({ cutPlanIssueStitch }) => {
      if (!cutPlanIssueStitch.id) {
        this.defaultDetails();
      } else {
        this.stitchLineIssueDetails = cutPlanIssueStitch.stitchLineIssueDetails;
        const lengthDetails = this.stitchLineIssueDetails.length;
        this.setColorcodes(lengthDetails - 1, lengthDetails);
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
        this.cutPlanIssueStitchService
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

  defaultDetails(): void {
    this.stitchLineIssueDetails = [];
    for (let i = 0; i < 5; i++) {
      this.stitchLineIssueDetails.push(new CutIssueStitchDetails());
    }
  }

  updateForm(stitchLineIssue: IStitchLineIssue): void {
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
      this.subscribeToSaveResponse(this.cutPlanIssueStitchService.update(cutPlanIssueStitch));
    } else {
      this.subscribeToSaveResponse(this.cutPlanIssueStitchService.create(cutPlanIssueStitch));
    }
  }

  fetchColors(): void {
    if (this.editForm.controls['projectcode'].value) {
      this.cutPlanIssueStitchService.style(this.editForm.controls['projectcode'].value).subscribe(style => {
        this.colors = style.body.colors;
      });
    } else {
      this.colors = [];
    }
  }

  private createFromForm(): IStitchLineIssue {
    return {
      ...new StitchLineIssue(),
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
      postedBy: this.editForm.get(['postedBy'])!.value,
      postedDate: this.editForm.get(['postedDate'])!.value ? moment(this.editForm.get(['postedDate'])!.value, DATE_TIME_FORMAT) : undefined,
      stitchLineIssueDetails: this.stitchLineIssueDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStitchLineIssue>>, toast?: any): void {
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

  fetchDetails(index: number) {
    if (this.stitchLineIssueDetails[index].bundleCode) {
      const lengthDetails = this.stitchLineIssueDetails.length;
      const search = new CutPlanSearch();
      search.id = Number(this.stitchLineIssueDetails[index].bundleCode);
      search.style = this.editForm.controls['style'].value;
      search.color = this.editForm.controls['color'].value;
      search.destination = this.editForm.controls['destination'].value;
      search.plantCode = this.editForm.controls['plantCode'].value;
      this.cutPlanIssueStitchService.bundle(search).subscribe(
        cutIssueStitchDetailTemp => {
          if (cutIssueStitchDetailTemp.body && cutIssueStitchDetailTemp.body.id) {
            let existIndex = 0;
            let exist = false;
            this.stitchLineIssueDetails.forEach(cutIssueStitchDetail => {
              if (cutIssueStitchDetailTemp.body.id === cutIssueStitchDetail.id) {
                exist = true;
              }
              ++existIndex;
            });
            if (existIndex === this.stitchLineIssueDetails.length && exist === false) {
              this.stitchLineIssueDetails[index] = cutIssueStitchDetailTemp.body;
              this.setColorcodes(index, lengthDetails);
            } else if (existIndex === this.stitchLineIssueDetails.length) {
              this.stitchLineIssueDetails[index].bundleCode = undefined;
              const groupId = 'field_bundleCode' + index;
              const input = this.elementRef.nativeElement.querySelector(`input[id='${groupId}']`);
              input.focus();
            }
          } else {
            this.cdRef.detectChanges();
            this.enteredbundleCodes.toArray()[index].nativeElement.focus();
            this.stitchLineIssueDetails[index].bundleCode = undefined;
          }
        },
        (error: HttpErrorResponse) => this.onError(index, error.headers)
      );
    }
  }

  protected onError(index: number, res: HttpHeaders) {
    this.stitchLineIssueDetails[index].bundleCode = undefined;
    const groupId = 'field_bundleCode' + index;
    const input = this.elementRef.nativeElement.querySelector(`input[id="${groupId}"]`);
    input.focus();
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  setColorcodes(headerIndex: number, headerLength: number): void {
    const sizeColorMap = new Map();
    this.colorCounter = 0;
    let index = 0;
    this.stitchLineIssueDetailsTemp = [];
    this.stitchLineIssueDetails.forEach(cutIssueStitchDetail => {
      if (cutIssueStitchDetail.itemtypecode && cutIssueStitchDetail.itemtypecode === 'CPT') {
        if (cutIssueStitchDetail && cutIssueStitchDetail.subcode08) {
          const copy = Object.assign({}, cutIssueStitchDetail);
          if (sizeColorMap.has(cutIssueStitchDetail.subcode08)) {
            copy.colorCode = this.colorPalleteService.colorCodes(sizeColorMap.get(cutIssueStitchDetail.subcode08));
            this.stitchLineIssueDetailsTemp.push(copy);
          } else {
            copy.colorCode = this.colorPalleteService.colorCodes(this.colorCounter);
            sizeColorMap.set(cutIssueStitchDetail.subcode08, this.colorCounter);
            this.stitchLineIssueDetailsTemp.push(copy);
            ++this.colorCounter;
          }
        }
      } else {
        if (cutIssueStitchDetail && cutIssueStitchDetail.subcode06) {
          const copy = Object.assign({}, cutIssueStitchDetail);
          if (sizeColorMap.has(cutIssueStitchDetail.subcode06)) {
            copy.colorCode = this.colorPalleteService.colorCodes(sizeColorMap.get(cutIssueStitchDetail.subcode06));
            this.stitchLineIssueDetailsTemp.push(copy);
          } else {
            copy.colorCode = this.colorPalleteService.colorCodes(this.colorCounter);
            sizeColorMap.set(cutIssueStitchDetail.subcode06, this.colorCounter);
            this.stitchLineIssueDetailsTemp.push(copy);
            ++this.colorCounter;
          }
        }
      }
      ++index;
    });
    if (index === this.stitchLineIssueDetails.length) {
      this.stitchLineIssueDetails = [];
      let tempIndex = 0;
      sizeColorMap.forEach((value: number, key: string) => {
        this.stitchLineIssueDetailsTemp.forEach(cutIssueStitchDetail => {
          ++tempIndex;
          if (cutIssueStitchDetail.itemtypecode && cutIssueStitchDetail.itemtypecode === 'CPT') {
            if (cutIssueStitchDetail && cutIssueStitchDetail.subcode08 && cutIssueStitchDetail.subcode08 === key) {
              const copy = Object.assign({}, cutIssueStitchDetail);
              this.stitchLineIssueDetails.push(copy);
            }
          } else {
            if (cutIssueStitchDetail && cutIssueStitchDetail.subcode06 && cutIssueStitchDetail.subcode06 === key) {
              const copy = Object.assign({}, cutIssueStitchDetail);
              this.stitchLineIssueDetails.push(copy);
            }
          }
        });
      });
      if (tempIndex === sizeColorMap.size * this.stitchLineIssueDetailsTemp.length) {
        if (headerLength === this.stitchLineIssueDetails.length) {
          this.stitchLineIssueDetails.push(new CutIssueStitchDetails());
          this.cdRef.detectChanges();
          this.enteredbundleCodes.toArray()[headerIndex + 1].nativeElement.focus();
        } else {
          let temp = 0;
          const loopLength = headerLength - this.stitchLineIssueDetails.length;
          for (let x = 0; x < loopLength; x++) {
            this.stitchLineIssueDetails.push(new CutIssueStitchDetails());
            ++temp;
          }
          if (temp === loopLength) {
            this.cdRef.detectChanges();
            this.enteredbundleCodes.toArray()[headerIndex + 1].nativeElement.focus();
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
      this.stitchLineIssueDetails.splice(index, 1);
      this.cdRef.detectChanges();
      let tempIndex = undefined;
      this.stitchLineIssueDetails.forEach((cutIssueStitchDetail, tIndex) => {
        if (!cutIssueStitchDetail.subcode08 && tempIndex === undefined) {
          tempIndex = tIndex;
        }
      });
      if (tempIndex || tempIndex === 0) {
        this.enteredbundleCodes.toArray()[tempIndex].nativeElement.focus();
      }
    }
  }

  generatePdf() {
    // this.spinner.show();
    this.cutPlanIssueStitchService.downloadPdf(this.editForm.get(['id']).value).subscribe(
      res => {
        FileSaver.saveAs(res, 'IssueToLine.pdf');
        // this.spinner.hide();
      },
      () => {
        // this.spinner.hide();
      }
    );
  }

  callDetails(): void {
    this.modalService.open(StitchLineProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  callTransfer(stitchLineIssueDetail: IStitchLineIssueDetails): void {
    this.ngbModalRef = this.modalService.open(StitchLineTransferSelectionComponent, { size: 'lg', backdrop: 'static' });
    this.ngbModalRef.componentInstance.plantCode = this.editForm.controls['plantCode'].value;
    this.ngbModalRef.componentInstance.stitchLineIssueDetail = stitchLineIssueDetail;
    this.ngbModalRef.result.then(
      result => {
        if (result === 'CLOSE') {
          this.previousState(); // Refresh Data in table grid
        }
      },
      reason => {}
    );
  }

  delete(toast, cutIssueDetail: ICutIssueStitchDetails, index?: number): void {
    this.cutPlanIssueStitchService.deleteDetails(cutIssueDetail.detailId).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.stitchLineIssueDetails.splice(index, 1);
      this.cdRef.detectChanges();
      let tempIndex = undefined;
      this.stitchLineIssueDetails.forEach((cutIssueStitchDetail, tIndex) => {
        if (!cutIssueStitchDetail.subcode08 && tempIndex === undefined) {
          tempIndex = tIndex;
        }
      });
      if (tempIndex || tempIndex === 0) {
        this.enteredbundleCodes.toArray()[tempIndex].nativeElement.focus();
      }
    });
  }

  fetchLines(): void {
    if (this.editForm.controls['plantCode'].value) {
      this.userPlants.forEach(plant => {
        if (this.editForm.controls['plantCode'].value === plant.id.plantCode) {
          this.editForm.controls['plantDescription'].setValue(plant.plantDescription);
        }
      });
      this.cutPlanIssueStitchService.resourcesByPlantCode(this.editForm.controls['plantCode'].value).subscribe(resources => {
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
    this.subscribeToSaveResponse(this.cutPlanIssueStitchService.post(this.editForm.controls['id'].value), this.isProcess);
  }
}
