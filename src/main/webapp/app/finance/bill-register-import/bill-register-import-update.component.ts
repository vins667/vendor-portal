import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BillRegisterImportMaster, IBillRegisterImportMaster } from 'app/finance/bill-register-import/bill-register-import-master.model';
import { BillRegisterImportDetails, IBillRegisterImportDetails } from 'app/finance/bill-register-import/bill-register-import-details.model';
import { BillRegisterImportMasterService } from 'app/finance/bill-register-import/bill-register-import-master.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { ICurrency } from 'app/shared/db2/model/currency.model';
import { ICompany } from 'app/shared/db2/model/company.model';
import { IDivision } from 'app/shared/db2/model/division.model';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PlantInvoiceSelectionComponent } from './plant-invoice-selection.component';
import { JhiEventManager } from 'ng-jhipster';
import { CurrencyService } from 'app/shared/db2/service/currency.service';
import { IBuyerRegisterPurchaseLineBeanModel } from './buyer-register-purchase-line-bean.model';

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
  selector: 'jhi-bill-register-import-update',
  templateUrl: './bill-register-import-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class BillRegisterImportUpdateComponent implements OnInit {
  isSaving = false;
  currencies?: ICurrency[];
  companies?: ICompany[];
  divisions?: IDivision[];
  finbusinessunits?: IFinbusinessunit[];
  public dataRemotePlantInvoice: RemoteData;
  public dataRemoteSupplier: RemoteData;
  billRegisterDetails: IBillRegisterImportDetails[] = [];
  currentBillRegisterImportDetail: IBillRegisterImportDetails;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;

  editForm = this.fb.group({
    id: [],
    company: [null, [Validators.required, Validators.maxLength(3)]],
    division: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcompanycode: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcode: [null, [Validators.required, Validators.maxLength(10)]],
    billtype: [null, [Validators.required, Validators.maxLength(20)]],
    billnumber: [null, [Validators.required, Validators.maxLength(50)]],
    billdate: [null, [Validators.required]],
    customersuppliertype: [null, [Validators.required, Validators.maxLength(1)]],
    customersuppliercode: [null, [Validators.required, Validators.maxLength(20)]],
    customersuppliername: [null, [Validators.maxLength(100)]],
    currencycode: [null, [Validators.required, Validators.maxLength(3)]],
    currencyrate: [null, [Validators.required]],
    totalQuantity: [null, [Validators.required]],
    totalValue: [null, [Validators.required]],
    remarks: [null, [Validators.maxLength(2000)]],
    submitDate: [null],
    receiveDate: [],
    createdby: [],
    createddate: [],
    updatedby: [],
    updateddate: [],
    billRegisterDetailsBeans: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected billRegisterMasterService: BillRegisterImportMasterService,
    protected companyService: CompanyService,
    protected currencyService: CurrencyService,
    protected divisionService: DivisionService,
    protected finbusinessunitService: FinbusinessunitService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected snotifyService: SnotifyService,
    private cdRef: ChangeDetectorRef,
    public completerService: CompleterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.dataRemoteSupplier = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlSupplier + 2 + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');
    this.dataRemotePlantInvoice = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlPlantinvoices,
      'challanno',
      'challanno'
    );
    this.dataRemotePlantInvoice.dataField('');
  }

  ngOnInit(): void {
    this.registerChangeInOrderFilter();
    this.currencyService.query().subscribe(currencies => {
      this.currencies = currencies.body;
    });
    this.activatedRoute.data.subscribe(({ billRegisterMaster }) => {
      if (billRegisterMaster.id === undefined) {
        billRegisterMaster.billtype = 'SUPPLIER';
        billRegisterMaster.currencycode = 'USD';
        billRegisterMaster.customersuppliertype = '2';
        billRegisterMaster.queryFlag = false;
        const billRegisterDetail = new BillRegisterImportDetails();
        billRegisterDetail.shipmentMode = 'SEA';
        this.billRegisterDetails.push(billRegisterDetail);
      } else {
        this.billRegisterDetails = billRegisterMaster.billRegisterDetailsBeans;
      }

      this.updateForm(billRegisterMaster);
      this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
        this.companies = companies.body;
        if (billRegisterMaster.id === undefined) {
          this.editForm.controls['company'].setValue(this.companies[0].code);
          this.editForm.controls['businessunitcompanycode'].setValue(this.companies[0].code);
        }
        this.fetchDivision();
        this.fetchBusinessUnit();
      });
    });
  }

  changeCurrency(): void {
    if (this.editForm.controls['billtype'].value === 'SUPPLIER') {
      this.editForm.controls['currencycode'].setValue('USD');
      this.editForm.controls['currencyrate'].setValue(undefined);
      this.billRegisterDetails.forEach(value => (value.shipmentMode = 'SEA'));
    } else {
      this.editForm.controls['currencycode'].setValue('INR');
      this.editForm.controls['currencyrate'].setValue(1);
      if (this.editForm.controls['billtype'].value === 'OTHER') {
        this.billRegisterDetails.forEach(value => (value.shipmentMode = 'OTHEXP'));
      } else {
        this.billRegisterDetails.forEach(value => (value.shipmentMode = 'SEA'));
      }
    }
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

  addDetails(): void {
    const billRegisterDetail = new BillRegisterImportDetails();
    if (this.editForm.controls['billtype'].value === 'OTHER') {
      billRegisterDetail.shipmentMode = 'OTHEXP';
    } else {
      billRegisterDetail.shipmentMode = 'SEA';
    }
    this.billRegisterDetails.push(billRegisterDetail);
  }

  removeDetals(index, billRegisterDetail: IBillRegisterImportDetails): any {
    if (billRegisterDetail.id && billRegisterDetail.id !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.delete(toast, billRegisterDetail, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      this.billRegisterDetails.splice(index, 1);
    }
  }

  delete(toast, billRegisterDetail: IBillRegisterImportDetails, index?: number): void {
    this.billRegisterMasterService.deleteDetails(billRegisterDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.billRegisterDetails.splice(index, 1);
      this.cdRef.detectChanges();
    });
  }

  updateForm(billRegisterMaster: IBillRegisterImportMaster) {
    this.editForm.patchValue({
      id: billRegisterMaster.id,
      company: billRegisterMaster.company,
      division: billRegisterMaster.division,
      businessunitcompanycode: billRegisterMaster.businessunitcompanycode,
      businessunitcode: billRegisterMaster.businessunitcode,
      billtype: billRegisterMaster.billtype,
      billnumber: billRegisterMaster.billnumber,
      billdate: billRegisterMaster.billdate,
      customersuppliertype: billRegisterMaster.customersuppliertype,
      customersuppliercode: billRegisterMaster.customersuppliercode,
      customersuppliername: billRegisterMaster.customersuppliername,
      currencycode: billRegisterMaster.currencycode,
      currencyrate: billRegisterMaster.currencyrate,
      remarks: billRegisterMaster.remarks,
      totalQuantity: billRegisterMaster.totalQuantity,
      totalValue: billRegisterMaster.totalValue,
      submitDate: billRegisterMaster.submitDate,
      receiveDate: billRegisterMaster.receiveDate,
      createdby: billRegisterMaster.createdby,
      createddate: billRegisterMaster.createddate != null ? billRegisterMaster.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: billRegisterMaster.updatedby,
      updateddate: billRegisterMaster.updateddate != null ? billRegisterMaster.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = false;
    const billRegisterMaster = this.createFromForm();
    if (billRegisterMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.billRegisterMasterService.update(billRegisterMaster.id, billRegisterMaster));
    } else {
      this.subscribeToSaveResponse(this.billRegisterMasterService.create(billRegisterMaster));
    }
  }

  changeValue(): void {
    let index = 0;
    let totalValue = 0;
    let totalQuantity = 0;
    this.billRegisterDetails.forEach(billRegisterDetail => {
      if (billRegisterDetail.grossvalue) {
        totalValue = Number(totalValue) + Number(billRegisterDetail.grossvalue);
        totalQuantity = Number(totalQuantity) + Number(billRegisterDetail.quantity);
      }
      ++index;
    });
    if (index === this.billRegisterDetails.length) {
      this.editForm.controls['totalValue'].setValue(Number(totalValue.toFixed(2)));
      this.editForm.controls['totalQuantity'].setValue(Number(totalQuantity.toFixed(2)));
    }
  }

  private createFromForm(): IBillRegisterImportMaster {
    return {
      ...new BillRegisterImportMaster(),
      id: this.editForm.get(['id']).value,
      company: this.editForm.get(['company'])!.value,
      division: this.editForm.get(['division'])!.value,
      businessunitcompanycode: this.editForm.get(['businessunitcompanycode'])!.value,
      businessunitcode: this.editForm.get(['businessunitcode'])!.value,
      billtype: this.editForm.get(['billtype']).value,
      billnumber: this.editForm.get(['billnumber']).value,
      billdate: this.editForm.get(['billdate']).value,
      customersuppliertype: this.editForm.get(['customersuppliertype']).value,
      customersuppliercode: this.editForm.get(['customersuppliercode']).value,
      customersuppliername: this.editForm.get(['customersuppliername']).value,
      currencycode: this.editForm.get(['currencycode'])!.value,
      currencyrate: this.editForm.get(['currencyrate'])!.value,
      remarks: this.editForm.get(['remarks']).value,
      totalQuantity: this.editForm.get(['totalQuantity']).value,
      totalValue: this.editForm.get(['totalValue']).value,
      submitDate: this.editForm.get(['submitDate']).value,
      receiveDate: this.editForm.get(['receiveDate']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined,
      billRegisterDetailsBeans: this.billRegisterDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillRegisterImportMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  onSupplierSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['customersuppliertype'].setValue(selected.originalObject.customersuppliertype);
      this.editForm.controls['customersuppliercode'].setValue(selected.originalObject.customersuppliercode);
      this.editForm.controls['customersuppliername'].setValue(selected.originalObject.addressee);
    }
  }

  resetURL(type: number): void {
    this.dataRemoteSupplier = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlSupplier + type + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');
  }

  /* calculateValue(): void {
    let ctr = 0;
    this.billRegisterDetails.forEach(billRegisterDetail => {
      if (billRegisterDetail.quantity && billRegisterDetail.price) {
        const value = (billRegisterDetail.quantity * billRegisterDetail.price).toFixed(2);
        billRegisterDetail.grossvalue = Number(value);
      }
      ++ctr;
    });
    if (ctr === this.billRegisterDetails.length) {
      this.changeValue();
    }
  } */

  calculateValue(): void {
    if (this.editForm.controls['totalValue'].value) {
      let totalQuantity = 0;
      let index = 0;
      this.billRegisterDetails.forEach(billRegisterDetail => {
        if (billRegisterDetail.quantity) {
          totalQuantity = Number(totalQuantity) + Number(billRegisterDetail.quantity);
        }
        ++index;
      });
      if (index === this.billRegisterDetails.length) {
        this.editForm.controls['totalQuantity'].setValue(Number(totalQuantity.toFixed(2)));
        const rate = (Number(this.editForm.controls['totalValue'].value) / Number(totalQuantity)).toFixed(3);
        let totalIndex = 0;
        this.billRegisterDetails.forEach(billRegisterDetail => {
          if (billRegisterDetail.quantity) {
            billRegisterDetail.price = Number(rate);
            const value = Number((Number(billRegisterDetail.quantity) * Number(rate)).toFixed(3));
            billRegisterDetail.grossvalue = value;
          }
          ++totalIndex;
        });
        if (totalIndex === this.billRegisterDetails.length) {
          let totalValue = 0;
          let indexLevel1 = 0;
          let lastIndex = 0;
          this.billRegisterDetails.forEach(billRegisterDetail => {
            if (billRegisterDetail.grossvalue) {
              totalValue = Number(totalValue) + Number(billRegisterDetail.grossvalue);
              lastIndex = indexLevel1;
            }
            ++indexLevel1;
          });
          if (indexLevel1 === this.billRegisterDetails.length) {
            const adjustmentValue = Number(this.editForm.controls['totalValue'].value) - Number(totalValue);
            this.billRegisterDetails[lastIndex].grossvalue = Number(
              (Number(this.billRegisterDetails[lastIndex].grossvalue) + adjustmentValue).toFixed(3)
            );
          }
        }
      }
    }
  }

  searchInvoices(billRegisterDetail: IBillRegisterImportDetails) {
    this.currentBillRegisterImportDetail = billRegisterDetail;
    this.ngbModalRef = this.modalService.open(PlantInvoiceSelectionComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
    this.ngbModalRef.componentInstance.supplierCode = this.editForm.controls['customersuppliercode'].value;
  }

  selectAllSubmitDate(): void {
    if (this.editForm.controls['submitDate'].value) {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.submitdate = this.editForm.controls['submitDate'].value;
      });
    } else {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.submitdate = undefined;
      });
    }
  }

  selectAllReceiveDate(): void {
    if (this.editForm.controls['receiveDate'].value) {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.receiveDate = this.editForm.controls['receiveDate'].value;
      });
    } else {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.receiveDate = undefined;
      });
    }
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('billRegisterPurchaseorderFilter', data => {
      const buyerRegisterPurchaseLineBean: IBuyerRegisterPurchaseLineBeanModel = data.content;
      this.currentBillRegisterImportDetail.companycode = buyerRegisterPurchaseLineBean.companycode;
      this.currentBillRegisterImportDetail.countercode = buyerRegisterPurchaseLineBean.countercode;
      this.currentBillRegisterImportDetail.code = buyerRegisterPurchaseLineBean.code;
      this.currentBillRegisterImportDetail.orderdate = buyerRegisterPurchaseLineBean.orderdate;
      this.currentBillRegisterImportDetail.projectcode = buyerRegisterPurchaseLineBean.projectcode;
      this.currentBillRegisterImportDetail.summarizeddescription = buyerRegisterPurchaseLineBean.summarizeddescription;
      this.currentBillRegisterImportDetail.userprimaryuomcode = buyerRegisterPurchaseLineBean.userprimaryuomcode;
      this.currentBillRegisterImportDetail.quantity = buyerRegisterPurchaseLineBean.userprimaryquantity;
      this.currentBillRegisterImportDetail.price = buyerRegisterPurchaseLineBean.price;
      this.currentBillRegisterImportDetail.grossvalue = buyerRegisterPurchaseLineBean.value;
      this.changeValue();

      const billRegisterDetailTemp = new BillRegisterImportDetails();
      if (this.editForm.controls['billtype'].value === 'OTHER') {
        billRegisterDetailTemp.shipmentMode = 'OTHEXP';
      } else {
        billRegisterDetailTemp.shipmentMode = 'SEA';
      }
      this.billRegisterDetails.push(billRegisterDetailTemp);
    });
  }
}
