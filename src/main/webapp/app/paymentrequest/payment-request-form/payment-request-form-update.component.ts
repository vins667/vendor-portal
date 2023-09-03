import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PaymentRequestFormService } from 'app/paymentrequest/payment-request-form/payment-request-form.service';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { SnotifyService } from 'ng-snotify';
import { UserService } from 'app/core/user/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IPaymentRequestForm, PaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { MY_MOMENT_FORMATS } from 'app/entities/mmr-master';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { ICompany } from 'app/shared/db2/model/company.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { IDivision } from 'app/shared/db2/model/division.model';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { IFactory } from 'app/shared/model/factory.model';
import { FactoryService } from 'app/shared/db2/service/factory.service';
import { PaymentmethodService } from 'app/shared/db2/service/paymentmethod.service';
import { OutstandingBean } from 'app/shared/db2/model/outstanding-bean.model';
import { PaymentRequestForwardService } from 'app/paymentrequest/payment-request-forward/service/payment-request-forward.service';
import { IPaymentRequestForward } from 'app/paymentrequest/payment-request-forward/payment-request-forward.model';
import {
  IPaymentRequestFormDetails,
  PaymentRequestFormDetails
} from 'app/paymentrequest/payment-request-form/payment-request-form-details.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { IPaymentRequestInvoice, PaymentRequestInvoice } from 'app/paymentrequest/payment-request-form/payment-request-invoice.model';
import { ITdsDetail } from 'app/paymentrequest/payment-request-form/tds-detail.model';
import { IPaymentmethod } from 'app/shared/db2/model/paymentmethod.model';
import { PopupComponent } from 'app/shared/popup/popup.component';

