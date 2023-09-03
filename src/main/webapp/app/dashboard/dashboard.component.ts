import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';
import { NewsDetailsService } from 'app/entities/news-details';
import { NewsDetails } from 'app/shared/model/news-details.model';
import {
  ModalDismissReasons,
  NgbCalendar,
  NgbCarouselConfig,
  NgbDate,
  NgbDateAdapter,
  NgbDateNativeAdapter,
  NgbDateStruct,
  NgbModal,
  NgbModalRef
} from '@ng-bootstrap/ng-bootstrap';
import { INewsDetailsAttach } from 'app/shared/model/news-details-attach.model';
import * as FileSaver from 'file-saver';
import { DashboardService } from 'app/entities/dashboard';
import { Dashboard } from 'app/shared/model/dashboard.model';
import { EmployeeView } from 'app/shared/model/employee-view.model';
import { WishBean } from 'app/shared/model/wish-bean.model';
import { IPollEmployeeDetails, PollEmployeeDetails } from 'app/shared/model/poll-employee-details.model';
import { IPollDetails } from 'app/shared/model/poll-details.model';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { PollEmployeeDetailsService } from 'app/entities/poll-employee-details';
import { IMessage } from 'app/shared/model/message.model';
import { NgbDatepickerNavigateEvent } from '@ng-bootstrap/ng-bootstrap/datepicker/datepicker';
import { IUser } from 'app/core/user/user.model';
import { AccountService } from 'app/core/auth/account.service';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { LoginService } from 'app/core/login/login.service';
import { StateStorageService } from 'app/core/auth/state-storage.service';
import { UserService } from 'app/core/user/user.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { Account } from 'app/core/user/account.model';
import { NewsLetterComponent } from 'app/dashboard/news-letter.component';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styles: [
    `
      .custom-day {
        text-align: center;
        padding: 0.3rem 0.25rem;
        border-radius: 2rem;
        display: inline-block;
        width: 1.854rem;
        height: 1.854rem;
      }

      .custom-day:hover,
      .custom-day.focused {
        background-color: #fff;
        border-radius: 2rem;
      }

      .weekend {
        background-color: rgba(30, 144, 255, 0.3);
        border-radius: 2rem;
        color: #fff !important;
      }

      .weekend:hover,
      .weekend.focused {
        background-color: rgba(30, 144, 255, 0.3);
        color: #fff !important;
      }

      .holiday {
        background-color: #f7fa32 !important;
        color: #000 !important;
        border-radius: 2rem;
      }

      .holiday:hover,
      .holiday.focused {
        background-color: #f7fa32 !important;
        color: #000 !important;
      }

      .validAttend {
        background-color: rgb(0, 255, 0);
        color: #000 !important;
        border-radius: 2rem;
      }

      .validAttend:hover,
      .validAttend.focused {
        background-color: rgb(0, 255, 0);
        color: #000 !important;
      }

      .leaveAttend {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgba(168, 82, 255) 40%,
          rgba(168, 82, 255) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #fff !important;
        border-radius: 2rem;
      }

      .leaveAttend:hover,
      .leaveAttend.focused {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgba(168, 82, 255) 40%,
          rgba(168, 82, 255) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #fff !important;
      }

      .overtimeAttend {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgb(255, 255, 0) 40%,
          rgb(255, 255, 0) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #000 !important;
        border-radius: 2rem;
      }

      .overtimeAttend:hover,
      .overtimeAttend.focused {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgb(255, 255, 0) 40%,
          rgb(255, 255, 0) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #000 !important;
      }

      .shortLeaveAttend {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgb(242, 243, 174) 40%,
          rgb(242, 243, 174) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #000 !important;
        border-radius: 2rem;
      }

      .shortLeaveAttend:hover,
      .shortLeaveAttend.focused {
        background: linear-gradient(
          180deg,
          rgb(0, 255, 0),
          rgb(0, 255, 0) 30%,
          rgb(242, 243, 174) 40%,
          rgb(242, 243, 174) 70%,
          rgb(0, 255, 0) 30%
        );
        color: #000 !important;
      }

      .firstHalfAttend {
        background: linear-gradient(180deg, rgb(255, 0, 0), rgb(255, 0, 0) 50%, rgb(0, 255, 0), rgb(0, 255, 0) 50%) !important;
        color: #fff !important;
        border-radius: 2rem;
      }

      .firstHalfAttend:hover,
      .firstHalfAttend.focused {
        background: linear-gradient(180deg, rgb(255, 0, 0), rgb(255, 0, 0) 50%, rgb(0, 255, 0), rgb(0, 255, 0) 50%) !important;
        color: #fff !important;
      }

      .secondHalfAttend {
        background: linear-gradient(180deg, rgb(0, 255, 0), rgb(0, 255, 0) 50%, rgb(255, 0, 0), rgb(255, 0, 0) 50%) !important;
        color: #fff !important;
        border-radius: 2rem;
      }

      .secondHalfAttend:hover,
      .secondHalfAttend.focused {
        background: linear-gradient(180deg, rgb(0, 255, 0), rgb(0, 255, 0) 50%, rgb(255, 0, 0), rgb(255, 0, 0) 50%) !important;
        color: #fff !important;
      }

      .invalidAttend {
        background-color: rgb(255, 0, 0);
        color: #fff !important;
        border-radius: 2rem;
      }

      .invalidAttend:hover,
      .invalidAttend.focused {
        background-color: rgb(255, 0, 0);
        color: #fff !important;
      }

      .singleAttend {
        background-color: rgb(168, 82, 255);
        color: #fff !important;
        border-radius: 2rem;
      }

      .singleAttend:hover,
      .singleAttend.focused {
        background-color: rgb(168, 82, 255);
        color: #fff !important;
      }

      .ltAttend {
        background-color: rgb(242, 243, 174);
        color: #000 !important;
        border-radius: 2rem;
      }

      .ltAttend:hover,
      .ltAttend.focused {
        background-color: rgb(242, 243, 174);
        color: #000 !important;
      }

      .statusDay {
        background-color: #3e4524;
        border-radius: 2rem;
        color: #fff !important;
      }

      .statusDay:hover,
      .statusDay.focused {
        background-color: #3e4524;
        color: #fff !important;
      }

      .hidden {
        display: none;
      }
    `
  ],
  providers: [NgbCarouselConfig, { provide: NgbDateAdapter, useClass: NgbDateNativeAdapter }] // add NgbCarouselConfig to the component providers
})
export class DashboardComponent implements OnInit {
  account: Account;
  dashboard: Dashboard;
  newsDetails: NewsDetails[];
  sidebarActive: string;
  closeResult: string;
  messageTitle: string;
  messageType: string;
  employeeView: EmployeeView;
  celebrationText: string;
  pollEmployeeDetails: IPollEmployeeDetails;
  pollDetails: IPollDetails;
  pollDetailsId: number;
  isSaving: boolean;
  options: any = { timepicker: true, format12h: true, inline: true };
  date: Date = new Date();
  model: NgbDateStruct;
  user: IUser;
  ngbModalRef: NgbModalRef;

