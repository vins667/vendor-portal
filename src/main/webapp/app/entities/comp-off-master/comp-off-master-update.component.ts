import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CompOffMaster, ICompOffMaster } from 'app/shared/model/comp-off-master.model';
import { CompOffMasterService } from './comp-off-master.service';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { DateSearch } from 'app/shared/model/date-search.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { UserService } from 'app/core/user/user.service';
import { IUser } from 'app/core/user/user.model';
// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
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
  selector: 'jhi-comp-off-master-update',
  templateUrl: './comp-off-master-update.component.html',
  encapsulation: ViewEncapsulation.None,
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class CompOffMasterUpdateComponent implements OnInit {
  compOffMaster: ICompOffMaster;
  isSaving: boolean;
  compOffDate: string;
  availDate: string;
  hodApprovedDate: string;
  createdDate: string;
  employeeView: IEmployeeView;
  hodEmployeeView: IEmployeeView;

  constructor(
    protected compOffMasterService: CompOffMasterService,
    protected activatedRoute: ActivatedRoute,
    protected userService: UserService,
    protected employeeViewService: EmployeeViewService,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.compOffMaster = new CompOffMaster();
    this.userService.currentuser().subscribe((res: HttpResponse<IUser>) => {
      this.compOffMaster.userCode = res.body;
      this.employeeViewService.find(res.body.login).subscribe((res1: HttpResponse<IEmployeeView>) => {
        this.employeeView = res1.body;
        if (this.employeeView.supervisor !== undefined && this.employeeView.supervisor.includes('(') === true) {
          const hodcardNo = this.employeeView.supervisor.substring(0, this.employeeView.supervisor.indexOf('('));
          this.employeeViewService.findByCard(hodcardNo).subscribe((res2: HttpResponse<IEmployeeView>) => {
            this.hodEmployeeView = res2.body;
            this.compOffMaster.hodApprovedBy = res2.body.login.toLowerCase();
          });
        }
      });
    });
    this.activatedRoute.data.subscribe(({ compOffMaster }) => {
      this.compOffMaster = compOffMaster;
      this.compOffDate = this.compOffMaster.compOffDate != null ? this.compOffMaster.compOffDate.format(DATE_TIME_FORMAT) : null;
      this.availDate = this.compOffMaster.availDate != null ? this.compOffMaster.availDate.format(DATE_TIME_FORMAT) : null;
      this.hodApprovedDate =
        this.compOffMaster.hodApprovedDate != null ? this.compOffMaster.hodApprovedDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.compOffMaster.createdDate != null ? this.compOffMaster.createdDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  callCompOff() {
    if (this.compOffDate != null) {
      const dateSearch = new DateSearch();
      dateSearch.date = moment(this.compOffDate, DATE_TIME_FORMAT);
      this.compOffMasterService.fetch(dateSearch).subscribe(
        compOffMaster => {
          this.compOffMaster.timeFrom = compOffMaster.body.timeFrom;
          this.compOffMaster.timeTo = compOffMaster.body.timeTo;
          this.compOffMaster.balance = compOffMaster.body.balance;
        },
        (res: HttpErrorResponse) => {
          this.compOffMaster.timeFrom = null;
          this.compOffMaster.timeTo = null;
          this.compOffMaster.balance = null;
          this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
        }
      );
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.compOffMaster.compOffDate = this.compOffDate != null ? moment(this.compOffDate, DATE_TIME_FORMAT) : null;
    this.compOffMaster.availDate = this.availDate != null ? moment(this.availDate, DATE_TIME_FORMAT) : null;
    this.compOffMaster.hodApprovedDate = this.hodApprovedDate != null ? moment(this.hodApprovedDate, DATE_TIME_FORMAT) : null;
    this.compOffMaster.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    if (this.compOffMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.compOffMasterService.update(this.compOffMaster));
    } else {
      this.subscribeToSaveResponse(this.compOffMasterService.create(this.compOffMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompOffMaster>>) {
    result.subscribe((res: HttpResponse<ICompOffMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
