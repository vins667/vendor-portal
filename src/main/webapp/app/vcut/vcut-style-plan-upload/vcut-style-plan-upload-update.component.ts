import { Component, OnInit, ViewEncapsulation } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { IVcutStylePlanUpload, VcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { VcutStylePlanUploadService } from './vcut-style-plan-upload.service';
import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';
import { VcutPlanChangeMasterService } from 'app/vcut/vcut-plan-change-master/vcut-plan-change-master.service';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { VcutStylePlanSessionBreakupComponent } from './vcut-style-plan-session-breakup.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { IResources } from 'app/shared/model/resources.model';
import { CutPlanMrkrEntryService } from 'app/cutting/cut-plan-mrkr-entry/cut-plan-mrkr-entry.service';
import { StitchLineIssueService } from 'app/stitch/stitch-line-issue/stitch-line-issue.service';
import { VcutLineProductionorderSelectionComponent } from './vcut-line-productionorder-selection.component';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
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
  selector: 'jhi-vcut-style-plan-upload-update',
  templateUrl: './vcut-style-plan-upload-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class VcutStylePlanUploadUpdateComponent implements OnInit {
  isSaving: boolean;
  vcutplanchangemasters: IVcutPlanChangeMaster[];
  userPlants?: IUserPlant[] = [];
  resources: IResources[] = [];
  planDateDp: any;
  colors?: IMaster[] = [];
  destinations?: IMaster[];
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;
  editForm = this.fb.group({
    id: [],
    planDate: [null, [Validators.required]],
    plantCode: [null, [Validators.required, Validators.maxLength(20)]],
    plantDescription: [null, [Validators.maxLength(100)]],
    floor: [null, [Validators.required, Validators.maxLength(20)]],
    lineNo: [null, [Validators.required, Validators.maxLength(50)]],
    lineDesc: [],
    poNoCounter: [null, [Validators.required, Validators.maxLength(20)]],
    poNo: [null, [Validators.required, Validators.maxLength(20)]],
    projectcode: [null, [Validators.required, Validators.maxLength(20)]],
    style: [null, [Validators.required, Validators.maxLength(20)]],
    color: [null, [Validators.required, Validators.maxLength(20)]],
    colorName: [null, [Validators.required, Validators.maxLength(100)]],
    destination: [null, [Validators.required, Validators.maxLength(100)]],
    destinationDesc: [null, [Validators.required, Validators.maxLength(100)]],
    buyer: [null, [Validators.maxLength(20)]],
    buyerName: [null, [Validators.maxLength(100)]],
    itemType: [null, [Validators.required, Validators.maxLength(20)]],
    itemName: [null, [Validators.required, Validators.maxLength(100)]],
    quantity: [null, [Validators.required]],
    kickOff: [null, [Validators.required]],
    smv: [null, [Validators.required]],
    days: [null, [Validators.required]],
    operators: [null, [Validators.required]],
    helpers: [null, [Validators.required]],
    workingHours: [],
    merchant: [null, [Validators.maxLength(50)]],
    merchantName: [null, [Validators.maxLength(100)]],
    createBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    vcutPlanChangeMaster: [null, Validators.required],
    vcutSessionMasterId: [],
    activePlan: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected cutPlanIssueStitchService: StitchLineIssueService,
    protected vcutStylePlanUploadService: VcutStylePlanUploadService,
    protected vcutPlanChangeMasterService: VcutPlanChangeMasterService,
    protected cutPlanEntryService: CutPlanMrkrEntryService,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    const leaveDateFrom = new Date();
    leaveDateFrom.setHours(0, 0, 0, 0);
    this.activatedRoute.data.subscribe(({ vcutStylePlanUpload }) => {
      this.updateForm(vcutStylePlanUpload, leaveDateFrom);
    });
    this.cutPlanEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
    this.vcutPlanChangeMasterService.query().subscribe(res => {
      this.vcutplanchangemasters = res.body;
    });
    this.registerChangeInOrderFilter();
  }

  updateForm(vcutStylePlanUpload: IVcutStylePlanUpload, leaveDateFrom?: any) {
    this.editForm.patchValue({
      id: vcutStylePlanUpload.id,
      planDate: vcutStylePlanUpload.planDate ? vcutStylePlanUpload.planDate : leaveDateFrom,
      plantCode: vcutStylePlanUpload.plantCode,
      plantDescription: vcutStylePlanUpload.plantDescription,
      floor: vcutStylePlanUpload.floor,
      lineNo: vcutStylePlanUpload.lineNo,
      lineDesc: vcutStylePlanUpload.lineDesc,
      poNoCounter: vcutStylePlanUpload.poNoCounter,
      poNo: vcutStylePlanUpload.poNo,
      projectcode: vcutStylePlanUpload.projectcode,
      style: vcutStylePlanUpload.style,
      color: vcutStylePlanUpload.color,
      colorName: vcutStylePlanUpload.colorName,
      destination: vcutStylePlanUpload.destination,
      destinationDesc: vcutStylePlanUpload.destinationDesc,
      buyer: vcutStylePlanUpload.buyer,
      buyerName: vcutStylePlanUpload.buyerName,
      itemType: vcutStylePlanUpload.itemType,
      itemName: vcutStylePlanUpload.itemName,
      quantity: vcutStylePlanUpload.quantity,
      kickOff: vcutStylePlanUpload.kickOff,
      smv: vcutStylePlanUpload.smv,
      days: vcutStylePlanUpload.days,
      operators: vcutStylePlanUpload.operators,
      helpers: vcutStylePlanUpload.helpers,
      workingHours: vcutStylePlanUpload.workingHours,
      merchant: vcutStylePlanUpload.merchant,
      merchantName: vcutStylePlanUpload.merchantName,
      createBy: vcutStylePlanUpload.createBy,
      createdDate: vcutStylePlanUpload.createdDate != null ? vcutStylePlanUpload.createdDate.format(DATE_TIME_FORMAT) : null,
      vcutPlanChangeMaster: vcutStylePlanUpload.vcutPlanChangeMaster,
      vcutSessionMasterId: vcutStylePlanUpload.vcutSessionMasterId,
      activePlan: vcutStylePlanUpload.activePlan
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const vcutStylePlanUpload = this.createFromForm();
    if (vcutStylePlanUpload.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutStylePlanUploadService.update(vcutStylePlanUpload));
    } else {
      this.subscribeToSaveResponse(this.vcutStylePlanUploadService.create(vcutStylePlanUpload));
    }
  }

  private createFromForm(): IVcutStylePlanUpload {
    return {
      ...new VcutStylePlanUpload(),
      id: this.editForm.get(['id']).value,
      planDate: this.editForm.get(['planDate']).value != null ? moment(this.editForm.get(['planDate']).value, DATE_TIME_FORMAT) : undefined,
      plantCode: this.editForm.get(['plantCode']).value,
      plantDescription: this.editForm.get(['plantDescription']).value,
      floor: this.editForm.get(['floor']).value,
      lineNo: this.editForm.get(['lineNo']).value,
      lineDesc: this.editForm.get(['lineDesc']).value,
      poNoCounter: this.editForm.get(['poNoCounter']).value,
      poNo: this.editForm.get(['poNo']).value,
      projectcode: this.editForm.get(['projectcode']).value,
      style: this.editForm.get(['style']).value,
      color: this.editForm.get(['color']).value,
      colorName: this.editForm.get(['colorName']).value,
      destination: this.editForm.get(['destination']).value,
      destinationDesc: this.editForm.get(['destinationDesc']).value,
      buyer: this.editForm.get(['buyer']).value,
      buyerName: this.editForm.get(['buyerName']).value,
      itemType: this.editForm.get(['itemType']).value,
      itemName: this.editForm.get(['itemName']).value,
      quantity: this.editForm.get(['quantity']).value,
      kickOff: this.editForm.get(['kickOff']).value,
      smv: this.editForm.get(['smv']).value,
      days: this.editForm.get(['days']).value,
      operators: this.editForm.get(['operators']).value,
      helpers: this.editForm.get(['helpers']).value,
      workingHours: this.editForm.get(['workingHours']).value,
      merchant: this.editForm.get(['merchant']).value,
      merchantName: this.editForm.get(['merchantName']).value,
      createBy: this.editForm.get(['createBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      vcutPlanChangeMaster: this.editForm.get(['vcutPlanChangeMaster']).value,
      vcutSessionMasterId: this.editForm.get(['vcutSessionMasterId']).value,
      activePlan: this.editForm.get(['activePlan']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutStylePlanUpload>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
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

  trackVcutPlanChangeMasterById(index: number, item: IVcutPlanChangeMaster) {
    return item.id;
  }

  attachPlan(id?: number) {
    this.vcutStylePlanUploadService.find(id).subscribe(vcutStylePlanUpload => {
      this.ngbModalRef = this.modalService.open(VcutStylePlanSessionBreakupComponent, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'sxlModal'
      });
      this.ngbModalRef.componentInstance.vcutStylePlanUpload = vcutStylePlanUpload.body;
    });
  }

  fetchLines(): void {
    if (this.editForm.get('plantCode').value) {
      this.userPlants.forEach(plant => {
        if (this.editForm.get('plantCode').value === plant.id.plantCode) {
          this.editForm.get('plantDescription').setValue(plant.plantDescription);
        }
      });
      this.cutPlanIssueStitchService.resourcesByPlantCode(this.editForm.get('plantCode').value).subscribe(resources => {
        this.resources = resources.body;
      });
    } else {
      this.editForm.get('plantDescription').setValue(undefined);
    }
  }

  selectLine(): void {
    if (this.editForm.get('lineNo').value) {
      this.resources.forEach(resource => {
        if (resource.id.code === this.editForm.get('lineNo').value) {
          this.editForm.get('lineDesc').setValue(resource.longdescription);
        }
      });
    } else {
      this.editForm.get('lineDesc').setValue(undefined);
    }
  }

  callDetails(): void {
    this.modalService.open(VcutLineProductionorderSelectionComponent, { size: 'lg', backdrop: 'static', windowClass: 'xlModal' });
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('vcutPlanEntryFilter', data => {
      const productionorder: IProductionorder = data.content;
      this.editForm.controls['poNoCounter'].setValue(productionorder.productionordercountercode.trim());
      this.editForm.controls['poNo'].setValue(productionorder.id.code.trim());
      if (this.editForm.controls['poNo'].value && this.editForm.controls['poNo'].value !== undefined) {
        this.cutPlanIssueStitchService.getAllDetailByPo(this.editForm.controls['poNo'].value).subscribe((master: HttpResponse<IMaster>) => {
          const styleMaster = master.body;
          this.editForm.controls['projectcode'].setValue(styleMaster.name.trim());
          this.editForm.controls['style'].setValue(styleMaster.desc.trim());
          this.editForm.controls['buyer'].setValue(styleMaster.buyer.trim());
          this.editForm.controls['buyerName'].setValue(styleMaster.buyerName.trim());
          this.editForm.controls['itemType'].setValue(styleMaster.itemType.trim());
          this.editForm.controls['itemName'].setValue(styleMaster.itemName.trim());
          this.fetchColors();
        });
      }
    });
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

  fetchReservationItem(): void {
    if (this.editForm.controls['color'].value) {
      this.colors.forEach(color => {
        if (color.name === this.editForm.controls['color'].value) {
          this.editForm.controls['colorName'].setValue(color.desc);
        }
      });
    }
    if (this.editForm.controls['projectcode'].value && this.editForm.controls['color'].value) {
      const search = new Master();
      search.id = this.editForm.controls['projectcode'].value;
      search.desc = this.editForm.controls['color'].value;
      search.code = this.editForm.controls['poNo'].value;

      this.cutPlanEntryService.queryColorByDestination(search).subscribe(destinations => {
        this.destinations = destinations.body;
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
}
