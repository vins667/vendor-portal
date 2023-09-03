import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDirectBookingEntry, DirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';
import { DirectBookingApprovalEntryService } from './direct-booking-approval-entry.service';
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
import { toastConfig } from 'app/core/toast/toast-config';
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
  selector: 'jhi-direct-booking-approval-entry-update',
  templateUrl: './direct-booking-approval-entry-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class DirectBookingApprovalEntryUpdateComponent implements OnInit {
  isSaving = false;
  companies?: ICompany[];
  divisions?: IDivision[];
  factories?: IFactory[];
  finbusinessunits?: IFinbusinessunit[];
  directBookingDetails?: IDirectBookingDetails[];
  orderpartnertdss?: IOrderpartnertds[];
  public dataRemoteSupplier: RemoteData;
  public dataRemoteCostcenter: RemoteData;
  public dataRemotePaymentmethod: RemoteData;
  public dataRemoteItems: RemoteData;
  public dataRemoteCGst: RemoteData;
  public dataRemoteSGst: RemoteData;
  public dataRemoteIGst: RemoteData;
  isDisabled = true;
  isProcess = false;

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
    billno: [null, [Validators.required, Validators.maxLength(25)]],
    billdate: [null, [Validators.required]],
    billamount: [null, [Validators.required]],
    paymenttermcode: [null, [Validators.required, Validators.maxLength(3)]],
    paymenttermdesc: [null, [Validators.maxLength(100)]],
    costcentercode: [null, [Validators.required, Validators.maxLength(20)]],
    costcenterdesc: [null, [Validators.maxLength(100)]],
    remarks: [null, [Validators.maxLength(500)]],
    vehicleNo: [null, [Validators.maxLength(20)]],
    projectcode: [null, [Validators.maxLength(100)]],
    gatenumber: [null, [Validators.maxLength(20)]],
    gateentrynumber: [null, [Validators.maxLength(100)]],
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
    flag: [],
    tcsValue: [],
    createddate: [],
    createdby: [],
    styleNo: [],
    customerCode: [],
    customerName: [],
    customerGstName: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected directBookingEntryService: DirectBookingApprovalEntryService,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected factoryService: FactoryService,
    protected finbusinessunitService: FinbusinessunitService,
    protected vieworderpartnerService: VieworderpartnerService,
    protected orderpartnertdsService: OrderpartnertdsService,
    protected fullitemkeydecoderService: FullitemkeydecoderService,
    protected viewditaxglmappingService: ViewDitaxglmappingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    public completerService: CompleterService,
    public snotifyService: SnotifyService
  ) {
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
        if (!directBookingEntry.id) {
          this.editForm.controls['company'].setValue(this.companies[0].code);
          this.editForm.controls['businessunitcompanycode'].setValue(this.companies[0].code);
          this.editForm.controls['bookingdate'].setValue(moment(new Date()));
          this.editForm.controls['bookingfor'].setValue('2');
          this.editForm.controls['flag'].setValue('E');

          this.dataRemoteSupplier = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
            'legalname1',
            'legalname1'
          );
          this.dataRemoteSupplier.dataField('');

          this.fetchDivision();
          this.fetchBusinessUnit();
        } else {
          this.orderpartnertdss = directBookingEntry.orderpartnertdss;
          this.directBookingDetails = directBookingEntry.directBookingDetails;
          this.dataRemoteSupplier = this.completerService.remote(
            this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
            'legalname1',
            'legalname1'
          );
          this.dataRemoteSupplier.dataField('');
          this.fetchFactories();
          this.fetchDivision();
          this.fetchBusinessUnit();
        }
      });
    });
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
      flag: directBookingEntry.flag,
      createddate: directBookingEntry.createddate ? directBookingEntry.createddate.format(DATE_TIME_FORMAT) : null,
      createdby: directBookingEntry.createdby,
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
    this.isSaving = true;
    const directBookingEntry = this.createFromForm();
    if (directBookingEntry.id !== undefined) {
      this.subscribeToSaveResponse(this.directBookingEntryService.update(directBookingEntry));
    } else {
      this.subscribeToSaveResponse(this.directBookingEntryService.create(directBookingEntry));
    }
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
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  selectFactory(): void {
    if (this.editForm.controls['factorycode'].value && this.editForm.controls['factorycode'].value.length > 0) {
      this.factories.forEach(factory => {
        if (this.editForm.controls['factorycode'].value === factory.factCode) {
          this.editForm.controls['factorystate'].setValue(factory.statecode);
        }
      });
    } else {
      this.editForm.controls['factorystate'].setValue(undefined);
    }
  }

  remoteURL(): void {
    this.dataRemoteSupplier = this.completerService.remote(
      this.directBookingEntryService.resourceUrlSupplier + this.editForm.controls['bookingfor'].value + '/',
      'legalname1',
      'legalname1'
    );
    this.dataRemoteSupplier.dataField('');
  }

  onSupplierSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['suppliercustomertype'].setValue(selected.originalObject.customersuppliertype);
      this.editForm.controls['suppliercustomercode'].setValue(selected.originalObject.customersuppliercode);
      this.editForm.controls['paymenttermcode'].setValue(selected.originalObject.paymentmethodcode);
      // this.pa
      this.vieworderpartnerService.state(selected.originalObject.orderbusinesspartnernumberid).subscribe(states => {
        this.editForm.controls['suppliercustomerstate'].setValue(states.body.desc);
      });
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
      this.orderpartnertdss = [];
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
      directBookingDetail.quantity = 1;
      directBookingDetail.price = 1;
      directBookingDetail.amount = 1;
      this.fullitemkeydecoderService.find(selected.originalObject).subscribe(fullitemkeydecoder => {
        directBookingDetail.uom = fullitemkeydecoder.body.uom;
        directBookingDetail.hsncode = fullitemkeydecoder.body.tariffcode;

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
    }
  }

  calculateValue(directBookingDetail?: IDirectBookingDetails) {
    if (directBookingDetail.price && directBookingDetail.quantity) {
      directBookingDetail.amount = directBookingDetail.price * directBookingDetail.quantity;
      if (
        this.editForm.controls['factorystate'].value &&
        this.editForm.controls['suppliercustomerstate'].value &&
        this.editForm.controls['factorystate'].value !== this.editForm.controls['suppliercustomerstate'].value
      ) {
        if (directBookingDetail.igstperc) {
          directBookingDetail.igstvalue = Number(((directBookingDetail.amount * directBookingDetail.igstperc) / 100).toFixed(2));

          directBookingDetail.gstperc = directBookingDetail.igstperc;
          directBookingDetail.gstvalue = directBookingDetail.igstvalue;

          directBookingDetail.totalvalue = directBookingDetail.amount + directBookingDetail.gstvalue;
        } else {
          directBookingDetail.igstvalue = 0;
          directBookingDetail.gstperc = 0;
          directBookingDetail.gstvalue = 0;
          directBookingDetail.totalvalue = directBookingDetail.amount;
        }
      } else if (
        this.editForm.controls['factorystate'].value &&
        this.editForm.controls['suppliercustomerstate'].value &&
        this.editForm.controls['factorystate'].value === this.editForm.controls['suppliercustomerstate'].value
      ) {
        if (directBookingDetail.cgstperc) {
          directBookingDetail.cgstvalue = Number(((directBookingDetail.amount * directBookingDetail.cgstperc) / 100).toFixed(2));
        } else {
          directBookingDetail.cgstperc = 0;
          directBookingDetail.cgstvalue = 0;
        }
        if (directBookingDetail.sgstperc) {
          directBookingDetail.sgstvalue = Number(((directBookingDetail.amount * directBookingDetail.sgstperc) / 100).toFixed(2));
        } else {
          directBookingDetail.sgstperc = 0;
          directBookingDetail.sgstvalue = 0;
        }
        directBookingDetail.gstperc = directBookingDetail.cgstperc + directBookingDetail.sgstperc;
        directBookingDetail.gstvalue = directBookingDetail.cgstvalue + directBookingDetail.sgstvalue;
        directBookingDetail.totalvalue = directBookingDetail.amount + directBookingDetail.cgstvalue + directBookingDetail.sgstvalue;
      }
    } else {
      directBookingDetail.amount = 0;
      directBookingDetail.igstvalue = 0;
      directBookingDetail.gstperc = 0;
      directBookingDetail.gstvalue = 0;
      directBookingDetail.totalvalue = 0;
    }
  }

  onLineCGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.cgstperc = selected.originalObject.value;
      directBookingDetail.cgsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.cgstglcode = selected.originalObject.glcode;
      directBookingDetail.cgstdesc = selected.originalObject.longdescription;

      this.calculateValue(directBookingDetail);
    } else {
      directBookingDetail.cgstperc = undefined;
      directBookingDetail.cgsttaxcode = undefined;
      directBookingDetail.cgstglcode = undefined;
      directBookingDetail.cgstdesc = undefined;

      this.calculateValue(directBookingDetail);
    }
  }

  onLineSGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.sgstperc = selected.originalObject.value;
      directBookingDetail.sgsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.sgstglcode = selected.originalObject.glcode;
      directBookingDetail.sgstdesc = selected.originalObject.longdescription;

      this.calculateValue(directBookingDetail);
    } else {
      directBookingDetail.sgstperc = undefined;
      directBookingDetail.sgsttaxcode = undefined;
      directBookingDetail.sgstglcode = undefined;
      directBookingDetail.sgstdesc = undefined;

      this.calculateValue(directBookingDetail);
    }
  }

  onLineIGSTSelected(selected?: CompleterItem, directBookingDetail?: IDirectBookingDetails): void {
    if (selected && !!this.remoteData) {
      directBookingDetail.igstperc = selected.originalObject.value;
      directBookingDetail.igsttaxcode = selected.originalObject.taxcode;
      directBookingDetail.igstglcode = selected.originalObject.glcode;
      directBookingDetail.igstdesc = selected.originalObject.longdescription;

      this.calculateValue(directBookingDetail);
    } else {
      directBookingDetail.igstperc = undefined;
      directBookingDetail.igsttaxcode = undefined;
      directBookingDetail.igstglcode = undefined;
      directBookingDetail.igstdesc = undefined;

      this.calculateValue(directBookingDetail);
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

  returnBooking() {
    this.snotifyService.confirm('Are you sure to Return for changes?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.return(toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  return(toast) {
    this.directBookingEntryService.return(this.editForm.controls['id'].value).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.previousState();
    });
  }

  postBooking() {
    this.snotifyService.confirm('Are you sure to Post Document?', 'Confirm', {
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

  post(toast) {
    this.snotifyService.remove(toast.id);
    this.isProcess = true;
    this.directBookingEntryService.post(this.editForm.controls['id'].value).subscribe(
      any => {
        this.isProcess = false;
        this.previousState();
      },
      (res: HttpErrorResponse) => this.onPostError(res.headers)
    );
  }

  protected onPostError(res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
  }

  getTCSValue(): number {
    if (this.editForm.controls['tcsValue'].value) {
      return Number(this.editForm.controls['tcsValue'].value);
    } else {
      return 0;
    }
  }
}
