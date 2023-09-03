import { AfterViewInit, Component,  OnInit,  ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IWorkerJoining } from 'app/shared/model/worker-joining.model';
import { WorkerJoiningService } from './worker-joining.service';
import { DesignationMaster, IDesignationMaster } from 'app/shared/model/designation-master.model';
import { DepartmentMaster, IDepartmentMaster } from 'app/shared/model/department-master.model';
import { DateTimeAdapter, OWL_DATE_TIME_LOCALE, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { IRelationMaster } from 'app/shared/model/relation-master.model';
import { IWorkerFamilyDetails, WorkerFamilyDetails } from 'app/shared/model/worker-family-details.model';
import { IBankMaster } from 'app/shared/model/bank-master.model';
import { IWorkerJobsDetails, WorkerJobsDetails } from 'app/shared/model/worker-jobs-details.model';
import { isMoment } from 'moment';
import { ILanguageMaster } from 'app/shared/model/language-master.model';
import { IWorkerLanguageDetails, WorkerLanguageDetails } from 'app/shared/model/worker-language-details.model';
import { IWorkerNominationDetails, WorkerNominationDetails } from 'app/shared/model/worker-nomination-details.model';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';
import { IWorkerAddressDetails, WorkerAddressDetails } from 'app/shared/model/worker-address-details.model';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';
import { RecruitmentStateMasterService } from 'app/entities/recruitment-state-master';
import { RecruitmentDistrictService } from 'app/entities/recruitment-district';
import { RecruitmentCityMasterService } from 'app/entities/recruitment-city-master';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';
import { IWorkerEducationDetails, WorkerEducationDetails } from 'app/shared/model/worker-education-details.model';
import { IEducationMaster } from 'app/shared/model/education-master.model';
import { IEducationTypeMaster } from 'app/shared/model/education-type-master.model';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';
import { RecruitmentDocumentMasterService } from 'app/entities/recruitment-document-master';
import { ModalDismissReasons, NgbModal, NgbModalRef, NgbTabset } from '@ng-bootstrap/ng-bootstrap';
import { IWorkerReferenceDetails, WorkerReferenceDetails } from 'app/shared/model/worker-reference-details.model';
import { WorkerDocumentDetails } from 'app/shared/model/worker-document-details.model';
import { FileUpload, IFileUpload } from 'app/shared/model/file-upload.model';
import * as FileSaver from 'file-saver';
import { IWorkerMaster } from 'app/shared/model/worker-master.model';
import { IOccupationMaster } from 'app/shared/model/occupation-master.model';
import { ICost } from 'app/shared/model/cost.model';
import { ISubdept } from 'app/shared/model/subdept.model';
import { ICatgory } from 'app/shared/model/catgory.model';
import { IFloor } from 'app/shared/model/floor.model';
import { IFoodcat } from 'app/shared/model/foodcat.model';
import { IGrade } from 'app/shared/model/grade.model';
import { ISection } from 'app/shared/model/section.model';
import { IStaffWork } from 'app/shared/model/staff-work.model';
import { IWoff } from 'app/shared/model/woff.model';
import { IShift } from 'app/shared/model/shift.model';
import { WorkerJoinFlowDetailsUpdateComponent } from './worker-join-flow-details-update.component';
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
  selector: 'jhi-worker-joining-update',
  templateUrl: './worker-joining-update.component.html',
  styleUrls: ['./flow.scss'],
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS },
    NgbTabset
  ]
})
export class WorkerJoiningUpdateComponent implements OnInit, AfterViewInit {
  workerJoining: IWorkerJoining;
  isSaving: boolean;
  bankmasters: IBankMaster[];
  eventSubscriber: Subscription;

