import { Component, OnInit, ViewEncapsulation } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IJobWorkFollowup, JobWorkFollowup } from './job-work-followup.model';
import { JobWorkFollowupService } from './job-work-followup.service';
import { Master } from 'app/shared/model/master.modal';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DayStatusService } from 'app/entities/day-status';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDayStatus } from 'app/shared/model/day-status.model';
import { IFollowupBuyer } from 'app/finance/followup-buyer/followup-buyer.model';
import { FollowupBuyerService } from 'app/finance/followup-buyer/followup-buyer.service';
import { SnotifyService } from 'ng-snotify';
import { UserService } from 'app/core/user/user.service';
import { JobWorkFollowupSearchComponent } from 'app/finance/job-work-followup/job-work-followup-search.component';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/leave-entry-hr';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { ShiftService } from 'app/entities/shift';
import { switchMap } from 'rxjs-compat/operator/switchMap';
import { JobWorkFollowupScheduleComponent } from 'app/finance/job-work-followup/job-work-followup-schedule.component';

@Component({
  selector: 'jhi-job-work-followup-update',
  templateUrl: './job-work-followup-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class JobWorkFollowupUpdateComponent implements OnInit {
  isSaving: boolean;
  selectUndefinedOptionValue: any;
  selectBuyerNamesLists: IJobWorkFollowup;
  buyercode: string;
  buyername: string;
  followpBuyers: IFollowupBuyer[];
  dateFrom: any;
  dateTo: any;
  createdDate: string;
  employeeView: IEmployeeView;
  hodEmployeeView: IEmployeeView;
  timeFrom: any;
  timeTo: any;
  balance: number;
  isTimeDisabled = false;
  isSubTypeDisabled = false;
  inTimeDisabled = false;
  outTimeDisabled = false;
  protected ngbModalRef: NgbModalRef;
  responsiblepersoncode01: string;
  responsiblepersonname01: string;
  name: string;
  cardNo: string;
  userType?: string;

  editForm = this.fb.group({
    id: [],
    jobworkcode: [null, [Validators.required, Validators.maxLength(20)]],
    jobworkname: [null, [Validators.required, Validators.maxLength(200)]],
    buyercode: [null, [Validators.required, Validators.maxLength(20)]],
    buyername: [null, [Validators.required, Validators.maxLength(200)]],
    responsiblepersoncode01: [null, [Validators.maxLength(50)]],
    responsiblepersonname01: [null, [Validators.maxLength(200)]],
    responsiblepersonmail01: [null, [Validators.maxLength(100)]],
    responsiblepersoncode02: [null, [Validators.maxLength(50)]],
    responsiblepersonname02: [null, [Validators.maxLength(200)]],
    responsiblepersonmail02: [null, [Validators.maxLength(100)]],
    responsiblepersoncode03: [null, [Validators.maxLength(50)]],
    responsiblepersonname03: [null, [Validators.maxLength(200)]],
    responsiblepersonmail03: [null, [Validators.maxLength(100)]],
    level01reminderpersoncode01: [null, [Validators.maxLength(50)]],
    level01reminderpersonname01: [null, [Validators.maxLength(200)]],
    level01reminderpersonmail01: [null, [Validators.maxLength(100)]],
    level01reminderpersoncode02: [null, [Validators.maxLength(50)]],
    level01reminderpersonname02: [null, [Validators.maxLength(200)]],
    level01reminderpersonmail02: [null, [Validators.maxLength(100)]],
    level01reminderpersoncode03: [null, [Validators.maxLength(50)]],
    level01reminderpersonname03: [null, [Validators.maxLength(200)]],
    level01reminderpersonmail03: [null, [Validators.maxLength(100)]],
    level02reminderpersoncode01: [null, [Validators.maxLength(50)]],
    level02reminderpersonname01: [null, [Validators.maxLength(200)]],
    level02reminderpersonmail01: [null, [Validators.maxLength(100)]],
    level02reminderpersoncode02: [null, [Validators.maxLength(50)]],
    level02reminderpersonname02: [null, [Validators.maxLength(200)]],
    level02reminderpersonmail02: [null, [Validators.maxLength(100)]],
    level02reminderpersoncode03: [null, [Validators.maxLength(50)]],
    level02reminderpersonname03: [null, [Validators.maxLength(200)]],
    level02reminderpersonmail03: [null, [Validators.maxLength(100)]]
  });
  private shift: any;

  constructor(
    protected jobWorkFollowupService: JobWorkFollowupService,
    protected activatedRoute: ActivatedRoute,
    protected jhiAlertService: JhiAlertService,
    protected followupBuyerService: FollowupBuyerService,
    private eventManager: JhiEventManager,
    protected snotifyService: SnotifyService,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    protected shiftService: ShiftService,
    private fb: FormBuilder,
    protected modalService: NgbModal
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.dateFrom = new Date();
    this.dateTo = new Date();
    this.timeTo = new Date();
    this.timeFrom = new Date();
    this.selectBuyerNamesLists = new JobWorkFollowup();
    this.selectBuyerNamesLists.flag = 'E';
    this.followupBuyerService.query().subscribe(
      (res: HttpResponse<IJobWorkFollowup[]>) => {
        this.followpBuyers = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.activatedRoute.data.subscribe(({ jobWorkFollowup }) => {
      this.updateForm(jobWorkFollowup);
    });
    this.registerChangeInSearchUser();
  }

  updateForm(jobWorkFollowup: IJobWorkFollowup) {
    this.editForm.patchValue({
      id: jobWorkFollowup.id,
      jobworkcode: jobWorkFollowup.jobworkcode,
      jobworkname: jobWorkFollowup.jobworkname,
      buyercode: jobWorkFollowup.buyercode,
      buyername: jobWorkFollowup.buyername,
      responsiblepersoncode01: jobWorkFollowup.responsiblepersoncode01,
      responsiblepersonname01: jobWorkFollowup.responsiblepersonname01,
      responsiblepersonmail01: jobWorkFollowup.responsiblepersonmail01,
      responsiblepersoncode02: jobWorkFollowup.responsiblepersoncode02,
      responsiblepersonname02: jobWorkFollowup.responsiblepersonname02,
      responsiblepersonmail02: jobWorkFollowup.responsiblepersonmail02,
      responsiblepersoncode03: jobWorkFollowup.responsiblepersoncode03,
      responsiblepersonname03: jobWorkFollowup.responsiblepersonname03,
      responsiblepersonmail03: jobWorkFollowup.responsiblepersonmail03,
      level01reminderpersoncode01: jobWorkFollowup.level01reminderpersoncode01,
      level01reminderpersonname01: jobWorkFollowup.level01reminderpersonname01,
      level01reminderpersonmail01: jobWorkFollowup.level01reminderpersonmail01,
      level01reminderpersoncode02: jobWorkFollowup.level01reminderpersoncode02,
      level01reminderpersonname02: jobWorkFollowup.level01reminderpersonname02,
      level01reminderpersonmail02: jobWorkFollowup.level01reminderpersonmail02,
      level01reminderpersoncode03: jobWorkFollowup.level01reminderpersoncode03,
      level01reminderpersonname03: jobWorkFollowup.level01reminderpersonname03,
      level01reminderpersonmail03: jobWorkFollowup.level01reminderpersonmail03,
      level02reminderpersoncode01: jobWorkFollowup.level02reminderpersoncode01,
      level02reminderpersonname01: jobWorkFollowup.level02reminderpersonname01,
      level02reminderpersonmail01: jobWorkFollowup.level02reminderpersonmail01,
      level02reminderpersoncode02: jobWorkFollowup.level02reminderpersoncode02,
      level02reminderpersonname02: jobWorkFollowup.level02reminderpersonname02,
      level02reminderpersonmail02: jobWorkFollowup.level02reminderpersonmail02,
      level02reminderpersoncode03: jobWorkFollowup.level02reminderpersoncode03,
      level02reminderpersonname03: jobWorkFollowup.level02reminderpersonname03,
      level02reminderpersonmail03: jobWorkFollowup.level02reminderpersonmail03
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const jobWorkFollowup = this.createFromForm();
    if (jobWorkFollowup.id !== undefined) {
      this.subscribeToSaveResponse(this.jobWorkFollowupService.update(jobWorkFollowup));
    } else {
      this.subscribeToSaveResponse(this.jobWorkFollowupService.create(jobWorkFollowup));
    }
  }

  buyerSelect(): void {
    if (this.editForm.controls['buyercode'].value) {
      this.followpBuyers.forEach(followpBuyer => {
        if (followpBuyer.buyercode === this.editForm.controls['buyercode'].value) {
          this.editForm.controls['buyername'].setValue(followpBuyer.buyername);
        }
      });
    } else {
      this.editForm.controls['buyername'].setValue(undefined);
    }
  }

  private createFromForm(): IJobWorkFollowup {
    return {
      ...new JobWorkFollowup(),
      id: this.editForm.get(['id']).value,
      jobworkcode: this.editForm.get(['jobworkcode']).value,
      jobworkname: this.editForm.get(['jobworkname']).value,
      buyercode: this.editForm.get(['buyercode']).value,
      buyername: this.editForm.get(['buyername']).value,
      responsiblepersoncode01: this.editForm.get(['responsiblepersoncode01']).value,
      responsiblepersonname01: this.editForm.get(['responsiblepersonname01']).value,
      responsiblepersonmail01: this.editForm.get(['responsiblepersonmail01']).value,
      responsiblepersoncode02: this.editForm.get(['responsiblepersoncode02']).value,
      responsiblepersonname02: this.editForm.get(['responsiblepersonname02']).value,
      responsiblepersonmail02: this.editForm.get(['responsiblepersonmail02']).value,
      responsiblepersoncode03: this.editForm.get(['responsiblepersoncode03']).value,
      responsiblepersonname03: this.editForm.get(['responsiblepersonname03']).value,
      responsiblepersonmail03: this.editForm.get(['responsiblepersonmail03']).value,
      level01reminderpersoncode01: this.editForm.get(['level01reminderpersoncode01']).value,
      level01reminderpersonname01: this.editForm.get(['level01reminderpersonname01']).value,
      level01reminderpersonmail01: this.editForm.get(['level01reminderpersonmail01']).value,
      level01reminderpersoncode02: this.editForm.get(['level01reminderpersoncode02']).value,
      level01reminderpersonname02: this.editForm.get(['level01reminderpersonname02']).value,
      level01reminderpersonmail02: this.editForm.get(['level01reminderpersonmail02']).value,
      level01reminderpersoncode03: this.editForm.get(['level01reminderpersoncode03']).value,
      level01reminderpersonname03: this.editForm.get(['level01reminderpersonname03']).value,
      level01reminderpersonmail03: this.editForm.get(['level01reminderpersonmail03']).value,
      level02reminderpersoncode01: this.editForm.get(['level02reminderpersoncode01']).value,
      level02reminderpersonname01: this.editForm.get(['level02reminderpersonname01']).value,
      level02reminderpersonmail01: this.editForm.get(['level02reminderpersonmail01']).value,
      level02reminderpersoncode02: this.editForm.get(['level02reminderpersoncode02']).value,
      level02reminderpersonname02: this.editForm.get(['level02reminderpersonname02']).value,
      level02reminderpersonmail02: this.editForm.get(['level02reminderpersonmail02']).value,
      level02reminderpersoncode03: this.editForm.get(['level02reminderpersoncode03']).value,
      level02reminderpersonname03: this.editForm.get(['level02reminderpersonname03']).value,
      level02reminderpersonmail03: this.editForm.get(['level02reminderpersonmail03']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobWorkFollowup>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  loadTypes() {}

  trackLeaveTypeMasterById(index: number, item: IFollowupBuyer) {
    return item.id;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  searchEmployee(userType?: string) {
    this.userType = userType;
    this.ngbModalRef = this.modalService.open(JobWorkFollowupSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  registerChangeInSearchUser() {
    this.eventManager.subscribe('followUpSelectedUserLinkCreation', message => {
      const userDetails = message.content;
      if (this.userType === 'responsiblepersoncode01') {
        this.editForm.controls['responsiblepersoncode01'].setValue(userDetails.cardNo);
        this.editForm.controls['responsiblepersonname01'].setValue(userDetails.name);
        this.editForm.controls['responsiblepersonmail01'].setValue(userDetails.email);
      } else if (this.userType === 'responsiblepersoncode02') {
        this.editForm.controls['responsiblepersoncode02'].setValue(userDetails.cardNo);
        this.editForm.controls['responsiblepersonname02'].setValue(userDetails.name);
        this.editForm.controls['responsiblepersonmail02'].setValue(userDetails.email);
      } else if (this.userType === 'responsiblepersoncode03') {
        this.editForm.controls['responsiblepersoncode03'].setValue(userDetails.cardNo);
        this.editForm.controls['responsiblepersonname03'].setValue(userDetails.name);
        this.editForm.controls['responsiblepersonmail03'].setValue(userDetails.email);
      } else if (this.userType === 'level01reminderpersoncode01') {
        this.editForm.controls['level01reminderpersoncode01'].setValue(userDetails.cardNo);
        this.editForm.controls['level01reminderpersonname01'].setValue(userDetails.name);
        this.editForm.controls['level01reminderpersonmail01'].setValue(userDetails.email);
      } else if (this.userType === 'level01reminderpersoncode02') {
        this.editForm.controls['level01reminderpersoncode02'].setValue(userDetails.cardNo);
        this.editForm.controls['level01reminderpersonname02'].setValue(userDetails.name);
        this.editForm.controls['level01reminderpersonmail02'].setValue(userDetails.email);
      } else if (this.userType === 'level01reminderpersoncode03') {
        this.editForm.controls['level01reminderpersoncode03'].setValue(userDetails.cardNo);
        this.editForm.controls['level01reminderpersonname03'].setValue(userDetails.name);
        this.editForm.controls['level01reminderpersonmail03'].setValue(userDetails.email);
      } else if (this.userType === 'level02reminderpersoncode01') {
        this.editForm.controls['level02reminderpersoncode01'].setValue(userDetails.cardNo);
        this.editForm.controls['level02reminderpersonname01'].setValue(userDetails.name);
        this.editForm.controls['level02reminderpersonmail01'].setValue(userDetails.email);
      } else if (this.userType === 'level02reminderpersoncode02') {
        this.editForm.controls['level02reminderpersoncode02'].setValue(userDetails.cardNo);
        this.editForm.controls['level02reminderpersonname02'].setValue(userDetails.name);
        this.editForm.controls['level02reminderpersonmail02'].setValue(userDetails.email);
      } else if (this.userType === 'level02reminderpersoncode03') {
        this.editForm.controls['level02reminderpersoncode03'].setValue(userDetails.cardNo);
        this.editForm.controls['level02reminderpersonname03'].setValue(userDetails.name);
        this.editForm.controls['level02reminderpersonmail03'].setValue(userDetails.email);
      }
    });
  }

  selectTimeline() {
    this.ngbModalRef = this.modalService.open(JobWorkFollowupScheduleComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }
}
