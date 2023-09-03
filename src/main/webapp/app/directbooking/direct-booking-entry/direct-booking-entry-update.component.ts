import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDirectBookingEntry, DirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingEntryService } from './direct-booking-entry.service';
import { ICompany } from 'app/shared/db2/model/company.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { IDivision } from 'app/shared/db2/model/division.model';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { DirectBookingDetails, IDirectBookingDetails } from 'app/shared/model/direct-booking-details.model';
import { IFactory } from 'app/shared/model/factory.model';
import { FactoryService } from 'app/shared/db2/service/factory.service';
import { VieworderpartnerService } from 'app/shared/db2/service/vieworderpartner.service';
import { OrderpartnertdsService } from 'app/shared/db2/service/orderpartnertds.service';
import { Master } from 'app/shared/model/master.modal';
import { IOrderpartnertds } from 'app/shared/db2/model/orderpartnertds.model';
import { FullitemkeydecoderService } from 'app/shared/db2/service/fullitemkeydecoder.service';
import { ViewDitaxglmappingService } from 'app/shared/db2/service/ViewDitaxglmapping.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { ItemvseventglmapService } from 'app/shared/db2/service/itemvseventglmap.service';
import { ItemvseventglmapSearch } from 'app/shared/db2/model/itemvseventglmap-search.model';
import { PaymentmethodService } from 'app/shared/db2/service/paymentmethod.service';
import { toastConfig } from 'app/core/toast/toast-config';
import { isMoment } from 'moment';
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
  selector: 'jhi-direct-booking-entry-update',
  templateUrl: './direct-booking-entry-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class DirectBookingEntryUpdateComponent implements OnInit, AfterViewInit {
  isSaving = false;
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  directBookingDetails?: IDirectBookingDetails[];
  orderpartnertdss?: IOrderpartnertds[];
  public dataRemoteSupplier: RemoteData;
  public dataRemoteCustomer: RemoteData;
  public dataRemoteCostcenter: RemoteData;
  public dataRemotePaymentmethod: RemoteData;
  public dataRemoteItems: RemoteData;
  public dataRemoteCGst: RemoteData;
  public dataRemoteSGst: RemoteData;
  public dataRemoteIGst: RemoteData;
  public dataRemoteProject: RemoteData;
  public dataRemoteGateentry: RemoteData;
  public dataRemoteGatepass: RemoteData;
  maxdate?: any;
  tdsBool = false;

  editForm = this.fb.group({
    id: [],
    company: [null, [Validators.required, Validators.maxLength(3)]],
    division: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcompanycode: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcode: [null, [Validators.required, Validators.maxLength(10)]],
    factorycode: [null, [Validators.required, Validators.maxLength(10)]],
    factorystate: [null, [Validators.required, Validators.maxLength(10)]],
    bookingdate: [null, [Validators.required]],
    bookingtype: [null, [Validators.required, Validators.maxLength(1)]],
    bookingfor: [null, [Validators.required, Validators.maxLength(1)]],
    suppliercustomertype: [null, [Validators.required, Validators.maxLength(1)]],
    suppliercustomercode: [null, [Validators.required, Validators.maxLength(8)]],
    suppliercustomerdesc: [null, [Validators.maxLength(100)]],
    suppliercustomerstate: [null, [Validators.required, Validators.maxLength(10)]],
    supplierlegalname: [null, [Validators.maxLength(100)]],
    gstin: [null, [Validators.maxLength(20)]],
    address: [null, [Validators.maxLength(1000)]],
    billno: [null, [Validators.required, Validators.maxLength(16)]],
    billdate: [null, [Validators.required]],
    billamount: [null, [Validators.required]],
    paymenttermcode: [null, [Validators.required, Validators.maxLength(3)]],
    paymenttermdesc: [null, [Validators.maxLength(100)]],
    costcentercode: [null, [Validators.required, Validators.maxLength(20)]],
    costcenterdesc: [null, [Validators.maxLength(100)]],
    remarks: [null, [Validators.required, Validators.maxLength(500)]],
    vehicleNo: [null, [Validators.maxLength(20)]],
    projectcode: [null, [Validators.maxLength(100)]],
    gatenumber: [null, [Validators.maxLength(20)]],
    gateentrynumber: [null, [Validators.maxLength(20)]],
    gateNoRequired: [],
    rcmBill: [],
    shippingBill: [],
    freightApplicable: [],
    freightType: [null, [Validators.maxLength(1)]],
    freightValue: [],
    discountApplicable: [],
    discountType: [null, [Validators.maxLength(1)]],
    discountValue: [],
    otherChargesApplicable: [],
    otherChargesType: [null, [Validators.maxLength(1)]],
    otherChargesValue: [],
    tcsApplicable: [],
    value: [],
    itaxvalue: [],
    ctaxvalue: [],
    staxvalue: [],
    taxvalue: [],
    totalvalue: [],
    roundOffValue: [],
    roundOffType: [],
    netAmount: [],
    tdsValue: [],
    mtds: [],
    flag: [],
    tcsValue: [],
    createddate: [],
    createdby: [],
    findocumentcode: [],
    styleNo: [],
    customerCode: [],
    customerName: [],
    customerGstName: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected directBookingEntryService: DirectBookingEntryService,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected factoryService: FactoryService,
    protected finbusinessunitService: FinbusinessunitService,
    protected vieworderpartnerService: VieworderpartnerService,
    protected orderpartnertdsService: OrderpartnertdsService,
    protected fullitemkeydecoderService: FullitemkeydecoderService,
    protected viewditaxglmappingService: ViewDitaxglmappingService,
    protected paymentmethodService: PaymentmethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public completerService: CompleterService,
    public snotifyService: SnotifyService,
    public itemvseventglmapService: ItemvseventglmapService
  ) {
    this.dataRemoteProject = this.completerService.remote(this.directBookingEntryService.resourceUrlProject, 'id.code', 'id.code');
    this.dataRemoteCostcenter = this.completerService.remote(
      this.directBookingEntryService.resourceUrlCostcenter,
      'longdescription',
      'longdescription'
    );
    this.dataRemotePaymentmethod = this.completerService.remote(
      this.directBookingEntryService.resourceUrlPaymentmethod,
      'longdescription',
      'longdescription'
    );
    this.dataRemoteItems = this.completerService.remote(
      this.directBookingEntryService.resourceUrlItems,
      'summarizeddescription',
      'summarizeddescription'
    );
    this.dataRemoteCGst = this.completerService.remote(
      this.directBookingEntryService.resourceUrlGST + 'CG/',
      'longdescription',
      'longdescription'
    );
    this.dataRemoteSGst = this.completerService.remote(
      this.directBookingEntryService.resourceUrlGST + 'SG/',
      'longdescription',
      'longdescription'
    );
    this.dataRemoteIGst = this.completerService.remote(
      this.directBookingEntryService.resourceUrlGST + 'IG/',
      'longdescription',
      'longdescription'
    );
  }

  ngOnInit(): void {
    this.defaultDetails();
    this.activatedRoute.data.subscribe(({ directBookingEntry }) => {
      this.updateForm(directBookingEntry);
      this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
        this.companies = companies.body;
        if (directBookingEntry.copyFlag && directBookingEntry.copyFlag === 'Y') {
          this.editForm.controls['id'].setValue(undefined);

          this.dataRemoteCustomer = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + '1/',
            'addressee',
            'addressee'
          );
          this.dataRemoteCustomer.dataField('');

          this.dataRemoteGateentry = this.completerService.remote(
            this.directBookingEntryService.resourceUrlMainEntry + this.editForm.controls['factorycode'].value + '/',
            'id.maingateentrysrno',
            'id.maingateentrysrno'
          );
          this.dataRemoteGateentry.dataField('');

          this.dataRemoteGatepass = this.completerService.remote(
            this.directBookingEntryService.resourceUrlPassNo + this.editForm.controls['factorycode'].value + '/',
            'gatepassno',
            'gatepassno'
          );
          this.dataRemoteGatepass.dataField('');

          this.fetchDivision();
          this.fetchBusinessUnit();
        }
        if (!directBookingEntry.id && !directBookingEntry.copyFlag) {
          this.editForm.controls['gateNoRequired'].setValue(true);
          this.editForm.controls['company'].setValue(this.companies[0].code);
          this.editForm.controls['businessunitcompanycode'].setValue(this.companies[0].code);
          this.directBookingEntryService.currentDate().subscribe(dateBean => {
            this.editForm.controls['bookingdate'].setValue(moment(dateBean.body.date));
            this.maxdate = moment(dateBean.body.date);
          });
          this.editForm.controls['bookingfor'].setValue('2');
          this.editForm.controls['flag'].setValue('E');

          this.dataRemoteSupplier = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
            'addressee',
            'addressee'
          );
          this.dataRemoteSupplier.dataField('');

          this.dataRemoteCustomer = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + '1/',
            'addressee',
            'addressee'
          );
          this.dataRemoteCustomer.dataField('');

          this.dataRemoteGateentry = this.completerService.remote(
            this.directBookingEntryService.resourceUrlMainEntry + this.editForm.controls['factorycode'].value + '/',
            'id.maingateentrysrno',
            'id.maingateentrysrno'
          );
          this.dataRemoteGateentry.dataField('');

          this.dataRemoteGatepass = this.completerService.remote(
            this.directBookingEntryService.resourceUrlPassNo + this.editForm.controls['factorycode'].value + '/',
            'gatepassno',
            'gatepassno'
          );
          this.dataRemoteGatepass.dataField('');

          this.fetchDivision();
          this.fetchBusinessUnit();
        } else {
          this.tdsBool = true;
          this.maxdate = moment(directBookingEntry.createddate);
          this.orderpartnertdss = directBookingEntry.orderpartnertdss;
          this.directBookingDetails = directBookingEntry.directBookingDetails;
          this.dataRemoteSupplier = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
            'addressee',
            'addressee'
          );
          this.dataRemoteSupplier.dataField('');
          this.fetchFactories();
          this.fetchDivision();
          this.fetchBusinessUnit();
        }
      });
    });
  }

  ngAfterViewInit(): void {
    this.tdsBool = false;
  }

  defaultDetails(): void {
    this.directBookingDetails = [];
    for (let i = 0; i < 1; i++) {
      this.directBookingDetails.push(new DirectBookingDetails());
    }
  }

  updateForm(directBookingEntry: IDirectBookingEntry): void {
    this.editForm.patchValue({
      id: directBookingEntry.id,
      company: directBookingEntry.company,
      division: directBookingEntry.division,
      businessunitcompanycode: directBookingEntry.businessunitcompanycode,
      businessunitcode: directBookingEntry.businessunitcode,
      factorycode: directBookingEntry.factorycode,
      factorystate: directBookingEntry.factorystate,
      bookingdate: directBookingEntry.bookingdate ? directBookingEntry.bookingdate.format(DATE_TIME_FORMAT) : null,
      bookingtype: directBookingEntry.bookingtype,
      bookingfor: directBookingEntry.bookingfor,
      suppliercustomertype: directBookingEntry.suppliercustomertype,
      suppliercustomercode: directBookingEntry.suppliercustomercode,
      suppliercustomerdesc: directBookingEntry.suppliercustomerdesc,
      suppliercustomerstate: directBookingEntry.suppliercustomerstate,
      supplierlegalname: directBookingEntry.supplierlegalname,
      gstin: directBookingEntry.gstin,
      address: directBookingEntry.address,
      billno: directBookingEntry.billno,
      billdate: directBookingEntry.billdate ? directBookingEntry.billdate.format(DATE_TIME_FORMAT) : null,
      billamount: directBookingEntry.billamount,
      paymenttermcode: directBookingEntry.paymenttermcode,
      paymenttermdesc: directBookingEntry.paymenttermdesc,
      costcentercode: directBookingEntry.costcentercode,
      costcenterdesc: directBookingEntry.costcenterdesc,
      remarks: directBookingEntry.remarks,
      vehicleNo: directBookingEntry.vehicleNo,
      projectcode: directBookingEntry.projectcode,
      gatenumber: directBookingEntry.gatenumber,
      gateentrynumber: directBookingEntry.gateentrynumber,
      gateNoRequired: directBookingEntry.gateNoRequired,
      rcmBill: directBookingEntry.rcmBill,
      shippingBill: directBookingEntry.shippingBill,
      freightApplicable: directBookingEntry.freightApplicable,
      freightType: directBookingEntry.freightType,
      freightValue: directBookingEntry.freightValue,
      discountApplicable: directBookingEntry.discountApplicable,
      discountType: directBookingEntry.discountType,
      discountValue: directBookingEntry.discountValue,
      otherChargesApplicable: directBookingEntry.otherChargesApplicable,
      otherChargesType: directBookingEntry.otherChargesType,
      otherChargesValue: directBookingEntry.otherChargesValue,
      tcsApplicable: directBookingEntry.tcsApplicable,
      tcsValue: directBookingEntry.tcsValue,
      value: directBookingEntry.value,
      itaxvalue: directBookingEntry.itaxvalue,
      ctaxvalue: directBookingEntry.ctaxvalue,
      staxvalue: directBookingEntry.staxvalue,
      taxvalue: directBookingEntry.taxvalue,
      totalvalue: directBookingEntry.totalvalue,
      roundOffValue: directBookingEntry.roundOffValue,
      roundOffType: directBookingEntry.roundOffType,
      netAmount: directBookingEntry.netAmount,
      tdsValue: directBookingEntry.tdsValue,
      mtds: directBookingEntry.mtds,
      flag: directBookingEntry.flag,
      createddate: directBookingEntry.createddate ? directBookingEntry.createddate.format(DATE_TIME_FORMAT) : null,
      createdby: directBookingEntry.createdby,
      findocumentcode: directBookingEntry.findocumentcode,
      styleNo: directBookingEntry.styleNo,
      customerCode: directBookingEntry.customerCode,
      customerName: directBookingEntry.customerName,
      customerGstName: directBookingEntry.customerGstName
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

  previousState(): void {
    window.history.back();
  }

  save(): void {
    if (this.validate() === true) {
      this.isSaving = true;
      const directBookingEntry = this.createFromForm();
      // alert(directBookingEntry.id);
      if (directBookingEntry.id !== undefined) {
        this.subscribeToSaveResponse(this.directBookingEntryService.update(directBookingEntry));
      } else {
        this.subscribeToSaveResponse(this.directBookingEntryService.create(directBookingEntry));
      }
    }
  }

  validate(): boolean {
    if (moment(this.editForm.controls['bookingdate'].value).isSameOrAfter(moment(this.editForm.controls['billdate'].value))) {
      if (this.directBookingDetails && this.directBookingDetails.length > 0) {
        let counter = 0;
        this.directBookingDetails.forEach(directBookingDetail => {
          if (!directBookingDetail.itemtypecode || directBookingDetail.itemtypecode.length === 0) {
            this.snotifyService.error('Item type must not be empty!', '', toastConfig);
            return false;
          } else if (!directBookingDetail.summerizeddescription || directBookingDetail.summerizeddescription.length === 0) {
            this.snotifyService.error('Item Description must not be empty!', '', toastConfig);
            return false;
          } else if (!directBookingDetail.glcode || directBookingDetail.glcode.length === 0) {
            this.snotifyService.error('GL Number must not be empty!', '', toastConfig);
            return false;
          } else if (!directBookingDetail.hsncode || directBookingDetail.hsncode.length === 0) {
            this.snotifyService.error('HSN must not be empty!', '', toastConfig);
            return false;
          } else if (!directBookingDetail.quantity || directBookingDetail.quantity <= 0) {
            this.snotifyService.error('Quantity must not be blank or zero!', '', toastConfig);
            return false;
          } else if (!directBookingDetail.price || directBookingDetail.price <= 0) {
            this.snotifyService.error('Price must not be blank or zero!', '', toastConfig);
            return false;
          } else if (
            this.editForm.controls['factorystate'].value !== this.editForm.controls['suppliercustomerstate'].value &&
            (!directBookingDetail.igstdesc || !directBookingDetail.igstglcode || directBookingDetail.igstperc < 0)
          ) {
            this.snotifyService.error('IGST percentage must not be blank!', '', toastConfig);
            return false;
          } else if (
            this.editForm.controls['factorystate'].value === this.editForm.controls['suppliercustomerstate'].value &&
            (!directBookingDetail.cgstdesc || !directBookingDetail.cgstglcode || directBookingDetail.cgstperc < 0)
          ) {
            this.snotifyService.error('CGST percentage must not be blank!', '', toastConfig);
            return false;
          } else if (
            this.editForm.controls['factorystate'].value === this.editForm.controls['suppliercustomerstate'].value &&
            (!directBookingDetail.sgstdesc || !directBookingDetail.sgstglcode || directBookingDetail.sgstperc < 0)
          ) {
            this.snotifyService.error('SGST percentage must not be blank!', '', toastConfig);
            return false;
          } else {
            ++counter;
          }
        });
        if (counter === this.directBookingDetails.length) {
          if (this.editForm.controls['roundOffValue'].value !== undefined && this.editForm.controls['roundOffValue'].value === 0) {
            return true;
          } else if (
            this.editForm.controls['roundOffValue'].value !== undefined &&
            this.editForm.controls['roundOffValue'].value < 10 &&
            this.editForm.controls['roundOffValue'].value > -10
          ) {
            this.snotifyService.error('Rounding/Different value will be posted on GL 71001. ', '', toastConfig);
            return true;
          } else {
            this.snotifyService.error('Rounding/Difference Value should be between 9.99 and -9.99. ', '', toastConfig);
            return false;
          }
        }
      } else {
        this.snotifyService.error('Add atleast 1 line', '', toastConfig);
        return false;
      }
    } else {
      this.snotifyService.error('Posting date must be after Bill Date.', '', toastConfig);
      return false;
    }
    return false;
  }

  private createFromForm(): IDirectBookingEntry {
    return {
      ...new DirectBookingEntry(),
      id: this.editForm.get(['id'])!.value,
      company: this.editForm.get(['company'])!.value,
      division: this.editForm.get(['division'])!.value,
      businessunitcompanycode: this.editForm.get(['businessunitcompanycode'])!.value,
      businessunitcode: this.editForm.get(['businessunitcode'])!.value,
      factorycode: this.editForm.get(['factorycode'])!.value,
      factorystate: this.editForm.get(['factorystate'])!.value,
      bookingdate: this.editForm.get(['bookingdate'])!.value
        ? moment(this.editForm.get(['bookingdate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      bookingtype: this.editForm.get(['bookingtype'])!.value,
      bookingfor: this.editForm.get(['bookingfor'])!.value,
      suppliercustomertype: this.editForm.get(['suppliercustomertype'])!.value,
      suppliercustomercode: this.editForm.get(['suppliercustomercode'])!.value,
      suppliercustomerdesc: this.editForm.get(['suppliercustomerdesc'])!.value,
      suppliercustomerstate: this.editForm.get(['suppliercustomerstate'])!.value,
      supplierlegalname: this.editForm.get(['supplierlegalname'])!.value,
      gstin: this.editForm.get(['gstin'])!.value,
      address: this.editForm.get(['address'])!.value,
      billno: this.editForm.get(['billno'])!.value,
      billdate: this.editForm.get(['billdate'])!.value ? moment(this.editForm.get(['billdate'])!.value, DATE_TIME_FORMAT) : undefined,
      billamount: this.editForm.get(['billamount'])!.value,
      paymenttermcode: this.editForm.get(['paymenttermcode'])!.value,
      paymenttermdesc: this.editForm.get(['paymenttermdesc'])!.value,
      costcentercode: this.editForm.get(['costcentercode'])!.value,
      costcenterdesc: this.editForm.get(['costcenterdesc'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      vehicleNo: this.editForm.get(['vehicleNo'])!.value,
      projectcode: this.editForm.get(['projectcode'])!.value,
      gatenumber: this.editForm.get(['gatenumber'])!.value,
      gateentrynumber: this.editForm.get(['gateentrynumber'])!.value,
      gateNoRequired: this.editForm.get(['gateNoRequired'])!.value,
      rcmBill: this.editForm.get(['rcmBill'])!.value,
      shippingBill: this.editForm.get(['shippingBill'])!.value,
      freightApplicable: this.editForm.get(['freightApplicable'])!.value,
      freightType: this.editForm.get(['freightType'])!.value,
      freightValue: this.editForm.get(['freightValue'])!.value,
      discountApplicable: this.editForm.get(['discountApplicable'])!.value,
      discountType: this.editForm.get(['discountType'])!.value,
      discountValue: this.editForm.get(['discountValue'])!.value,
      otherChargesApplicable: this.editForm.get(['otherChargesApplicable'])!.value,
      otherChargesType: this.editForm.get(['otherChargesType'])!.value,
      otherChargesValue: this.editForm.get(['otherChargesValue'])!.value,
      tcsApplicable: this.editForm.get(['tcsApplicable'])!.value,
      tcsValue: this.editForm.get(['tcsValue'])!.value,
      value: this.editForm.get(['value'])!.value,
      itaxvalue: this.editForm.get(['itaxvalue'])!.value,
      ctaxvalue: this.editForm.get(['ctaxvalue'])!.value,
      staxvalue: this.editForm.get(['staxvalue'])!.value,
      taxvalue: this.editForm.get(['taxvalue'])!.value,
      totalvalue: this.editForm.get(['totalvalue'])!.value,
      roundOffValue: this.editForm.get(['roundOffValue'])!.value,
      roundOffType: this.editForm.get(['roundOffType'])!.value,
      netAmount: this.editForm.get(['netAmount'])!.value,
      tdsValue: this.editForm.get(['tdsValue'])!.value,
      mtds: this.editForm.get(['mtds'])!.value,
      flag: this.editForm.get(['flag'])!.value,
      createddate: this.editForm.get(['createddate'])!.value
        ? moment(this.editForm.get(['createddate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdby: this.editForm.get(['createdby'])!.value,
      styleNo: this.editForm.get(['styleNo'])!.value,
      customerCode: this.editForm.get(['customerCode'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      customerGstName: this.editForm.get(['customerGstName']).value,
      directBookingDetails: this.directBookingDetails,
      orderpartnertdss: this.orderpartnertdss
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDirectBookingEntry>>): void {
    result.subscribe(() => this.onSaveSuccess(), (httpErrorResponse: HttpErrorResponse) => this.onSaveError(httpErrorResponse));
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(httpErrorResponse: HttpErrorResponse): void {
    this.snotifyService.error(httpErrorResponse.headers.get('x-vamaniportalapp-error'), '', toastConfig);
    this.isSaving = false;
  }

  selectFactory(): void {
    if (this.editForm.controls['factorycode'].value && this.editForm.controls['factorycode'].value.length > 0) {
      this.factories.forEach(factory => {
        if (this.editForm.controls['factorycode'].value === factory.factCode) {
          this.editForm.controls['factorystate'].setValue(factory.statecode);
          this.remoteURLGateEntry();
        }
      });
    } else {
      this.editForm.controls['factorystate'].setValue(undefined);
    }
  }

  remoteURL(): void {
    this.dataRemoteSupplier = this.completerService.remote(
      this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');
  }

  remoteURLGateEntry(): void {
    this.dataRemoteGateentry = this.completerService.remote(
      this.directBookingEntryService.resourceUrlMainEntry + this.editForm.controls['factorycode'].value + '/',
      'id.maingateentrysrno',
      'id.maingateentrysrno'
    );
    this.dataRemoteGateentry.dataField('');

    this.dataRemoteGatepass = this.completerService.remote(
      this.directBookingEntryService.resourceUrlPassNo + this.editForm.controls['factorycode'].value + '/',
      'gatepassno',
      'gatepassno'
    );
    this.dataRemoteGatepass.dataField('');
  }

  onSupplierSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['suppliercustomertype'].setValue(selected.originalObject.customersuppliertype);
      this.editForm.controls['suppliercustomercode'].setValue(selected.originalObject.customersuppliercode);
      this.editForm.controls['paymenttermcode'].setValue(selected.originalObject.paymentmethodcode);
      this.editForm.controls['suppliercustomerstate'].setValue(selected.originalObject.statecode);
      this.editForm.controls['supplierlegalname'].setValue(selected.originalObject.legalname1);
      this.editForm.controls['gstin'].setValue(selected.originalObject.gstinnumber);
      this.editForm.controls['address'].setValue(selected.originalObject.address);
      if (this.editForm.controls['paymenttermcode'].value) {
        this.paymentmethodService.find(this.editForm.controls['paymenttermcode'].value).subscribe(paymentmethod => {
          this.editForm.controls['paymenttermdesc'].setValue(paymentmethod.body.longdescription);
        });
      }
      const master = new Master();
      master.id = selected.originalObject.customersuppliercompanycode;
      master.name = selected.originalObject.customersuppliertype;
      master.desc = selected.originalObject.customersuppliercode;
      this.orderpartnertdsService.tds(master).subscribe(orderpartnertdss => {
        this.orderpartnertdss = orderpartnertdss.body;
      });
    } else {
      this.editForm.controls['suppliercustomertype'].setValue(undefined);
      this.editForm.controls['suppliercustomercode'].setValue(undefined);
      this.editForm.controls['paymenttermcode'].setValue(undefined);
      this.editForm.controls['paymenttermdesc'].setValue(undefined);
      this.editForm.controls['suppliercustomerstate'].setValue(undefined);
      this.editForm.controls['supplierlegalname'].setValue(undefined);
      this.editForm.controls['gstin'].setValue(undefined);
      this.editForm.controls['address'].setValue(undefined);
      this.orderpartnertdss = [];
    }
  }

  onCustomerSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['customerCode'].setValue(selected.originalObject.customersuppliercode);
      this.editForm.controls['customerName'].setValue(selected.originalObject.legalname1);
    } else {
      this.editForm.controls['customerCode'].setValue(undefined);
      this.editForm.controls['customerName'].setValue(undefined);
    }
  }

  onCostcenterSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['costcentercode'].setValue(selected.originalObject.id.code);
    } else {
      this.editForm.controls['costcentercode'].setValue(undefined);
    }
  }

  onPaymentmethodSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['paymenttermcode'].setValue(selected.originalObject.id.code);
    } else {
      this.editForm.controls['paymenttermcode'].setValue(undefined);
    }
  }

  onLineItemSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      if (this.editForm.controls['flag'].value === 'E' && !directBookingDetail.copyFlag) {
        directBookingDetail.itemtypecode = selected.originalObject.id.itemtypecode;
        directBookingDetail.subcode01 = selected.originalObject.id.subcode01;
        directBookingDetail.subcode02 = selected.originalObject.id.subcode02;
        directBookingDetail.subcode03 = selected.originalObject.id.subcode03;
        directBookingDetail.subcode04 = selected.originalObject.id.subcode04;
        directBookingDetail.subcode05 = selected.originalObject.id.subcode05;
        directBookingDetail.subcode06 = selected.originalObject.id.subcode06;
        directBookingDetail.subcode07 = selected.originalObject.id.subcode07;
        directBookingDetail.subcode08 = selected.originalObject.id.subcode08;
        directBookingDetail.subcode09 = selected.originalObject.id.subcode09;
        directBookingDetail.subcode10 = selected.originalObject.id.subcode10;
        directBookingDetail.quantity = directBookingDetail.quantity ? directBookingDetail.quantity : 1;
        directBookingDetail.price = directBookingDetail.price ? directBookingDetail.price : 1;
        directBookingDetail.amount = directBookingDetail.amount ? directBookingDetail.amount : 1;

        const search = new ItemvseventglmapSearch();
        search.division = this.editForm.controls['division'].value;
        search.customersuppliertype = this.editForm.controls['suppliercustomertype'].value;
        search.itemtypecode = selected.originalObject.id.itemtypecode;
        search.subcode01 = selected.originalObject.id.subcode01;
        this.itemvseventglmapService.query(search).subscribe(
          itemvseventglmap => {
            if (itemvseventglmap.body) {
              directBookingDetail.glcode = itemvseventglmap.body.debitglcode;
              directBookingDetail.gldescription = itemvseventglmap.body.debitgldescription;
            } else {
              directBookingDetail.glcode = undefined;
            }
          },
          () => {
            directBookingDetail.glcode = undefined;
          }
        );
        this.fullitemkeydecoderService.find(selected.originalObject).subscribe(fullitemkeydecoder => {
          directBookingDetail.uom = fullitemkeydecoder.body.uom;
          directBookingDetail.hsncode = fullitemkeydecoder.body.tariffcode
            ? fullitemkeydecoder.body.tariffcode.trim()
            : fullitemkeydecoder.body.tariffcode;

          if (directBookingDetail.hsncode) {
            const master = new Master();
            master.name = this.editForm.controls['bookingtype'].value;
            master.desc = directBookingDetail.hsncode;
            if (
              this.editForm.controls['factorystate'].value &&
              this.editForm.controls['suppliercustomerstate'].value &&
              this.editForm.controls['factorystate'].value !== this.editForm.controls['suppliercustomerstate'].value
            ) {
              this.viewditaxglmappingService.igst(master).subscribe(viewditaxglmappings => {
                if (viewditaxglmappings.body && viewditaxglmappings.body.length === 1) {
                  directBookingDetail.igstdesc = viewditaxglmappings.body[0].longdescription;
                  directBookingDetail.igstglcode = viewditaxglmappings.body[0].glcode;
                  directBookingDetail.igstperc = viewditaxglmappings.body[0].value;
                  directBookingDetail.igsttaxcode = viewditaxglmappings.body[0].id.taxcode;
                  directBookingDetail.igstvalue = Number(((directBookingDetail.amount * directBookingDetail.igstperc) / 100).toFixed(2));

                  directBookingDetail.gstperc = directBookingDetail.igstperc;
                  directBookingDetail.gstvalue = directBookingDetail.igstvalue;

                  directBookingDetail.totalvalue = directBookingDetail.amount + directBookingDetail.gstvalue;
                }
              });
            } else if (
              this.editForm.controls['factorystate'].value &&
              this.editForm.controls['suppliercustomerstate'].value &&
              this.editForm.controls['factorystate'].value === this.editForm.controls['suppliercustomerstate'].value
            ) {
              this.viewditaxglmappingService.cgst(master).subscribe(viewditaxglmappings => {
                if (viewditaxglmappings.body && viewditaxglmappings.body.length === 2) {
                  directBookingDetail.cgstdesc = viewditaxglmappings.body[0].longdescription;
                  directBookingDetail.cgstglcode = viewditaxglmappings.body[0].glcode;
                  directBookingDetail.cgstperc = viewditaxglmappings.body[0].value;
                  directBookingDetail.cgsttaxcode = viewditaxglmappings.body[0].id.taxcode;
                  directBookingDetail.cgstvalue = Number(((directBookingDetail.amount * directBookingDetail.cgstperc) / 100).toFixed(2));

                  directBookingDetail.sgstdesc = viewditaxglmappings.body[1].longdescription;
                  directBookingDetail.sgstglcode = viewditaxglmappings.body[1].glcode;
                  directBookingDetail.sgstperc = viewditaxglmappings.body[1].value;
                  directBookingDetail.sgsttaxcode = viewditaxglmappings.body[1].id.taxcode;
                  directBookingDetail.sgstvalue = Number(((directBookingDetail.amount * directBookingDetail.sgstperc) / 100).toFixed(2));

                  directBookingDetail.gstperc = directBookingDetail.cgstperc + directBookingDetail.sgstperc;
                  directBookingDetail.gstvalue = directBookingDetail.cgstvalue + directBookingDetail.sgstvalue;

                  directBookingDetail.totalvalue = directBookingDetail.amount + directBookingDetail.gstvalue;
                }
              });
            }
          }
        });
      } else {
        if (!directBookingDetail.id && !directBookingDetail.copyFlag) {
          directBookingDetail.summerizeddescription = undefined;
          directBookingDetail.itemtypecode = undefined;
          directBookingDetail.subcode01 = undefined;
          directBookingDetail.subcode02 = undefined;
          directBookingDetail.subcode03 = undefined;
          directBookingDetail.subcode04 = undefined;
          directBookingDetail.subcode05 = undefined;
          directBookingDetail.subcode06 = undefined;
          directBookingDetail.subcode07 = undefined;
          directBookingDetail.subcode08 = undefined;
          directBookingDetail.subcode09 = undefined;
          directBookingDetail.subcode10 = undefined;
        }
      }
    }
  }

  hsnTrim(directBookingDetail?: IDirectBookingDetails): void {
    directBookingDetail.hsncode = directBookingDetail.hsncode ? directBookingDetail.hsncode.trim() : directBookingDetail.hsncode;
  }

  itemTrim(directBookingDetail?: IDirectBookingDetails): void {
    directBookingDetail.summerizeddescription = directBookingDetail.summerizeddescription
      ? directBookingDetail.summerizeddescription.trim()
      : directBookingDetail.summerizeddescription;
  }

  calculateValue(directBookingDetail?: IDirectBookingDetails) {
    if (directBookingDetail.price && directBookingDetail.quantity) {
      directBookingDetail.amount = Number((directBookingDetail.price * directBookingDetail.quantity).toFixed(2));
      if (
        this.editForm.controls['factorystate'].value &&
        this.editForm.controls['suppliercustomerstate'].value &&
        this.editForm.controls['factorystate'].value !== this.editForm.controls['suppliercustomerstate'].value
      ) {
        if (directBookingDetail.igstperc) {
          directBookingDetail.taxablevalue = Number(
            (
              directBookingDetail.amount -
              (directBookingDetail.discount ? directBookingDetail.discount : 0) +
              (directBookingDetail.freight ? directBookingDetail.freight : 0) +
              (directBookingDetail.others ? directBookingDetail.others : 0)
            ).toFixed(2)
          );
          if (!directBookingDetail.migst) {
            directBookingDetail.igstvalue = Number(((directBookingDetail.taxablevalue * directBookingDetail.igstperc) / 100).toFixed(2));
          }

          directBookingDetail.gstperc = directBookingDetail.igstperc;
          directBookingDetail.gstvalue = directBookingDetail.igstvalue;

          directBookingDetail.totalvalue = Number((directBookingDetail.taxablevalue + directBookingDetail.gstvalue).toFixed(2));
          this.totalCalculateValue();
        } else {
          directBookingDetail.igstvalue = 0;
          directBookingDetail.gstperc = 0;
          directBookingDetail.gstvalue = 0;
          directBookingDetail.totalvalue =
            directBookingDetail.amount -
            (directBookingDetail.discount ? directBookingDetail.discount : 0) +
            (directBookingDetail.freight ? directBookingDetail.freight : 0) +
            (directBookingDetail.others ? directBookingDetail.others : 0);
          directBookingDetail.taxablevalue = Number(
            (
              directBookingDetail.amount -
              (directBookingDetail.discount ? directBookingDetail.discount : 0) +
              (directBookingDetail.freight ? directBookingDetail.freight : 0) +
              (directBookingDetail.others ? directBookingDetail.others : 0)
            ).toFixed(2)
          );
          this.totalCalculateValue();
        }
      } else if (
        this.editForm.controls['factorystate'].value &&
        this.editForm.controls['suppliercustomerstate'].value &&
        this.editForm.controls['factorystate'].value === this.editForm.controls['suppliercustomerstate'].value
      ) {
        directBookingDetail.taxablevalue = Number(
          (
            directBookingDetail.amount -
            (directBookingDetail.discount ? directBookingDetail.discount : 0) +
            (directBookingDetail.freight ? directBookingDetail.freight : 0) +
            (directBookingDetail.others ? directBookingDetail.others : 0)
          ).toFixed(2)
        );
        if (directBookingDetail.cgstperc) {
          if (!directBookingDetail.mcgst) {
            directBookingDetail.cgstvalue = Number(((directBookingDetail.taxablevalue * directBookingDetail.cgstperc) / 100).toFixed(2));
          }
        } else {
          directBookingDetail.cgstperc = 0;
          directBookingDetail.cgstvalue = 0;
        }
        if (directBookingDetail.sgstperc) {
          if (!directBookingDetail.msgst) {
            directBookingDetail.sgstvalue = Number(((directBookingDetail.taxablevalue * directBookingDetail.sgstperc) / 100).toFixed(2));
          }
        } else {
          directBookingDetail.sgstperc = 0;
          directBookingDetail.sgstvalue = 0;
        }
        directBookingDetail.gstperc = directBookingDetail.cgstperc + directBookingDetail.sgstperc;
        directBookingDetail.gstvalue = directBookingDetail.cgstvalue + directBookingDetail.sgstvalue;
        directBookingDetail.totalvalue = Number(
          (directBookingDetail.taxablevalue + directBookingDetail.cgstvalue + directBookingDetail.sgstvalue).toFixed(2)
        );
        this.totalCalculateValue();
      }
    } else {
      directBookingDetail.amount = 0;
      directBookingDetail.igstvalue = 0;
      directBookingDetail.gstperc = 0;
      directBookingDetail.gstvalue = 0;
      directBookingDetail.totalvalue =
        directBookingDetail.amount -
        (directBookingDetail.discount ? directBookingDetail.discount : 0) +
        (directBookingDetail.freight ? directBookingDetail.freight : 0) +
        (directBookingDetail.others ? directBookingDetail.others : 0);
      directBookingDetail.taxablevalue = Number(
        (
          directBookingDetail.amount -
          (directBookingDetail.discount ? directBookingDetail.discount : 0) +
          (directBookingDetail.freight ? directBookingDetail.freight : 0) +
          (directBookingDetail.others ? directBookingDetail.others : 0)
        ).toFixed(2)
      );
      this.totalCalculateValue();
    }
  }

  totalCalculateValue() {
    let value = 0;
    let cgst = 0;
    let sgst = 0;
    let igst = 0;
    let index = 0;
    this.directBookingDetails.forEach(directBookingDetail => {
      if (directBookingDetail.taxablevalue && directBookingDetail.taxablevalue > 0) {
        value += directBookingDetail.taxablevalue;
      }
      if (directBookingDetail.cgstvalue && directBookingDetail.cgstvalue > 0) {
        cgst += directBookingDetail.cgstvalue;
      }
      if (directBookingDetail.sgstvalue && directBookingDetail.sgstvalue > 0) {
        sgst += directBookingDetail.sgstvalue;
      }
      if (directBookingDetail.igstvalue && directBookingDetail.igstvalue > 0) {
        igst += directBookingDetail.igstvalue;
      }
      ++index;
    });
    if (index === this.directBookingDetails.length) {
      this.editForm.controls['value'].setValue(value);
      this.editForm.controls['ctaxvalue'].setValue(cgst);
      this.editForm.controls['staxvalue'].setValue(sgst);
      this.editForm.controls['itaxvalue'].setValue(igst);
      this.editForm.controls['taxvalue'].setValue(Number((cgst + sgst + igst).toFixed(2)));
      this.editForm.controls['totalvalue'].setValue(Number((value + cgst + sgst + igst).toFixed(2)));
      this.editForm.controls['netAmount'].setValue(Number((value + cgst + sgst + igst).toFixed(2)));
      this.editForm.controls['roundOffValue'].setValue(
        Number(
          (
            Number(this.editForm.controls['billamount'].value) -
            Number((value + cgst + sgst + igst).toFixed(2)) -
            Number(
              this.editForm.controls['tcsApplicable'].value && this.editForm.controls['tcsValue'].value
                ? this.editForm.controls['tcsValue'].value
                : 0
            )
          ).toFixed(2)
        )
      );
      if (this.orderpartnertdss && this.orderpartnertdss.length > 0) {
        let activeOrderpartnertds = undefined;
        let indexTds = 0;
        this.orderpartnertdss.forEach(orderpartnertds => {
          if (orderpartnertds.tdsApplicable && orderpartnertds.tdsApplicable === true) {
            activeOrderpartnertds = orderpartnertds;
          }
          ++indexTds;
        });
        if (indexTds === this.orderpartnertdss.length && activeOrderpartnertds !== undefined) {
          if (!this.tdsBool) {
            const tdsValue = Math.ceil(
              Number((((value + this.editForm.controls['roundOffValue'].value) * activeOrderpartnertds.value) / 100).toFixed(2))
            );
            this.editForm.controls['tdsValue'].setValue(tdsValue);
          }
        } else if (indexTds === this.orderpartnertdss.length) {
          this.editForm.controls['tdsValue'].setValue(0);
        }
      } else {
        this.editForm.controls['tdsValue'].setValue(0);
      }
    }
  }

  onLineCGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.cgstperc = selected.originalObject.value;
      directBookingDetail.cgsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.cgstglcode = selected.originalObject.glcode;
      directBookingDetail.cgstdesc = selected.originalObject.longdescription;

      this.calculateDiscountFrieghtAndOthers();
    } else {
      directBookingDetail.cgstperc = undefined;
      directBookingDetail.cgsttaxcode = undefined;
      directBookingDetail.cgstglcode = undefined;
      directBookingDetail.cgstdesc = undefined;
      directBookingDetail.cgstvalue = 0;

      this.calculateDiscountFrieghtAndOthers();
    }
  }

  onLineSGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.sgstperc = selected.originalObject.value;
      directBookingDetail.sgsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.sgstglcode = selected.originalObject.glcode;
      directBookingDetail.sgstdesc = selected.originalObject.longdescription;

      this.calculateDiscountFrieghtAndOthers();
    } else {
      directBookingDetail.sgstperc = undefined;
      directBookingDetail.sgsttaxcode = undefined;
      directBookingDetail.sgstglcode = undefined;
      directBookingDetail.sgstdesc = undefined;
      directBookingDetail.sgstvalue = 0;

      this.calculateDiscountFrieghtAndOthers();
    }
  }

  onLineIGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.igstperc = selected.originalObject.value;
      directBookingDetail.igsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.igstglcode = selected.originalObject.glcode;
      directBookingDetail.igstdesc = selected.originalObject.longdescription;

      this.calculateDiscountFrieghtAndOthers();
    } else {
      directBookingDetail.igstperc = undefined;
      directBookingDetail.igsttaxcode = undefined;
      directBookingDetail.igstglcode = undefined;
      directBookingDetail.igstdesc = undefined;
      directBookingDetail.igstvalue = 0;

      this.calculateDiscountFrieghtAndOthers();
    }
  }

  onLineCostcenterSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.costcentercode = selected.originalObject.id.code;
    }
  }

  addRow(): void {
    this.directBookingDetails.push(new DirectBookingDetails());
  }

  forwardBooking() {
    this.snotifyService.confirm('Are you sure to Forward for approval?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.forward(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  forward(toast) {
    this.directBookingEntryService.forward(this.editForm.controls['id'].value).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.previousState();
    });
  }

  calculateDiscountFrieghtAndOthers() {
    let totalvalue = 0;
    let index = 0;
    this.directBookingDetails.forEach(directBookingDetail => {
      if (directBookingDetail.price && directBookingDetail.quantity) {
        directBookingDetail.amount = Number((directBookingDetail.price * directBookingDetail.quantity).toFixed(2));
        totalvalue += directBookingDetail.amount;
      }
      ++index;
    });
    if (index === this.directBookingDetails.length) {
      if (
        this.editForm.controls['discountApplicable'].value &&
        this.editForm.controls['discountApplicable'].value === true &&
        this.editForm.controls['discountType'].value &&
        this.editForm.controls['discountType'].value === 'P' &&
        this.editForm.controls['discountValue'].value &&
        this.editForm.controls['discountValue'].value > 0
      ) {
        this.directBookingDetails.forEach(directBookingDetail => {
          if (directBookingDetail.amount > 0) {
            const value = Number(((directBookingDetail.amount * this.editForm.controls['discountValue'].value) / 100).toFixed(2));
            directBookingDetail.discount = value;
            this.calculateValue(directBookingDetail);
          }
        });
      } else if (
        this.editForm.controls['discountApplicable'].value &&
        this.editForm.controls['discountApplicable'].value === true &&
        this.editForm.controls['discountType'].value &&
        this.editForm.controls['discountType'].value === 'V' &&
        this.editForm.controls['discountValue'].value &&
        this.editForm.controls['discountValue'].value > 0
      ) {
        const discountConst = this.editForm.controls['discountValue'].value / totalvalue;
        let discountBalance = 0;
        this.directBookingDetails.forEach((directBookingDetail, loopIndex) => {
          if (directBookingDetail.amount > 0 && loopIndex === this.directBookingDetails.length - 1) {
            const value = Number((this.editForm.controls['discountValue'].value - discountBalance).toFixed(2));
            directBookingDetail.discount = value;
          } else {
            if (directBookingDetail.amount > 0) {
              const value = Number((directBookingDetail.amount * discountConst).toFixed(2));
              directBookingDetail.discount = value;
              discountBalance += value;
              this.calculateValue(directBookingDetail);
            }
          }
        });
      } else {
        this.directBookingDetails.forEach(directBookingDetail => {
          directBookingDetail.discount = 0;
          this.calculateValue(directBookingDetail);
        });
      }

      if (
        this.editForm.controls['freightApplicable'].value &&
        this.editForm.controls['freightApplicable'].value === true &&
        this.editForm.controls['freightType'].value &&
        this.editForm.controls['freightType'].value === 'P' &&
        this.editForm.controls['freightValue'].value &&
        this.editForm.controls['freightValue'].value > 0
      ) {
        this.directBookingDetails.forEach(directBookingDetail => {
          if (directBookingDetail.amount > 0) {
            const value = Number(((directBookingDetail.amount * this.editForm.controls['freightValue'].value) / 100).toFixed(2));
            directBookingDetail.freight = value;
            this.calculateValue(directBookingDetail);
          }
        });
      } else if (
        this.editForm.controls['freightApplicable'].value &&
        this.editForm.controls['freightApplicable'].value === true &&
        this.editForm.controls['freightType'].value &&
        this.editForm.controls['freightType'].value === 'V' &&
        this.editForm.controls['freightValue'].value &&
        this.editForm.controls['freightValue'].value > 0
      ) {
        const freightConst = this.editForm.controls['freightValue'].value / totalvalue;
        let freightBalance = 0;
        this.directBookingDetails.forEach((directBookingDetail, loopIndex) => {
          if (directBookingDetail.amount > 0 && loopIndex === this.directBookingDetails.length - 1) {
            const value = Number((this.editForm.controls['freightValue'].value - freightBalance).toFixed(2));
            directBookingDetail.freight = value;
            this.calculateValue(directBookingDetail);
          } else {
            if (directBookingDetail.amount > 0) {
              const value = Number((directBookingDetail.amount * freightConst).toFixed(2));
              directBookingDetail.freight = value;
              freightBalance += value;
              this.calculateValue(directBookingDetail);
            }
          }
        });
      } else {
        this.directBookingDetails.forEach(directBookingDetail => {
          directBookingDetail.freight = 0;
          this.calculateValue(directBookingDetail);
        });
      }

      if (
        this.editForm.controls['otherChargesApplicable'].value &&
        this.editForm.controls['otherChargesApplicable'].value === true &&
        this.editForm.controls['otherChargesType'].value &&
        this.editForm.controls['otherChargesType'].value === 'P' &&
        this.editForm.controls['otherChargesValue'].value &&
        this.editForm.controls['otherChargesValue'].value > 0
      ) {
        this.directBookingDetails.forEach(directBookingDetail => {
          if (directBookingDetail.amount > 0) {
            const value = Number(((directBookingDetail.amount * this.editForm.controls['otherChargesValue'].value) / 100).toFixed(2));
            directBookingDetail.others = value;
            this.calculateValue(directBookingDetail);
          }
        });
      } else if (
        this.editForm.controls['otherChargesApplicable'].value &&
        this.editForm.controls['otherChargesApplicable'].value === true &&
        this.editForm.controls['otherChargesType'].value &&
        this.editForm.controls['otherChargesType'].value === 'V' &&
        this.editForm.controls['otherChargesValue'].value &&
        this.editForm.controls['otherChargesValue'].value > 0
      ) {
        const otherChargesConst = this.editForm.controls['otherChargesValue'].value / totalvalue;
        let otherChargesBalance = 0;
        this.directBookingDetails.forEach((directBookingDetail, loopIndex) => {
          if (directBookingDetail.amount > 0 && loopIndex === this.directBookingDetails.length - 1) {
            const value = Number((this.editForm.controls['otherChargesValue'].value - otherChargesBalance).toFixed(2));
            directBookingDetail.others = value;
            this.calculateValue(directBookingDetail);
          } else {
            if (directBookingDetail.amount > 0) {
              const value = Number((directBookingDetail.amount * otherChargesConst).toFixed(2));
              directBookingDetail.others = value;
              otherChargesBalance += value;
              this.calculateValue(directBookingDetail);
            }
          }
        });
      } else {
        this.directBookingDetails.forEach(directBookingDetail => {
          directBookingDetail.others = 0;
          this.calculateValue(directBookingDetail);
        });
      }
      this.tdsBool = false;
    }
  }

  selectTDS(selectedIndex) {
    let counter = 0;
    this.orderpartnertdss.forEach((orderpartnertds, index) => {
      if (index !== selectedIndex) {
        orderpartnertds.tdsApplicable = false;
      }
      ++counter;
    });
    if (counter === this.orderpartnertdss.length) {
      this.calculateDiscountFrieghtAndOthers();
    }
  }

  delete(directBookingDetail?: IDirectBookingDetails, index?: number): void {
    if (directBookingDetail.id && directBookingDetail.id !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.deleteRow(toast, directBookingDetail, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      this.directBookingDetails.splice(index, 1);
      this.calculateDiscountFrieghtAndOthers();
    }
  }

  deleteRow(toast, directBookingDetail?: IDirectBookingDetails, index?: number): void {
    this.directBookingEntryService.deleteDetails(directBookingDetail.id).subscribe(response => {
      this.directBookingDetails.splice(index, 1);
      this.snotifyService.remove(toast.id);
      this.calculateDiscountFrieghtAndOthers();
      this.save();
    });
  }

  getTCSValue(): number {
    if (this.editForm.controls['tcsValue'].value) {
      return Number(this.editForm.controls['tcsValue'].value);
    } else {
      return 0;
    }
  }

  onProjectSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['projectcode'].setValue(selected.originalObject.id.code);
    } else {
      this.editForm.controls['projectcode'].setValue(undefined);
    }
  }

  onGateEntrySelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      if (selected.originalObject.gatepassno) {
        this.editForm.controls['gatenumber'].setValue(selected.originalObject.gatepassno);
      } else {
        this.editForm.controls['gatenumber'].setValue(' ');
      }
    } else {
      this.editForm.controls['gatenumber'].setValue(undefined);
    }
  }

  onGatePassSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['gateentrynumber'].setValue(selected.originalObject.id.maingateentrysrno);
    } else {
      this.editForm.controls['gateentrynumber'].setValue(undefined);
    }
  }

  validatePostingDate($event: any): void {
    console.log();
    if (isMoment($event.value) && $event.value._i.length === 6) {
      this.editForm.controls['bookingdate'].setValue(moment(this.editForm.controls['bookingdate'].value));
    }
  }

  validateBillDate($event: any): void {
    console.log();
    if (isMoment($event.value) && $event.value._i.length === 6) {
      this.editForm.controls['billdate'].setValue(moment(this.editForm.controls['billdate'].value));
    }
  }
}