  @ViewChild('carousel', { static: false }) carousel: any;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private loginService: LoginService,
    private stateStorageService: StateStorageService,
    private router: Router,
    private snotifyService: SnotifyService,
    private newsDetailsService: NewsDetailsService,
    config: NgbCarouselConfig,
    private jhiAlertService: JhiAlertService,
    private dashboardService: DashboardService,
    private modalService: NgbModal,
    private pollEmployeeDetailsService: PollEmployeeDetailsService,
    private calendar: NgbCalendar,
    private userService: UserService
  ) {
    config.interval = 10000;
    config.keyboard = false;
    config.pauseOnHover = true;
  }

  ngOnInit() {
    this.isSaving = false;
    this.accountService.identity().then(account => {
      if (account.ndaActivated !== true) {
        this.router.navigate(['nda']);
      }
    });
    this.model = this.calendar.getToday();
    this.pollEmployeeDetails = new PollEmployeeDetails();
    this.newsDetailsService.dashboard().subscribe(newsDetails => {
      this.newsDetails = newsDetails.body;
      this.newsDetails.forEach(newsDetail => {
        newsDetail.newsBody = '';
        newsDetail.newsDetailsBodies.forEach(newsDetailsBody => {
          newsDetail.newsBody += newsDetailsBody.newsBody;
        });
      });
    });
    this.dashboardService.query().subscribe(dashboard => {
      this.dashboard = dashboard.body;
      this.sidebarActive = 'birthday';
      this.pollEmployeeDetails.pollMaster = this.dashboard.pollMaster;
    });
    this.accountService.identity().then(account => {
      this.account = account;
    });
  }

  onNavigateChange($event: NgbDatepickerNavigateEvent) {
    let monthYear = '';
    if ($event.next.month < 10) {
      monthYear = '0' + $event.next.month + '-' + $event.next.year;
    } else {
      monthYear = $event.next.month + '-' + $event.next.year;
    }
    if (this.dashboard !== undefined) {
      this.dashboardService.calendar(monthYear).subscribe(dashboard => {
        this.dashboard.attendanceList = [];
        this.dashboard.attendanceList = dashboard.body.attendanceList;
      });
    }
  }

  chooseOption(pollDetails) {
    this.pollEmployeeDetails.pollDetails = pollDetails;
  }

  savePoll() {
    this.isSaving = true;
    this.subscribeToSavePollResponse(this.pollEmployeeDetailsService.create(this.pollEmployeeDetails));
  }

  protected subscribeToSavePollResponse(result: Observable<HttpResponse<IMessage>>) {
    result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res: HttpResponse<IMessage>) {
    this.isSaving = false;
    if (res.body.type === 'success') {
      this.snotifyService.success(res.body.msg, '', toastConfig);
    } else {
      this.snotifyService.error(res.body.msg, '', toastConfig);
    }
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  changeSidebar(type) {
    this.sidebarActive = type;
  }

  download(newsDetailsAttach: INewsDetailsAttach): any {
    this.newsDetailsService.download(newsDetailsAttach.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${newsDetailsAttach.attachDisplayFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  open(content, employeeView, subject, type) {
    this.employeeView = employeeView;
    this.messageTitle = subject;
    this.messageType = type;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      result => {
        this.closeResult = `Closed with: ${result}`;
      },
      reason => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  postMail() {
    if (this.employeeView !== null && this.messageType !== null && this.celebrationText !== null) {
      const wishBean = new WishBean();
      wishBean.employeeView = this.employeeView;
      wishBean.messageType = this.messageType;
      wishBean.celebrationMessageText = this.celebrationText;
      this.dashboardService.postmail(wishBean).subscribe(message => {
        if (message.body.type === 'success') {
          this.snotifyService.success(message.body.msg, '', toastConfig);
        } else {
          this.snotifyService.error(message.body.msg, '', toastConfig);
        }
        this.celebrationText = '';
        this.modalService.dismissAll();
      });
    }
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  isDisabled = (date: NgbDate, current: { month: number }) => true;
  isWeekend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'WO'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return this.calendar.getWeekday(date) > 6;
    }
  };
  isHoliday = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.holidayMastersList !== undefined) {
      this.dashboard.holidayMastersList.forEach(holidayMasters => {
        const holidayDateTemp = holidayMasters.holidayDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isValidAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag !== 'ERROR' &&
          attendance.flag !== 'SP' &&
          attendance.flag !== 'LT' &&
          attendance.flag !== 'H1' &&
          attendance.flag !== 'H2' &&
          attendance.flag !== 'LEAVE' &&
          attendance.flag !== 'OD' &&
          attendance.flag !== 'SL' &&
          attendance.flag !== 'WO'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isShortLeaveAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'SL'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isLeaveAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'LEAVE'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isOvertimeAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'OD'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isFirstHalfAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'H1'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isSecondHalfAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'H2'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isInValidAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'ERROR'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isSingleAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'SP'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isLtAttend = (date: NgbDate) => {
    let holidayDate = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const holidayDateTemp = attendance.attendanceDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year &&
          attendance.flag === 'LT'
        ) {
          holidayDate = holidayDateTemp;
        }
      });
    }
    if (holidayDate !== null) {
      return true;
    } else {
      return false;
    }
  };

  isHolidayTitle = (date: NgbDate) => {
    let holidayDate = null;
    let holidayTitle = null;
    if (this.dashboard !== undefined && this.dashboard.holidayMastersList !== undefined) {
      this.dashboard.holidayMastersList.forEach(holidayMasters => {
        const holidayDateTemp = holidayMasters.holidayDate.toDate();
        if (
          holidayDateTemp.getDate() === date.day &&
          holidayDateTemp.getMonth() + 1 === date.month &&
          holidayDateTemp.getFullYear() === date.year
        ) {
          holidayDate = holidayDateTemp;
          holidayTitle = holidayMasters.holidayName;
        }
      });
    }
    if (holidayDate !== null) {
      return holidayTitle;
    } else {
      return '';
    }
  };

  isAttendanceTitle = (date: NgbDate) => {
    let attendanceDate = null;
    let attendanceTitle = null;
    if (this.dashboard !== undefined && this.dashboard.attendanceList !== undefined) {
      this.dashboard.attendanceList.forEach(attendance => {
        const attendanceDateTemp = attendance.attendanceDate.toDate();
        if (
          attendanceDateTemp.getDate() === date.day &&
          attendanceDateTemp.getMonth() + 1 === date.month &&
          attendanceDateTemp.getFullYear() === date.year &&
          attendance.flag !== 'ERROR'
        ) {
          attendanceDate = attendanceDateTemp;
          if (attendance.msg !== null) {
            attendanceTitle = 'In- ' + attendance.inTime + ' | Out- ' + attendance.outTime + ' | ' + attendance.msg;
          } else {
            attendanceTitle = 'In- ' + attendance.inTime + ' | Out- ' + attendance.outTime;
          }
        } else if (
          attendanceDateTemp.getDate() === date.day &&
          attendanceDateTemp.getMonth() + 1 === date.month &&
          attendanceDateTemp.getFullYear() === date.year &&
          attendance.flag === 'ERROR'
        ) {
          attendanceDate = attendanceDateTemp;
          attendanceTitle = 'In- ' + attendance.inTime + ' | Out- ' + attendance.outTime + ' | ' + attendance.msg;
        }
      });
    }
    if (attendanceDate !== null) {
      return attendanceTitle;
    } else {
      return '';
    }
  };
  pause() {
    this.carousel.pause();
  }
  cycle() {
    this.carousel.cycle();
  }

  openMonthly(fileUrl) {
    this.ngbModalRef = this.modalService.open(NewsLetterComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
    this.ngbModalRef.componentInstance.fileUrl = fileUrl;
  }
}