  costs: ICost[];
  designationmasters: IDesignationMaster[];
  relationmasters: IRelationMaster[];
  departmentmasters: IDepartmentMaster[];
  occupationmasters: IOccupationMaster[];
  languagemasters: ILanguageMaster[];
  workerfamilydetails: IWorkerFamilyDetails[];
  nominationtypemasters: INominationTypeMaster[];
  recruitmentcountrymasters: IRecruitmentCountryMaster[];
  educationmasters: IEducationMaster[];
  educationtypemasters: IEducationTypeMaster[];
  institutemasters: IInstituteMaster[];
  shifts: IShift[];
  subdepts: ISubdept[];
  catgories?: ICatgory[];
  floors?: IFloor[];
  foodcats?: IFoodcat[];
  grades?: IGrade[];
  sections?: ISection[];
  staffWorks?: IStaffWork[];
  woffs?: IWoff[];
  recruitmentdocumentmasters: IRecruitmentDocumentMaster[];
  closeResult: string;
  createdDate: string;
  lastUpdatedDate: string;
  signatureImage: any;
  signatureImageFile: any;
  signatureId: number;
  signatureType: string;
  fileuploads: IFileUpload[];
  isDownloading = false;
  tabsInitialized = false;
  @ViewChild('tabs', { static: false }) public tabs: NgbTabset;
  retrieveDataResolver: any;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected workerJoiningService: WorkerJoiningService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected recruitmentStateMasterService: RecruitmentStateMasterService,
    protected recruitmentDistrictService: RecruitmentDistrictService,
    protected recruitmentCityMasterService: RecruitmentCityMasterService,
    protected recruitmentDocumentMasterService: RecruitmentDocumentMasterService,
    private modalService: NgbModal,
    private router: Router,
    protected eventManager: JhiEventManager
  ) {}

  ngAfterViewInit() {
    this.tabsInitialized = true;
  }

  saveImage(data) {
    this.signatureImage = data;
    const base64 = this.signatureImage.split(',')[1];
    const imageBlob = this.dataURItoBlob(base64);
    this.signatureImageFile = new File([imageBlob], 'signature.jpeg', { type: 'image/jpeg' });
    this.modalService.dismissAll('Cross click');
  }

  clearImages() {
    this.signatureImage = undefined;
    this.signatureImageFile = undefined;
  }

  pushPayCode() {
    this.workerJoining.payCode = this.workerJoining.cardNo;
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

  selectFile(event, workerDocumentDetail) {
    if (this.fileuploads && this.fileuploads.length > 0) {
      let exist = true;
      let ctr = 0;
      this.fileuploads.forEach(fileUpload => {
        if (fileUpload.docId === workerDocumentDetail.recruitmentDocumentMaster.id) {
          ++ctr;
        }
      });
      if (ctr > 0) {
        exist = true;
      } else {
        exist = false;
      }
      if (exist === false) {
        const fileUpload = new FileUpload();
        fileUpload.id = this.workerJoining.id;
        fileUpload.docId = workerDocumentDetail.recruitmentDocumentMaster.id;
        fileUpload.docType = workerDocumentDetail.documentType;
        const files = event.target.files;
        const fileForm = files.item(0);
        fileUpload.file = fileForm;
        this.fileuploads.push(fileUpload);
      }
    } else {
      const fileUpload = new FileUpload();
      fileUpload.id = this.workerJoining.id;
      fileUpload.docId = workerDocumentDetail.recruitmentDocumentMaster.id;
      fileUpload.docType = workerDocumentDetail.documentType;
      const files = event.target.files;
      const fileForm = files.item(0);
      fileUpload.file = fileForm;
      this.fileuploads.push(fileUpload);
    }
  }

  reCall() {
    this.workerJoiningService.find(this.workerJoining.workerRecruitment.id).subscribe(workerJoining => {
      this.workerJoining = workerJoining.body;
      if (this.workerJoining && this.workerJoining.bankMaster) {
        this.workerJoining.bankMasterId = this.workerJoining.bankMaster.id;
      }
      if (this.workerJoining && this.workerJoining.departmentMaster) {
        this.workerJoining.departmentMasterId = this.workerJoining.departmentMaster.id;
      }
      if (this.workerJoining && this.workerJoining.designationMaster) {
        this.workerJoining.designationMasterId = this.workerJoining.designationMaster.id;
      }
      this.createdDate = this.workerJoining.createdDate != null ? this.workerJoining.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.workerJoining.lastUpdatedDate != null ? this.workerJoining.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;

      if (this.workerJoining && this.workerJoining.id) {
        if (this.workerJoining && this.workerJoining.workerFamilyDetails) {
          for (let i = this.workerJoining.workerFamilyDetails.length; i < 8; i++) {
            this.workerJoining.workerFamilyDetails.push(new WorkerFamilyDetails());
          }
        } else {
          this.workerJoining.workerFamilyDetails = [];
          for (let i = this.workerJoining.workerFamilyDetails.length; i < 8; i++) {
            this.workerJoining.workerFamilyDetails.push(new WorkerFamilyDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerJobsDetails) {
          for (let i = this.workerJoining.workerJobsDetails.length; i < 8; i++) {
            this.workerJoining.workerJobsDetails.push(new WorkerJobsDetails());
          }
        } else {
          this.workerJoining.workerJobsDetails = [];
          for (let i = this.workerJoining.workerJobsDetails.length; i < 8; i++) {
            this.workerJoining.workerJobsDetails.push(new WorkerJobsDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerLanguageDetails) {
          for (let i = this.workerJoining.workerLanguageDetails.length; i < 8; i++) {
            this.workerJoining.workerLanguageDetails.push(new WorkerLanguageDetails());
          }
        } else {
          this.workerJoining.workerLanguageDetails = [];
          for (let i = this.workerJoining.workerLanguageDetails.length; i < 8; i++) {
            this.workerJoining.workerLanguageDetails.push(new WorkerLanguageDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerNominationDetails) {
          for (let i = this.workerJoining.workerNominationDetails.length; i < 8; i++) {
            this.workerJoining.workerNominationDetails.push(new WorkerNominationDetails());
          }
        } else {
          this.workerJoining.workerNominationDetails = [];
          for (let i = this.workerJoining.workerNominationDetails.length; i < 8; i++) {
            this.workerJoining.workerNominationDetails.push(new WorkerNominationDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerEducationDetails) {
          this.workerJoining.workerEducationDetails.forEach(workerEducationDetail => {
            this.fetchEduState(workerEducationDetail);
            this.fetchEduDistrict(workerEducationDetail);
            this.fetchEduCity(workerEducationDetail);
          });
          for (let i = this.workerJoining.workerEducationDetails.length; i < 8; i++) {
            this.workerJoining.workerEducationDetails.push(new WorkerEducationDetails());
          }
        } else {
          this.workerJoining.workerEducationDetails = [];
          for (let i = this.workerJoining.workerEducationDetails.length; i < 8; i++) {
            this.workerJoining.workerEducationDetails.push(new WorkerEducationDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerReferenceDetails) {
          for (let i = this.workerJoining.workerReferenceDetails.length; i < 8; i++) {
            this.workerJoining.workerReferenceDetails.push(new WorkerReferenceDetails());
          }
        } else {
          this.workerJoining.workerReferenceDetails = [];
          for (let i = this.workerJoining.workerReferenceDetails.length; i < 8; i++) {
            this.workerJoining.workerReferenceDetails.push(new WorkerReferenceDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerAddressDetails) {
          if (this.workerJoining.workerAddressDetails.length > 0) {
            this.workerJoining.workerAddressDetails.forEach(workerAddressDetail => {
              this.fetchState(workerAddressDetail);
              this.fetchDistrict(workerAddressDetail);
              this.fetchCity(workerAddressDetail);
            });
          } else {
            this.workerJoining.workerAddressDetails = [];
            const addressDetails1 = new WorkerAddressDetails();
            addressDetails1.addressType = 'P';
            this.workerJoining.workerAddressDetails.push(addressDetails1);

            const addressDetails2 = new WorkerAddressDetails();
            addressDetails2.addressType = 'C';
            this.workerJoining.workerAddressDetails.push(addressDetails2);
          }
        } else {
          this.workerJoining.workerAddressDetails = [];
          const addressDetails1 = new WorkerAddressDetails();
          addressDetails1.addressType = 'P';
          this.workerJoining.workerAddressDetails.push(addressDetails1);

          const addressDetails2 = new WorkerAddressDetails();
          addressDetails2.addressType = 'C';
          this.workerJoining.workerAddressDetails.push(addressDetails2);
        }

        this.workerJoiningService.findFamily(this.workerJoining.id).subscribe(workerFamilyDetails => {
          this.workerfamilydetails = workerFamilyDetails.body;
        });
      }
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.fileuploads = [];
    this.registerChangeInWorkFlow();
    this.activatedRoute.data.subscribe(({ workerJoining }) => {
      this.workerJoining = workerJoining;
      if (this.workerJoining && this.workerJoining.bankMaster) {
        this.workerJoining.bankMasterId = this.workerJoining.bankMaster.id;
      }
      if (this.workerJoining && this.workerJoining.departmentMaster) {
        this.workerJoining.departmentMasterId = this.workerJoining.departmentMaster.id;
      }
      if (this.workerJoining && this.workerJoining.designationMaster) {
        this.workerJoining.designationMasterId = this.workerJoining.designationMaster.id;
      }
      this.createdDate = this.workerJoining.createdDate != null ? this.workerJoining.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.workerJoining.lastUpdatedDate != null ? this.workerJoining.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;

      if (this.workerJoining && this.workerJoining.id) {
        if (this.workerJoining && this.workerJoining.workerFamilyDetails) {
          for (let i = this.workerJoining.workerFamilyDetails.length; i < 8; i++) {
            this.workerJoining.workerFamilyDetails.push(new WorkerFamilyDetails());
          }
        } else {
          this.workerJoining.workerFamilyDetails = [];
          for (let i = this.workerJoining.workerFamilyDetails.length; i < 8; i++) {
            this.workerJoining.workerFamilyDetails.push(new WorkerFamilyDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerJobsDetails) {
          for (let i = this.workerJoining.workerJobsDetails.length; i < 8; i++) {
            this.workerJoining.workerJobsDetails.push(new WorkerJobsDetails());
          }
        } else {
          this.workerJoining.workerJobsDetails = [];
          for (let i = this.workerJoining.workerJobsDetails.length; i < 8; i++) {
            this.workerJoining.workerJobsDetails.push(new WorkerJobsDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerLanguageDetails) {
          for (let i = this.workerJoining.workerLanguageDetails.length; i < 8; i++) {
            this.workerJoining.workerLanguageDetails.push(new WorkerLanguageDetails());
          }
        } else {
          this.workerJoining.workerLanguageDetails = [];
          for (let i = this.workerJoining.workerLanguageDetails.length; i < 8; i++) {
            this.workerJoining.workerLanguageDetails.push(new WorkerLanguageDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerNominationDetails) {
          for (let i = this.workerJoining.workerNominationDetails.length; i < 8; i++) {
            this.workerJoining.workerNominationDetails.push(new WorkerNominationDetails());
          }
        } else {
          this.workerJoining.workerNominationDetails = [];
          for (let i = this.workerJoining.workerNominationDetails.length; i < 8; i++) {
            this.workerJoining.workerNominationDetails.push(new WorkerNominationDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerEducationDetails) {
          this.workerJoining.workerEducationDetails.forEach(workerEducationDetail => {
            this.fetchEduState(workerEducationDetail);
            this.fetchEduDistrict(workerEducationDetail);
            this.fetchEduCity(workerEducationDetail);
          });
          for (let i = this.workerJoining.workerEducationDetails.length; i < 8; i++) {
            this.workerJoining.workerEducationDetails.push(new WorkerEducationDetails());
          }
        } else {
          this.workerJoining.workerEducationDetails = [];
          for (let i = this.workerJoining.workerEducationDetails.length; i < 8; i++) {
            this.workerJoining.workerEducationDetails.push(new WorkerEducationDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerReferenceDetails) {
          for (let i = this.workerJoining.workerReferenceDetails.length; i < 8; i++) {
            this.workerJoining.workerReferenceDetails.push(new WorkerReferenceDetails());
          }
        } else {
          this.workerJoining.workerReferenceDetails = [];
          for (let i = this.workerJoining.workerReferenceDetails.length; i < 8; i++) {
            this.workerJoining.workerReferenceDetails.push(new WorkerReferenceDetails());
          }
        }

        if (this.workerJoining && this.workerJoining.workerAddressDetails) {
          if (this.workerJoining.workerAddressDetails.length > 0) {
            this.workerJoining.workerAddressDetails.forEach(workerAddressDetail => {
              this.fetchState(workerAddressDetail);
              this.fetchDistrict(workerAddressDetail);
              this.fetchCity(workerAddressDetail);
            });
          } else {
            this.workerJoining.workerAddressDetails = [];
            const addressDetails1 = new WorkerAddressDetails();
            addressDetails1.addressType = 'P';
            this.workerJoining.workerAddressDetails.push(addressDetails1);

            const addressDetails2 = new WorkerAddressDetails();
            addressDetails2.addressType = 'C';
            this.workerJoining.workerAddressDetails.push(addressDetails2);
          }
        } else {
          this.workerJoining.workerAddressDetails = [];
          const addressDetails1 = new WorkerAddressDetails();
          addressDetails1.addressType = 'P';
          this.workerJoining.workerAddressDetails.push(addressDetails1);

          const addressDetails2 = new WorkerAddressDetails();
          addressDetails2.addressType = 'C';
          this.workerJoining.workerAddressDetails.push(addressDetails2);
        }

        this.workerJoiningService.findFamily(this.workerJoining.id).subscribe(workerFamilyDetails => {
          this.workerfamilydetails = workerFamilyDetails.body;
        });
      }
    });

    this.workerJoiningService.master().subscribe((res: HttpResponse<IWorkerMaster>) => {
      const workerMaster: IWorkerMaster = res.body;
      this.bankmasters = workerMaster.bankMasters;
      this.departmentmasters = workerMaster.departmentMasters;
      this.designationmasters = workerMaster.designationMasters;
      this.relationmasters = workerMaster.relationMasters;
      this.occupationmasters = workerMaster.occupationMasters;
      this.languagemasters = workerMaster.languageMasters;
      this.nominationtypemasters = workerMaster.nominationTypeMasters;
      this.recruitmentcountrymasters = workerMaster.countries;
      this.educationmasters = workerMaster.educationMasters;
      this.educationtypemasters = workerMaster.educationTypeMasters;
      this.institutemasters = workerMaster.instituteMasters;
      this.costs = workerMaster.costs;
      this.subdepts = workerMaster.subdepts;
      this.catgories = workerMaster.catgories;
      this.floors = workerMaster.floors;
      this.foodcats = workerMaster.foodcats;
      this.grades = workerMaster.grades;
      this.sections = workerMaster.sections;
      this.staffWorks = workerMaster.staffWorks;
      this.woffs = workerMaster.woffs;
      this.shifts = workerMaster.shifts;
    });

    this.recruitmentDocumentMasterService.queryByType('W').subscribe(
      (res: HttpResponse<IRecruitmentDocumentMaster[]>) => {
        this.recruitmentdocumentmasters = res.body;
        if (this.workerJoining && this.workerJoining.workerDocumentDetails && this.workerJoining.workerDocumentDetails.length > 0) {
          this.recruitmentdocumentmasters.forEach(recruitmentDocument => {
            let exist = false;
            let count = 0;
            this.workerJoining.workerDocumentDetails.forEach(workerDocumentDetail => {
              ++count;
              if (recruitmentDocument.id === workerDocumentDetail.recruitmentDocumentMaster.id) {
                exist = true;
              }
            });
            if (count === this.workerJoining.workerDocumentDetails.length && exist === false) {
              const workerDocumentDetails = new WorkerDocumentDetails();
              workerDocumentDetails.recruitmentDocumentMaster = recruitmentDocument;
              this.workerJoining.workerDocumentDetails.push(workerDocumentDetails);
            }
          });
        } else {
          this.workerJoining.workerDocumentDetails = [];
          this.recruitmentdocumentmasters.forEach(recruitmentDocument => {
            const workerDocumentDetails = new WorkerDocumentDetails();
            workerDocumentDetails.recruitmentDocumentMaster = recruitmentDocument;
            this.workerJoining.workerDocumentDetails.push(workerDocumentDetails);
          });
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    this.router.navigate(['/worker-joining']);
  }

  fetchState(workerAddressDetail: IWorkerAddressDetails) {
    if (workerAddressDetail && workerAddressDetail.recruitmentCountryMaster) {
      this.recruitmentStateMasterService.findByCountry(workerAddressDetail.recruitmentCountryMaster.id).subscribe(
        (res: HttpResponse<IRecruitmentStateMaster[]>) => {
          workerAddressDetail.recruitmentstatemasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchDistrict(workerAddressDetail: IWorkerAddressDetails) {
    if (workerAddressDetail && workerAddressDetail.recruitmentStateMaster) {
      this.recruitmentDistrictService.findByState(workerAddressDetail.recruitmentStateMaster.id).subscribe(
        (res: HttpResponse<IRecruitmentDistrict[]>) => {
          workerAddressDetail.recruitmentdistricts = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchCity(workerAddressDetail: IWorkerAddressDetails) {
    if (workerAddressDetail && workerAddressDetail.recruitmentDistrict) {
      this.recruitmentCityMasterService.findByDistrict(workerAddressDetail.recruitmentDistrict.id).subscribe(
        (res: HttpResponse<IRecruitmentCityMaster[]>) => {
          workerAddressDetail.recruitmentcitymasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchEduState(workerEducationDetail: IWorkerEducationDetails) {
    if (workerEducationDetail && workerEducationDetail.recruitmentCountryMaster) {
      this.recruitmentStateMasterService.findByCountry(workerEducationDetail.recruitmentCountryMaster.id).subscribe(
        (res: HttpResponse<IRecruitmentStateMaster[]>) => {
          workerEducationDetail.recruitmentstatemasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchEduDistrict(workerEducationDetail: IWorkerEducationDetails) {
    if (workerEducationDetail && workerEducationDetail.recruitmentStateMaster) {
      this.recruitmentDistrictService.findByState(workerEducationDetail.recruitmentStateMaster.id).subscribe(
        (res: HttpResponse<IRecruitmentDistrict[]>) => {
          workerEducationDetail.recruitmentdistricts = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  fetchEduCity(workerEducationDetail: IWorkerEducationDetails) {
    if (workerEducationDetail && workerEducationDetail.recruitmentDistrict) {
      this.recruitmentCityMasterService.findByDistrict(workerEducationDetail.recruitmentDistrict.id).subscribe(
        (res: HttpResponse<IRecruitmentCityMaster[]>) => {
          workerEducationDetail.recruitmentcitymasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  copyAddress(currentIndex: number) {
    this.workerJoining.workerAddressDetails.forEach((workerAddressDetailTemp, index) => {
      if (currentIndex + 1 === index) {
        const type = this.workerJoining.workerAddressDetails[index].addressType;
        this.workerJoining.workerAddressDetails[index] = this.workerJoining.workerAddressDetails[currentIndex];
        this.workerJoining.workerAddressDetails[index].addressType = type;
      }
    });
  }

  save() {
    this.isSaving = true;
    if (this.workerJoining.departmentMasterId) {
      this.workerJoining.departmentMaster = new DepartmentMaster();
      this.workerJoining.departmentMaster.id = this.workerJoining.departmentMasterId;
    }
    if (this.workerJoining.designationMasterId) {
      this.workerJoining.designationMaster = new DesignationMaster();
      this.workerJoining.designationMaster.id = this.workerJoining.designationMasterId;
    }
    this.workerJoining.joinDate = this.workerJoining.joinDate != null ? moment(this.workerJoining.joinDate, DATE_TIME_FORMAT) : null;
    this.workerJoining.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.workerJoining.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;

    if (this.workerJoining && this.workerJoining.workerJobsDetails) {
      this.workerJoining.workerJobsDetails.forEach(workerJobsDetails => {
        workerJobsDetails.fromDate = workerJobsDetails.fromDate != null ? moment(workerJobsDetails.fromDate) : null;
        workerJobsDetails.toDate = workerJobsDetails.toDate != null ? moment(workerJobsDetails.toDate) : null;
      });
    }

    if (this.workerJoining && this.workerJoining.id && this.workerJoining.id !== undefined) {
      this.subscribeToSaveResponse(this.workerJoiningService.update(this.workerJoining));
    } else {
      this.subscribeToSaveResponse(this.workerJoiningService.create(this.workerJoining));
    }
  }

  retrieveDataPromise(): Promise<any> {
    return new Promise(resolve => {
      this.retrieveDataResolver = resolve;
      this.approval();
    });
  }

  approval() {
    this.isSaving = true;

    // Tab 1 Validation (Personal)
    if (!this.workerJoining.gender) {
      this.snotifyService.error("Gender ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.joinDate) {
      this.snotifyService.error("Joining date ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.grade) {
      this.snotifyService.error("Grade ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.ctc) {
      this.snotifyService.error("CTC ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.motherName) {
      this.snotifyService.error("Mother Name ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.mobileNo) {
      this.snotifyService.error("Mobile ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.maritalStatus) {
      this.snotifyService.error("Marital Status ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.supervisorName) {
      this.snotifyService.error("Supervisor Name ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.departmentMasterId) {
      this.snotifyService.error("Department ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.designationMasterId) {
      this.snotifyService.error("Designation ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    } else if (!this.workerJoining.subdeptId) {
      this.snotifyService.error("Sub Department ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('personal');
      }
      return false;
    }

    // Tab 2 Validation (Addition Info)
    if (!this.workerJoining.cardNo) {
      this.snotifyService.error("Card No ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.secCode) {
      this.snotifyService.error("Line ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.gCode) {
      this.snotifyService.error("Leave Grade ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.swCode) {
      this.snotifyService.error("Staff/Worker ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.catCode) {
      this.snotifyService.error("Grade ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.flCode) {
      this.snotifyService.error("Work Unit ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.foodCode) {
      this.snotifyService.error("Food Grade ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.wCode) {
      this.snotifyService.error("Weekly Off ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.ot) {
      this.snotifyService.error("Overtime Mode ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.sftCode) {
      this.snotifyService.error("Shift ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.sftType) {
      this.snotifyService.error("Shift Type ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.punch) {
      this.snotifyService.error("Punching Mode ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    } else if (!this.workerJoining.salMode) {
      this.snotifyService.error("Salary Mode ca'nt be empty", '', toastConfig);
      this.isSaving = false;
      if (this.tabsInitialized) {
        this.tabs.select('addInfo');
      }
      return false;
    }

    // Tab 3 Validation (Address)
    this.workerJoining.workerAddressDetails.forEach(workerAddress => {
      if (!workerAddress.addressLine1) {
        this.snotifyService.error("Address Line ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      } else if (!workerAddress.recruitmentCountryMaster) {
        this.snotifyService.error("Country ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      } else if (!workerAddress.recruitmentStateMaster) {
        this.snotifyService.error("State ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      } else if (!workerAddress.recruitmentDistrict) {
        this.snotifyService.error("District ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      } else if (!workerAddress.recruitmentCityMaster) {
        this.snotifyService.error("City ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      } else if (!workerAddress.pinCode) {
        this.snotifyService.error("Pin Code ca'nt be empty", '', toastConfig);
        this.isSaving = false;
        if (this.tabsInitialized) {
          this.tabs.select('address');
        }
        return false;
      }
    });

    // Tab 4 Validation (Family)
    this.workerJoining.workerFamilyDetails.forEach(workerFamily => {
      if (workerFamily.name && workerFamily.name.length > 0) {
        if (!workerFamily.age) {
          this.snotifyService.error("Age ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('family');
          }
          return false;
        } else if (!workerFamily.relationMaster) {
          this.snotifyService.error("Relation ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('family');
          }
          return false;
        } else if (!workerFamily.occupationMasterId) {
          this.snotifyService.error("Occupation ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('family');
          }
          return false;
        } else if (!workerFamily.dependency) {
          this.snotifyService.error("Dependency ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('family');
          }
          return false;
        }
      }
    });

    // Tab 5 Validation (Nomination)
    this.workerJoining.workerNominationDetails.forEach(workerNomination => {
      if (workerNomination.workerFamilyDetails) {
        if (!workerNomination.nominationTypeMaster) {
          this.snotifyService.error("Nomination Type ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerNomination.nominationPercentage) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        }
      }
    });

    // Tab 7 Validation (Education)
    this.workerJoining.workerEducationDetails.forEach(workerEducation => {
      if (workerEducation.educationMaster) {
        if (!workerEducation.educationTypeMaster) {
          this.snotifyService.error("Education Type ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerEducation.instituteMaster) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerEducation.recruitmentCountryMaster) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerEducation.recruitmentStateMaster) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerEducation.recruitmentDistrict) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        } else if (!workerEducation.recruitmentCityMaster) {
          this.snotifyService.error("Percentage ca'nt be empty", '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('nomination');
          }
          return false;
        }
      }
    });

    // Tab 9 (Document Upload)
    this.workerJoining.workerDocumentDetails.forEach(workerDocuments => {
      if (workerDocuments.recruitmentDocumentMaster && workerDocuments.recruitmentDocumentMaster.documentMandatory) {
        if (!workerDocuments.documentType) {
          this.snotifyService.error(
            "Document type ca'nt be empty for " + workerDocuments.recruitmentDocumentMaster.description,
            '',
            toastConfig
          );
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('document');
          }
        } else if (!workerDocuments.remarks) {
          this.snotifyService.error("Remarks ca'nt be empty for " + workerDocuments.recruitmentDocumentMaster.description, '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('document');
          }
        } else if (workerDocuments.recruitmentDocumentMaster.attachType === 'C' && !workerDocuments.attached) {
          this.snotifyService.error(workerDocuments.recruitmentDocumentMaster.description + ' must be attach', '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('document');
          }
        } else if (
          workerDocuments.recruitmentDocumentMaster.attachType === 'U' &&
          workerDocuments.recruitmentDocumentMaster.description === 'Signature'
        ) {
          if (workerDocuments.fileName === undefined || workerDocuments.fileName === null || this.signatureImageFile === undefined || this.signatureImageFile === null) {
            this.snotifyService.error('Please upload signature', '', toastConfig);
            this.isSaving = false;
            if (this.tabsInitialized) {
              this.tabs.select('document');
            }
          }
        } else if (
          workerDocuments.recruitmentDocumentMaster.attachType === 'U' &&
          workerDocuments.recruitmentDocumentMaster.description !== 'Signature'
        ) {
          if (workerDocuments.fileName === undefined || workerDocuments.fileName === null) {
            this.snotifyService.error('Please upload file', '', toastConfig);
            this.isSaving = false;
            if (this.tabsInitialized) {
              this.tabs.select('document');
            }
          }
        }
      } else if (workerDocuments.documentType && workerDocuments.documentType.length > 0) {
        if (!workerDocuments.remarks) {
          this.snotifyService.error("Remarks ca'nt be empty for " + workerDocuments.recruitmentDocumentMaster.description, '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('document');
          }
        } else if (workerDocuments.recruitmentDocumentMaster.attachType === 'C' && !workerDocuments.attached) {
          this.snotifyService.error(workerDocuments.recruitmentDocumentMaster.description + ' must be attach', '', toastConfig);
          this.isSaving = false;
          if (this.tabsInitialized) {
            this.tabs.select('document');
          }
        } else if (
          workerDocuments.recruitmentDocumentMaster.attachType === 'U' &&
          workerDocuments.recruitmentDocumentMaster.description === 'Signature'
        ) {
          if (workerDocuments.fileName === undefined || workerDocuments.fileName === null || this.signatureImageFile === undefined || this.signatureImageFile === null) {
            this.snotifyService.error('Please upload signature', '', toastConfig);
            this.isSaving = false;
            if (this.tabsInitialized) {
              this.tabs.select('document');
            }
          }
        } else if (
          workerDocuments.recruitmentDocumentMaster.attachType === 'U' &&
          workerDocuments.recruitmentDocumentMaster.description !== 'Signature'
        ) {
          if (workerDocuments.fileName === undefined || workerDocuments.fileName === null) {
            this.snotifyService.error('Please upload file' + workerDocuments.recruitmentDocumentMaster.description, '', toastConfig);
            this.isSaving = false;
            if (this.tabsInitialized) {
              this.tabs.select('document');
            }
          }
        }
      }
    });
  }

  sendForApproval() {
    this.approval();
    if (this.isSaving) {
      this.open();
    }
  }

  open() {
    this.workFlow();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkerJoining>>) {
    result.subscribe((res: HttpResponse<IWorkerJoining>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IWorkerJoining>) {
    this.isSaving = false;
    if (this.signatureId && this.signatureImageFile) {
      this.workerJoiningService
        .uploadSignature(this.signatureImageFile, this.workerJoining.id, this.signatureId, this.signatureType)
        .subscribe(workerDocumentDetails => {
        });
      if (this.fileuploads && this.fileuploads.length > 0) {
        let ctr = 0;
        this.fileuploads.forEach(fileUpload => {
          ++ctr;
          this.workerJoiningService
            .uploadDocument(fileUpload.file, fileUpload.id, fileUpload.docId, fileUpload.docType)
            .subscribe(workerDocumentDetails => {});
        });
        if (ctr === this.fileuploads.length) {
          this.showMessage(result);
        }
      } else {
        this.showMessage(result);
      }
    } else if (this.fileuploads && this.fileuploads.length > 0) {
      let ctr = 0;
      this.fileuploads.forEach(fileUpload => {
        ++ctr;
        this.workerJoiningService
          .uploadDocument(fileUpload.file, fileUpload.id, fileUpload.docId, fileUpload.docType)
          .subscribe(workerDocumentDetails => {});
      });
      if (ctr === this.fileuploads.length) {
        this.showMessage(result);
      }
    } else {
      this.showMessage(result);
    }
  }

  showMessage(result) {
    this.workerJoining = result.body;
    this.snotifyService.success('Save Successfully!', '', toastConfig);
    this.router.navigate(['/worker-joining/' + result.body.workerRecruitment.id + '/edit', { save: new Date().getTime() }]);
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackDesignationMasterById(index: number, item: IDesignationMaster) {
    return item.id;
  }

  trackDepartmentMasterById(index: number, item: IDepartmentMaster) {
    return item.id;
  }

  calculateExp(workerJobsDetails: IWorkerJobsDetails) {
    if (workerJobsDetails.fromDate && workerJobsDetails.toDate) {
      let fromDate: any;
      let toDate: any;
      if (isMoment(workerJobsDetails.fromDate)) {
        fromDate = workerJobsDetails.fromDate;
      } else {
        fromDate = moment(workerJobsDetails.fromDate);
      }

      if (isMoment(workerJobsDetails.toDate)) {
        toDate = workerJobsDetails.toDate;
      } else {
        toDate = moment(workerJobsDetails.toDate);
      }
      const diff = toDate.diff(fromDate, 'years', true);
      workerJobsDetails.exp = diff.toFixed(2);
    }
  }

  deleteFamily(workerFamilyDetail: IWorkerFamilyDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.family(toast, workerFamilyDetail), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  family(toast, workerFamilyDetail: IWorkerFamilyDetails) {
    this.workerJoiningService.deleteFamily(workerFamilyDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerFamilyDetails.forEach((workerFamily, index) => {
        if (workerFamily.id && workerFamily.id === workerFamilyDetail.id) {
          this.workerJoining.workerFamilyDetails.splice(index, 1);
          this.workerJoining.workerFamilyDetails.push(new WorkerFamilyDetails());
        }
      });
    });
  }

  deleteExperience(workerJobDetail: IWorkerJobsDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.experience(toast, workerJobDetail), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  experience(toast, workerJobDetail: IWorkerJobsDetails) {
    this.workerJoiningService.deleteExperience(workerJobDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerJobsDetails.forEach((workerJobs, index) => {
        if (workerJobs.id && workerJobs.id === workerJobDetail.id) {
          this.workerJoining.workerJobsDetails.splice(index, 1);
          this.workerJoining.workerJobsDetails.push(new WorkerFamilyDetails());
        }
      });
    });
  }

  deleteLanguage(workerLanguageDetails: IWorkerLanguageDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.language(toast, workerLanguageDetails), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  language(toast, workerLanguageDetails: IWorkerLanguageDetails) {
    this.workerJoiningService.deleteLanguage(workerLanguageDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerLanguageDetails.forEach((workerLanguage, index) => {
        if (workerLanguage.id && workerLanguage.id === workerLanguageDetails.id) {
          this.workerJoining.workerLanguageDetails.splice(index, 1);
          this.workerJoining.workerLanguageDetails.push(new WorkerLanguageDetails());
        }
      });
    });
  }

  deleteNomination(workerNominationDetails: IWorkerNominationDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.nomination(toast, workerNominationDetails), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  nomination(toast, workerNominationDetails: IWorkerNominationDetails) {
    this.workerJoiningService.deleteNomination(workerNominationDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerNominationDetails.forEach((workerNomination, index) => {
        if (workerNomination.id && workerNomination.id === workerNominationDetails.id) {
          this.workerJoining.workerNominationDetails.splice(index, 1);
          this.workerJoining.workerNominationDetails.push(new WorkerNominationDetails());
        }
      });
    });
  }

  deleteEducation(workerEducationDetails: IWorkerEducationDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.education(toast, workerEducationDetails), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  education(toast, workerEducationDetails: IWorkerEducationDetails) {
    this.workerJoiningService.deleteEducation(workerEducationDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerEducationDetails.forEach((workerEducation, index) => {
        if (workerEducation.id && workerEducation.id === workerEducationDetails.id) {
          this.workerJoining.workerEducationDetails.splice(index, 1);
          this.workerJoining.workerEducationDetails.push(new WorkerEducationDetails());
        }
      });
    });
  }

  deleteReference(workerReferenceDetails: IWorkerReferenceDetails) {
    this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.reference(toast, workerReferenceDetails), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  reference(toast, workerReferenceDetails: IWorkerReferenceDetails) {
    this.workerJoiningService.deleteReference(workerReferenceDetails.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.workerJoining.workerReferenceDetails.forEach((workerReference, index) => {
        if (workerReference.id && workerReference.id === workerReferenceDetails.id) {
          this.workerJoining.workerReferenceDetails.splice(index, 1);
          this.workerJoining.workerReferenceDetails.push(new WorkerReferenceDetails());
        }
      });
    });
  }

  openSignature(content, workerDocumentDetail) {
    this.signatureId = workerDocumentDetail.recruitmentDocumentMaster.id;
    this.signatureType = workerDocumentDetail.documentType;
    if (workerDocumentDetail.fileName) {
      this.signatureImage = '../file_upload/workerrecruitemt/signature/' + workerDocumentDetail.fileName;
    }
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title', windowClass: 'sxlModal' }).result.then(
      result => {
        this.closeResult = `Closed with: ${result}`;
      },
      reason => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
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

  downloadSignature(id, fileName) {
    if (id) {
      this.isDownloading = true;
      this.workerJoiningService.downloadSignature(id).subscribe(
        res => {
          FileSaver.saveAs(res, fileName);
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }

  download(id, fileName) {
    if (id) {
      this.isDownloading = true;
      this.workerJoiningService.download(id).subscribe(
        res => {
          FileSaver.saveAs(res, fileName);
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }

  workFlow() {
    this.workerJoiningService.workFlow(this.workerJoining.id).subscribe(workerJoinFlow => {
      this.ngbModalRef = this.modalService.open(WorkerJoinFlowDetailsUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
      this.ngbModalRef.componentInstance.workerJoinFlow = workerJoinFlow.body;
    });
  }

  registerChangeInWorkFlow() {
    this.eventSubscriber = this.eventManager.subscribe('joinFlowForwardStatusModification', response => this.reCall());
  }
}
