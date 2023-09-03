import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { WorkerRecruitmentService } from './worker-recruitment.service';
import { BankMaster, IBankMaster } from 'app/shared/model/bank-master.model';
import { BankMasterService } from 'app/entities/bank-master';
import { DesignationMaster, IDesignationMaster } from 'app/shared/model/designation-master.model';
import { DesignationMasterService } from 'app/entities/designation-master';
import { DepartmentMaster, IDepartmentMaster } from 'app/shared/model/department-master.model';
import { DepartmentMasterService } from 'app/entities/department-master';
import { WebcamUtil, WebcamImage } from 'ngx-webcam';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { NgxXml2jsonService } from 'ngx-xml2json';
import { isMoment } from 'moment';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AadharScanComponent } from './aadhar-scan.component';

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
  selector: 'jhi-worker-recruitment-update',
  templateUrl: './worker-recruitment-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class WorkerRecruitmentUpdateComponent implements OnInit {
  workerRecruitment: IWorkerRecruitment;
  isSaving: boolean;
  bankmasters: IBankMaster[];
  designationmasters: IDesignationMaster[];
  departmentmasters: IDepartmentMaster[];
  dobDp: any;
  createdDate: string;
  lastUpdatedDate: string;
  isAadhaar = false;
  isName = false;
  isDob = false;
  isFather = false;
  isAddress = false;
  showWebcam = true;
  allowCameraSwitch = true;
  multipleWebCamsAvailable = false;
  webcamImage: WebcamImage = null;
  trigger: Subject<void> = new Subject<void>();
  xml = null;
  base64: string;
  imageFile: File;

  protected ngbModalRef: NgbModalRef;

  @ViewChild('aadharNo', { static: true })
  aadharNo: any;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected workerRecruitmentService: WorkerRecruitmentService,
    protected bankMasterService: BankMasterService,
    protected designationMasterService: DesignationMasterService,
    protected departmentMasterService: DepartmentMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected modalService: NgbModal,
    private ngxXml2jsonService: NgxXml2jsonService,
    private router: Router,
    private eventManager: JhiEventManager
  ) {}

  public existCheck() {
  }

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInAadhar();
    this.activatedRoute.data.subscribe(({ workerRecruitment }) => {
      this.workerRecruitment = workerRecruitment;
      if (this.workerRecruitment && this.workerRecruitment.departmentMaster) {
        this.workerRecruitment.departmentMasterId = this.workerRecruitment.departmentMaster.id;
      }
      if (this.workerRecruitment && this.workerRecruitment.designationMaster) {
        this.workerRecruitment.designationMasterId = this.workerRecruitment.designationMaster.id;
      }
      if (this.workerRecruitment && this.workerRecruitment.bankMaster) {
        this.workerRecruitment.bankMasterId = this.workerRecruitment.bankMaster.id;
      }
      this.createdDate = this.workerRecruitment.createdDate != null ? this.workerRecruitment.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.workerRecruitment.lastUpdatedDate != null ? this.workerRecruitment.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    if (this.workerRecruitment && this.workerRecruitment.id) {
      this.isAadhaar = true;
      this.isName = true;
      this.isDob = true;
      this.isFather = true;
      this.isAddress = true;
    } else {
      this.aadharNo.nativeElement.focus();
    }
    this.bankMasterService.query().subscribe(
      (res: HttpResponse<IBankMaster[]>) => {
        this.bankmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.designationMasterService.query().subscribe(
      (res: HttpResponse<IDesignationMaster[]>) => {
        this.designationmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.departmentMasterService.query().subscribe(
      (res: HttpResponse<IDepartmentMaster[]>) => {
        this.departmentmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    WebcamUtil.getAvailableVideoInputs().then((MediaDevices: MediaDeviceInfo[]) => {
      this.multipleWebCamsAvailable = MediaDevices && MediaDevices.length > 1;
    });
  }

  previousState() {
    // Do something after
    this.router.navigate(['/worker-recruitment']).then(result1 => {});
  }

  clearPage() {
    // Do something after
    this.router.navigate(['/']).then(result => {
      this.router.navigate(['/worker-recruitment/new']).then(result1 => {});
    });
  }

  save() {
    this.isSaving = true;
    this.workerRecruitment.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.workerRecruitment.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.workerRecruitment.departmentMasterId) {
      this.workerRecruitment.departmentMaster = new DepartmentMaster();
      this.workerRecruitment.departmentMaster.id = this.workerRecruitment.departmentMasterId;
    }
    if (this.workerRecruitment.designationMasterId) {
      this.workerRecruitment.designationMaster = new DesignationMaster();
      this.workerRecruitment.designationMaster.id = this.workerRecruitment.designationMasterId;
    }
    if (this.workerRecruitment.bankMasterId) {
      this.workerRecruitment.bankMaster = new BankMaster();
      this.workerRecruitment.bankMaster.id = this.workerRecruitment.bankMasterId;
    }
    if (isMoment(this.workerRecruitment.dob)) {
      this.workerRecruitment.dob = moment(this.workerRecruitment.dob).format(DATE_FORMAT);
    }
    if (this.workerRecruitment.id !== undefined) {
      this.subscribeToSaveResponse(this.workerRecruitmentService.update(this.imageFile, this.workerRecruitment));
    } else {
      this.subscribeToSaveResponse(this.workerRecruitmentService.create(this.imageFile, this.workerRecruitment));
    }
  }

  saveNew() {
    this.isSaving = true;
    this.workerRecruitment.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.workerRecruitment.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.workerRecruitment.departmentMasterId) {
      this.workerRecruitment.departmentMaster = new DepartmentMaster();
      this.workerRecruitment.departmentMaster.id = this.workerRecruitment.departmentMasterId;
    }
    if (this.workerRecruitment.designationMasterId) {
      this.workerRecruitment.designationMaster = new DesignationMaster();
      this.workerRecruitment.designationMaster.id = this.workerRecruitment.designationMasterId;
    }
    if (this.workerRecruitment.bankMasterId) {
      this.workerRecruitment.bankMaster = new BankMaster();
      this.workerRecruitment.bankMaster.id = this.workerRecruitment.bankMasterId;
    }
    if (isMoment(this.workerRecruitment.dob)) {
      this.workerRecruitment.dob = moment(this.workerRecruitment.dob).format(DATE_FORMAT);
    }
    if (this.workerRecruitment.id !== undefined) {
      this.subscribeToSaveResponseNew(this.workerRecruitmentService.update(this.imageFile, this.workerRecruitment));
    } else {
      this.subscribeToSaveResponseNew(this.workerRecruitmentService.create(this.imageFile, this.workerRecruitment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerRecruitment>>) {
    result.subscribe((res: HttpResponse<IWorkerRecruitment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected subscribeToSaveResponseNew(result: Observable<HttpResponse<IWorkerRecruitment>>) {
    result.subscribe((res: HttpResponse<IWorkerRecruitment>) => this.onSaveSuccessNew(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.router.navigate(['/worker-recruitment']).then(result1 => {});
  }

  protected onSaveSuccessNew(res: HttpResponse<IWorkerRecruitment>) {
    this.isSaving = false;
    (async () => {
      // Do something before delay
      this.workerRecruitment = res.body;
      if (this.workerRecruitment && this.workerRecruitment.departmentMaster) {
        this.workerRecruitment.departmentMasterId = this.workerRecruitment.departmentMaster.id;
      }
      if (this.workerRecruitment && this.workerRecruitment.designationMaster) {
        this.workerRecruitment.designationMasterId = this.workerRecruitment.designationMaster.id;
      }
      if (this.workerRecruitment && this.workerRecruitment.bankMaster) {
        this.workerRecruitment.bankMasterId = this.workerRecruitment.bankMaster.id;
      }
      this.snotifyService.success('Save Successfully! Profile id: ' + res.body.id, '', toastConfig);

      await this.delay(5000);

      // Do something after
      this.router.navigate(['/']).then(result => {
        this.router.navigate(['/worker-recruitment/new']).then(result1 => {});
      });
    })();
  }

  private delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBankMasterById(index: number, item: IBankMaster) {
    return item.id;
  }

  trackDesignationMasterById(index: number, item: IDesignationMaster) {
    return item.id;
  }
  //  This is methods of capturing of Pictures by webcam
  public triggerSnapshot() {
    this.trigger.next();
    this.showWebcam = false;
  }
  public toggleWebcam(): void {
    this.showWebcam = true;
    this.webcamImage = null;
  }
  public getWebcam(): void {
    this.workerRecruitment.fileName = null;
  }
  public handleImage(webcamImage: WebcamImage): void {
    this.webcamImage = webcamImage;
    this.base64 = webcamImage.imageAsBase64;
    // Naming the image
    const date = new Date().valueOf();
    let text = '';
    const possibleText = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for (let i = 0; i < 5; i++) {
      text += possibleText.charAt(Math.floor(Math.random() * possibleText.length));
    }
    // Replace extension according to your media type
    const imageName = date + '.' + text + '.jpeg';
    // call method that creates a blob from dataUri
    const imageBlob = this.dataURItoBlob(this.base64);
    this.imageFile = new File([imageBlob], imageName, { type: 'image/jpeg' });
  }

  dataURItoBlob(dataURI) {
    const byteString = atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([arrayBuffer], { type: 'image/jpeg' });
    return blob;
  }

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }
  // This Method Reading Aadhar Card by Scanner and set values into Form also
  public getAadharDetail() {
    if (this.workerRecruitment.aadharNo) {
      this.xml = this.workerRecruitment.aadharNo;
      const parser = new DOMParser();
      const xml = parser.parseFromString(this.xml, 'text/xml');
      const obj: any = this.ngxXml2jsonService.xmlToJson(xml);
      const myObjStr = JSON.stringify(obj);
      JSON.parse(myObjStr, (key, value) => {
        if (typeof value === 'string') {
          return value.toUpperCase();
        }
        if (value.uid !== undefined) {
          this.workerRecruitment.aadharNo = value.uid;
          this.isAadhaar = true;
        }
        if (value.co !== undefined) {
          this.workerRecruitment.fatherName = value.co;
          this.isFather = true;
        } else if (value.careOf !== undefined) {
          this.workerRecruitment.fatherName = value.careOf;
          this.isFather = true;
        }

        if (value.dob !== undefined) {
          if (value.dob.lastIndexOf('-') !== -1) {
            if (value.dob.substring(value.dob.lastIndexOf('-') + 1, value.dob.length).length === 2) {
              this.workerRecruitment.dob = value.dob;
            } else if (value.dob.substring(value.dob.lastIndexOf('-') + 1, value.dob.length).length === 4) {
              const dob = moment(
                value.dob.substring(value.dob.lastIndexOf('-') + 1, value.dob.length) +
                  '-' +
                  value.dob.substring(value.dob.indexOf('-') + 1, value.dob.lastIndexOf('-')) +
                  '-' +
                  value.dob.substring(0, value.dob.indexOf('-'))
              );
              this.workerRecruitment.dob = dob;
            }
            this.isDob = true;
          } else if (value.dob.lastIndexOf('/') !== -1) {
            const dob = moment(
              value.dob.substring(value.dob.lastIndexOf('/') + 1, value.dob.length) +
                '-' +
                value.dob.substring(value.dob.indexOf('/') + 1, value.dob.lastIndexOf('/')) +
                '-' +
                value.dob.substring(0, value.dob.indexOf('/'))
            );
            this.workerRecruitment.dob = dob;
            this.isDob = true;
          }
        }

        if (value.dateOfBirth !== undefined) {
          if (value.dateOfBirth.lastIndexOf('-') !== -1) {
            if (value.dateOfBirth.substring(value.dateOfBirth.lastIndexOf('-') + 1, value.dateOfBirth.length).length === 2) {
              this.workerRecruitment.dob = value.dateOfBirth;
            } else if (value.dateOfBirth.substring(value.dateOfBirth.lastIndexOf('-') + 1, value.dateOfBirth.length).length === 4) {
              const dob = moment(
                value.dateOfBirth.substring(value.dateOfBirth.lastIndexOf('-') + 1, value.dateOfBirth.length) +
                  '-' +
                  value.dateOfBirth.substring(value.dateOfBirth.indexOf('-') + 1, value.dateOfBirth.lastIndexOf('-')) +
                  '-' +
                  value.dateOfBirth.substring(0, value.dateOfBirth.indexOf('-'))
              );
              this.workerRecruitment.dob = dob;
            }
            this.isDob = true;
          } else if (value.dateOfBirth.lastIndexOf('/') !== -1) {
            const dob = moment(
              value.dateOfBirth.substring(value.dateOfBirth.lastIndexOf('/') + 1, value.dateOfBirth.length) +
                '-' +
                value.dateOfBirth.substring(value.dateOfBirth.indexOf('/') + 1, value.dateOfBirth.lastIndexOf('/')) +
                '-' +
                value.dateOfBirth.substring(0, value.dateOfBirth.indexOf('/'))
            );
            this.workerRecruitment.dob = dob;
            this.isDob = true;
          }
        }
        if (value.name !== undefined) {
          this.workerRecruitment.name = value.name;
          this.isName = true;
        }
        if (value.state || value.stateName) {
          let address = '';
          if (value.house !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.house;
            } else {
              address = value.house;
            }
          }

          if (value.street !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.street;
            } else {
              address = value.street;
            }
          }
          if (value.loc !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.loc;
            } else {
              address = value.loc;
            }
          }
          if (value.po !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.po;
            } else {
              address = value.po;
            }
          }
          if (value.vtc !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.vtc;
            } else {
              address = value.vtc;
            }
          }
          if (value.dist !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.dist;
            } else {
              address = value.dist;
            }
          }
          if (value.state !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.state;
            } else {
              address = value.state;
            }
          }
          if (value.pc !== undefined) {
            if (address.length > 0) {
              address = address + ' - ' + value.pc;
            } else {
              address = value.pc;
            }
          }

          if (value.building !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.building;
            } else {
              address = value.building;
            }
          }

          if (value.locality !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.locality;
            } else {
              address = value.locality;
            }
          }

          if (value.poName !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.poName;
            } else {
              address = value.poName;
            }
          }

          if (value.vtcName !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.vtcName;
            } else {
              address = value.vtcName;
            }
          }

          if (value.subDistrictName !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.subDistrictName;
            } else {
              address = value.subDistrictName;
            }
          }

          if (value.districtName !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.districtName;
            } else {
              address = value.districtName;
            }
          }

          if (value.stateName !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.stateName;
            } else {
              address = value.stateName;
            }
          }

          if (value.pincode !== undefined) {
            if (address.length > 0) {
              address = address + ', ' + value.pincode;
            } else {
              address = value.pincode;
            }
          }

          this.workerRecruitment.address = address;
          this.isAddress = true;
        }
      });
    }
  }

  registerChangeInAadhar() {
    this.eventManager.subscribe('scanAadharCardByScanner', workerRecruitment => {
      if (workerRecruitment.content.exist === true) {
        this.workerRecruitment = workerRecruitment.content;
        this.isAddress = true;
        this.isAadhaar = true;
        this.isFather = true;
        this.isName = true;

        if (workerRecruitment.content.dob.lastIndexOf('-') !== -1) {
          if (
            workerRecruitment.content.dob.substring(
              workerRecruitment.content.dob.lastIndexOf('-') + 1,
              workerRecruitment.content.dob.length
            ).length === 4
          ) {
            const dob = moment(
              workerRecruitment.content.dob.substring(
                workerRecruitment.content.dob.lastIndexOf('-') + 1,
                workerRecruitment.content.dob.length
              ) +
                '-' +
                workerRecruitment.content.dob.substring(
                  workerRecruitment.content.dob.indexOf('-') + 1,
                  workerRecruitment.content.dob.lastIndexOf('-')
                ) +
                '-' +
                workerRecruitment.content.dob.substring(0, workerRecruitment.content.dob.indexOf('-'))
            );
            this.workerRecruitment.dob = dob;
          }
          this.isDob = true;
        }
      } else {
        this.isAddress = false;
        this.isAadhaar = false;
        this.isFather = false;
        this.isName = false;
        this.isDob = false;
        const aadhart = this.workerRecruitment.aadharNo;
        this.workerRecruitment = workerRecruitment.content;
        this.workerRecruitment.aadharNo = aadhart;
        this.snotifyService.error('Scan data and Aadhar data mismatched!!!', '', toastConfig);
      }
    });
  }

  scanAadhar() {
    this.ngbModalRef = this.modalService.open(AadharScanComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.aadhar = this.workerRecruitment.aadharNo;
  }
}