@Component({
  selector: 'jhi-payment-request-form-update',
  templateUrl: './payment-request-form-update.component.html',
  styleUrls: ['./payment-request-form.scss'],
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class PaymentRequestFormUpdateComponent implements OnInit {
  isSaving: boolean;
  isInvoice = false;
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  paymentRequestInvoices?: IPaymentRequestInvoice[];
  dateRequest: any;
  datePo: any;
  dateInvoice: any;
  public dataRemotePaymentmethod: RemoteData;
  public dataRemoteSupplier: RemoteData;
  public dataRemotePurchaseorder: RemoteData;
  public dataRemotePurchaseinvoice: RemoteData;
  dateUtr: any;
  paymentRequestForms: IPaymentRequestForm;
  files: any = [];
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];
  loading = false; // Flag variable
  file: File = null; // Variable to store file
  maxdate?: any;
  datePl: any;
  forwardType?: string;
  forwardCode?: string;
  forwardName?: string;
  remarks?: string;
  paymentRequestForwards?: IPaymentRequestForward[];
  paymentRequestFormDetails?: IPaymentRequestFormDetails[];
  tdsDetails?: ITdsDetail[];
  paymentmethods?: IPaymentmethod[];

  editForm = this.fb.group({
    id: [],
    company: [null, [Validators.required, Validators.maxLength(3)]],
    division: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcode: [null, [Validators.required, Validators.maxLength(10)]],
    paymentType: [null, [Validators.required, Validators.maxLength(2)]],
    requestNo: [null, [Validators.maxLength(50)]],
    requestDate: [],
    supplierType: [null, [Validators.required, Validators.maxLength(1)]],
    supplierCode: [null, [Validators.required, Validators.maxLength(8)]],
    supplierName: [null, [Validators.maxLength(200)]],
    supplierGstName: [null, [Validators.maxLength(200)]],
    gstin: [null, [Validators.maxLength(20)]],
    gstrBFilling: [null, [Validators.maxLength(50)]],
    gstrFillingMonth: [null, [Validators.maxLength(50)]],
    countrycode: [],
    currencycode: [],
    gstr2a: [],
    msmeNo: [null, [Validators.maxLength(50)]],
    requestType: 'P',
    potype: 'PO_GENERAL',
    poNo: [null, [Validators.maxLength(50)]],
    poDate: [],
    piNo: [null, [Validators.maxLength(50)]],
    piDate: [],
    invoiceNo: [null, [Validators.maxLength(500)]],
    invoiceDate: [],
    paymenttermcode: [null, [Validators.required, Validators.maxLength(3)]],
    paymenttermdesc: [null, [Validators.maxLength(100)]],
    conversionRate: [],
    piAmount: [],
    outstandingAmount: [],
    paymentRelease: [null, [Validators.required]],
    poBasic: [],
    poGst: [],
    totalPoValue: [],
    piBasic: [],
    piGstPerc: [],
    piGst: [],
    totalPiValue: [],
    freightRequired: 'N',
    freightValue: [],
    requestAmount: [],
    requestGst: [],
    tdsType: [null, [Validators.maxLength(50)]],
    tdsValue: [],
    totalReleaseAmount: [],
    chequeNo: [null, [Validators.maxLength(50)]],
    utrNo: [null, [Validators.maxLength(50)]],
    utrDate: [],
    findocbusinessunitcode: [],
    findocfinancialyearcode: [],
    findoccode: [],
    forwardCode: [],
    forwardName: [],
    poFile: [null, [Validators.maxLength(50)]],
    piFile: [null, [Validators.maxLength(50)]],
    remarks: [null, [Validators.maxLength(50)]],
    status: [],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    approvedBy: [null, [Validators.maxLength(50)]],
    approvedDate: [],
    invoices: []
  });
  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected paymentRequestFormService: PaymentRequestFormService,
    protected paymentRequestForwardService: PaymentRequestForwardService,
    protected activatedRoute: ActivatedRoute,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected factoryService: FactoryService,
    protected paymentmethodService: PaymentmethodService,
    protected finbusinessunitService: FinbusinessunitService,
    private fb: FormBuilder,
    protected snotifyService: SnotifyService,
    protected userService: UserService,
    protected modalService: NgbModal,
    public completerService: CompleterService,
    protected jhiAlertService: JhiAlertService
  ) {
    this.dataRemotePaymentmethod = this.completerService.remote(
      this.paymentRequestFormService.resourceUrlPaymentmethod,
      'longdescription',
      'longdescription'
    );
    this.dataRemoteSupplier = this.completerService.remote(
      this.paymentRequestFormService.resourceUrlSupplier + '2' + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');

    this.dataRemotePurchaseorder = this.completerService.remote(
      this.paymentRequestFormService.resourceUrlPurchaseorder + this.editForm.controls['supplierCode'].value + '/',
      'id.code',
      'id.code'
    );

    this.dataRemotePurchaseinvoice = this.completerService.remote(
      this.paymentRequestFormService.resourceUrlPurchaseinvoice + this.editForm.controls['supplierCode'].value + '/',
      'id.code',
      'id.code'
    );
  }

  ngOnInit(): void {
    this.isSaving = false;
    this.paymentmethodService.query().subscribe(paymentmethods => {
      this.paymentmethods = paymentmethods.body;
    });
    this.activatedRoute.data.subscribe(({ paymentRequestForm }) => {
      if (!paymentRequestForm.id) {
        paymentRequestForm.freightRequired = 'N';
        paymentRequestForm.requestType = 'P';
      }
      if (paymentRequestForm.requestType && paymentRequestForm.requestType === 'J') {
        this.dataRemotePurchaseorder = this.completerService.remote(
          this.paymentRequestFormService.resourceUrlJobwork + this.editForm.controls['supplierCode'].value + '/',
          'id.code',
          'id.code'
        );
      } else {
        this.dataRemotePurchaseorder = this.completerService.remote(
          this.paymentRequestFormService.resourceUrlPurchaseorder + this.editForm.controls['supplierCode'].value + '/',
          'id.code',
          'id.code'
        );
      }
      this.updateForm(paymentRequestForm);
      this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
        this.companies = companies.body;
        if (!paymentRequestForm.id) {
          this.editForm.controls['paymentType'].setValue('PO');
          if (this.companies && this.companies.length > 0) {
            this.editForm.controls['company'].setValue(this.companies[0].code);
            this.fetchDivision();
            this.fetchBusinessUnit();
          }
          this.defaultInvoices();
        } else {
          this.paymentRequestFormDetails = paymentRequestForm.paymentRequestFormDetails;
          this.fetchDivision();
          this.fetchBusinessUnit();
          if (!paymentRequestForm.invoices) {
            this.defaultInvoices();
          } else {
            this.paymentRequestInvoices = paymentRequestForm.invoices;
          }
        }
      });
    });

    this.paymentRequestFormService.currentDate().subscribe(dateBean => {
      this.editForm.controls['requestDate'].setValue(moment(dateBean.body.date));
      this.dateRequest = moment(dateBean.body.date);
    });
  }

  defaultInvoices(): void {
    this.paymentRequestInvoices = [];
    for (let i = 0; i < 5; i++) {
      this.paymentRequestInvoices.push(new PaymentRequestInvoice());
    }
  }

  addInvoices(): void {
    if (!this.paymentRequestInvoices) {
      this.paymentRequestInvoices = [];
      this.paymentRequestInvoices.push(new PaymentRequestInvoice());
    } else {
      this.paymentRequestInvoices.push(new PaymentRequestInvoice());
    }
  }

  removeInvoices(index: number): void {
    this.paymentRequestInvoices.splice(index, 1);
  }

  onSupplierSelected(selected?: CompleterItem): void {
    if (selected) {
      if (!!this.remoteData) {
        this.editForm.controls['supplierType'].setValue(selected.originalObject.customersuppliertype);
        this.editForm.controls['supplierCode'].setValue(selected.originalObject.customersuppliercode);
        this.editForm.controls['paymenttermcode'].setValue(selected.originalObject.paymentmethodcode);
        this.editForm.controls['supplierName'].setValue(selected.originalObject.legalname1);
        this.editForm.controls['gstin'].setValue(selected.originalObject.gstinnumber);
        this.editForm.controls['countrycode'].setValue(selected.originalObject.countrycode);
        if (this.editForm.controls['paymenttermcode'].value) {
          this.paymentmethodService.find(this.editForm.controls['paymenttermcode'].value).subscribe(paymentmethod => {
            this.editForm.controls['paymenttermdesc'].setValue(paymentmethod.body.longdescription);
          });
        }
        if (this.editForm.controls['supplierCode'].value) {
          this.paymentRequestFormService.findSupplier(this.editForm.controls['supplierCode'].value).subscribe(orderPartnerDetail => {
            this.editForm.controls['gstrFillingMonth'].setValue(orderPartnerDetail.body.gstr1);
            this.editForm.controls['gstrBFilling'].setValue(orderPartnerDetail.body.gstr3B);
            this.editForm.controls['msmeNo'].setValue(orderPartnerDetail.body.msmeNo);
          });
          this.paymentRequestFormService.findTdsDetail(this.editForm.controls['supplierCode'].value).subscribe(tdsDetail => {
            this.tdsDetails = tdsDetail.body;
          });
        }
        if (this.editForm.controls['requestType'].value && this.editForm.controls['requestType'].value === 'J') {
          this.dataRemotePurchaseorder = this.completerService.remote(
            this.paymentRequestFormService.resourceUrlJobwork + this.editForm.controls['supplierCode'].value + '/',
            'id.code',
            'id.code'
          );
        } else {
          this.dataRemotePurchaseorder = this.completerService.remote(
            this.paymentRequestFormService.resourceUrlPurchaseorder + this.editForm.controls['supplierCode'].value + '/',
            'id.code',
            'id.code'
          );
        }
        this.dataRemotePurchaseinvoice = this.completerService.remote(
          this.paymentRequestFormService.resourceUrlPurchaseinvoice + this.editForm.controls['supplierCode'].value + '/',
          'id.code',
          'id.code'
        );
        const search = new OutstandingBean();
        search.supplierCode = selected.originalObject.customersuppliercode;
        this.paymentRequestFormService.fetchOutstanding(search).subscribe(outstandingBean => {
          this.editForm.controls['outstandingAmount'].setValue(outstandingBean.body.amount);
        });
      } else {
        this.editForm.controls['supplierType'].setValue(undefined);
        this.editForm.controls['supplierCode'].setValue(undefined);
        this.editForm.controls['paymenttermcode'].setValue(undefined);
        this.editForm.controls['paymenttermdesc'].setValue(undefined);
        this.editForm.controls['supplierName'].setValue(undefined);
        this.editForm.controls['gstin'].setValue(undefined);
        this.editForm.controls['outstandingAmount'].setValue(undefined);
      }
    } else {
      this.editForm.controls['supplierType'].setValue(undefined);
      this.editForm.controls['supplierCode'].setValue(undefined);
      this.editForm.controls['paymenttermcode'].setValue(undefined);
      this.editForm.controls['paymenttermdesc'].setValue(undefined);
      this.editForm.controls['supplierName'].setValue(undefined);
      this.editForm.controls['gstin'].setValue(undefined);
      this.editForm.controls['outstandingAmount'].setValue(undefined);
    }
  }

  onPOSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['poDate'].setValue(selected.originalObject.orderdate);
      this.paymentRequestFormService.findPurchaseOrderDetails(this.editForm.controls['poNo'].value).subscribe(purchaseOrderDetail => {
        this.editForm.controls['paymenttermcode'].setValue(purchaseOrderDetail.body.paymentMethodcode);
        this.editForm.controls['paymenttermdesc'].setValue(purchaseOrderDetail.body.paymentMethodDescription);
        this.editForm.controls['poBasic'].setValue(purchaseOrderDetail.body.poBasicValue);
        this.editForm.controls['poGst'].setValue(purchaseOrderDetail.body.poGstValue);
        this.editForm.controls['totalPoValue'].setValue(
          (Number(purchaseOrderDetail.body.poBasicValue) + Number(purchaseOrderDetail.body.poGstValue)).toFixed(2)
        );
      });
    } else {
      this.editForm.controls['poDate'].setValue(undefined);
    }
  }

  onPISelected(paymentRequestInvoice: IPaymentRequestInvoice, selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      paymentRequestInvoice.invoiceDate = selected.originalObject.id.invoicedate;
      this.generateInvoice();
    } else {
      paymentRequestInvoice.invoiceDate = undefined;
    }
  }

  updateForm(paymentRequestForm: IPaymentRequestForm) {
    this.editForm.patchValue({
      id: paymentRequestForm.id,
      company: paymentRequestForm.company,
      division: paymentRequestForm.division,
      businessunitcode: paymentRequestForm.businessunitcode,
      paymentType: paymentRequestForm.paymentType,
      requestNo: paymentRequestForm.requestNo,
      requestDate: paymentRequestForm.requestDate != null ? paymentRequestForm.requestDate.format(DATE_TIME_FORMAT) : null,
      supplierType: paymentRequestForm.supplierType,
      supplierCode: paymentRequestForm.supplierCode,
      supplierName: paymentRequestForm.supplierGstName,
      supplierGstName: paymentRequestForm.supplierGstName,
      gstin: paymentRequestForm.gstin,
      gstrBFilling: paymentRequestForm.gstrBFilling,
      gstrFillingMonth: paymentRequestForm.gstrFillingMonth,
      gstr2a: paymentRequestForm.gstr2a,
      countrycode: paymentRequestForm.countrycode,
      currencycode: paymentRequestForm.currencycode,
      msmeNo: paymentRequestForm.msmeNo,
      requestType: paymentRequestForm.requestType,
      poNo: paymentRequestForm.poNo,
      poDate: paymentRequestForm.poDate != null ? paymentRequestForm.poDate.format(DATE_TIME_FORMAT) : null,
      piNo: paymentRequestForm.piNo,
      piDate: paymentRequestForm.piDate != null ? paymentRequestForm.piDate.format(DATE_TIME_FORMAT) : null,
      invoiceNo: paymentRequestForm.invoiceNo,
      invoiceDate: paymentRequestForm.invoiceDate != null ? paymentRequestForm.invoiceDate.format(DATE_TIME_FORMAT) : null,
      paymenttermcode: paymentRequestForm.paymenttermcode,
      paymenttermdesc: paymentRequestForm.paymenttermdesc,
      conversionRate: paymentRequestForm.conversionRate,
      piAmount: paymentRequestForm.piAmount,
      outstandingAmount: paymentRequestForm.outstandingAmount,
      paymentRelease: paymentRequestForm.paymentRelease,
      poBasic: paymentRequestForm.poBasic,
      poGst: paymentRequestForm.poGst,
      totalPoValue: paymentRequestForm.totalPoValue,
      piBasic: paymentRequestForm.piBasic,
      piGstPerc: paymentRequestForm.piGstPerc,
      piGst: paymentRequestForm.piGst,
      totalPiValue: paymentRequestForm.totalPiValue,
      freightRequired: paymentRequestForm.freightRequired,
      freightValue: paymentRequestForm.freightValue,
      requestAmount: paymentRequestForm.requestAmount,
      requestGst: paymentRequestForm.requestGst,
      tdsType: paymentRequestForm.tdsType,
      tdsValue: paymentRequestForm.tdsValue,
      totalReleaseAmount: paymentRequestForm.totalReleaseAmount,
      chequeNo: paymentRequestForm.chequeNo,
      utrNo: paymentRequestForm.utrNo,
      utrDate: paymentRequestForm.utrDate != null ? paymentRequestForm.utrDate.format(DATE_TIME_FORMAT) : null,
      findocbusinessunitcode: paymentRequestForm.findocbusinessunitcode,
      findocfinancialyearcode: paymentRequestForm.findocfinancialyearcode,
      findoccode: paymentRequestForm.findoccode,
      forwardCode: paymentRequestForm.forwardCode,
      forwardName: paymentRequestForm.forwardName,
      poFile: paymentRequestForm.poFile,
      piFile: paymentRequestForm.piFile,
      remarks: paymentRequestForm.remarks,
      status: paymentRequestForm.status,
      createdBy: paymentRequestForm.createdBy,
      createdDate: paymentRequestForm.createdDate != null ? paymentRequestForm.createdDate.format(DATE_TIME_FORMAT) : null,
      approvedBy: paymentRequestForm.approvedBy
      // approvedDate: paymentRequestForm.approvedDate != null ? paymentRequestForm.approvedDate.format(DATE_TIME_FORMAT) : null,
    });
  }

  fetchDivision(): void {
    if (this.editForm.controls['company'] && this.editForm.controls['company'].value) {
      this.divisionService.query(this.editForm.controls['company'].value).subscribe((divisions: HttpResponse<IDivision[]>) => {
        this.divisions = divisions.body;
        if (this.divisions && this.divisions.length > 0) {
          this.editForm.controls['division'].setValue(this.divisions[0].id.code);
        }
      });
    } else {
      this.divisions = [];
    }
  }

  fetchBusinessUnit(): void {
    if (this.editForm.controls['company'] && this.editForm.controls['company'].value) {
      this.finbusinessunitService
        .query(this.editForm.controls['company'].value)
        .subscribe((businessunits: HttpResponse<IFinbusinessunit[]>) => {
          this.finbusinessunits = businessunits.body;
        });
    } else {
      this.finbusinessunits = [];
    }
  }

  fetchFactories(): void {
    if (this.editForm.controls['businessunitcode'] && this.editForm.controls['businessunitcode'].value) {
      this.factoryService.byDivision(this.editForm.controls['businessunitcode'].value).subscribe((factories: HttpResponse<IFactory[]>) => {
        this.factories = factories.body;
      });
    } else {
      this.factories = [];
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const paymentRequestForm = this.createFromForm();
    if (paymentRequestForm.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentRequestFormService.update(paymentRequestForm));
    } else {
      this.subscribeToSaveResponse(this.paymentRequestFormService.create(paymentRequestForm));
    }
  }

  private createFromForm(): IPaymentRequestForm {
    return {
      ...new PaymentRequestForm(),
      id: this.editForm.get(['id']).value,
      company: this.editForm.get(['company']).value,
      division: this.editForm.get(['division']).value,
      businessunitcode: this.editForm.get(['businessunitcode']).value,
      paymentType: this.editForm.get(['paymentType']).value,
      requestNo: this.editForm.get(['requestNo']).value,
      requestDate:
        this.editForm.get(['requestDate']).value != null ? moment(this.editForm.get(['requestDate']).value, DATE_TIME_FORMAT) : undefined,
      supplierType: this.editForm.get(['supplierType']).value,
      supplierCode: this.editForm.get(['supplierCode']).value,
      supplierName: this.editForm.get(['supplierName']).value,
      supplierGstName: this.editForm.get(['supplierGstName']).value,
      gstin: this.editForm.get(['gstin']).value,
      gstrBFilling: this.editForm.get(['gstrBFilling']).value,
      gstrFillingMonth: this.editForm.get(['gstrFillingMonth']).value,
      gstr2a: this.editForm.get(['gstr2a']).value,
      countrycode: this.editForm.get(['countrycode']).value,
      currencycode: this.editForm.get(['currencycode']).value,
      msmeNo: this.editForm.get(['msmeNo']).value,
      requestType: this.editForm.get(['requestType']).value,
      poNo: this.editForm.get(['poNo']).value,
      poDate: this.editForm.get(['poDate']).value != null ? moment(this.editForm.get(['poDate']).value, DATE_TIME_FORMAT) : undefined,
      piNo: this.editForm.get(['piNo']).value,
      piDate: this.editForm.get(['piDate']).value != null ? moment(this.editForm.get(['piDate']).value, DATE_TIME_FORMAT) : undefined,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      invoiceDate:
        this.editForm.get(['invoiceDate']).value != null ? moment(this.editForm.get(['invoiceDate']).value, DATE_TIME_FORMAT) : undefined,
      paymenttermcode: this.editForm.get(['paymenttermcode']).value,
      paymenttermdesc: this.editForm.get(['paymenttermdesc']).value,
      conversionRate: this.editForm.get(['conversionRate']).value,
      piAmount: this.editForm.get(['piAmount']).value,
      outstandingAmount: this.editForm.get(['outstandingAmount']).value,
      paymentRelease: this.editForm.get(['paymentRelease']).value,
      poBasic: this.editForm.get(['poBasic']).value,
      poGst: this.editForm.get(['poGst']).value,
      totalPoValue: this.editForm.get(['totalPoValue']).value,
      piBasic: this.editForm.get(['piBasic']).value,
      piGstPerc: this.editForm.get(['piGstPerc']).value,
      piGst: this.editForm.get(['piGst']).value,
      totalPiValue: this.editForm.get(['totalPiValue']).value,
      freightRequired: this.editForm.get(['freightRequired']).value,
      freightValue: this.editForm.get(['freightValue']).value,
      requestAmount: this.editForm.get(['requestAmount']).value,
      requestGst: this.editForm.get(['requestGst']).value,
      tdsType: this.editForm.get(['tdsType']).value,
      tdsValue: this.editForm.get(['tdsValue']).value,
      totalReleaseAmount: this.editForm.get(['totalReleaseAmount']).value,
      chequeNo: this.editForm.get(['chequeNo']).value,
      utrNo: this.editForm.get(['utrNo']).value,
      utrDate: this.editForm.get(['utrDate']).value != null ? moment(this.editForm.get(['utrDate']).value, DATE_TIME_FORMAT) : undefined,
      findocbusinessunitcode: this.editForm.get(['findocbusinessunitcode']).value,
      findocfinancialyearcode: this.editForm.get(['findocfinancialyearcode']).value,
      findoccode: this.editForm.get(['findoccode']).value,
      forwardCode: this.editForm.get(['forwardCode']).value,
      forwardName: this.editForm.get(['forwardName']).value,
      poFile: this.editForm.get(['poFile']).value,
      piFile: this.editForm.get(['piFile']).value,
      remarks: this.editForm.get(['remarks']).value,
      status: this.editForm.get(['status']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy']).value,
      invoices: this.paymentRequestInvoices
    };
  }

  onUpload(): void {
    this.loading = !this.loading;
    this.paymentRequestFormService.upload(this.file).subscribe((event: any) => {
      if (typeof event === 'object') {
        this.loading = false;
      }
    });
  }

  queryByType(): void {
    if (this.forwardType) {
      this.paymentRequestForwardService.queryByType(this.forwardType).subscribe(paymentRequestForwards => {
        this.paymentRequestForwards = [];
        this.paymentRequestForwards = paymentRequestForwards.body;
        if (this.paymentRequestForwards.length > 0) {
          this.forwardCode = this.paymentRequestForwards[0].forwardCode;
          this.forwardName = this.paymentRequestForwards[0].forwardName;
        }
      });
    }
  }

  chooseForward(): void {
    if (this.forwardCode) {
      this.paymentRequestForwards.forEach(value => {
        if (value.forwardCode === this.forwardCode) {
          this.forwardName = value.forwardName;
        }
      });
    }
  }

  forwardMessage(): void {
    if (this.forwardCode && this.forwardType) {
      this.isSaving = true;
      const details = new PaymentRequestFormDetails();
      details.flag = this.forwardType;
      details.forwardCode = this.forwardCode;
      details.forwardName = this.forwardName;
      details.remarks = this.remarks ? this.remarks : ' ';
      details.paymentRequestFormId = this.editForm.controls['id'].value;
      this.paymentRequestFormService.createDetails(details).subscribe(data => {
        this.snotifyService.success('Record forward Successfully', '', toastConfig);
        this.paymentRequestFormService.find(this.editForm.controls['id'].value).subscribe(
          paymentRequestForm => {
            this.isSaving = false;
            this.updateForm(paymentRequestForm.body);
            this.paymentRequestFormDetails = paymentRequestForm.body.paymentRequestFormDetails;
            this.fetchDivision();
            this.fetchBusinessUnit();
            this.forwardType = undefined;
            this.forwardCode = undefined;
            this.forwardName = undefined;
            this.remarks = undefined;
          },
          () => {
            this.isSaving = false;
          }
        );
      });
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentRequestForm>>) {
    result.subscribe(
      (res: HttpResponse<IPaymentRequestForm>) => this.onSaveSuccess(res),
      (res: HttpErrorResponse) => this.onSaveError(res.headers)
    );
  }

  protected onSaveSuccess(result: HttpResponse<IPaymentRequestForm>) {
    this.isSaving = false;
    this.snotifyService.success('Record Save Successfully', '', toastConfig);
    this.paymentRequestFormService.find(result.body.id).subscribe(paymentRequestForm => {
      this.updateForm(paymentRequestForm.body);
      this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
        this.companies = companies.body;
        this.paymentRequestFormDetails = paymentRequestForm.body.paymentRequestFormDetails;
        this.fetchDivision();
        this.fetchBusinessUnit();
      });
    });
  }

  protected onSaveError(res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
    this.isSaving = false;
  }

  showHideInvoice(): void {
    this.isInvoice = !this.isInvoice;
  }

  generateInvoice(): void {
    let invoice = '';
    let tindex = 0;
    this.paymentRequestInvoices.forEach((value, index) => {
      if (index === 0 && value.invoiceNo) {
        invoice = value.invoiceNo;
      } else if (value.invoiceNo) {
        invoice += ',' + value.invoiceNo;
      }
      ++tindex;
    });
    if (tindex === this.paymentRequestInvoices.length) {
      this.editForm.controls['invoiceNo'].setValue(invoice.toUpperCase());
    }
  }

  calculateResultForm(): void {
    if (
      this.editForm.controls['paymentRelease'].value &&
      this.editForm.controls['poBasic'].value &&
      this.editForm.controls['paymentType'].value === 'PO'
    ) {
      const paymentAmount = (
        (Number(this.editForm.controls['poBasic'].value) * Number(this.editForm.controls['paymentRelease'].value)) /
        100
      ).toFixed(2);
      this.editForm.get('requestAmount').setValue(paymentAmount);

      let gstAmount: any = 0;
      if (this.editForm.controls['poGst'].value && Number(this.editForm.controls['poGst'].value) > 0) {
        gstAmount = (
          (Number(this.editForm.controls['poGst'].value) * Number(this.editForm.controls['paymentRelease'].value)) /
          100
        ).toFixed(2);
      }
      this.editForm.get('requestGst').setValue(gstAmount);
      const totalReleaseAmt = (Number(paymentAmount) + Number(gstAmount)).toFixed(2);
      this.editForm.controls['totalReleaseAmount'].setValue(totalReleaseAmt);
      this.calculateTdsValue();
    } else if (
      this.editForm.controls['paymentRelease'].value &&
      this.editForm.controls['piBasic'].value &&
      this.editForm.controls['paymentType'].value === 'PI'
    ) {
      const paymentAmount = (
        (Number(this.editForm.controls['piBasic'].value) * Number(this.editForm.controls['paymentRelease'].value)) /
        100
      ).toFixed(2);
      this.editForm.get('requestAmount').setValue(paymentAmount);

      let gstAmount: any = 0;
      if (this.editForm.controls['piGst'].value && Number(this.editForm.controls['piGst'].value) > 0) {
        gstAmount = (
          (Number(this.editForm.controls['piGst'].value) * Number(this.editForm.controls['paymentRelease'].value)) /
          100
        ).toFixed(2);
      }
      this.editForm.get('requestGst').setValue(gstAmount);
      const totalReleaseAmt = (Number(paymentAmount) + Number(gstAmount)).toFixed(2);
      this.editForm.controls['totalReleaseAmount'].setValue(totalReleaseAmt);
      this.calculateTdsValue();
    }
  }

  calculateTdsValue(): void {
    if (this.editForm.controls['tdsType'].value && this.editForm.controls['requestAmount'].value) {
      this.tdsDetails.forEach(tdsDetail => {
        if (this.editForm.controls['tdsType'].value === tdsDetail.tdsiTexCode) {
          const tdsAmount = ((Number(this.editForm.controls['requestAmount'].value) * Number(tdsDetail.value)) / 100).toFixed(2);
          this.editForm.get('tdsValue').setValue(tdsAmount);

          const totalReleaseAmt = (
            Number(this.editForm.get('requestAmount').value) +
            Number(this.editForm.get('requestGst').value) -
            Number(this.editForm.get('tdsValue').value)
          ).toFixed(2);
          this.editForm.controls['totalReleaseAmount'].setValue(totalReleaseAmt);
        }
      });
    }
  }

  gstValueCalculation(): void {
    if (this.editForm.controls['countrycode'].value && this.editForm.controls['countrycode'].value === 'IND') {
      if (this.editForm.controls['piGstPerc'].value && this.editForm.controls['piBasic'].value) {
        const gstValue = (
          (Number(this.editForm.controls['piBasic'].value) * Number(this.editForm.controls['piGstPerc'].value)) /
          100
        ).toFixed(2);
        this.editForm.controls['piGst'].setValue(gstValue);
        this.editForm.controls['totalPiValue'].setValue((Number(this.editForm.controls['piBasic'].value) + Number(gstValue)).toFixed(2));
        this.calculateResultForm();
      } else {
        this.editForm.controls['piGst'].setValue(0);
        this.editForm.controls['totalPiValue'].setValue(Number(this.editForm.controls['piBasic'].value).toFixed(2));
        this.calculateResultForm();
      }
    } else {
      if (this.editForm.controls['conversionRate'].value && this.editForm.controls['piAmount'].value) {
        this.editForm.controls['piBasic'].setValue(
          (Number(this.editForm.controls['conversionRate'].value) * Number(this.editForm.controls['piAmount'].value)).toFixed(2)
        );

        this.editForm.controls['piGst'].setValue(0);
        this.editForm.controls['totalPiValue'].setValue(Number(this.editForm.controls['piBasic'].value).toFixed(2));
        this.calculateResultForm();
      }
    }
  }

  downloadReport(): void {
    this.isSaving = true;
    this.paymentRequestFormService
      .downloadPDF(this.editForm.controls['poNo'].value, this.editForm.controls['potype'].value)
      .subscribe(res => {
        this.isSaving = false;
        // FileSaver.saveAs(res, 'PurchaseOrder.pdf');
        const file = new Blob([res], { type: 'application/pdf' });
        const fileURL = window.URL.createObjectURL(file);
        const modalRef = this.modalService.open(PopupComponent as Component, {
          size: 'lg',
          backdrop: 'static',
          windowClass: 'xlModal'
        });
        modalRef.componentInstance.content = fileURL;
      });
  }

  fetchExchangeRate(): void {
    if (this.editForm.controls['currencycode'].value) {
      this.paymentRequestFormService.exchangeRate(this.editForm.controls['currencycode'].value).subscribe(currencydailyexchangerate => {
        this.editForm.controls['conversionRate'].setValue(currencydailyexchangerate.body.purchaseexchangerate);
        this.gstValueCalculation();
      });
    }
  }

  changeJobwork(): void {
    if (this.editForm.controls['requestType'].value && this.editForm.controls['requestType'].value === 'J') {
      this.dataRemotePurchaseorder = this.completerService.remote(
        this.paymentRequestFormService.resourceUrlJobwork + this.editForm.controls['supplierCode'].value + '/',
        'id.code',
        'id.code'
      );
    } else {
      this.dataRemotePurchaseorder = this.completerService.remote(
        this.paymentRequestFormService.resourceUrlPurchaseorder + this.editForm.controls['supplierCode'].value + '/',
        'id.code',
        'id.code'
      );
    }
  }
}
